package kh.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfCheckAdvice {

	 @Around("execution(* kh.spring.practice.HomeController.loginProc(..))")
	 public Object perfCheck(ProceedingJoinPoint pjp) {
		 long startTime = System.currentTimeMillis();
		 Object retVal = null;
		 try {
			 retVal = pjp.proceed(); // 
		 }catch(Throwable e) {
			 e.printStackTrace();
		 }
		 
		 long endTime = System.currentTimeMillis();
		 System.out.println((endTime - startTime)/1000.0 + "초간 수행");
		 return retVal;
	 }
}
