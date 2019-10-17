package com.shuframework.designpattern.mediator.example3;

/**
 * 伊朗
 *
 * @author shuheng
 */
public class Iran extends AbstractCountry {

    public Iran(UnitedNations mediator) {
        super(mediator, "iran");
    }

    public void getMessage(String message) {
        System.out.println("伊朗获得对方信息: " + message);
    }

}
