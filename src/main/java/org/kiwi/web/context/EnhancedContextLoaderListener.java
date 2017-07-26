package org.kiwi.web.context;

import org.kiwi.context.ApplicationContextHolder;
import org.kiwi.context.DeployPathHolder;
import org.kiwi.context.KiwiConfig;
import org.kiwi.context.PropertiesHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.File;
import java.util.Enumeration;

import static org.kiwi.context.Constant.*;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/26.
 */
public class EnhancedContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        /**
         * 设置profiles.env变量
         */
        ServletContext servletContext = event.getServletContext();
        Enumeration<String> initParamNames = servletContext.getInitParameterNames();
        while (initParamNames.hasMoreElements()) {
            String initParamName = initParamNames.nextElement();
            if (PROFILE_ENV.equals(initParamName)) {
                String profileEnv = servletContext.getInitParameter(initParamName);
                System.setProperty(PROFILE_ENV, profileEnv);
                PropertiesHolder.setProperty(PROFILE_ENV, profileEnv);
            }
        }

        /**
         * 初始化根上下文
         */
        ApplicationContext applicationContext = initWebApplicationContext(event.getServletContext());

        /**
         * 存储根上下文
         */
        ApplicationContextHolder.setApplicationContext(applicationContext);

        /**
         * 线程本地存储部署目录绝对路径
         */
        DeployPathHolder.setDeployPath(event.getServletContext().getRealPath("") +
                File.separator + WEB_INF +
                File.separator + CLASSES +
                File.separator + CONFIG);

        /**
         * 加载使用kiwi框架的业务方配置文件
         */
        KiwiConfig.getInstance();
    }

}