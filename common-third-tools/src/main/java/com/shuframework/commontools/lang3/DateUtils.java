package com.shuframework.commontools.lang3;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author shuheng
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String format(long millis, String pattern) {
        return DateFormatUtils.format(millis, pattern);
    }

}
