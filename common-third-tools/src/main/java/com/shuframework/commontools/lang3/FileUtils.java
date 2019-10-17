package com.shuframework.commontools.lang3;

import com.shuframework.commontools.codec.DigestUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author shuheng
 */
public class FileUtils extends IOUtils {

    /**
     * 通过文件大小和内容 进行文件md5
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String path1 = "F:\\扩展书籍\\01--实用性阅读指南.pdf";
//        String path2 = "F:\\扩展书籍\\00--如何阅读一本书\\01--实用性阅读指南.pdf";
        String path2 = "F:\\扩展书籍\\00--如何阅读一本书\\05--如何阅读一本文学书-[美]托马斯·福斯特.pdf";

        FileInputStream input1 = new FileInputStream(path1);
        FileInputStream input2 = new FileInputStream(path2);

        String md5Hex1 = DigestUtil.md5Hex(IOUtils.toByteArray(input1));
        String md5Hex2 = DigestUtil.md5Hex(IOUtils.toByteArray(input2));

        System.out.println(md5Hex1);
        System.out.println(md5Hex2);
        System.out.println(md5Hex1.equals(md5Hex2));
    }

}
