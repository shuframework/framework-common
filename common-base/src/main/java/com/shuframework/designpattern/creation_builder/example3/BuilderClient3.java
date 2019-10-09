package com.shuframework.designpattern.creation_builder.example3;


public class BuilderClient3 {

    public static void main(String[] args) {
        //创建构建器
        InsuranceContract2.ConcreteBuilder builder = new InsuranceContract2.ConcreteBuilder("001", 12345L, 67890L);
        InsuranceContract2 contract = builder.setPersonName("张三")
                .setOtherData("test")
                .build();

        //操作保险合同对象的方法
        contract.someOperation();

        ///简化
        //直接构建 InsuranceContract2，然后调用set方法没什么特殊的地方，方便的地方在于可以链式操作
        InsuranceContract2 contract2 = new InsuranceContract2.ConcreteBuilder("002", 12345L, 67890L)
                .setPersonName("张三2")
                .setCompanyName("test2 company")
                .setOtherData("test2")
                .build();
        contract2.someOperation();

    }
}

