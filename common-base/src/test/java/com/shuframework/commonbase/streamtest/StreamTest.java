package com.shuframework.commonbase.streamtest;

import com.shuframework.commonbase.util.lang.DateUtil;
import com.shuframework.jdkdemo.testmodel.BookInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shuheng
 */
public class StreamTest {

    List<BookInfo> list = new ArrayList<>();

    @Before
    public void init(){
        BookInfo bookInfo1 = new BookInfo();
        bookInfo1.setId(1);
        bookInfo1.setName("aa");
        bookInfo1.setCreateTime(DateUtil.strToDateShort("2019-07-24"));

        BookInfo bookInfo2 = new BookInfo();
        bookInfo2.setId(2);
        bookInfo2.setName("bb");
        bookInfo2.setCreateTime(DateUtil.strToDateShort("2019-07-26"));

        BookInfo bookInfo3 = new BookInfo();
        bookInfo3.setId(3);
        bookInfo3.setName("cc");
        bookInfo3.setCreateTime(DateUtil.strToDateShort("2019-07-25"));

        list.add(bookInfo1);
        list.add(bookInfo2);
        list.add(bookInfo3);
        System.out.println(list);
    }


    @Test
    public void max_time_test(){
//        List<BookInfo> sortList = list.stream().sorted(Comparator.comparing(BookInfo::getCreateTime).reversed()).collect(Collectors.toList());
//        System.out.println(sortList);
        BookInfo maxBook = list.stream().sorted(Comparator.comparing(BookInfo::getCreateTime).reversed()).findFirst().orElse(null);
        System.out.println(maxBook);
    }


    @Test
    public void test(){
//        //并发修改异常：java.util.ConcurrentModificationException
//        for (BookInfo book : list){
//            if ("bb".equals(book.getName())){
//                list.remove(book);
//            }
//        }
        for (int i = 0, max = list.size(); i < max; i++) {
            BookInfo book = list.get(i);
            if ("bb".equals(book.getName())){
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}
