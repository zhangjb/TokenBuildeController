package org.cics.demo2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class TokenBuildeController {
    private final AppConfig appConfig;
    private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    public TokenBuildeController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    // 创建日志实例
    private static final Logger logger = LoggerFactory.getLogger(TokenBuildeController.class);
    @GetMapping("/hello")
    public String sayHello() {
        logger.info(appConfig.getcloudHome() + ":Welcome to TokenBuildeController!");
        return appConfig.getcloudHome() + ":Welcome to TokenBuildeController!";
    }
    @GetMapping("/genToken")
    public  String getToken(@RequestParam(name = "uid", required = false, defaultValue = "dadmin")String userId){

        String token= null;
        try {
            token = RSAUtils.encrypt(sdf.format(new Date())+userId,appConfig.getcloudHome());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info(token);
        return  token;
    }



}
