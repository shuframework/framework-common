package com.shuframework.commoncore.base;

import com.shuframework.commonbase.result.PageConvert;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO extends PageConvert {

    //已在分页条件类（PageConvert）处理了
//    private Integer pageSize;
//    private Integer pageIndex;

//////公共的功能条件
    /** 开始时间 */
    private Date beginCreateTime;
    /** 结束时间 */
    private Date endCreateTime;
    private Long menuId;
    private Long cityId;
    private String cityName;
//    private Long enterpriseId;
    private Long deptId;

}