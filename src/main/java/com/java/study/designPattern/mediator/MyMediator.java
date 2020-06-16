package com.java.study.designPattern.mediator;

public class MyMediator implements Mediator {

    private User user1;

    private User user2;
    @Override
    public void createMediator() {
        User1 user1 = new User1(this);
        User2 user2 = new User2(this);
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();
    }
}
