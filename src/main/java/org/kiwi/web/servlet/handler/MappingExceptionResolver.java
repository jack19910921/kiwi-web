package org.kiwi.web.servlet.handler;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
public class MappingExceptionResolver extends SimpleMappingExceptionResolver {

    // private final Logger logger = LoggerFactory.getLogger(MappingExceptionResolver.class);
    //
    // @Getter
    // @Setter
    // private MessageSource messageSource;
    //
    // @Override
    // protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
    //                                           Exception ex) {
    //
    //     if (ex instanceof BusinessException) {
    //         logger.warn("The business exception is caught and resolved", ex);
    //     }
    //     if (ex instanceof NoHandlerFoundException) {
    //         logger.warn("The business exception is caught and resolved", ex);
    //     }
    //     return null;
    // }

}
