package com.java.study.designPattern.factory.complicated;

import com.java.study.designPattern.factory.simple.MailSender;
import com.java.study.designPattern.factory.simple.Sender;
import com.java.study.designPattern.factory.simple.SmsSender;

public class SendFactory2 {

    public Sender produceMail(){
        return new MailSender();
    }

    public Sender produceSms(){
        return new SmsSender();
    }
}
