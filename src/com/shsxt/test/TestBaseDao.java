package com.shsxt.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shsxt.dao.BaseDao;
import com.shsxt.vo.User;

public class TestBaseDao {
	private BaseDao baseDao;
	@Before
	public void init(){
		baseDao=new BaseDao();
	}
	@Test
	public void testQuerySingleValue() {
		String sql="select count(1) from t_user where id=?";
		Object [] objects={1};
		Long count=(Long)baseDao.querySingleValue(sql, objects);
		System.out.println("一共有："+count+"条记录");
	}

	@Test
	public void testExecuteUpdate() {
		String sql="insert into t_user(user_name,user_pwd) values(?,?)";
		Object[] objects={"zw","zw"};
		int result=baseDao.executeUpdate(sql, objects);
		System.out.println("成功了"+result);
	}
	@Test
	public void testQueryRows() throws InstantiationException, IllegalAccessException{
		String sql="SELECT id,user_name as userName,user_pwd as userPwd from t_user";
		List<User> users=baseDao.queryRows(sql, User.class, null);
		if(users!=null){
			for(User user:users){
				System.out.println(user.getUserName());
			}
		}
	}

}
