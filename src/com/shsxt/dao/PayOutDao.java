package com.shsxt.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shsxt.dto.PayInDto;
import com.shsxt.dto.PayOutDto;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.PayOut;

public class PayOutDao extends BaseDao<PayOut>{
	/**
	 * 新增支出：
	 * 1、在支出表中新增一条记录
	 * 2、在帐户表中减去相应的支出 
	 * @param payOut
	 * @return
	 */
	public int savePayOut(PayOut payOut){
		List<SqlParam> list=new ArrayList<SqlParam>();
		String sql1="insert into t_pay_out(name,type,money,remark,create_time,update_time,aid)"
				+" values(?,?,?,?,?,?,?)";
		Object []params1={payOut.getName(),payOut.getType(),payOut.getMoney(),payOut.getRemark(),
				new Date(),new Date(),payOut.getAid()};
		String sql2="update t_account set money=money-?,update_time=? where id=?";
		Object []params2={payOut.getMoney(),new Date(),payOut.getAid()};
		list.add(new SqlParam(sql1,params1));
		list.add(new SqlParam(sql2,params2));
		return executeUpdateBatch(list);
	}
	public PageInfo<PayOutDto> queryPayOutByParams(int uid,String type,String name,String time,int pageNum){
		List params=new ArrayList();
		StringBuffer stringBuffer=new StringBuffer();
		String sqlCount="select count(1)"
					+ " from t_pay_out p LEFT JOIN t_account a ON p.aid=a.id "
				 	+"LEFT JOIN (SELECT CONCAT(pot1.id,',',pot2.id) as type,CONCAT(pot1.name,'-',pot2.name) as typeName"
				 	+" from t_pay_out_type pot1 LEFT JOIN t_pay_out_type pot2 ON pot1.id=pot2.pid ) temp ON p.type=temp.type "
				 	+"WHERE a.user_id=?";
		String sqlQuery="SELECT p.id,p.name,p.type,p.money,p.remark,p.create_time as createTime,"
				 	+ " p.update_time as updateTime,a.aname,temp.typeName "
				 	+ " from t_pay_out p LEFT JOIN t_account a ON p.aid=a.id "
				 	+"LEFT JOIN (SELECT CONCAT(pot1.id,',',pot2.id) as type,CONCAT(pot1.name,'-',pot2.name) as typeName"
				 	+" from t_pay_out_type pot1 LEFT JOIN t_pay_out_type pot2 ON pot1.id=pot2.pid ) temp ON p.type=temp.type "
				 	+"WHERE a.user_id=?";
		params.add(uid);
		if(!StringUtil.isNullOrEmpty(name)){
			stringBuffer.append(" and p.name like concat('%',?,'%')");
			params.add(name);
		}
		if(!StringUtil.isNullOrEmpty(time)){
			stringBuffer.append(" and p.create_time <= ?");
			params.add(time);
		}
		if(!StringUtil.isNullOrEmpty(type) && !type.equals("-1")){
			stringBuffer.append(" and SUBSTRING_INDEX(p.type,',',1) = ?");
			params.add(type);
		}
		PageInfo<PayOutDto>pageInfo=new PageInfo<PayOutDto>(sqlCount+stringBuffer.toString(),
				sqlQuery+stringBuffer.toString(), params.toArray(), pageNum, PayOutDto.class);
		return pageInfo;
	}
	/**
	 * 进行数据回填
	 * @param id
	 * @return
	 */
	public PayOut queryPayOutById(int id){
		String sql="select id,name,type,remark,money,aid from t_pay_out where id=?";
		return querySingleRow(sql, PayOut.class, id);
	}
	/**
	 * 
	 * @param payOutDto
	 * @return
	 */
	public int updatePayOut(PayOutDto payOutDto){
		List<SqlParam> list=new ArrayList<SqlParam>();
		//1、修改支出表
		String sql1="update t_pay_out set name=?,money=?,type=?,remark=?,update_time=?,aid=? "
				+" where id=?";
		Object[] params1={payOutDto.getName(),payOutDto.getMoney(),payOutDto.getType(),
				payOutDto.getRemark(),new Date(),payOutDto.getAid(),payOutDto.getId()};
		//2、回退帐户记录
		String sql2="update t_account set money=money+?,update_time=? where id=?";
		Object[] params2={payOutDto.getOldMoney(),new Date(),payOutDto.getOldAid()};
		//3、修改帐户记录
		String sql3="update t_account set money=money-?,update_time=? where id=?";
		Object[] params3={payOutDto.getMoney(),new Date(),payOutDto.getAid()};
		list.add(new SqlParam(sql1,params1));
		list.add(new SqlParam(sql2,params2));
		list.add(new SqlParam(sql3,params3));
		return executeUpdateBatch(list);
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public int deletePayOut(String ids){
		List<SqlParam>list=new ArrayList<SqlParam>();
		//1、查询SQL  用于遍历要删除的每个支出中的帐户与金额
		String sql1="select aid,money from t_pay_out where id=?";
		//2、回退所有帐户
		String sql2="update t_account set money=money+?,update_time=? where id=?";
		//所有要删除的id
		String [] tempIds=ids.split(",");
		for(int i=0;i<tempIds.length;i++){
			PayOut payOut=querySingleRow(sql1, PayOut.class, tempIds[i]);
			Object []params={payOut.getMoney(),new Date(),payOut.getAid()};
			list.add(new SqlParam(sql2,params));
		}
		String sql3="delete from t_pay_out where id in("+ids+")";
		list.add(new SqlParam(sql3,null));
		return executeUpdateBatch(list);
	}
}
