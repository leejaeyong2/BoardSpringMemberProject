package kh.spring.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginChkAdvice {
	@Autowired
	private HttpSession session;
	
	@Pointcut("execution(* kh.spring.practice.HomeController.logout(..))")
	public void logout() {}
	@Pointcut("execution(* kh.spring.practice.HomeController.myPage(..))")
	public void myPage() {}
	@Pointcut("execution(* kh.spring.practice.HomeController.signOut(..))")
	public void signOut() {}
	
	@Around("logout() || myPage() || signOut()")
	public Object loginChkAdvice(ProceedingJoinPoint pjp) throws Throwable {
		if((String)session.getAttribute("loginId")==null) {
			
			HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
			request.setAttribute("pleaseLogin", "로그인을 해 주세요.");
			return "pleaseLogin";
		}else {
			return pjp.proceed();	// 원래 호출된 메서드를 실행하라. mypage, logout , signout
			
		}
		
	}
}
