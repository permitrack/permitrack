package com.sehinc.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class StringUtil
    extends Object
{
    private StringUtil()
    {
        super();
    }

    public static boolean isEmpty(String content)
    {
        if (content
            == null
            || content.trim()
                   .length()
               == 0)
        {
            return true;
        }
        return false;
    }

    public static String replace(String content, String oldString, String newString)
    {
        if ((content
             == null)
            ||
            (oldString
             == null)
            ||
            (oldString.length()
             < 1)
            ||
            (newString
             == null))
        {
            return content;
        }
        if (content.length()
            < oldString.length())
        {
            return content;
        }
        String
            newContent =
            content;
        int
            foundIndex =
            content.indexOf(oldString);
        // Recurse through the string, replacing ALL occurrences recurse rather
        // than loop to allow a replace that includes itself (eg "'" with "''" -
        // SQL escaping)
        if (foundIndex
            != -1)
        {
            newContent =
                content.substring(0,
                                  foundIndex)
                + newString
                + replace(content.substring(foundIndex
                                            + oldString.length(),
                                            content.length()),
                          oldString,
                          newString);
        }
        return newContent;
    }

    public static String filterHTML(String content)
    {
        String
            newContent =
            replace(content,
                    "&",
                    "&amp;");
        newContent =
            replace(newContent,
                    "\"",
                    "&quot;");
        newContent =
            replace(newContent,
                    "<",
                    "&lt;");
        newContent =
            replace(newContent,
                    ">",
                    "&gt;");
        newContent =
            replace(newContent,
                    "^",
                    "&circ;");
        newContent =
            replace(newContent,
                    "~",
                    "&tilde;");
        newContent =
            replace(newContent,
                    "‘",
                    "&lsquo;");
        newContent =
            replace(newContent,
                    "’",
                    "&rsquo;");
        newContent =
            escapeCarriageReturn(newContent);
        return replace(newContent,
                       "\\n",
                       "<br/>");
    }

    public static String remove(String content, String toBeRemoved)
    {
        if (null
            == content
            ||
            content.length()
            < 1
            ||
            null
            == toBeRemoved
            ||
            toBeRemoved.length()
            < 1)
        {
            return content;
        }
        StringBuffer
            newContent =
            new StringBuffer(content.length());
        StringTokenizer
            st =
            new StringTokenizer(content,
                                toBeRemoved,
                                false);
        // Loop through, This will give only strings that don't include the
        // characters contained in "toBeRemoved"....just append them together.
        while (st.hasMoreTokens())
        {
            try
            {
                newContent.append(st.nextToken());
            }
            catch (NoSuchElementException eNSEE)
            {
                // This should NEVER happen...since we use hasMoreTokens() as
                // limit check.
            }
        }
        return newContent.toString();
    }

    public static String stripNonAlphaNumericCharacters(String s)
    {
        if (s
            == null)
        {
            return s;
        }
        StringBuffer
            returnVal =
            new StringBuffer("");
        char[]
            chars =
            s.toCharArray();
        for (
            int
                i =
                0; i
                   < chars.length; i++)
        {
            if (Character.isLetterOrDigit(chars[i]))
            {
                returnVal.append(chars[i]);
            }
        }
        return returnVal.toString();
    }

    public static String stripNonAlphaNumericCharactersExceptSpaces(String s)
    {
        if (s
            == null)
        {
            return s;
        }
        StringBuffer
            returnVal =
            new StringBuffer("");
        char[]
            chars =
            s.toCharArray();
        for (
            int
                i =
                0; i
                   < chars.length; i++)
        {
            if (Character.isLetterOrDigit(chars[i])
                || Character.isSpaceChar(chars[i]))
            {
                returnVal.append(chars[i]);
            }
        }
        return returnVal.toString();
    }

    public static String escapeSingleQuote(String s)
    {
        if (s
            == null)
        {
            return "";
        }
        String
            temp =
            replace(replace(s,
                            "\\",
                            "\\\\"),
                    "'",
                    "\\'");
        temp =
            replace(temp,
                    String.valueOf((char) 13)
                    + String.valueOf((char) 10),
                    "\\n");
        temp =
            replace(temp,
                    String.valueOf((char) 10),
                    "\\n");
        return temp;
    }

    public static String escapeCarriageReturn(String s)
    {
        if (s
            == null)
        {
            return s;
        }
        String
            temp =
            replace(s,
                    String.valueOf((char) 13)
                    + String.valueOf((char) 10),
                    "\\n");
        temp =
            replace(temp,
                    String.valueOf((char) 10),
                    "\\n");
        return temp;
    }

    public static String trim(String val)
    {
        String
            ret =
            null;
        if (val
            != null
            && val.trim()
                   .length()
               > 0)
        {
            ret =
                val.trim();
        }
        return ret;
    }

    public static String replaceAt(String oldString, int index, String replacementString)
        throws NullPointerException, StringIndexOutOfBoundsException
    {
        int
            length =
            oldString.length();
        String
            newString;
        if (index
            >= length
            || index
               < 0)
        {
            throw new StringIndexOutOfBoundsException("Index "
                                                      + index
                                                      + " is out of bounds for string length "
                                                      + length);
        }
        String
            paddedString =
            oldString
            + " ";
        String
            beforeIndex =
            paddedString.substring(0,
                                   index);
        String
            afterIndex =
            paddedString.substring(index
                                   + 1);
        newString =
            beforeIndex
            + replacementString
            + afterIndex;
        newString =
            newString.substring(0,
                                newString.length()
                                - 1);
        return newString;
    }

    public static String padRight(String oldString, String fillCharacter, int stringLength)
    {
        String
            newString =
            oldString;
        while (newString.length()
               < stringLength)
        {
            newString +=
                fillCharacter;
        }
        if (" ".equals(newString))
        {
            newString =
                "";
        }
        return newString;
    }

    public static List split(String str, String delim)
    {
        if (str
            == null)
        {
            return null;
        }
        List
            list =
            new ArrayList();
        if (str.equals(delim))
        {
            list.add("");
            list.add("");
            return list;
        }
        if (str.indexOf(delim)
            < 0)
        {
            list.add(str);
            return list;
        }
        if (str.startsWith(delim))
        {
            list.add("");
        }
        StringTokenizer
            st =
            new StringTokenizer(str,
                                delim);
        while (st.hasMoreTokens())
        {
            list.add(st.nextToken());
        }
        if (str.endsWith(delim))
        {
            list.add("");
        }
        return list;
    }

    public static String trimCharLeft(String in, char trim)
    {
        int
            i;
        for (
            i =
                0; i
                   < in.length()
                   && in.charAt(i)
                      == trim; i++)
        {
            ;
        }
        return in.substring(i,
                            in.length());
    }

    public static boolean hasContent(String s)
    {
        return (s
                != null
                && s.length()
                   > 0);
    }

    public static String lt(String strS)
    {
        // Check for Less Than Character
        String
            s =
            new String("");
        String
            l =
            new String("&lt;");
        String
            g =
            new String("&gt;");
        int
            i; //index value into the string.
        if (strS
            == null)
        {
            strS =
                s;
        }
        // check of existance of the string representations
        if (strS.indexOf(l)
            + strS.indexOf(g)
            >= -1)
        { // Chars need to be replaced
            s =
                strS;
            while (s.indexOf(l)
                   >= 0)
            {
                i =
                    s.indexOf(l);
                if (i
                    == 0) // Is the first character of the string
                {
                    s =
                        "<"
                        + s.substring(4);
                }
                else if ((s.length()
                          - 1)
                         >= (i
                             + 4)) // Is embedded witin the string
                {
                    s =
                        s.substring(0,
                                    i)
                        + "<"
                        + s.substring(i
                                      + 4);
                }
                else // Is the last character of the string.
                {
                    s =
                        s.substring(0,
                                    i)
                        + "<";
                }
            }
            while (s.indexOf(g)
                   >= 0)
            {
                i =
                    s.indexOf(g);
                if (i
                    == 0) // Is the first character of the string
                {
                    s =
                        "<"
                        + s.substring(4);
                }
                else if ((s.length()
                          - 1)
                         >= (i
                             + 4)) // Is embedded witin the string
                {
                    s =
                        s.substring(0,
                                    i)
                        + ">"
                        + s.substring(i
                                      + 4);
                }
                else // Is the last character of the string.
                {
                    s =
                        s.substring(0,
                                    i)
                        + ">";
                }
            }
        }
        else
        {
            s =
                strS;
        }
        return s;
    }

    public static String html(String strS)
    {
        org.apache.log4j.Logger
            LOG =
            org.apache
                .log4j
                .Logger
                .getLogger("StringUtil");
        String
            s =
            new String("");
        String
            l =
            new String("<");
        String
            lt =
            new String("&lt;");
        String
            g =
            new String(">");
        String
            gt =
            new String("&gt;");
        int
            i;
        LOG.debug("In ------------------------------------->> "
                  + strS);
        if (strS
            == null)
        {
            strS =
                s;
        }
        if (strS.indexOf(l)
            + strS.indexOf(g)
            >= -1)
        {
            s =
                strS;
            while (s.indexOf(l)
                   >= 0)
            {
                i =
                    s.indexOf(l);
                LOG.debug("l  ------------------------------------->> "
                          + i);
                if (i
                    == 0)
                {
                    s =
                        lt
                        + s.substring(1);
                }
                else if ((s.length()
                          - 1)
                         >= (i
                             + 1))
                {
                    s =
                        s.substring(0,
                                    i)
                        + lt
                        + s.substring(i
                                      + 1);
                }
                else
                {
                    s =
                        s.substring(0,
                                    i)
                        + lt;
                }
            }
            while (s.indexOf(g)
                   >= 0)
            {
                i =
                    s.indexOf(g);
                LOG.debug("g  ------------------------------------->> "
                          + i);
                if (i
                    == 0)
                {
                    s =
                        gt
                        + s.substring(1);
                }
                else if ((s.length()
                          - 1)
                         >= (i
                             + 1))
                {
                    s =
                        s.substring(0,
                                    i)
                        + gt
                        + s.substring(i
                                      + 1);
                }
                else
                {
                    s =
                        s.substring(0,
                                    i)
                        + gt;
                }
            }
        }
        else
        {
            s =
                strS;
        }
        LOG.debug("Out ------------------------------------->> "
                  + s);
        return s;
    }

    public static String maxLength(String strS, int intMaxLength)
    {
        String
            strReturn =
            new String("");
        if (strS
            != null)
        {
            if (intMaxLength
                > 0)
            {
                if (strS.length()
                    > intMaxLength)
                {
                    strReturn =
                        strS.substring(0,
                                       intMaxLength);
                }
            }
            else
            {
                strReturn =
                    strS;
            }
        }
        return strReturn;
    }

    public static String nullSafeToString(Object o)
    {
        if (o
            == null)
        {
            return "";
        }
        return o.toString();
    }
}
