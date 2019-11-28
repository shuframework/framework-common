package com.shuframework.jdkdemo.stream;

import com.shuframework.jdkdemo.testmodel.BookInfo;
import com.shuframework.jdkdemo.testmodel.SubBookInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shuheng
 */
public class CollectTest {

    @Test
    public void test_list_extends(){

        SubBookInfo book1 = new SubBookInfo();
        book1.setTitle("title book1");
        book1.setId(1);
        book1.setName("book1");
        SubBookInfo book2 = new SubBookInfo();
        book2.setTitle("title book2");
        book2.setId(2);
        book2.setName("book2");

        List<SubBookInfo> subBookInfoList = new ArrayList<>();
        subBookInfoList.add(book1);
        subBookInfoList.add(book2);
        show(subBookInfoList);

    }

    private void show(List<? extends BookInfo> list) {
        for (Object book : list) {
            if (book instanceof SubBookInfo) {
                SubBookInfo subbook = (SubBookInfo) book;
                System.out.println("id:" + subbook.getId());
                System.out.println("title:" + subbook.getTitle());
                System.out.println(subbook);
            }
        }
        for (int i = 0, max = list.size(); i < max; i++) {
            BookInfo book = list.get(i);// 多态
            System.out.println("id:" + book.getId());
            // 实际调用时 需要具体的子类对象
//                System.out.println("title:" + book.getTitle());
                System.out.println(book);
        }
    }
//    private void show2(List<? super BookInfo> list) {
//        for (Object book : list) {
//            if (book instanceof SubBookInfo) {
//                SubBookInfo subbook = (SubBookInfo) book;
//                System.out.println("id:" + subbook.getId());
//                System.out.println(subbook);
//            }
//        }
//    }

}
