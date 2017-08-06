package org.kiwi.web.framework.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Synonym for {@link org.springframework.stereotype.Controller Controller} which define that the controller is provided
 * by the framework
 * Framework controller will be matched with a {@link org.kiwi.web.framework.FrameworkControllerHandlerMapping} in servlet context
 * </p>
 *
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FrameworkController {

    /**
     * as same as {@link org.springframework.stereotype.Controller#value()}
     *
     * @return
     */
    String value() default "";
}
