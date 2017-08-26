package com.shsxt.vo;

import java.util.Date;

public class PayIn {
	private Integer id;
	private String name;
	private String type;
	private String remark;
	private Date createTime;
	private Date updateTime;
	private Double money;
	private Integer aid;
	public PayIn() {
		// TODO Auto-generated constructor stub
	}
	
	public PayIn(String name, String type, String remark, Double money,
			Integer aid) {
		super();
		this.name = name;
		this.type = type;
		this.remark = remark;
		this.money = money;
		this.aid = aid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
