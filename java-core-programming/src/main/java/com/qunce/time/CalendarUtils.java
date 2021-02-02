package com.qunce.time;

import cn.hutool.core.date.CalendarUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class CalendarUtils {

    public Date forward12Months(Date endTime) {
        Calendar calendar = CalendarUtil.calendar(endTime);
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 12);
        // calendar = CalendarUtil.beginOfMonth(calendar);
        return calendar.getTime();
    }

    @Test
    public void forward12Months() {
        for (int i = 0; i < 12; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - i);
            Date time = calendar.getTime();
            Date date1 = forward12Months(time);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            System.out.println(sdf.format(date1));
        }
    }

    @Test
    public void instantToDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(Date.from(Instant.now())));
    }

    @Test
    public void sdf() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
        System.out.println(sdf.format(new Date()));
    }

    @Test
    public void getTimeList() {
        List<String> timeList = getTimeList(new Date());
        timeList.forEach(System.out::println);
    }

    public List<String> getTimeList(Date endTime) {
        Calendar calendar = CalendarUtil.calendar(endTime);
        calendar.setTimeZone(TimeZone.getDefault());
        List<String> timeList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
        for (int i = 0; i < 12; i++) {
            String formatTime = sdf.format(calendar.getTime());
            timeList.add(formatTime);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        }
        return timeList;
    }

}
