package com.shsxt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.constant.WacConstant;
import com.shsxt.dto.PayInDto;
import com.shsxt.model.MessageModel;
import com.shsxt.service.PayInService;
import com.shsxt.utils.JsonUtil;
import com.shsxt.utils.PageInfo;
import com.shsxt.utils.StringUtil;
import com.shsxt.vo.PayIn;
import com.shsxt.vo.User;
@WebServlet(name="payInController",urlPatterns="/payIn")
public class PayInController extends HttpServlet{
	private PayInService payInService;
	@Override
	public void init() throws ServletException {
		payInService=new PayInService();
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
			if(op.equals("addPayIn")){
				addPayIn(req,resp);
			}else if(op.equals("queryPayInByParams")){
				queryPayInByParams(req,resp);
			}else if(op.equals("queryPayInById")){
				queryPayInById(req,resp);
			}else if(op.equals("updatePayIn")){
				updatePayIn(req,resp);
			}else if(op.equals("deletePayIn")){
				deletePayIn(req,resp);
			}
		}
	}
	private void deletePayIn(HttpServletRequest req,HttpServletResponse resp){
		//要删除的id,有可能 是一个，有可能 是多个
		String ids=req.getParameter("ids");
		MessageModel messageModel=payInService.deletePayIn(ids);
		JsonUtil.toJson(resp, messageModel);
	}
	/**
	 * 修改收入记录
	 * @param req
	 * @param resp
	 */
	private void updatePayIn(HttpServletRequest req,HttpServletResponse resp){
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		//新money
		String money=req.getParameter("money");
		String remark=req.getParameter("remark");
		//新帐户
		String aid=req.getParameter("aid");
		String oldAid=req.getParameter("oldAid");
		String oldMoney=req.getParameter("oldMoney");
		String id=req.getParameter("id");
		if(StringUtil.isNullOrEmpty(money)){
			money="0";
		}
		PayInDto payInDto=new PayInDto();
		payInDto.setAid(Integer.parseInt(aid));
		payInDto.setOldAid(Integer.parseInt(oldAid));
		payInDto.setId(Integer.parseInt(id));
		payInDto.setName(name);
		payInDto.setRemark(remark);
		payInDto.setMoney(Double.parseDouble(money));
		payInDto.setOldMoney(Double.parseDouble(oldMoney));
		payInDto.setType(type);
		
		MessageModel messageMode=payInService.updatePayIn(payInDto);
		JsonUtil.toJson(resp, messageMode);
		
	}
	/**
	 * 进行数据回填
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryPayInById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String id=req.getParameter("id");
		PayIn payIn=payInService.queryPayInById(Integer.parseInt(id));
		req.setAttribute("payIn", payIn);
		req.getRequestDispatcher("payIn/edit_pay_in.jsp").forward(req, resp);
	}
	private void queryPayInByParams(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		User user=(User)req.getSession().getAttribute("user");
		String type=req.getParameter("p_type");
		String name=req.getParameter("p_name");
		String time=req.getParameter("p_time");
		String pageNum=req.getParameter("pageNum");
		//默认是全部
		if(StringUtil.isNullOrEmpty(type)){
			type="-1";
		}
		//默认第一页
		if(StringUtil.isNullOrEmpty(pageNum)){
			pageNum="1";
		}
		PageInfo<PayInDto> pageInfo=payInService.queryPayInByParams(user.getId(), name, time, type, Integer.parseInt(pageNum));
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("p_type", type);
		req.setAttribute("p_time", time);
		req.setAttribute("p_name", name);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("payIn/pay_in_list.jsp").forward(req, resp);
				
	}
	private void addPayIn(HttpServletRequest req,HttpServletResponse resp){
		MessageModel messageModel=new MessageModel();
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		String money=req.getParameter("money");
		String remark=req.getParameter("remark");
		String aid=req.getParameter("aid");
		if(StringUtil.isNullOrEmpty(money)){
			messageModel.setMsg("请输入收入的金额！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}else if(StringUtil.isNullOrEmpty(aid)){
			messageModel.setMsg("请选择帐户！");
			messageModel.setResultCode(WacConstant.OPTION_FAILED_CODE);
		}else{
			PayIn payIn=new PayIn(name,type,remark,Double.parseDouble(money),Integer.parseInt(aid));
			messageModel=payInService.savePayIn(payIn);
		}
		JsonUtil.toJson(resp, messageModel);
	}
}
