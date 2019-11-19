package com.shuframework.designpattern.composite.example1;

/**
 * @author shuheng
 */
public abstract class Component {

    public abstract void someOperation();

    public void addChild(Component child) {
        throw new UnsupportedOperationException("对象不支持这个功能");
    }

    public void removeChild(Component child) {
        throw new UnsupportedOperationException("对象不支持这个功能");
    }

    public Component getChild(int index) {
        throw new UnsupportedOperationException("对象不支持这个功能");
    }

    public abstract void print();

}
