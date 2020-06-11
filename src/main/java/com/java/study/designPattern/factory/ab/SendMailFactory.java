package com.java.study.designPattern.factory.ab;

import com.java.study.designPattern.factory.simple.MailSender;
import com.java.study.designPattern.factory.simple.Sender;

public class SendMailFactory implements Provider {


    @Override
    public Sender produce() {
        return new MailSender();
    }
}
