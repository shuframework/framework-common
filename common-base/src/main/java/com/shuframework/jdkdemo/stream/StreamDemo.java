package com.shuframework.jdkdemo.stream;

import com.shuframework.jdkdemo.testmodel.BookInfo;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * list的 filter和map示例 其实在Stream Api里面已经提供了
 *
 * @author shuheng
 */
public class StreamDemo {

    List<BookInfo> bookInfoList;

    @Before
    public void init() {
        bookInfoList = InitData.initBookInfoList();
        System.out.println(bookInfoList);
    }

    @Test
    public void max_stream_test() {
        // 价格最高的一本书
        BookInfo bookInfo = bookInfoList.stream()
                .max(Comparator.comparing(BookInfo::getPrice))
                .get();
        System.out.println(bookInfo);
        System.out.println("==========");
        // 价格最高的一本书
        BookInfo bookInfo2 = bookInfoList.stream()
                .max(Comparator.comparing(BookInfo::getPrice).reversed())
                .get();
        System.out.println(bookInfo2);
        System.out.println("==========");
    }

    @Test
    public void sum_Double_stream_test() {
        double sum = bookInfoList.stream().mapToDouble(book -> book.getPrice()).sum();
        System.out.println(sum);
    }

    @Test
    public void sum_BigDecimal_stream_test() {
//        bookInfoList = new ArrayList<>();
        BigDecimal sum = bookInfoList.stream().map(BookInfo::getPriceBig)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }

}
