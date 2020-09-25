package com.qunce.sharding.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Course
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/24 17:01
 * @ModifyDate 2020/9/24 17:01
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Course {

    private Long cid;
    private String cname;
    private Long userId;
    private String status;
}
