package com.qunce.file;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class FileSuffix {


    @Test
    public void test() {
        String filname1 = "aa.svg";
        String filname2 = "aa.svg";
        String filname3 = "aa.svg";
        String filname4 = "aa.sv";

    }

    public void img(String originalFilename) {

        if (StringUtils.isBlank(originalFilename)) {
            throw new IllegalStateException("上传文件不存在");
        }
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        if (StringUtils.isBlank(substring) || !(originalFilename.endsWith("svg") || originalFilename.endsWith("png") || originalFilename.endsWith("jpg"))) {
            throw new IllegalStateException("上传文件非法");
        }

    }

}
