package com.shuframework.commonbase.util.generator;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class IdWorkerTest {

    @Test
    public void nextId() {
        for (int i = 0; i < 50; i++) {
            long id = IdWorker.getInstance().nextId();
            System.out.println(id);
        }
    }

    @Test
    public void nextId2() {
        long id = IdWorker.getInstance().nextId();
        System.out.println(id);

        Date date = IdWorker.getInstance().getTime(id);
        System.out.println(date);
    }


    @Test
    public void nextId2Str() {
        String id = IdGenerator.getInstance().nextId();
        System.out.println(id);

    }

    @Test
    public void getTime() {
    }
}