package org.kiwi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
public class HealthCheckController {

    @RequestMapping(value = "/_health_check", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
