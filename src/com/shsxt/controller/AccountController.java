package com.shsxt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shsxt.constant.WacConstant;
import com.shsxt.model.MessageModel;
import com.shsxt.service.AccountService;
import com.shsxt.utils.JsonUtil;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.Account;
import com.shsxt.vo.User;
@WebServlet(name="accountController",urlPatterns="/account")
public class AccountController extends HttpServlet{
	private AccountService accountService;
	@Override
	public void init() throws ServletException {
		accountService=new AccountService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op=req.getParameter("op");
		if(!StringUtil.isNullOrEmpty(op)){
			if(op.equals("addAccount")){
				addAccount(req, resp);
			}else if(op.equals("queryAccountByParams")){
				queryAccountByParams(req,resp);
			}else if(op.equals("queryAccountById")){
				queryAccountById(req, resp);
			}else if(op.equals("updateAccount")){
				updateAccount(req,resp);
			}else if(op.equals("deleteAccount")){
				deleteAccount(req,resp);
			}else if(op.equals("queryAccountByUser")){
				queryAccountByUser(req,resp);
			}
		}
	}
	private void queryAccountByUser(HttpServletRequest req,HttpServletResponse resp){
		User user=(User)req.getSession().getAttribute("user");
		List<Account> accounts=accountService.queryAccountByUser(user.getId());
		JsonUtil.toJson(resp, accounts);
	}
	/**
	 * 删除记录
	 * @param req
	 * @param resp
	 */
	private void deleteAccount(HttpServletRequest req,HttpServletResponse resp){
		String ids=req.getParameter("ids");
		MessageModel messageModel=accountService.deleteAccount(ids);
		JsonUtil.toJson(resp, messageModel);
	}
	/**
	 * 修改帐户
	 * @param req
	 * @param resp
	 */
	private void updateAccount(HttpServletRequest req,HttpServletResponse resp){
		User user=(User)req.getSession().getAttribute("user");
		String id=req.getParameter("id");
		String aname=req.getParameter("aname");
		String type=req.getParameter("type");
		String money=req.getParameter("money");
		if(StringUtil.isNullOrEmpty(money)){
			money="0";
		}
		String remark=req.getParameter("remark");
		Account account=new Account();
		account.setId(Integer.parseInt(id));
		account.setAname(aname);
		account.setType(type);
		account.setMoney(Double.parseDouble(money));
		account.setRemark(remark);
		account.setUserId(user.getId());
		MessageModel messageModel=accountService.updateAccount(account);
		JsonUtil.toJson(resp, messageModel);
	}
	/**
	 * 修改之前回填数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryAccountById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String id=req.getParameter("id");
		Account account=accountService.queryAccountById(Integer.parseInt(id));
		req.setAttribute("account", account);
		req.getRequestDispatcher("account/edit_account.jsp").forward(req, resp);
	}
	/**
	 * 多条件分页查询
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void queryAccountByParams(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		User user=(User)req.getSession().getAttribute("user");
		//获取前台传递过来的查询条件
		String a_name=req.getParameter("a_name");
		String a_type=req.getParameter("a_type");
		String a_time=req.getParameter("a_time");
		//获取当前页数
		String pageNum=req.getParameter("pageNum");
		//若当前是初始化
		if(StringUtil.isNullOrEmpty(pageNum)){
			pageNum="1";
		}
		PageInfo<Account> pageInfo=accountService.queryPageInfoByParams(user.getId(),a_name,a_type,a_time,Integer.parseInt(pageNum));
		req.setAttribute("pageInfo", pageInfo);
		//让查询条件回填
		req.setAttribute("a_name", a_name);
		req.setAttribute("a_type", a_type);
		req.setAttribute("a_time", a_time);
		req.getRequestDispatcher("account/account_list.jsp").forward(req, resp);
	}
	/**
	 * 添加帐户
	 * @param req
	 * @param resp
	 */
	private void addAccount(HttpServletRequest req,HttpServletResponse resp){
		//获取session存放的user,拿到userId
		User user=(User)req.getSession().getAttribute("user");
		String aname=req.getParameter("aname");
		String type=req.getParameter("type");
		String money=req.getParameter("money");
		String remark=req.getParameter("remark");
		Account account=new Account();
		account.setAname(aname);
		account.setType(type);
		account.setMoney(Double.parseDouble(money));
		account.setRemark(remark);
		account.setUserId(user.getId());
		MessageModel messageModel=accountService.saveAccount(account);
		
		JsonUtil.toJson(resp, messageModel);
		
	}
}
