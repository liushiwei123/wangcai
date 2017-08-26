package com.shsxt.service;

import com.shsxt.constant.WacConstant;
import com.shsxt.dao.UserDao;
import com.shsxt.model.MessageModel;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.User;

/**
 * service层：
 * 	1、和dao层做交互
 *  2、处理业务逻辑
 * @author Administrator
 *
 */
public class UserService {
	private UserDao userDao=new UserDao();
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public MessageModel saveUser(User user){
		MessageModel messageModel=new MessageModel();
		//用户名不能为空
		if(StringUtil.isNullOrEmpty(user.getUserName())){
			messageModel.setMsg("用户名不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//密码不能为空
		if(StringUtil.isNullOrEmpty(user.getUserPwd())){
			messageModel.setMsg("密码不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//先查看要注册的用户名是否存在
		User temp=userDao.queryByUserName(user.getUserName());
		if(null!=temp){
			messageModel.setMsg("该用户已经存在！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//如果上述条件均满足，则进行注册
		int result=userDao.saveUser(user);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public MessageModel userLogin(User user){
		MessageModel messageModel=new MessageModel();
		//用户名不能为空
		if(StringUtil.isNullOrEmpty(user.getUserName())){
			messageModel.setMsg("用户名不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//密码不能为空
		if(StringUtil.isNullOrEmpty(user.getUserPwd())){
			messageModel.setMsg("密码不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//先查看要注册的用户名是否存在
		User temp=userDao.queryByUserName(user.getUserName());
		if(temp==null){
			messageModel.setMsg("该用户不存在！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//证明用户名存在，该较验密码正确与否
		if(!user.getUserPwd().equals(temp.getUserPwd())){
			messageModel.setMsg("密码不正确！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		messageModel.setResult(temp);
		return messageModel;
	}
}
