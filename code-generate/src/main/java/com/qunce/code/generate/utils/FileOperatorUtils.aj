package com.qunce.code.generate.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;

public class FileOperatorUtils {


    private static void writeData(String content, File file) throws Exception {
        if (file.exists()) {
            file.delete();
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        IOUtils.write(content, new FileOutputStream(file), "UTF-8");
    }
}
