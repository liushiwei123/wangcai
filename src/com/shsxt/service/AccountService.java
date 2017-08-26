package com.shsxt.service;

import java.util.List;

import com.shsxt.constant.WacConstant;
import com.shsxt.dao.AccountDao;
import com.shsxt.model.MessageModel;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.Account;

public class AccountService {
	private AccountDao accountDao=new AccountDao();
	public MessageModel saveAccount(Account account){
		MessageModel messageModel=new MessageModel();
		//帐户名不能为空
		if(StringUtil.isNullOrEmpty(account.getAname())){
			messageModel.setMsg("帐户名不能为空!");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//该用户所在的帐户中不能有重名帐户
		Account temp=accountDao.queryAccountByAnameUid(account.getUserId(), account.getAname());
		if(temp!=null){
			messageModel.setMsg("该帐户已经存在！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		int result=accountDao.saveAccount(account);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	/**
	 * 多条件分页查询
	 * @param uid
	 * @param aname
	 * @param type
	 * @param time
	 * @param pageNum
	 * @return
	 */
	public List<Account> queryAccountByParams(int uid,String aname,String type,String time,int pageNum){
		return accountDao.queryAccountByParams(uid, aname, type, time,pageNum);
	}
	public Long queryAccountTotalByParams(int uid,String aname,String type,String time,int pageNum){
		return accountDao.queryAccountTotalByParams(uid, aname, type, time, pageNum);
	}
	/**
	 * 通过PageInfo获取所有分页相关信息
	 * @param uid
	 * @param aname
	 * @param type
	 * @param time
	 * @param pageNum
	 * @return
	 */
	public PageInfo<Account>queryPageInfoByParams(int uid,String aname,String type,String time,int pageNum){
		return accountDao.queryPageInfoByParams(uid, aname, type, time, pageNum);
	}
	public Account queryAccountById(int id){
		return accountDao.queryAccountById(id);
	}
	/**
	 * 修改当前帐户的方法
	 * @param account
	 * @return
	 */
	public MessageModel updateAccount(Account account){
		MessageModel messageModel=new MessageModel();
		//判断：帐户名称不能为空
		if(StringUtil.isNullOrEmpty(account.getAname())){
			messageModel.setMsg("帐户名不能为空！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//ID不能为空
		if(null==account.getId() || account.getId()==0){
			messageModel.setMsg("ID不能为空");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		//要修改的帐户存在与否
		Account temp=accountDao.queryAccountById(account.getId());
		if(temp==null){
			messageModel.setMsg("待更新的记录不存在");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
			return messageModel;
		}
		Account reName=accountDao.queryAccountByAnameUid(account.getUserId(), account.getAname());
		//修改以后的帐户名称不能与除本身以外的帐户名称同名
		if(reName!=null){
			if(account.getId()!=reName.getId()){
				messageModel.setMsg("该帐户名已经存在！");
				messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
				return messageModel;
			}
		}
		//进行修改
		int result=accountDao.updateAccount(account);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	/**
	 *帐户 记录删除
	 * @param ids
	 * @return
	 */
	public MessageModel deleteAccount(String ids){
		MessageModel messageModel=new MessageModel();
		int result=accountDao.deleteAccount(ids);
		if(result<1){
			messageModel.setMsg(WacConstant.OPTION_FAILED_MSG);
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}
		return messageModel;
	}
	public List<Account> queryAccountByUser(int uid){
		return accountDao.queryAccountByUser(uid);
	}
}
