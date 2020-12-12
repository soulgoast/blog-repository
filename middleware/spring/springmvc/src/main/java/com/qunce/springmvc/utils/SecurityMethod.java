package com.qunce.springmvc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public enum SecurityMethod {

    NULL,

    AES, RSA, DES, DES3,

    SHA1, MD5;

    public static SecurityMethod getValue(String value) {
        for (SecurityMethod r : SecurityMethod.values()) {
            if (StringUtils.equals(value, r.name())) {
                return r;
            }
        }
        return NULL;
    }

}
