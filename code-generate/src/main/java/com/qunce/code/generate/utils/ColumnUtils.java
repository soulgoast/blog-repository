package com.qunce.code.generate.utils;


import org.apache.commons.lang3.StringUtils;

public class ColumnUtils {
	public static String formatField(String columnName) {
		String as[] = columnName.split("_");
		columnName = "";
		int i = 0;
		
		for (int j = as.length; i < j; i++) {
			if (i > 0) {
				String s1 = as[i].toLowerCase();
				s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(s1.substring(1, s1.length())).toString();
				columnName = (new StringBuilder(String.valueOf(columnName))).append(s1).toString();
			} else {
				columnName = (new StringBuilder(String.valueOf(columnName))).append(as[i].toLowerCase()).toString();
			}
		}

		return columnName;
	}
	
	public static String convertFieldType(String columnType, String precision, String scale)
	{
		if (columnType.contains("char"))
			columnType = "java.lang.String";
		else
		if (columnType.equals("int"))
			columnType = "java.lang.Integer";
		else
			if (columnType.equals("bigint"))
				columnType = "java.lang.Long";
		else
		if (columnType.contains("float"))
			columnType = "java.lang.Float";
		else
		if (columnType.contains("double"))
			columnType = "java.lang.Double";
		else
		if (columnType.contains("number"))
		{
			if (StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0)
				columnType = "java.math.BigDecimal";
			else
			if (StringUtils.isNotBlank(precision) && Integer.parseInt(precision) > 10)
				columnType = "java.lang.Long";
			else
				columnType = "java.lang.Long";
		} else
		if (columnType.contains("decimal"))
			columnType = "BigDecimal";
		else
		if (columnType.contains("date"))
			columnType = "java.time.Instant";
		else
		if (columnType.contains("time"))
			columnType = "java.time.Instant";
		else
		if (columnType.contains("blob"))
			columnType = "byte[]";
		else
		if (columnType.contains("clob"))
			//columnType = "java.sql.Clob";
			columnType = "java.lang.String";
		else
		if (columnType.contains("numeric"))
			columnType = "BigDecimal";
		else
			columnType = "java.lang.String";
		return columnType;
	}
	public static String convertDbType(String columnType, Long collength) {
		String dbtyp = "";
		if (columnType.contains("char")) {
			dbtyp = columnType + "("+ collength +")";
		} else if (columnType.contains("int")) {
			dbtyp = columnType + "("+ collength +")";
		} else {
			dbtyp = columnType;
		}
		return  dbtyp;
	}
	public static String convertCriType(String columnType) {
		String critype = "";
		if (columnType.contains("date")) {
			critype = "InstantFilter";
		} else if (columnType.equals("int")) {
			critype = "LongFilter";
		} else if (columnType.equals("bigint")){
			critype = "LongFilter";
		} else {
			critype = "StringFilter";
		}
		return  critype;
	}

	public static String convertbldCri(String columnType) {
		String bldCri = "";
		if (columnType.contains("date")) {
			bldCri = "buildRangeSpecification";
		} else if (columnType.contains("int")) {
			bldCri = "buildRangeSpecification";
		} else {
			bldCri = "buildStringSpecification";
		}
		return  bldCri;
	}


	
	public static String convertFirstCapital(String str) {
		String s1 = str;
		s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(s1.substring(1, s1.length())).toString();
		return s1;
	}


}
