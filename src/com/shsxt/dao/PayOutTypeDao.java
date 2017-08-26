package com.shsxt.dao;

import java.util.List;

import com.shsxt.vo.PayOutType;

public class PayOutTypeDao extends BaseDao<PayOutType>{
	public List<PayOutType> queryPayOutTypeByPid(int pid){
		String sql="SELECT id,name from t_pay_out_type  where pid=?";
		return queryRows(sql, PayOutType.class, pid);
	}
}
