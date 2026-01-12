package com.sist.web.security;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;
import com.sist.web.vo.*;

@Component
@RequiredArgsConstructor
public class LoginFailHandler implements AuthenticationFailureHandler{
	private final MemberService mservice;
	//private final BCryptPasswordEncoder encoder;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errorMsg="";
		try
		{
			/*
			String id=request.getParameter("userid");
			String pwd=request.getParameter("userpwd");
			
			int count=mservice.memberIdCheck(id);
			if(count==0)
			{
				errorMsg="아이디가 존재하지 않습니다";
			}
			else
			{
				MemberVO vo=mservice.memberInfoData(id);
				if(encoder.matches(pwd, vo.getUserpwd()))
				{
					
				}
				else
				{
					errorMsg="비밀번호가 틀립니다";
				}
			}
			*/
			
			if(exception instanceof BadCredentialsException)
			{
				errorMsg="아이디 혹은 비밀번호가 틀립니다";
			}
			else if(exception instanceof InternalAuthenticationServiceException)
			{
				errorMsg="아이디 혹은 비밀번호가 틀립니다";
			}
			else if(exception instanceof DisabledException)
			{
				errorMsg="휴면 계정입니다";
			}
			
		}catch(Exception ex) {}
		request.setAttribute("message", errorMsg);
		request.getRequestDispatcher("/member/login").forward(request, response);
		
	}
}
