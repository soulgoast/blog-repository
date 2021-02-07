package com.qunce.code.generate.utils;

public class ObjStrUtls {

    public static final char UNDERLINE = '_';
    /**
     * @Description tableName 转Obj
     * @Param [tblNam]
     * @return java.lang.String
     **/
    public static String getObjNam(String tblNam){
        if (tblNam == null || "".equals(tblNam.trim())) {
            return "";
        }
        int len = tblNam.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = tblNam.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(tblNam.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String getObjCNam(String objNam){
        String s = String.valueOf(Character.toUpperCase(objNam.charAt(0)));
        return s + objNam.substring(1);
    }

    public static void main(String[] args) {
        String tblName = "pay_merchant_info";
        String objNam = getObjNam(tblName);
        String objCNam = getObjCNam(objNam);
        System.out.println(objNam);
        System.out.println(objCNam);
        String sql = "SELECT  COLUMN_NAME as 'columnName',COLUMN_COMMENT as 'columnComment' , "
                + "DATA_TYPE as 'columnType',CHARACTER_MAXIMUM_LENGTH as 'columnLength', "
                + "NUMERIC_PRECISION as 'columnPrecision',NUMERIC_SCALE AS 'columnScale' "
                + "FROM information_schema.`COLUMNS` where TABLE_NAME = ? ";
        System.out.println(sql);

    }
}
