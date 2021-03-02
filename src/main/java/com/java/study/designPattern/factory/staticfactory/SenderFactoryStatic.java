package com.java.study.designPattern.factory.staticfactory;

import com.java.study.designPattern.factory.simple.MailSender;
import com.java.study.designPattern.factory.simple.Sender;
import com.java.study.designPattern.factory.simple.SmsSender;

public class SenderFactoryStatic {

    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}
