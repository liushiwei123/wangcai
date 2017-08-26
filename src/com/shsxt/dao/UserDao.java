package com.shsxt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shsxt.utils.MyDbUtil;
import com.shsxt.vo.User;

/**
 * dao层：用来与数据库作联系
 * @author Administrator
 */
public class UserDao extends BaseDao<User> {
	/**
	 * 在新增用户之前，查看该用户是否存在
	 * @param userName
	 * @return
	 */
	public User queryByUserName(String userName){
		String sql="select id,user_name as userName,user_pwd as userPwd from t_user where user_name=?";
		return querySingleRow(sql, User.class, userName);
	}
	public int saveUser(User user){
		String sql="insert into t_user(user_name,user_pwd) values(?,?)";
		return executeUpdate(sql, user.getUserName(),user.getUserPwd());
	}
}
