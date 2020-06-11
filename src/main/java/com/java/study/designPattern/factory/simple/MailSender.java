package com.java.study.designPattern.factory.simple;

public class MailSender implements Sender{
    @Override
    public void send() {
        System.out.println("this is mailSender");
    }
}
