package com.shsxt.service;

import com.shsxt.constant.WacConstant;
import com.shsxt.dao.PayOutDao;
import com.shsxt.dto.PayOutDto;
import com.shsxt.model.MessageModel;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.PayOut;

public class PayOutService {
	private PayOutDao payOutDao=new PayOutDao();
	public MessageModel savePayOut(PayOut payOut){
		MessageModel messageModel=new MessageModel();
		if(StringUtil.isNullOrEmpty(payOut.getName())){
			messageModel.setMsg("支出名称不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		if(null==payOut.getAid()|| payOut.getAid()==0){
			messageModel.setMsg("请选择所属帐户！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		if(null==payOut.getMoney()|| payOut.getMoney()<=0){
			messageModel.setMsg("支出金额不能小于0！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		if(StringUtil.isNullOrEmpty(payOut.getType())){
			messageModel.setMsg("请选择支出类型！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		int result=payOutDao.savePayOut(payOut);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	public PageInfo<PayOutDto> queryPayOutByParams(int uid,String type,String name,String time,int pageNum){
		return payOutDao.queryPayOutByParams(uid, type, name, time, pageNum);
	}
	public PayOut queryPayOutById(int id){
		return payOutDao.queryPayOutById(id);
	}
	public MessageModel updatePayOut(PayOutDto payOutDto){
		MessageModel messageModel=new MessageModel();
		if(StringUtil.isNullOrEmpty(payOutDto.getName())){
			messageModel.setMsg("支出名称不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		if(null==payOutDto.getAid()|| payOutDto.getAid()==0 || null==payOutDto.getOldAid() || payOutDto.getOldAid()==0){
			messageModel.setMsg("请选择所属帐户！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		if(null==payOutDto.getMoney()|| payOutDto.getMoney()<=0 || null==payOutDto.getOldMoney() || payOutDto.getOldMoney()<=0){
			messageModel.setMsg("支出金额不能小于0！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		if(StringUtil.isNullOrEmpty(payOutDto.getType())){
			messageModel.setMsg("请选择支出类型！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		PayOut payOut=payOutDao.queryPayOutById(payOutDto.getId());
		if(payOut==null){
			messageModel.setMsg("待更新的记录不存在！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		int result=payOutDao.updatePayOut(payOutDto);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	public MessageModel deletePayOut(String ids){
		MessageModel messageModel=new MessageModel();
		int result=payOutDao.deletePayOut(ids);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
}
