package pl.training.bank.common;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;

@Log
public class Profiler {

    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long totalTime = System.nanoTime() - startTime;
        log.info(String.format("### %s executed in %d ns", proceedingJoinPoint.getSignature(), totalTime));
        return result;
    }

}
