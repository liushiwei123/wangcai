package com.shsxt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shsxt.model.MessageModel;
import com.shsxt.service.AccountService;
import com.shsxt.vo.Account;

public class TestAccountService {
	private AccountService accountService;
	@Before
	public void init(){
		accountService=new AccountService();
	}

	@Test
	public void testSaveAccount() {
		Account account=new Account();
		account.setAname("成功");
		account.setUserId(1);
		account.setMoney(20000.0);
		account.setRemark("随便吧");
		MessageModel messageModel=accountService.saveAccount(account);
		System.out.println(messageModel.getResultCode());
		
		
	}

}
