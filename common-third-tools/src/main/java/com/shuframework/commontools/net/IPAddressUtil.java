package com.shuframework.commontools.net;

import com.shuframework.commonbase.util.SystemUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP的工具类
 *
 * @author shuheng
 */
public class IPAddressUtil extends sun.net.util.IPAddressUtil {

    /**
     * 获取访问者IP
     * <p/>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p/>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()
     *
     * @param request
     */
    public static String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if (SystemUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (SystemUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 是否内网ip
     * @param ip
     */
    public static boolean isInternalIP(String ip) {
        //是不是ip4
        if(isIPv4LiteralAddress(ip)){
//            byte[] addr = textToNumericFormatV4(ip);
            return isInternalIPV4(textToNumericFormatV4(ip));
        }
        //是不是ip6，ip v6默认为内网
        if(isIPv6LiteralAddress(ip)){
            return true;
        }
        return false;
    }

    /**
     * 是不是内网 ip v4
     * @param ip
     */
    public static boolean isInternalIPV4(byte[] ip) {
        if (ip.length != 4) {
            throw new RuntimeException("illegal ipv4 bytes");
        }

        //10.0.0.0~10.255.255.255
        //172.16.0.0~172.31.255.255
        //192.168.0.0~192.168.255.255
        if (ip[0] == (byte) 10) {
            return true;
        } else if (ip[0] == (byte) 172) {
            if (ip[1] >= (byte) 16 && ip[1] <= (byte) 31) {
                return true;
            }
        } else if (ip[0] == (byte) 192) {
            if (ip[1] == (byte) 168) {
                return true;
            }
        }
        return false;
    }

}
