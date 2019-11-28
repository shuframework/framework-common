package com.shuframework.commonbase.util.lang;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author shuheng
 */
public class BigDecimalUtilTest {

    @Test
    public void pow_test3() {
        Long n = 1L;
        System.out.println(String.valueOf(n));
    }

    @Test
    public void pow_test2() {
        BigDecimal num1 = new BigDecimal("2");
//        BigDecimal num2 = new BigDecimal("3");
        BigDecimal result = num1.pow(3);
        System.out.println(result);
    }
    @Test
    public void compare_test2() {
        BigDecimal num1 = new BigDecimal("0");
        if(num1.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }

    @Test
    public void compare_test() {
        BigDecimal num1 = new BigDecimal("0.4");
        BigDecimal num2 = new BigDecimal("0.5");
        BigDecimal num3 = new BigDecimal("0.6");
        boolean flag1 = BigDecimalUtil.compare(num1, num2);
        boolean flag2 = BigDecimalUtil.compare(num2, num1);
        boolean flag3 = BigDecimalUtil.compare(num2, num3);
        System.out.println("num1 > num2,结果：" + flag1);
        System.out.println("num2 > num1,结果：" + flag2);
        System.out.println("num2 > num3,结果：" + flag3);
    }

    @Test
    public void up_test() {
        BigDecimal num1 = new BigDecimal("0.4");
        up(num1);
        System.out.println("============");
        BigDecimal num2 = new BigDecimal("0.5");
        up(num2);
        System.out.println("============");
        BigDecimal num3 = new BigDecimal("0.6");
        up(num3);

        System.out.println("============");
        BigDecimal num11 = new BigDecimal("-0.4");
        up(num11);
        System.out.println("============");
        BigDecimal num12 = new BigDecimal("-0.5");
        up(num12);
        System.out.println("============");
        BigDecimal num13 = new BigDecimal("-0.6");
        up(num13);
    }

    private void up(BigDecimal num) {
        BigDecimal r1 = num.divide(BigDecimal.ONE, 0, BigDecimal.ROUND_CEILING);
        System.out.println(num+" ,ceil:"+r1);
        BigDecimal r2 = num.divide(BigDecimal.ONE, 0, BigDecimal.ROUND_UP);
        System.out.println(num+" ,up:"+r2);
    }

    private void down(BigDecimal num) {
        BigDecimal r1 = num.divide(BigDecimal.ONE, 0, BigDecimal.ROUND_FLOOR);
        System.out.println(num+" ,floor:"+r1);
        BigDecimal r2 = num.divide(BigDecimal.ONE, 0, BigDecimal.ROUND_DOWN);
        System.out.println(num+" ,down:"+r2);
    }


    @Test
    public void ceil_test1() {
        BigDecimal num1 = BigDecimalUtil.ceil(new BigDecimal("0.4"));
        System.out.println(num1);
    }

    @Test
    public void floor_test2() {
        BigDecimal num1 = new BigDecimal("0.4");
        down(num1);
        System.out.println("============");
        BigDecimal num2 = new BigDecimal("0.5");
        down(num2);
        System.out.println("============");
        BigDecimal num3 = new BigDecimal("0.6");
        down(num3);

        System.out.println("============");
        BigDecimal num11 = new BigDecimal("-0.4");
        down(num11);
        System.out.println("============");
        BigDecimal num12 = new BigDecimal("-0.5");
        down(num12);
        System.out.println("============");
        BigDecimal num13 = new BigDecimal("-0.6");
        down(num13);
    }


    @Test
    public void round_test1() {
        //ROUND_HALF_DOWN 若舍弃部分>.5进位
        BigDecimal num = new BigDecimal("0.5");
        System.out.println(num.setScale(0, BigDecimal.ROUND_HALF_DOWN)); // 0
        System.out.println(num.setScale(0, BigDecimal.ROUND_HALF_UP)); // 1

        BigDecimal num2 = new BigDecimal("0.50");
        System.out.println(num2.setScale(0, BigDecimal.ROUND_HALF_DOWN)); // 0
        System.out.println(num2.setScale(0, BigDecimal.ROUND_HALF_UP)); // 1

        BigDecimal num3 = new BigDecimal("0.51");
        System.out.println(num3.setScale(0, BigDecimal.ROUND_HALF_DOWN)); // 1
        System.out.println(num3.setScale(0, BigDecimal.ROUND_HALF_UP)); // 1
    }
    @Test
    public void round_test2() {
        BigDecimal num = new BigDecimal("3.46150");
        System.out.println(num.setScale(1, BigDecimal.ROUND_HALF_DOWN)); // 3.5
        System.out.println(num.setScale(1, BigDecimal.ROUND_HALF_UP)); // 3.5
        System.out.println(num.setScale(2, BigDecimal.ROUND_HALF_DOWN)); // 3.46
        System.out.println(num.setScale(2, BigDecimal.ROUND_HALF_UP)); // 3.46
        //若舍弃部分>.5进位
        System.out.println(num.setScale(3, BigDecimal.ROUND_HALF_DOWN)); // 3.461
        System.out.println(num.setScale(3, BigDecimal.ROUND_HALF_UP)); // 3.462
    }

    @Test
    public void round_half_even_test1() {
        BigDecimal num = new BigDecimal("3.46159");
        // 如果舍弃部分左边的数字为偶数，则作 ROUND_HALF_DOWN
        System.out.println(num.setScale(1, BigDecimal.ROUND_HALF_EVEN)); // 3.5
        System.out.println(num.setScale(2, BigDecimal.ROUND_HALF_EVEN)); // 3.46

        BigDecimal num2 = new BigDecimal("3.37459");
        // 如果舍弃部分左边的数字为奇数，则作 ROUND_HALF_UP
        System.out.println(num2.setScale(1, BigDecimal.ROUND_HALF_EVEN)); // 3.4
        System.out.println(num2.setScale(2, BigDecimal.ROUND_HALF_EVEN)); // 3.37

    }
}