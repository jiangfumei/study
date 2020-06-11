package com.java.study.designPattern.factory;

import com.java.study.designPattern.factory.ab.Provider;
import com.java.study.designPattern.factory.ab.SendSmsFactory;
import com.java.study.designPattern.factory.complicated.SendFactory2;
import com.java.study.designPattern.factory.simple.SendFactory;
import com.java.study.designPattern.factory.simple.Sender;
import com.java.study.designPattern.factory.staticfactory.SenderFactoryStatic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendFactoryTest {

    @Test
    public void send(){
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produce("sms");
        sender.send();
    }

    @Test
    public void sender(){
        SendFactory2 sendFactory = new SendFactory2();
        Sender sender = sendFactory.produceMail();
    }

    @Test
    public void staticSend(){
       Sender sender = SenderFactoryStatic.produceSms();
       sender.send();
    }

    @Test
    public void abSend(){
        Provider provider = new SendSmsFactory();
        Sender sender = provider.produce();
        sender.send();
    }

}