package com.qunce.socket;

import com.qunce.datasecurity.DesUtil;
import org.junit.jupiter.api.Test;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class DesDemo {

    public static final String DES_KEY = "181D244714EE2A1A4E1E6C344359D89D181D244714EE2A1A";

    public static void main(String[] args) {
        String data = "[####*868807049006736*]";
        data = "88573145FD4EBB941AE6414E2B41F46E1C3A821300FBD2818B27C6EFCF97303EACE8709CC67A0ECFEE6B5ACEBEB0A0A150BDDA1AD92B1092";
        String result = DesUtil.decrypt(DES_KEY, data);
        System.out.println(result);

        data = "CS01*868807049006736*001E*UPLOAD,20200423142625I0506,600,OK";
        data = DesUtil.encrypt(DES_KEY, data);
        data = "[####*868807049006736*" + data + "]";
        System.out.println(data);
    }

    @Test
    public void encode() {


    }

}
