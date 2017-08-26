package com.shsxt.dto;

import com.shsxt.vo.PayOut;

public class PayOutDto extends PayOut {
	//类型名称
	private String typeName;
	//所属帐户
	private String aname;
	//修改前的支出
	private Double oldMoney;
	//修改前的帐户
	private Integer oldAid;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
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
