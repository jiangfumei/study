package com.java.study.designPattern.factory.ab;

import com.java.study.designPattern.factory.simple.Sender;
import com.java.study.designPattern.factory.simple.SmsSender;

public class SendSmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
