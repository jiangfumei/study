package com.java.study.rabbitmq;

import com.java.study.BasicTestMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitControllerTest extends BasicTestMvc {

    @Autowired
    private SimpleSender simpleSender;

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public com.xy.goone.common.vo.Result simpleTest() throws InterruptedException {
        for(int i=0;i<10;i++){
            simpleSender.send();
            Thread.sleep(1000);
        }
        return com.java.study.util.ResultUtil.success(null);
    }

}
