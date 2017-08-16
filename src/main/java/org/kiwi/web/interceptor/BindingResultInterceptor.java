package org.kiwi.web.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.kiwi.web.annotation.ResolveError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.lang.annotation.Annotation;

/**
 * <p>
 * if BindingResult was comment with @ResolveError, it will be checked by BindingResultResolverInterceptor
 * </p>
 *
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
public class BindingResultInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BindingResultInterceptor.class);


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long before = System.currentTimeMillis();
        Object[] args = invocation.getArguments();
        Annotation[][] annos = invocation.getMethod().getParameterAnnotations();
        for (int i = 0; i < annos.length; i++) {
            for (int j = 0; j < annos[i].length; j++) {
                if (annos[i][j].annotationType().isAssignableFrom(ResolveError.class)
                        && args[i] instanceof BindingResult) {
                    BindingResult bindingResult = (BindingResult) args[i];
                    if (bindingResult.hasErrors()) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("///  Method: " + invocation.getMethod().getName() + " /// Annotation: " + annos[i][j].annotationType().getSimpleName() + " /// Argument:  " + args[i]);
                            logger.debug("///  Before throwing costs " + ((System.currentTimeMillis() - before) / 1000d) + " ms");
                        }
                        // TODO: 17/8/16  
                        // throw new BeanValidationException(bindingResult);
                    }
                }
            }
        }
        Object object = invocation.proceed();
        if (logger.isDebugEnabled()) {
            logger.debug("///  Before return costs " + ((System.currentTimeMillis() - before) / 1000d) + " ms");
        }
        return object;
    }

}
