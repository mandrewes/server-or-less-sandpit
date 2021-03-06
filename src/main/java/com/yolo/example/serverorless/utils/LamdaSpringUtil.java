package com.yolo.example.serverorless.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class LamdaSpringUtil {
    private static final Logger LOG = Logger.getLogger(AbstractLambdaSpringService.class);
    private static Object lck = new Object();

    private static String globalRootContextPath = "/spring/app-ctx.xml";
    private static ApplicationContext ctx = null;

    @Autowired
    public void setCtx(ApplicationContext _ctx) {
        // in lambda we need to start up the CTX ourselves
        // for unit tests and REST, all the autowiring should happen automatically
        if (LamdaSpringUtil.ctx == null) {
            LamdaSpringUtil.ctx = _ctx;
        }
    }


    public static void setGlobalRootContextPath(String s) {
        globalRootContextPath = s;
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }

    public static void wireInSpring(Object o, String myBeanName) {
        // Lambda does not do this for you - though serverless does have a library to do it
        if (ctx == null) {
            synchronized (lck) {
                if (ctx == null) {
                    LOG.info("LamdaSpringUtil CTX is null -  initialising spring");
                    ctx = new ClassPathXmlApplicationContext(globalRootContextPath);
                }
            }
        } else {
            LOG.debug("LamdaSpringUtil CTX is not null - not initialising spring");
        }
        AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
        factory.autowireBean(o);
        factory.initializeBean(0, myBeanName);
    }
}

