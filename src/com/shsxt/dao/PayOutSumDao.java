package com.shsxt.dao;

import java.util.ArrayList;
import java.util.List;

import com.shsxt.dto.PayOutSumDto;
import com.shsxt.utils.StringUtil;

public class PayOutSumDao extends BaseDao<PayOutSumDto>{
	public List<PayOutSumDto> queryPayOutSum(int uid,String startTime,String endTime){
		StringBuffer stringBuffer=new StringBuffer();
		List params=new ArrayList();
		String sql="SELECT sum(po.money) as total,pot.name from t_account a LEFT JOIN t_pay_out po ON a.id=po.aid "
					+" LEFT JOIN t_pay_out_type pot ON SUBSTRING_INDEX(po.type,',',1)=pot.id "
					+" where a.user_id=?";
					//+" GROUP BY pot.id ";
		params.add(uid);
		if(!StringUtil.isNullOrEmpty(startTime)){
			stringBuffer.append(" and po.create_time>=?");
			params.add(startTime);
		}
		if(!StringUtil.isNullOrEmpty(endTime)){
			stringBuffer.append(" and po.create_time<=?");
			params.add(endTime);
		}
		stringBuffer.append(" GROUP BY pot.id");
		return queryRows(sql+stringBuffer.toString(), PayOutSumDto.class, params.toArray());
	}
}
