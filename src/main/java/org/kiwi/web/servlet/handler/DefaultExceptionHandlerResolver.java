package org.kiwi.web.servlet.handler;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
public class DefaultExceptionHandlerResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandlerResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        if (logger.isDebugEnabled()) {
            logger.debug("handle exception:[" + ex.getMessage() + "]", ex);
        }

        ModelAndView mv = new ModelAndView();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("success", Boolean.FALSE.toString());
            errorMap.put("msg", ex.getMessage());
            response.getWriter().write(JSON.toJSONString(errorMap));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return mv;
    }
}