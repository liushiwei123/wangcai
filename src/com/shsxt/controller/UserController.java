package com.shsxt.controller;

import com.shsxt.constant.WacConstant;
import com.shsxt.model.MessageModel;
import com.shsxt.service.UserService;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="userController",urlPatterns="/user")
public class UserController extends HttpServlet {
	private UserService userService;
	@Override
	public void init() throws ServletException {
		userService=new UserService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op=req.getParameter("op");
		if(!StringUtil.isNullOrEmpty(op)){
			if(op.equals("userRegister")){
				userRegister(req, resp);
			}else if(op.equals("userLogin")){
				userLogin(req,resp);
			}else if(op.equals("userLogout")){
				userLogout(req, resp);
			}
		}
	}
	/**
	 * 用户退出
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void userLogout(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		//清空session
		req.getSession().setAttribute("user",null);
		resp.sendRedirect("login.jsp");
	}
	/**
	 * 用于登录用户
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void userLogin(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		User user=new User();
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		user.setUserName(userName);
		user.setUserPwd(userPwd);
		MessageModel messageModel=userService.userLogin(user);
		if(messageModel.getResultCode()==WacConstant.OPTION_FAILED_CODE){
			req.setAttribute("msg", messageModel.getMsg());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		User temp=(User)messageModel.getResult();
		req.getSession().setAttribute("user",temp);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	/**
	 * 用于注册用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userRegister(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		User user=new User();
		user.setUserName(userName);
		user.setUserPwd(userPwd);
		MessageModel messageModel=userService.saveUser(user);
		if(messageModel.getResultCode()!=WacConstant.OPTION_SUCCESS_CODE){
			req.setAttribute("msg", messageModel.getMsg());
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("userName", userName);
		req.getRequestDispatcher("success.jsp").forward(req,resp);
	}
}
