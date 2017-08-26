package com.shsxt.dto;

import com.shsxt.vo.PayIn;

public class PayInDto extends PayIn {
	//返回帐户名称
	private String aname;
	//修改之前的金额
	private Double oldMoney;
	//修改之前的帐户
	private Integer oldAid;

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public Double getOldMoney() {
		return oldMoney;
	}

	public void setOldMoney(Double oldMoney) {
		this.oldMoney = oldMoney;
	}

	public Integer getOldAid() {
		return oldAid;
	}

	public void setOldAid(Integer oldAid) {
		this.oldAid = oldAid;
	}
	
}
