package com.shsxt.service;

import com.shsxt.constant.WacConstant;
import com.shsxt.dao.PayInDao;
import com.shsxt.dto.PayInDto;
import com.shsxt.model.MessageModel;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.PayIn;

public class PayInService {
	private PayInDao payInDao=new PayInDao();
	/**
	 * 添加收入
	 * @param payIn
	 * @return
	 */
	public MessageModel savePayIn(PayIn payIn){
		MessageModel messageModel=new MessageModel();
		//收入名称不能为空
		if(StringUtil.isNullOrEmpty(payIn.getName())){
			messageModel.setMsg("收入名称不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//不能不存在帐户
		if(null==payIn.getAid() || payIn.getAid()==0){
			messageModel.setMsg("请选择帐户！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//添加收入时，金额大于0
		if(null==payIn.getMoney() || payIn.getMoney()<=0){
			messageModel.setMsg("收入金额必须大于0！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		int result=payInDao.savePayIn(payIn);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	/**
	 * 查询当前用户下的所有收入
	 * @param uid
	 * @param name
	 * @param time
	 * @param type
	 * @param pageNum
	 * @return
	 */
	public PageInfo<PayInDto>queryPayInByParams(int uid,String name,String time,String type,int pageNum){
		return payInDao.queryPayInByParams(uid, name, time, type, pageNum);
	}
	/**
	 * 数据回填 
	 * @param id
	 * @return
	 */
	public PayIn queryPayInById(int id){
		return payInDao.queryPayInById(id);
	}
	public MessageModel updatePayIn(PayInDto payInDto){
		MessageModel messageModel=new MessageModel();
		//收入名称不能为空
		if(StringUtil.isNullOrEmpty(payInDto.getName())){
			messageModel.setMsg("收入名称不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//不能不存在帐户
		if(null==payInDto.getAid() || payInDto.getAid()==0 || payInDto.getOldAid()==null || payInDto.getOldAid()==0){
			messageModel.setMsg("请选择帐户！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//添加收入时，金额大于0
		if(null==payInDto.getMoney() || payInDto.getMoney()<=0 || payInDto.getOldMoney()==null || payInDto.getMoney()<=0){
			messageModel.setMsg("收入金额必须大于0！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		PayIn payIn=payInDao.queryPayInById(payInDto.getId());
		if(payIn==null){
			messageModel.setMsg("待更新记录不存在！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		int result=payInDao.updatePayIn(payInDto);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	/**
	 * 删除收入记录
	 * @param ids
	 * @return
	 */
	public MessageModel deletePayIn(String ids){
		MessageModel messageModel=new MessageModel();
		int result=payInDao.deletePayIn(ids);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
}
