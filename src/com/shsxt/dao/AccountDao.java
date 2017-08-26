package com.shsxt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shsxt.constant.WacConstant;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.Account;

public class AccountDao extends BaseDao<Account> {
	/**
	 * 在添加帐户之前：需要判断该用户下是否存在该帐户
	 * @param uid
	 * @param aname
	 * @return
	 */
	public Account queryAccountByAnameUid(int uid,String aname){
		String sql="select id,aname,type,money,create_time as createTime,update_time as updateTime,"
				+"user_id as userId,remark from t_account where user_id=? and aname=?";
		return querySingleRow(sql, Account.class, uid,aname);
	}
	/**
	 * 添加帐户
	 * @param account
	 * @return
	 */
	public int saveAccount(Account account){
		String sql="insert into t_account(aname,type,money,create_time,update_time,user_id,remark)"
				+" values(?,?,?,?,?,?,?)";
		return executeUpdate(sql, account.getAname(),account.getType(),account.getMoney(),new Date(),new Date(),account.getUserId(),account.getRemark());
	}
	/**
	 * 多条件查询
	 * @param uid
	 * @param aname
	 * @param type
	 * @param time
	 * @param pageNum	当前页数
	 * @return
	 */
	public List<Account> queryAccountByParams(int uid,String aname,String type,String time,int pageNum){
		/*String sql="select id,aname,type,money,remark,create_time as createTime,update_time as updateTime"
				+" from t_account where user_id=?";*/
		List params=new ArrayList();
		StringBuffer stringBuffer=new StringBuffer("select id,aname,type,money,remark,create_time as createTime,update_time as updateTime"
				+" from t_account where user_id=?");
		params.add(uid);
		if(!StringUtil.isNullOrEmpty(aname)){
			stringBuffer.append(" and aname like CONCAT('%',?,'%')");
			params.add(aname);
		}
		//type不为空时进入，并且type=-1时也不算作条件
		if(!StringUtil.isNullOrEmpty(type) && !type.equals("-1")){
			stringBuffer.append(" and type=? ");
			params.add(type);
		}
		if(!StringUtil.isNullOrEmpty(time)){
			stringBuffer.append(" and create_time <= ?");
			params.add(time);
		}
		
		int pageIndex=(pageNum-1)*WacConstant.PAGE_SIZE;
		stringBuffer.append(" limit ?,?");
		params.add(pageIndex);
		params.add(WacConstant.PAGE_SIZE);
		return queryRows(stringBuffer.toString(), Account.class, params.toArray());
	}
	/**
	 * 
	 * @param uid
	 * @param aname
	 * @param type
	 * @param time
	 * @param pageNum
	 * @return
	 */
	public Long queryAccountTotalByParams(int uid,String aname,String type,String time,int pageNum){
		List params=new ArrayList();
		StringBuffer stringBuffer=new StringBuffer("select count(1) from t_account where user_id=? ");
		params.add(uid);
		if(!StringUtil.isNullOrEmpty(aname)){
			stringBuffer.append(" and aname like CONCAT('%',?,'%')");
			params.add(aname);
		}
		//type不为空时进入，并且type=-1时也不算作条件
		if(!StringUtil.isNullOrEmpty(type) && !type.equals("-1")){
			stringBuffer.append(" and type=? ");
			params.add(type);
		}
		if(!StringUtil.isNullOrEmpty(time)){
			stringBuffer.append(" and create_time <= ?");
			params.add(time);
		}
		return (Long) querySingleValue(stringBuffer.toString(), params.toArray());
	}
	/**
	 * 通过PageInfo进行分页查询
	 * @param uid
	 * @param aname
	 * @param type
	 * @param time
	 * @param pageNum
	 * @return
	 */
	public PageInfo<Account> queryPageInfoByParams(int uid,String aname,String type,String time,int pageNum){
		StringBuffer stringBuffer=new StringBuffer();
		List params=new ArrayList();
		String sqlCount="select count(1) from t_account where user_id=?";
		String sqlQuery="select id,aname,type,money,remark,create_time as createTime,update_time as updateTime"
						+" from t_account where user_id=?";
		params.add(uid);
		if(!StringUtil.isNullOrEmpty(aname)){
			stringBuffer.append(" and aname like CONCAT('%',?,'%')");
			params.add(aname);
		}
		//type不为空时进入，并且type=-1时也不算作条件
		if(!StringUtil.isNullOrEmpty(type) && !type.equals("-1")){
			stringBuffer.append(" and type=? ");
			params.add(type);
		}
		if(!StringUtil.isNullOrEmpty(time)){
			stringBuffer.append(" and create_time <= ?");
			params.add(time);
		}
		return new PageInfo<Account>(sqlCount+stringBuffer.toString(), sqlQuery+stringBuffer.toString(), params.toArray(), pageNum, Account.class);
	}
	/**
	 * 修改之前，进行数据回填
	 * @param id
	 * @return
	 */
	public Account queryAccountById(int id){
		String sql="select id,aname,type,money,remark from t_account where id=?";
		return querySingleRow(sql, Account.class, id);
	}
	/**
	 * 修改当前记录
	 * @param account
	 * @return
	 */
	public int updateAccount(Account account){
		String sql="update t_account set aname=?,type=?,money=?,remark=?,update_time=? where id=?";
		return executeUpdate(sql, account.getAname(),account.getType(),account.getMoney(),account.getRemark(),new Date(),account.getId());
	}
	/**
	 * 删除记录
	 * @param ids
	 * @return
	 */
	public int deleteAccount(String ids){
		String sql="delete from t_account where id in("+ids+")";
		return executeUpdate(sql, null);
	}
	/**
	 * 加载帐户下拉框
	 * @param uid
	 * @return
	 */
	public List<Account> queryAccountByUser(int uid){
		String sql="select id,aname,money from t_account where user_id=?";
		return queryRows(sql, Account.class, uid);
	}
}
