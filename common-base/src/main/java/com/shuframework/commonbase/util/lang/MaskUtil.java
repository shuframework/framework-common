package com.shuframework.commonbase.util.lang;

/**
 * 脱敏处理
 *
 * @author shuheng
 */
public class MaskUtil {

    /**
     * 手机号码前三后四脱敏
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (StringUtil.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtil.isEmpty(email)) {
            return email;
        }
        return email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
    }

    /**
     * 身份证 显示前6位 和后4位, 其他部分隐藏
     *
     * @param id
     * @return
     */
    public static String maskIdCardNo(String id) {
        if (StringUtil.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
//        return id.replaceAll("(?<=\\w{6})\\w(?=\\w{4})", "*");
//        // 只显示前6位
        return id.replaceAll("(?<=\\d{6})\\w", "*");
    }

    /**
     * 地址 显示前6位
     *
     * @param address
     * @return
     */
    public static String maskAddress(String address) {
        if (StringUtil.isEmpty(address) || (address.length() < 8)) {
            return address;
        }
//        return address.replaceAll("(?<=\\w{6})\\w", "*");
        // 截取字符串
        return "";
    }

}
