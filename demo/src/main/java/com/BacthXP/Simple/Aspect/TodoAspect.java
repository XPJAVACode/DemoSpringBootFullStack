package com.BacthXP.Simple.Aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import com.BacthXP.Simple.Annotation.ExecutionTime;

@Aspect
@Component
public class TodoAspect {

	@Around("@annotation(executionTime)")
	public Object calculateExecutionTime(ProceedingJoinPoint point, ExecutionTime executionTime) throws Throwable {
		long start = System.currentTimeMillis(); //start time
		
		Object proceed = point.proceed();
		
		long totalExecutionTime = System.currentTimeMillis() - start;
		System.out.println("The Method executionTime is "+ totalExecutionTime);
		
		return proceed;
	}
}
