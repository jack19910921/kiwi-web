package org.kiwi.web.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/2/21.
 */
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void writeJsonResp(Object resp, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(resp));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
