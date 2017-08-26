package com.shsxt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.service.PayOutTypeService;
import com.shsxt.utils.JsonUtil;
import com.shsxt.vo.PayOutType;
@WebServlet(name="payOutTypeController",urlPatterns="/payOutType")
public class PayOutTypeController extends HttpServlet{
	private PayOutTypeService payOutTypeService;
	@Override
	public void init() throws ServletException {
		payOutTypeService=new PayOutTypeService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pid=req.getParameter("pid");
		List<PayOutType> list=payOutTypeService.queryPayOutTypeByPid(Integer.parseInt(pid));
		JsonUtil.toJson(resp, list);
	}
}
