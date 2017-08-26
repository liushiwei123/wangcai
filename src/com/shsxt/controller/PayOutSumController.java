package com.shsxt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.dto.PayOutSumDto;
import com.shsxt.service.PayOutSumService;
import com.shsxt.utils.JsonUtil;
import com.shsxt.vo.User;
@WebServlet(name="payOutSumController",urlPatterns="/payOutSum")
public class PayOutSumController extends HttpServlet{
	private PayOutSumService payOutSumService;
	@Override
	public void init() throws ServletException {
		payOutSumService=new PayOutSumService();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user=(User)req.getSession().getAttribute("user");
		String startTime=req.getParameter("startTime");
		String endTime=req.getParameter("endTime");
		List<PayOutSumDto> lists=payOutSumService.queryPayOutSum(user.getId(),startTime,endTime);
		JsonUtil.toJson(resp, lists);
	}
}
