package edu.njupt.sw.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

/*
加上这个函数的时候登录页面加载不出，报错说是空指针，尚未排查出原因
 */
    /**
     * AOP
     * 在IndexController运行之后添加一个切点，用于记录登录时间
     */
//    @Before("execution(* edu.njupt.sw.controller.*Controller.*(..))")
//    public void beforeMethod(JoinPoint joinPoint) {
//        StringBuilder sb = new StringBuilder();
//        for (Object arg : joinPoint.getArgs()) {
//            sb.append("arg:" + arg.toString() + "|");
//        }
//        logger.info("before method:" + sb.toString());
//    }


    /**
     * 在IndexController运行之前添加一个切点，用于记录登录时间
     */
    @After("execution(* edu.njupt.sw.controller.IndexController.*(..))")
    public void afterMethod() {
        logger.info("after method" + new Date());
    }
}