package com.sehinc.erosioncontrol.db.code;

public class StatusCodeData
    extends CodeData
{
    public final static
    String
        STATUS_CODE_ACTIVE =
        "1";
    public final static
    String
        STATUS_CODE_INACTIVE =
        "2";
    public final static
    String
        STATUS_CODE_DELETED =
        "3";
    public final static
    String
        STATUS_CODE_INCOMPLETE =
        "4";
    public final static
    String
        STATUS_CODE_ARCHIVED =
        "5";
    public final static
    String
        STATUS_CODE_CLOSED =
        "6";
    public final static
    String
        STATUS_CODE_AUTO_ACTIVATE =
        "7";

    public static String getStatusCodeName(String statusCode)
    {
        String
            name;
        try
        {
            switch (Integer.parseInt(statusCode))
            {
                case 1:
                    name =
                        "Active";
                    break;
                case 2:
                    name =
                        "Inactive";
                    break;
                case 3:
                    name =
                        "Deleted";
                    break;
                case 4:
                    name =
                        "Incomplete";
                    break;
                case 5:
                    name =
                        "Archived";
                    break;
                case 6:
                    name =
                        "Closed";
                    break;
                case 7:
                    name =
                        "Auto Activate";
                    break;
                default:
                    name =
                        "Unknown";
            }
            return name;
        }
        catch (NumberFormatException e)
        {
            return "Unknown";
        }
    }
}
