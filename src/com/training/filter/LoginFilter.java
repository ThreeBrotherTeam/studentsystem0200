package com.training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.training.common.config.Config;
import com.training.data.UserData;
import com.training.service.UserService;


public class LoginFilter implements Filter
{

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String excludeUri = Config.getStringProperty("login.exclude.uri");
		String[] excludeUriArray = StringUtils.split(excludeUri, ",");

		if (null == excludeUriArray)
		{
			HttpSession session = req.getSession();
			UserData data = (UserData) session.getAttribute("userData");
			if (data == null)
			{
				resp.sendRedirect("login");
			}
			else
			{
				chain.doFilter(req, resp);
			}
		}
		else
		{
			boolean access = false;
			for (String exuri : excludeUriArray)
			{
				if (uri.contains(exuri))
				{
					access = true;
					break;
				}
			}
			if (access)
			{
				chain.doFilter(req, resp);
			}
			else
			{
				HttpSession session = req.getSession();
				UserData data = (UserData) session.getAttribute("userData");
				if (data == null)
				{
					Cookie[] cookie = req.getCookies();
					Cookie rememberMe = null;
					if (cookie == null || cookie.length == 0)
					{
						resp.sendRedirect("login");
						return;
					}
					for (Cookie c : cookie)
					{
						if ("rememberMe".equals(c.getName()))
						{
							rememberMe = c;
							break;
						}
					}
					if (rememberMe != null)
					{
						String token = rememberMe.getValue();
						String[] str = token.split("\\(\\$\\)");
						String name = str[0];
						String password = str[1];
						WebApplicationContext ctx = WebApplicationContextUtils
								.getRequiredWebApplicationContext(req.getServletContext());
						UserService userService = ctx.getBean(UserService.class);
						data = userService.queryUserByNameAndPassword(name, password);
					}
					resp.sendRedirect("login");
				}
				else
				{
					chain.doFilter(req, resp);
				}
			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{

	}

}
