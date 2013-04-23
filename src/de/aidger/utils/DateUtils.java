/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
