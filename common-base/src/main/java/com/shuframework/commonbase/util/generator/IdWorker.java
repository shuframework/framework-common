package com.shuframework.commonbase.util.generator;

import java.util.Date;

/**
 * Twitter的snowflake算法
 * 组成：1位符号位 + 41位时间戳（毫秒级） + 10位数据机器位（包括5位datacenterId和5位workerId） + 12位毫秒内的序列 = 64位
 * 返回的是64bit的long型数字
 *
 * @author shuheng
 */
public class IdWorker {
    //算法详解 https://segmentfault.com/a/1190000011282426

    private IdWorker() {
    }

    //内部类
    private static class InnerIdWorker {
        // 建议 workerId 根据不同的服务器来变动，
        private static final IdWorker instance = new IdWorker(1, 1);
    }

    public static IdWorker getInstance() {
        return InnerIdWorker.instance;
    }

    /**
     * 起始时间戳 2018-01-01
     * 截止到2019-10 产生18位，订单号是12位
     */
    private final long epoch = 1514736000000L;
//    //2019-01-01  截止到2019-10 产生18位，订单号是11位
//    private final long epoch = 1546272000000L;
//    //2008-01-01  截止到2019-10 产生19位，订单号是12位
//    private final long epoch = 1199116800000L;
//    //2006-01-01  截止到2019-10 产生19位，订单号是12位
//    private final long epoch = 1136044800000L;

    /**
     * 时间位取&
     */
    private static final long timeBit = 0b1111111111111111111111111111111111111111110000000000000000000000L;

    private static final long FIVE = 5L;

    /**
     * 机器ID所占的位数
     */
    private final long workerIdBits = FIVE;

    /**
     * 数据标识ID所占的位数
     */
    private final long dataCenterIdBits = FIVE;

    /**
     * 支持的最大机器ID,结果是31 (2^5-1,从0开始的)
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * 支持的最大数据标识ID,结果是31
     */
    private final long maxDataCenterId = ~(-1 << dataCenterIdBits);

    /**
     * 毫秒内序列在id中所占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识ID向左移17(12+5)位
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳向左移22(12+5+5)位
     */
    private final long timestampShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * 数据标识ID（0～31）
     */
    private long dataCenterId;

    /**
     * 机器ID（0～31）
     */
    private long workerId;

    /**
     * 毫秒内序列（0～4095）
     */
    private long sequence;

    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

//	private IdWorker(){
//		this(FIVE, FIVE);
////		this(5L, 5L);
//	}

    /**
     * dataCenterId,workerId的最大值都是31
     *
     * @param dataCenterId
     * @param workerId
     */
    private IdWorker(long dataCenterId, long workerId) {
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     * 返回的是64bit的long型数字
     *
     * @return snowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳,说明系统时钟回退过,这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = nextMillis(lastTimestamp);
            }
        } else {// 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        // 移位并通过按位或运算拼到一起组成64位的ID
        return ((timestamp - epoch) << timestampShift) | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    /**
     * 从ID中获取时间
     *
     * @param id 由此类生成的ID
     * @return
     */
    public Date getTime(long id) {
        return new Date(((timeBit & id) >> 22) + epoch);
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long nextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = lastTimestamp;
        }
        return timestamp;
    }


}
