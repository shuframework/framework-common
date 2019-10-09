package com.shuframework.designpattern.creation_builder.example3;


/**
 * 保险合同的对象
 */
public class InsuranceContract2 {

    /**
     * 保险合同编号
     */
    private String contractId;
    /**
     * 被保险人员的名称，同一份保险合同，要么跟人员签订，要么跟公司签订，
     * 也就是说，"被保险人员"和"被保险公司"这两个属性，不可能同时有值
     */
    private String personName;
    /**
     * 被保险公司的名称
     */
    private String companyName;
    /**
     * 保险开始生效的日期
     */
    private long beginDate;
    /**
     * 保险失效的日期，一定会大于保险开始生效的日期
     */
    private long endDate;
    /**
     * 示例：其它数据
     */
    private String otherData;

    /**
     * 构造方法，访问级别是同包能访问
     * 将构造器的数据赋值给对象的属性
     */
    InsuranceContract2(ConcreteBuilder builder) {
        //2种效果一样
        this.contractId = builder.contractId;
//        this.contractId = builder.getContractId();
        this.personName = builder.getPersonName();
        this.companyName = builder.getCompanyName();
        this.beginDate = builder.getBeginDate();
        this.endDate = builder.getEndDate();
        this.otherData = builder.getOtherData();
    }

    /**
     * 示意：保险合同的某些操作
     */
    public void someOperation() {
        System.out.println("Insurance Contract2 someOperation==" + this.contractId + ",personName=" + this.personName + " ,cmpanyName=" + this.companyName);
    }


    /**
     * 内部类的 构造器
     * 与对象直接构建的区别：set方法没什么特殊的地方，方便的地方在于可以链式操作
     */
    public static class ConcreteBuilder {

        private String contractId;
        private String personName;
        private String companyName;
        private long beginDate;
        private long endDate;
        private String otherData;

        public ConcreteBuilder(String contractId, long beginDate, long endDate) {
            this.contractId = contractId;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }

        /**
         * 选填数据，被保险人员的名称
         *
         * @param personName 被保险人员的名称
         * @return 构建器对象
         */
        public ConcreteBuilder setPersonName(String personName) {
            this.personName = personName;
            return this;
        }

        public ConcreteBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public ConcreteBuilder setOtherData(String otherData) {
            this.otherData = otherData;
            return this;
        }

        /**
         * 构建真正的对象并返回
         *
         * @return 构建的保险合同的对象
         */
        public InsuranceContract2 build() {
            //如果构建过程有限制 可以在这里进行限制
            // dosome check
            return new InsuranceContract2(this);
        }

        public String getContractId() {
            return contractId;
        }

        public String getPersonName() {
            return personName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public long getBeginDate() {
            return beginDate;
        }

        public long getEndDate() {
            return endDate;
        }

        public String getOtherData() {
            return otherData;
        }
    }
}
