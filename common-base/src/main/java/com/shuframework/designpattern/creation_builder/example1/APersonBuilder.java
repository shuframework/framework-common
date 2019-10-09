package com.shuframework.designpattern.creation_builder.example1;

/**
 * @author shuheng
 */
public class APersonBuilder implements PersonBuild {

    @Override
    public void buildHead(String head) {
        // 实际过程中，这里的指导过程可能会很复杂
        System.out.println("person A 的头：" + head);
    }

    @Override
    public void buildBody(String body) {
        System.out.println("person A 的身体：" + body);
    }

    @Override
    public void buildFoot(String foot) {
        System.out.println("person A 的脚：" + foot);
    }
}
