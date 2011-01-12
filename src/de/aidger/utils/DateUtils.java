/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Taken from http://www.thedwick.com/blog/2008/04/simpledateformat-performance-pig/
 *
 * @author
 */
public class DateUtils {

    public static final String MY_STANDARD_DATE_FORMAT = "dd.MM.yy";

    public static java.util.Date parseDate(String dateString) throws ParseException {
        return getFormat().parse(dateString);
    }

    public static String formatDate(Date date) {
        return getFormat().format(date);
    }

    private static ThreadLocal format = new ThreadLocal(){
        @Override
        protected synchronized Object initialValue() {
            return new java.text.SimpleDateFormat(MY_STANDARD_DATE_FORMAT);
        }
    };

    private static DateFormat getFormat(){
        return (DateFormat) format.get();
    }
}