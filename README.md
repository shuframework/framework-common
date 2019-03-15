# framework-common 

公共服务模块,framework-common 是个父模块,含common-third-tools(第三方工具类)、common-core(业务核心部分)、common-starter(微服务集成部分)

## common-third-tools 模块

| 功能说明   |  jar  |
| ----- | ----- |
| bean、map的反射操作 | BeanUtil |
| 加密 | base64、md5、sha1、sha256 |
| Excel导出 | poi |
| json | gson、fastjson、jackson |
| 邮件 | mail 1.4.7 |
| 客户端模拟http | HttpClientUtil |
| ftp | commons-net |
| 文件上传（云文件） | qiniu |


//todo 集合工具类：
    commons-collections (Apache)
    google-collections (Google)
    
    
## common-core 模块 

common-core是业务核心模块, 配置按需常用的业务数据（尽量不依赖第三方jar）


    
## common-starter 模块 

common-starter是个父模块, 配置按需常用的服务

### tools-starter-excel 


### tools-starter-xml 


//todo 
    极光推送、短信