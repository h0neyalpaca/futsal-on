package com.kh.futsal.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.member.validator.JoinForm;
import com.kh.futsal.team.validator.CreateForm;


public class ValidatorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidatorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		if(uriArr.length != 0) {
			
			String redirectURI = null;
			
			switch (uriArr[1]) {
			case "member":
				redirectURI = memberValidation(httpRequest,httpResponse,uriArr);
				break;
			case "myteam":
				redirectURI = myteamValidation(httpRequest,httpResponse,uriArr);
				break;
			default:
				break;
			}
			
			if(redirectURI != null) {
				httpResponse.sendRedirect(redirectURI);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private String myteamValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		String redirectURI = null;
		
		switch (uriArr[2]) {
		case "create-form":
			CreateForm createForm = new CreateForm(httpRequest);
			if(!createForm.test()) {
				redirectURI = "/myteam/create-form?err=1";
			}
			break;
		default:
			break;
		}
		
		return redirectURI;
	}

	private String memberValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		String redirectURI = null;
		
		switch (uriArr[2]) {
		case "join-form":
			JoinForm joinForm = new JoinForm(httpRequest);
			if(!joinForm.test()) {
				redirectURI = "/member/join-form?err=1";
			}
			break;
		default:
			break;
		}
		
		return redirectURI;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
