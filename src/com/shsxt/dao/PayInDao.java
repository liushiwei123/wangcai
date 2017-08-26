package com.shsxt.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.shsxt.dto.PayInDto;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.PayIn;
public class PayInDao extends BaseDao<PayIn> {
	/**
	 * 添加收入：
	 * 1、在t_pay_in表中增加记录
	 * 2、在t_account表中当前帐户增加相应的金额
	 * @param payIn
	 * @return
	 */
	public int savePayIn(PayIn payIn){
		List<SqlParam>list=new ArrayList<SqlParam>();
		String sql1="insert into t_pay_in(name,money,remark,create_time,update_time,aid,type)"
					+ " values(?,?,?,?,?,?,?)";
		Object[] params1={payIn.getName(),payIn.getMoney(),payIn.getRemark(),new Date(),
				new Date(),payIn.getAid(),payIn.getType()};
		String sql2="update t_account set money=money+?,update_time=? where id=?";
		Object[] params2={payIn.getMoney(),new Date(),payIn.getAid()};
		
		list.add(new SqlParam(sql1,params1));
		list.add(new SqlParam(sql2,params2));
		return executeUpdateBatch(list);		
	}
	/**
	 * 多条件查询
	 * @param uid
	 * @param name
	 * @param time
	 * @param type
	 * @param pageNum
	 * @return
	 */
	public PageInfo<PayInDto> queryPayInByParams(int uid,String name,String time,String type,int pageNum){
		List params=new ArrayList();
		StringBuffer stringBuffer=new StringBuffer();
		String sqlCount="SELECT count(1) from t_pay_in p  LEFT JOIN t_account a"
				+" ON p.aid=a.id where user_id=?";
		String sqlQuery="SELECT p.id,p.name,p.type,p.aid,p.money,p.remark,"
				+ " p.create_time AS createTime,p.update_time AS updateTime,a.aname"
				+" from t_pay_in p  LEFT JOIN t_account a ON p.aid=a.id where user_id=? ";
		
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
			stringBuffer.append(" and p.type = ?");
			params.add(type);
		}
		PageInfo<PayInDto>payInfo;
		payInfo = new PageInfo<PayInDto>(sqlCount+stringBuffer.toString(),
				sqlQuery+stringBuffer.toString(), params.toArray(), pageNum, PayInDto.class);
		return payInfo;
				
	}
	/**
	 * 用来进行数据回填
	 * @param id
	 * @return
	 */
	public PayIn queryPayInById(int id){
		String sql="SELECT id,name,type,money,remark,aid from t_pay_in where id=?";
		return querySingleRow(sql, PayIn.class, id);
	}
	/**
	 * 修改收入:
	 * 1、修改收入本身记录
	 * 2、修改旧帐户的数据
	 * 3、修改新帐户的数据
	 * @param payInDto
	 * @return
	 */
	public int updatePayIn(PayInDto payInDto){
		List<SqlParam> list=new ArrayList<SqlParam>();
		//1、修改收入本身记录
		String sql1="update t_pay_in set money=?,type=?,name=?,remark=?,aid=?,update_time=?"
				+" where id=?";
		Object[] params1={payInDto.getMoney(),payInDto.getType(),payInDto.getName(),payInDto.getRemark(),
				payInDto.getAid(),new Date(),payInDto.getId()};
		//2、修改旧帐户的数据
		String sql2="update t_account set money=money-?,update_time=? where id=?";
		Object[] params2={payInDto.getOldMoney(),new Date(),payInDto.getOldAid()};
		//3、修改新帐户的数据
		String sql3="update t_account set money=money+?,update_time=? where id=?";
		Object[] params3={payInDto.getMoney(),new Date(),payInDto.getAid()};
		list.add(new SqlParam(sql1,params1));
		list.add(new SqlParam(sql2,params2));
		list.add(new SqlParam(sql3,params3));
		return executeUpdateBatch(list);
	}
	/**
	 * 删除：
	 * 1、删除收入表中的记录
	 * 2、逐个删除帐户表中对应的钱数
	 * @param ids
	 * @return
	 */
	public int deletePayIn(String ids){
		List<SqlParam> list=new ArrayList<SqlParam>();
		String [] temp=ids.split(",");
		String sql1="SELECT aid,money from t_pay_in where id=?";
		String sql2="update t_account set money=money-?,update_time=? where id=?";
		//遍历查询要删除的id 
		for(int i=0;i<temp.length;i++){
			String id=temp[i];
			PayIn payIn=querySingleRow(sql1, PayIn.class, id);
			SqlParam sqlParam=new SqlParam(sql2, new Object[]{payIn.getMoney(),new Date(),payIn.getAid()});
			list.add(sqlParam);
		}
		String sql3="delete from t_pay_in where id in ("+ids+")";
		list.add(new SqlParam(sql3,null));
		return executeUpdateBatch(list);
	}
}
