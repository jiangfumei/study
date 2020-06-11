package com.java.study.designPattern.factory.staticfactory;

import com.java.study.designPattern.factory.simple.MailSender;
import com.java.study.designPattern.factory.simple.SmsSender;

public class SenderFactoryStatic {

    public static MailSender produceMail(){
        return new MailSender();
    }

    public static SmsSender produceSms(){
        return new SmsSender();
    }
}
