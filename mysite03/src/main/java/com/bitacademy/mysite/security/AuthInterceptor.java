package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bitacademy.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// 디폴트 서블릿 헨들러가 처리하는 경우(보통, asserts의 정정 자원 접근)
			return true;
		}
		
		//2. 케스팅 
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method에 @Auth가  달려 있는 지 확인
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Mehtod에 @Auth가 안 달려 있으면
		if(auth == null) {
			return true;
		}
		//5. @Auth가 달려 있는 경우에는 인증 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authvo =(UserVo)session.getAttribute("authUser");
		if(authvo == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		return true;
	}

}
