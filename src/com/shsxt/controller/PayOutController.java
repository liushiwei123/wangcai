package com.shsxt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.dto.PayOutDto;
import com.shsxt.model.MessageModel;
import com.shsxt.service.PayOutService;
import com.shsxt.utils.JsonUtil;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.PayOut;
import com.shsxt.vo.User;
@WebServlet(name="payOutController",urlPatterns="/payOut")
public class PayOutController extends HttpServlet{
	private PayOutService payOutService;
	@Override
	public void init() throws ServletException {
		payOutService=new PayOutService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op=req.getParameter("op");
		if(!StringUtil.isNullOrEmpty(op)){
			if(op.equals("addPayOut")){
				addPayOut(req,resp);
			}else if(op.equals("queryPayOutByParams")){
				queryPayOutByParams(req,resp);
			}else if(op.equals("queryPayOutById")){
				queryPayOutById(req,resp);
			}else if(op.equals("updatePayOut")){
				updatePayOut(req,resp);
			}else if(op.equals("deletePayOut")){
				deletePayOut(req,resp);
			}
		}
	}
	private void deletePayOut(HttpServletRequest req,HttpServletResponse resp){
		String ids=req.getParameter("ids");
		MessageModel messageModel=payOutService.deletePayOut(ids);
		JsonUtil.toJson(resp, messageModel);
	}
	private void updatePayOut(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String remark=req.getParameter("remark");
		String aid=req.getParameter("aid");
		String money=req.getParameter("money");
		//pid,sid 组成type 
		String pid=req.getParameter("pid");
		String sid=req.getParameter("sid");
		String type=pid+","+sid;
		String oldAid=req.getParameter("oldAid");
		String oldMoney=req.getParameter("oldMoney");
		
		PayOutDto payOutDto=new PayOutDto();
		payOutDto.setAid(Integer.parseInt(aid));
		payOutDto.setName(name);
		payOutDto.setRemark(remark);
		payOutDto.setMoney(Double.parseDouble(money));
		payOutDto.setType(type);
		payOutDto.setOldAid(Integer.parseInt(oldAid));
		payOutDto.setOldMoney(Double.parseDouble(oldMoney));
		payOutDto.setId(Integer.parseInt(id));
		
		MessageModel messageModel=payOutService.updatePayOut(payOutDto);
		JsonUtil.toJson(resp, messageModel);
		
	}
	/**
	 * 回填记录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryPayOutById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String id=req.getParameter("id");
		PayOut payOut=	payOutService.queryPayOutById(Integer.parseInt(id));
		req.setAttribute("payOut", payOut);
		req.getRequestDispatcher("payOut/edit_pay_out.jsp").forward(req, resp);
	}
	private void queryPayOutByParams(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		User user=(User)req.getSession().getAttribute("user");
		String name=req.getParameter("p_name");
		String type=req.getParameter("p_type");
		String time=req.getParameter("p_time");
		String pageNum=req.getParameter("pageNum");
		if(StringUtil.isNullOrEmpty(pageNum)){
			pageNum="1";
		}
		if(StringUtil.isNullOrEmpty("type")){
			type="-1";
		}
		PageInfo<PayOutDto> pageInfo=payOutService.queryPayOutByParams(user.getId(), type, name, time, Integer.parseInt(pageNum));
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("p_name", name);
		req.setAttribute("p_type", type);
		req.setAttribute("p_time", time);
		req.getRequestDispatcher("payOut/pay_out_list.jsp").forward(req, resp);
	}
	private void addPayOut(HttpServletRequest req,HttpServletResponse resp){
		String name=req.getParameter("name");
		String remark=req.getParameter("remark");
		String aid=req.getParameter("aid");
		String money=req.getParameter("money");
		//pid,sid 组成type 
		String pid=req.getParameter("pid");
		String sid=req.getParameter("sid");
		String type=pid+","+sid;
		PayOut payOut=new PayOut();
		payOut.setAid(Integer.parseInt(aid));
		payOut.setName(name);
		payOut.setRemark(remark);
		payOut.setMoney(Double.parseDouble(money));
		payOut.setType(type);
		MessageModel messageModel=payOutService.savePayOut(payOut);
		JsonUtil.toJson(resp, messageModel);
		
		
		
	}
}
