package com.shuframework.commontools;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.shuframework.commonbase.enums.FailureEnum;
import com.shuframework.commonbase.result.Result;
import com.shuframework.commonbase.result.ResultUtil;
import com.shuframework.commontools.json.JsonUtil;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 七牛云文件服务器 工具类
 * 官方接口文档 https://developer.qiniu.com/kodo/sdk/1239/java#simple-uptoken
 *
 * @author shuheng
 */
public class QiniuUtil {

    public static final String BUCKET_DEFAULT = "file-picture";
    public static final String URL_PREFIX = "http://pn482tmb0.bkt.clouddn.com/";


    /**
     * 获得图片存储token
     *
     * @return
     */
    public static String getToken(String bucketName) {
        Auth auth = getAuth();
        String token = auth.uploadToken(bucketName);
        return token;
    }

    /**
     * 获得图片存储token
     * 如果有key 表示的是这个文件对应的token，没有key表示是这个空间名称(bucketName)的token
     *
     * @return
     */
    public static String getToken(String bucketName, String key) {
        Auth auth = getAuth();
        String token = auth.uploadToken(bucketName, key);
        return token;
    }


    /**
     * 上传方法
     *
     * @param filePath   文件完整路径
     * @param key        上传到七牛上的文件的名称 （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称
     */
    public static Result upload(String filePath, String key, String bucketName) {
        return upload(new File(filePath), key, bucketName);
    }

    /**
     * 上传方法
     * 底层使用的是FormUploader 或ResumeUploader
     *
     * @param file       文件
     * @param key        上传到七牛上的文件的名称 （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称
     */
    public static Result upload(File file, String key, String bucketName) {
        Result result = null;
        UploadManager uploadManager = getUploadManager();
        try {
            // 调用put方法上传
            Response res = uploadManager.put(file, key, getToken(bucketName));
            // 打印返回的信息
            System.out.println(res.bodyString());
            result = ResultUtil.success(getResult(res.bodyString()));
        } catch (QiniuException e) {
            result = getFailureResult(e);
        }
        return result;
    }

    //底层使用的是StreamUploader
    public static Result upload(InputStream input, String key, String bucketName) {
        Result result = null;
        UploadManager uploadManager = getUploadManager();
        try {
            // 调用put方法上传
            Response res = uploadManager.put(input, key, getToken(bucketName), null, null);
            // 打印返回的信息
            System.out.println(res.bodyString());
            result = ResultUtil.success(getResult(res.bodyString()));
        } catch (QiniuException e) {
            result = getFailureResult(e);
        }
        return result;
    }

    //todo 断点续上传 breakpointUpload

    //todo 文件下载到本地，一般直接访问 url（在线预览）
//    public void download(String targetUrl) {
//        String downloadUrl = getDownloadUrl(targetUrl);
//        System.out.println(downloadUrl);
//        //本地保存路径
//        String filePath = "D:/temp/picture/";
//        download(downloadUrl, filePath);
//    }
//
//    private String getDownloadUrl(String targetUrl) {
//        String downloadUrl = getAuth().privateDownloadUrl(targetUrl);
//        return downloadUrl;
//    }


    /**
     * 删除bucket中的文件名称
     *
     * @param bucketName bucker名称
     * @param key        文件名称,不是全路径
     */
    public static Result delete(String bucketName, String key) {
        Result result = ResultUtil.success();
        try {
            BucketManager mg = getBucketManager();
            Response res = mg.delete(bucketName, key);
            System.out.println(res.bodyString());
            result.setData(res.bodyString());
        } catch (QiniuException e) {
            result = getFailureResult(e);
        }
        return result;
    }

    /**
     * 获取bucket里面所有文件的信息
     *
     * @param bucketName
     */
    public static void getFileInfo(String bucketName) {
        BucketManager bucketManager = getBucketManager();
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucketName, prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);
            }
        }
    }

    /**
     * 获取所有的bucket
     */
    public static void getBucketsInfo() {
        try {
            BucketManager bucketManager = getBucketManager();
            //获取所有的bucket信息
            String[] bucketNms = bucketManager.buckets();
            for (String bucket : bucketNms) {
                System.out.println(bucket);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 删除多个文件
//     * @param bucketNm bucket的名称
//     * @param keys     文件名称数组
//     * //单次批量请求的文件数量不得超过1000 , 这个是七牛所规定的
//     * @return
//     */
//    public static Result deletes(String bucketNm ,String [] keys) {
//        Result result = null;
//        try {
//            //当文件大于1000的时候，就直接不处理
//            if(keys.length >1000) {
//                return new Result(false);
//            }
//
//            //设定删除的数据
//            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
//            batchOperations.addDeleteOp(bucketNm, keys);
//            BucketManager bucketManager = getBucketManager();
//            //发送请求
//            Response response = bucketManager.batch(batchOperations);
//
//            //返回数据
//            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
//            for (int i = 0; i < keys.length; i++) {
//                BatchStatus status = batchStatusList[i];
//                String key = keys[i];
//                System.out.print(key + "\t");
//                if (status.code == 200) {
//                    System.out.println("delete success");
//                } else {
//                    System.out.println(status.data.error);
//                    return new Result(false);
//                }
//            }
//            result = new Result(true);
//        }catch (Exception e) {
//            result = new Result(false);
//        }
//        return result;
//    }


    /**
     * 个人账户的密钥，如果是企业用户更换下即可
     */
    private static Auth getAuth() {
        String accessKey = "ZwVBDBXbROid4fYR6qu7m1tERiY0Tz4hBWNNkR9a";
        String secKey = "N2wEeTZTPzk3SZ7uDbISvq87CBx6BR4UyIHvK3VN";
        return Auth.create(accessKey, secKey);
    }

    private static Configuration getConfiguration() {
        //区域要和自己的bucket对上，不然就上传不成功
        //华东    Zone.zone0()
        //华北    Zone.zone1()
        //华南    Zone.zone2()
        //北美    Zone.zoneNa0()
        return new Configuration(Zone.zone0());
    }

    /**
     * 获取上传管理器
     */
    private static UploadManager getUploadManager() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = getConfiguration();
        UploadManager uploadManager = new UploadManager(cfg);
        return uploadManager;
    }

    /**
     * 获取Bucket的管理对象
     */
    private static BucketManager getBucketManager() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = getConfiguration();
        BucketManager bucketManager = new BucketManager(getAuth(), cfg);
        return bucketManager;
    }


    private static Map getResult(String bodyString) {
        Map map = JsonUtil.jsonStr2Map(bodyString);
        String key = map.get("key").toString();
        if (key != null) {
            map.put("filePath", URL_PREFIX + key);
        }
        return map;
    }

    private static Result getFailureResult(QiniuException e) {
        Response response = e.response;
        String msg = response.error;
        // 请求失败时打印的异常的信息
        System.out.println(response.toString());
        return ResultUtil.failure(FailureEnum.BUSINESS_FLOW_FAILURE, msg, response.toString());
    }

}
