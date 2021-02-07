package com.qunce.code.generate.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CodeDateUtils
{
  public static final String DATESTYLE = "yyyyMMddHHmmss";
  public static final String DATESTYLE_EX = "yyyy-MM-dd_HH-mm-ss";
  public static final String DATESTYLE_ = "yyyy-MM-dd";
  public static final String DATESTYLE_YEAR_MONTH = "yyyyMM";
  public static final String DATESTYLE_SHORT = "yyyyMMdd";
  public static final String DATESTYLE_SHORT_EX = "yyyy/MM/dd";
  public static final String DATESTYLE_YEAR_MONTH_EX = "yyyy/MM";
  public static final String DATESTYLE_DETAIL = "yyyyMMddHHmmssSSS";

  public static String dateToString(Date paramDate)
  {
    if (paramDate == null)
      return "";
    return FormatDate(paramDate, "yyyy-MM-dd HH:mm:ss");
  }

  public static String dateToStringShort(Date paramDate)
  {
    if (paramDate == null)
      return "";
    return FormatDate(paramDate, "yyyy-MM-dd");
  }

  public static long diffTwoDate(Date paramDate1, Date paramDate2)
  {
    long l1 = paramDate1.getTime();
    long l2 = paramDate2.getTime();
    return l1 - l2;
  }

  public static int diffTwoDateDay(Date paramDate1, Date paramDate2)
  {
    long l1 = paramDate1.getTime();
    long l2 = paramDate2.getTime();
    int i = Integer.parseInt(String.valueOf((l1 - l2) / 3600L / 24L / 1000L));
    return i;
  }

  public static String FormatDate(Date paramDate, String paramString)
  {
    if (paramDate == null)
      return "";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
    return localSimpleDateFormat.format(paramDate);
  }

  public static String getCurrDate()
  {
    Date localDate = new Date();
    return FormatDate(localDate, "yyyy-MM-dd");
  }

  public static Date getCurrDateTime()
  {
    return new Date(System.currentTimeMillis());
  }

  public static String getCurrTime()
  {
    Date localDate = new Date();
    return FormatDate(localDate, "yyyy-MM-dd HH:mm:ss");
  }

  public static String getDate10to8(String paramString)
  {
    String str1 = paramString.substring(0, 4);
    String str2 = paramString.substring(5, 7);
    String str3 = paramString.substring(8, 10);
    return str1 + str2 + str3;
  }

  public static String getDate8to10(String paramString)
  {
    String str1 = paramString.substring(0, 4);
    String str2 = paramString.substring(4, 6);
    String str3 = paramString.substring(6, 8);
    return str1 + "-" + str2 + "-" + str3;
  }

  public static String getDay(Date paramDate)
  {
    return FormatDate(paramDate, "dd");
  }

  public static String getHour(Date paramDate)
  {
    return FormatDate(paramDate, "HH");
  }

  public static String getMinute(Date paramDate)
  {
    return FormatDate(paramDate, "mm");
  }

  public static String getMonth(Date paramDate)
  {
    return FormatDate(paramDate, "MM");
  }

  public static int getMonth(Date paramDate1, Date paramDate2)
  {
    if (paramDate1.after(paramDate2))
    {
      Date localObject = paramDate1;
      paramDate1 = paramDate2;
      paramDate2 = (Date)localObject;
    }
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTime(paramDate1);
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTime(paramDate2);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTime(paramDate2);
    localCalendar2.add(5, 1);
    int i = localCalendar1.get(1) - ((Calendar)localObject).get(1);
    int j = localCalendar1.get(2) - ((Calendar)localObject).get(2);
    if ((((Calendar)localObject).get(5) == 1) && (localCalendar2.get(5) == 1))
      return i * 12 + j + 1;
    if ((((Calendar)localObject).get(5) != 1) && (localCalendar2.get(5) == 1))
      return i * 12 + j;
    if ((((Calendar)localObject).get(5) == 1) && (localCalendar2.get(5) != 1))
      return i * 12 + j;
    return i * 12 + j - 1 < 0 ? 0 : i * 12 + j;
  }

  public static String getSecond(Date paramDate)
  {
    return FormatDate(paramDate, "ss");
  }

  public static String getTime(String paramString1, String paramString2)
  {
    String str = "";
    int i = 31;
    int j = Integer.parseInt(paramString1);
    int k = Integer.parseInt(paramString2);
    if ((k == 4) || (k == 6) || (k == 9) || (k == 11))
      i = 30;
    if (k == 2)
    {
      i = 28;
      if (((j % 4 == 0) && (j % 100 == 0) && (j % 400 == 0)) || ((j % 4 == 0) && (j % 100 != 0)))
        i = 29;
    }
    str = paramString1 + "-" + paramString2 + "-" + String.valueOf(i);
    return str;
  }

  public static String getYear(Date paramDate)
  {
    return FormatDate(paramDate, "yyyy");
  }

  public static void main(String[] paramArrayOfString)
  {
    @SuppressWarnings("unused")
	CodeDateUtils localCodeDateUtils1 = new CodeDateUtils();
    String str = "2007-02-11";
    @SuppressWarnings("unused")
	Date localDate = stringToDateShort(str);
    @SuppressWarnings("unused")
	CodeDateUtils localCodeDateUtils2 = new CodeDateUtils();
  }

  public static Date stringToDate(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0))
      return null;
    String str1 = paramString.trim();
    String str2 = "yyyy-MM-dd HH:mm:ss";
    Date localDate = stringToDate(str1, str2);
    if (localDate == null)
      localDate = stringToDate(str1, "yyyy-MM-dd");
    if (localDate == null)
      localDate = stringToDate(str1, "yyyyMMdd");
    return localDate;
  }

  public static Date stringToDate(String paramString1, String paramString2)
  {
    ParsePosition localParsePosition = new ParsePosition(0);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    Date localDate = localSimpleDateFormat.parse(paramString1, localParsePosition);
    return localDate;
  }

  public static Date stringToDateShort(String paramString)
  {
    String str = "yyyy-MM-dd";
    Date localDate = stringToDate(paramString, str);
    return localDate;
  }
  
}