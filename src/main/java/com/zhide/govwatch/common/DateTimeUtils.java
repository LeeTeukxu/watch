package com.zhide.govwatch.common;

import org.apache.commons.lang.StringUtils;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateTimeUtils {
    public static int getYear(Date date) {
        Calendar C = Calendar.getInstance();
        C.setTime(date);
        return C.get(Calendar.YEAR);
    }

    public static int getDays(Date begin, Date end) {
        LocalDate beginD = (Timestamp.from(begin.toInstant())).toLocalDateTime().toLocalDate();
        LocalDate endD =(Timestamp.from(end.toInstant())).toLocalDateTime().toLocalDate();
        return IntegerUtils.parseInt(endD.toEpochDay() - beginD.toEpochDay());
    }
    public static String formatDate(Date dt,String formatText){
        SimpleDateFormat sf=new SimpleDateFormat(formatText);
        return sf.format(dt);
    }
    public static String formatCurrentDate(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(new Date());
    }
    public static String formatCurrentTime(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }
    public static Date parse(String dateText){
        if(StringUtils.isEmpty(dateText)) return null;
        dateText=dateText.replace("T"," ");
        String[] Vs=dateText.split(" ");
        if(Vs.length==1)dateText=dateText+" 00:00:00";
        SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simple.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convert(String date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null || date.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
            String datetemp = date.split(Pattern.quote("中国标准时间"))[0].replace("GMT+0800","GMT+08:00");
            date = format.format(sdf.parse(datetemp));
            return date;
        }
        return "";
    }
}
