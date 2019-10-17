package com.shuframework.commonbase.util.generator;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class IdWorkerTest {

    @Test
    public void nextId() {
        long id = IdWorker.getInstance().nextId();
        System.out.println(id);

        Date date = IdWorker.getInstance().getTime(id);
        System.out.println(date);
    }

    @Test
    public void getTime() {
    }
}