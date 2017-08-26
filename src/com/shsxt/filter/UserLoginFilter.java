package com.shsxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.vo.User;
@WebFilter(urlPatterns="/*",initParams={@WebInitParam(name="charset",value="utf-8")})
public class UserLoginFilter implements Filter {
	private FilterConfig filterConfig;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		/**
		 * 静态资源放行处理
		 * 			1)、需要放行的内容:  /skin/  /js/  /register.jsp  /login.jsp
		 * 			2)、需要放行的内容: user  userRegister  userLogin  userLogout
		 * 			3)、从session域对象中获取当前的user，若存在，则放行，若为null，则让其返回到登录页面去登录
		 */
		String uri=request.getRequestURI();
		if(uri.contains("/skin/") || uri.contains("/js/") || uri.contains("register.jsp")
				|| uri.contains("login.jsp")){
			chain.doFilter(request, response);
			return;
		}
		if(uri.contains("user")){
			String op=request.getParameter("op");
			if(op.equals("userRegister") || op.equals("userLogin") || op.equals("userLogout")){
				chain.doFilter(request, response);
				return;
			}
		}
		//当前没有用户登录
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
			return;
		}
		//正常登录，需要放行
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=filterConfig;
	}

}
