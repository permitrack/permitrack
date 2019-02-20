/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */

package com.sehinc.dataview.report;

import com.sehinc.common.util.StringUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * @author Chris Lawler
 * @version $Id:  $
 */
public class AveryMailingLabels5160DataSource
    implements JRDataSource
{
    private static
    Logger
        LOG =
        Logger.getLogger(AveryMailingLabels5160DataSource.class);
    private
    int
        _pages;
    private
    List
        _labels;
    private
    Object[][]
        data;
    private
    int
        _pageIndex =
        -1;
    /**
     *
     */
    /**
     private Object[][] data =
     {
     {"CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER"},
     {"CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER",
     "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER", "CHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER\nCHRISTIAN J AND CALLY A LAWLER"}
     };
     **/
    /**
     private Object[][] data =
     {
     {"Label 1\nLabel 1\nLabel 1\nLabel 1\nLabel 1", "Label 2\nLabel 2\nLabel 2\nLabel 2\nLabel 2", "Label 3\nLabel 3\nLabel 3\nLabel 3\nLabel 3",
     "Label 4\nLabel 4\nLabel 4\nLabel 4\nLabel 4", "Label 5\nLabel 5\nLabel 5\nLabel 5\nLabel 5","Label 6\nLabel 6\nLabel 6\nLabel 6\nLabel 6",
     "Label 7\nLabel 7\nLabel 7\nLabel 7\nLabel 7", "Label 8\nLabel 8\nLabel 8\nLabel 8\nLabel 8", "Label 9\nLabel 9\nLabel 9\nLabel 9\nLabel 9",
     "Label 10\nLabel 10\nLabel 10\nLabel 10\nLabel 10", "Label 11\nLabel 11\nLabel 11\nLabel 11\nLabel 11","Label 12\nLabel 12\nLabel 12\nLabel 12\nLabel 12",
     "Label 13\nLabel 13\nLabel 13\nLabel 13\nLabel 13", "Label 14\nLabel 14\nLabel 14\nLabel 14\nLabel 14","Label 15\nLabel 15\nLabel 15\nLabel 15\nLabel 15",
     "Label 16\nLabel 16\nLabel 16\nLabel 16\nLabel 16", "Label 17\nLabel 17\nLabel 17\nLabel 17\nLabel 17","Label 18\nLabel 18\nLabel 18\nLabel 18\nLabel 18",
     "Label 19\nLabel 19\nLabel 19\nLabel 19\nLabel 19", "Label 20\nLabel 20\nLabel 20\nLabel 20\nLabel 20","Label 21\nLabel 21\nLabel 21\nLabel 21\nLabel 21",
     "Label 22\nLabel 22\nLabel 22\nLabel 22\nLabel 22", "Label 23\nLabel 23\nLabel 23\nLabel 23\nLabel 23","Label 24\nLabel 24\nLabel 24\nLabel 24\nLabel 24",
     "Label 25\nLabel 25\nLabel 25\nLabel 25\nLabel 25", "Label 26\nLabel 26\nLabel 26\nLabel 26\nLabel 26","Label 27\nLabel 27\nLabel 27\nLabel 27\nLabel 27",
     "Label 28\nLabel 28\nLabel 28\nLabel 28\nLabel 28", "Label 29\nLabel 29\nLabel 29\nLabel 29\nLabel 29","Label 30\nLabel 30\nLabel 30\nLabel 30\nLabel 30"},
     {"Label 31", "Label 32", "Label 33", "Label 34", "Label 35",
     "Label 36", "Label 37", "Label 38", "Label 39", "Label 40",
     "Label 41", "Label 42", "Label 43", "Label 44", "Label 45",
     "Label 46", "Label 47", "Label 48", "Label 49", "Label 50",
     "Label 51", "Label 52", "Label 53", "Label 54", "Label 55",
     "Label 56", "Label 57", "Label 58", "Label 59", "Label 60"}
     };
     **/
    /**
     private Object[][] data =
     {
     {"Label 1", "Label 2", "Label 3", "Label 4", "Label 5",
     "Label 6", "Label 7", "Label 8", "Label 9", "Label 10",
     "Label 11", "Label 12", "Label 13", "Label 14", "Label 15",
     "Label 16", "Label 17", "Label 18", "Label 19", "Label 20",
     "Label 21", "Label 22", "Label 23", "Label 24", "Label 25",
     "Label 26", "Label 27", "Label 28", "Label 29", "Label 30"},
     {"Label 31", "Label 32", "Label 33", "Label 34", "Label 35",
     "Label 36", "Label 37", "Label 38", "Label 39", "Label 40",
     "Label 41", "Label 42", "Label 43", "Label 44", "Label 45",
     "Label 46", "Label 47", "Label 48", "Label 49", "Label 50",
     "Label 51", "Label 52", "Label 53", "Label 54", "Label 55",
     "Label 56", "Label 57", "Label 58", "Label 59", "Label 60"}
     };
     **/
    /**
     *
     */
    public AveryMailingLabels5160DataSource(List labels)
    {
        _labels =
            labels;
        loadData();
    }

    /**
     *
     */
    public boolean next()
        throws JRException
    {
        _pageIndex++;
        return (_pageIndex
                < _pages);
    }

    /**
     *
     */
    public Object getFieldValue(JRField field)
        throws JRException
    {
        Object
            value =
            null;
        String
            fieldName =
            field.getName();
        if ("field1".equals(fieldName))
        {
            value =
                data[_pageIndex][0];
        }
        else if ("field2".equals(fieldName))
        {
            value =
                data[_pageIndex][1];
        }
        else if ("field3".equals(fieldName))
        {
            value =
                data[_pageIndex][2];
        }
        else if ("field4".equals(fieldName))
        {
            value =
                data[_pageIndex][3];
        }
        else if ("field5".equals(fieldName))
        {
            value =
                data[_pageIndex][4];
        }
        else if ("field6".equals(fieldName))
        {
            value =
                data[_pageIndex][5];
        }
        else if ("field7".equals(fieldName))
        {
            value =
                data[_pageIndex][6];
        }
        else if ("field8".equals(fieldName))
        {
            value =
                data[_pageIndex][7];
        }
        else if ("field9".equals(fieldName))
        {
            value =
                data[_pageIndex][8];
        }
        else if ("field10".equals(fieldName))
        {
            value =
                data[_pageIndex][9];
        }
        else if ("field11".equals(fieldName))
        {
            value =
                data[_pageIndex][10];
        }
        else if ("field12".equals(fieldName))
        {
            value =
                data[_pageIndex][11];
        }
        else if ("field13".equals(fieldName))
        {
            value =
                data[_pageIndex][12];
        }
        else if ("field14".equals(fieldName))
        {
            value =
                data[_pageIndex][13];
        }
        else if ("field15".equals(fieldName))
        {
            value =
                data[_pageIndex][14];
        }
        else if ("field16".equals(fieldName))
        {
            value =
                data[_pageIndex][15];
        }
        else if ("field17".equals(fieldName))
        {
            value =
                data[_pageIndex][16];
        }
        else if ("field18".equals(fieldName))
        {
            value =
                data[_pageIndex][17];
        }
        else if ("field19".equals(fieldName))
        {
            value =
                data[_pageIndex][18];
        }
        else if ("field20".equals(fieldName))
        {
            value =
                data[_pageIndex][19];
        }
        else if ("field21".equals(fieldName))
        {
            value =
                data[_pageIndex][20];
        }
        else if ("field22".equals(fieldName))
        {
            value =
                data[_pageIndex][21];
        }
        else if ("field23".equals(fieldName))
        {
            value =
                data[_pageIndex][22];
        }
        else if ("field24".equals(fieldName))
        {
            value =
                data[_pageIndex][23];
        }
        else if ("field25".equals(fieldName))
        {
            value =
                data[_pageIndex][24];
        }
        else if ("field26".equals(fieldName))
        {
            value =
                data[_pageIndex][25];
        }
        else if ("field27".equals(fieldName))
        {
            value =
                data[_pageIndex][26];
        }
        else if ("field28".equals(fieldName))
        {
            value =
                data[_pageIndex][27];
        }
        else if ("field29".equals(fieldName))
        {
            value =
                data[_pageIndex][28];
        }
        else if ("field30".equals(fieldName))
        {
            value =
                data[_pageIndex][29];
        }
        return value;
    }

    private void loadData()
    {
        int
            _labelsPerPage =
            30;
        _pages =
            (int) Math.ceil((_labels.size()
                             * 1D)
                            / (_labelsPerPage
                               * 1D));
        data =
            new Object[_pages][_labelsPerPage];
        Iterator
            iter =
            _labels.iterator();
        int
            pageIndex =
            0;
        int
            labelIndex =
            0;
        while (iter.hasNext())
        {
            if (labelIndex
                == _labelsPerPage)
            {
                pageIndex++;
                labelIndex =
                    0;
            }
            //System.out.println("data["+pageIndex+"]["+labelIndex+"]");
            data[pageIndex][labelIndex++] =
                buildLabel((List) iter.next());
        }
    }

    private String buildLabel(List label)
    {
        String
            result =
            "";
        Iterator
            iter =
            label.iterator();
        boolean
            isFirst =
            true;
        while (iter.hasNext())
        {
            String
                line =
                (String) iter.next();
            if (!StringUtil.isEmpty(line))
            {
                //if there is a non-breaking space, skip that line too
                if (line.length()
                    == 1
                    && line.charAt(0)
                       == (char) 160)
                {
                    //skip this line
                }
                else
                {
                    if (!isFirst)
                    {
                        result +=
                            "\n";
                    }
                    result +=
                        line;
                    isFirst =
                        false;
                }
            }
        }
        return result;
    }
}
