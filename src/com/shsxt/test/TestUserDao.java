package com.shsxt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.shsxt.dao.UserDao;
import com.shsxt.vo.User;

public class TestUserDao {
	private UserDao userDao;
	@Before
	public void init(){
		userDao=new UserDao();
	}
	@Test
	public void testQueryByUserName() {
		User user=userDao.queryByUserName("zyy");
		if(user==null){
			System.out.println("可以提交该用户");
		}else{
			System.out.println("该用户已经存在");
		}
	}

	@Test
	public void testSaveUser() {
		User user=new User();
		user.setUserName("张伟");
		user.setUserPwd("zw");
		int result=userDao.saveUser(user);
		if(result<1){
			System.out.println("添加失败");
		}else {
			System.out.println("用户添加成功");
		}
	}

}
