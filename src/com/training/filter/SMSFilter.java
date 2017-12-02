package com.training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.training.common.config.Config;
import com.training.data.UserData;
import com.training.service.UserService;


public class SMSFilter implements Filter
{

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filter) throws IOException, ServletException
	{
		System.out.println("=======sms=====");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
		String smsLoginUrl = Config.getStringProperty("login.sms.url");
		if (!url.contains(smsLoginUrl))
		{
			filter.doFilter(arg0, arg1);
			return;
		}

		String mobile = request.getParameter("mobile");
		String smsCode = request.getParameter("smsCode");

		String sessionSMSCode = (String) request.getSession().getAttribute("smsCode");
		if (StringUtils.equals(smsCode, sessionSMSCode))
		{
			WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
			UserService userService = ctx.getBean(UserService.class);
			UserData data = userService.queryUserByMobile(mobile);
			if (data == null)
			{
				response.sendRedirect("login");
			}
			else
			{
				request.getSession().setAttribute("userData", data);
				//filter.doFilter(arg0, arg1);
				response.sendRedirect("loadStudentsByFields");
			}
		}
		else
		{
			response.sendRedirect("login");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

}
