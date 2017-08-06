package org.kiwi.web.annotation;

import java.lang.annotation.*;

/**
 * throw new BeanValidationException defined by the value of this annotation when the BindingResult have the error
 *
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER})
public @interface ResolveError {

    String value() default "";
}
