package com.shsxt.service;

import java.util.List;

import com.shsxt.dao.PayOutTypeDao;
import com.shsxt.vo.PayOutType;

public class PayOutTypeService {
	private PayOutTypeDao payOutTypeDao=new PayOutTypeDao();
	public List<PayOutType> queryPayOutTypeByPid(int pid){
		return payOutTypeDao.queryPayOutTypeByPid(pid);
	}
}
