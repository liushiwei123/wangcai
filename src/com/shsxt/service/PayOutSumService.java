package com.shsxt.service;

import java.util.List;

import com.shsxt.dao.PayOutSumDao;
import com.shsxt.dto.PayOutSumDto;

public class PayOutSumService {
	private PayOutSumDao payOutSumDao=new PayOutSumDao();
	public List<PayOutSumDto>queryPayOutSum(int uid,String startTime,String endTime){
		return payOutSumDao.queryPayOutSum(uid,startTime,endTime);
	}
}
