package com.shsxt.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import com.shsxt.utils.MyDbUtil;

public class TestMyDbUtil {
	private MyDbUtil myDbUtil;
	@Before
	public void init(){
		myDbUtil=new MyDbUtil();
	}
	@Test
	public void testGetConnection() {
		Connection connection=myDbUtil.getConnection();
		System.out.println(connection==null);
	}
}
