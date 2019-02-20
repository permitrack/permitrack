package com.sehinc.common.conversionservice;

public class ConversionService
{
    public static Float convert(Float value, Integer fromUnit, Integer toUnit, Float density)
    {
        switch (toUnit)
        {
            case 1:
                value =
                    convertToGallons(value,
                                     fromUnit,
                                     density);
                break;
            case 2:
                value =
                    convertToOunces(value,
                                    fromUnit);
                break;
            case 3:
                value =
                    convertToLiters(value,
                                    fromUnit,
                                    density);
                break;
            case 4:
                value =
                    convertToTons(value,
                                  fromUnit,
                                  density);
                break;
            case 5:
                value =
                    convertToMmCF(value,
                                  fromUnit,
                                  density);
                break;
            case 6:
                value =
                    convertToLbsPerGallon(value,
                                          fromUnit);
                break;
            case 7:
                value =
                    convertToLbsPer1000Gallon(value,
                                              fromUnit);
                break;
            case 8:
                value =
                    convertToLbsPerMmCF(value,
                                        fromUnit,
                                        density);
                break;
            case 9:
                value =
                    convertToCubicMeters(value,
                                         fromUnit,
                                         density);
                break;
            case 10:
                value =
                    convertToCubicFeet(value,
                                       fromUnit,
                                       density);
                break;
            case 11:
                System.out
                    .println("Invalid selection.");
                break;
            case 12:
                System.out
                    .println("Invalid selection.");
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToGallons(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 2. 		1 US fluid ounce = 0.0078125 US gallons
           * 3. 		1 liters = 0.264172052 US gallons
           * 4. 		1 pound / US gallon = 0.0005 short tons / US gallon -- density conversion
           * 5. & 10. 1 cubic foot = 7.48051948 US gallons
           * 5. 		1 MMCF = 1,000,000 cubic feet
           * 9. 		1 cubic meter = 264.172052 US gallons
           */
        switch (fromUnit)
        {
            case 1: //value is already in gallons
                break;
            case 2: //ounces
                value =
                    (float) (value
                             * 0.0078125);
                break;
            case 3: //liters
                value =
                    (float) (value
                             * 0.264172052);
                break;
            case 4: //tons
                value =
                    value
                    / (density
                       / 2000 /* divide by 2000 to convert from lbs/gallon to tons/gallon */);
                break;
            case 5: //MmCF
                value =
                    ((float) (value
                              * 7.48051948))
                    / 1000000;
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //Cubic Meters
                value =
                    (float) (value
                             * 264.172052);
                break;
            case 10: //Cubic feet
                value =
                    (float) (value
                             * 7.48051948);
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    //it is assumed that we are converting to fluid ounces when converting from a volume measurment;
    //otherwise we're converting to ounces that measure weight
    private static Float convertToOunces(Float value, Integer fromUnit)
    {
        /*
           * Conversions used
           * ================================================================================
           * 1. 		1 US gallon = 128 US fluid ounces
           * 3. 		1 liter = 33.8140227 US fluid ounces
           * 4. 		1 short ton = 32 000 ounces
           * 5. & 10. 1 cubic foot = 957.506494 US fluid ounces
           * 6. 		1 cubic meter = 33814.0227 US fluid ounces
           */
        switch (fromUnit)
        {
            case 1: //gallons
                value =
                    value
                    * 128;
                break;
            case 2: //value is already in ounces
                break;
            case 3: //liters
                value =
                    (float) (value
                             * 33.8140227);
                break;
            case 4: //tons
                value =
                    value
                    * 32000;
                break;
            case 5: //MmCF
                value =
                    (float) (value
                             * 957.506494)
                    / 1000000;
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //Cubic Meters
                value =
                    (float) (value
                             * 33814.0227);
                break;
            case 10: //Cubic feet
                value =
                    (float) (value
                             * 957.506);
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToLiters(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 1. 		1 US gallon = 3.78541178 liter
           * 2. 		1 US fluid ounce = 0.0295735296 liter
           * 4. 		1 pound per US gallon = 0.000132086026 short tons per liter
           * 5. & 10. 1 cubic foot = 28.3168466 liter
           * 6.		1 cubic meter = 1000 liter
           */
        switch (fromUnit)
        {
            case 1: //gallons
                value =
                    (float) (value
                             * 3.78541178);
                break;
            case 2: //ounces
                value =
                    (float) (value
                             * 0.0295735296);
                break;
            case 3: //value is already in liters
                break;
            case 4: //tons
                value =
                    value
                    / ((float) (density
                                * 0.000132086026)) /* tons/liter */;
                break;
            case 5: //MmCF
                value =
                    (float) (value
                             * 28.3168466)
                    / 1000000;
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //Cubic Meters
                value =
                    value
                    * 1000;
                break;
            case 10: //Cubic feet
                value =
                    (float) (value
                             * 28.3168466);
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToTons(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 1. 		1 pound = 0.0005 short tons
           * 2. 		1 US fluid ounce = 0.0078125 US gallons
           * 3. 		1 liter = 0.264172052 US gallons
           * 5. & 10. 1 cubic foot = 7.48051948 US gallons
           * 5. 		1 MMCF = 1,000,000 cubic feet
           * 9. 		1 cubic meter = 264.172052 US gallons
           */
        switch (fromUnit)
        {
            case 1: //gallons
                value =
                    (value
                     * density)
                    / 2000;
                break;
            case 2: //ounces
                value =
                    (((float) (value
                               * 0.0078125))
                     * density)
                    / 2000;
                break;
            case 3: //liters
                value =
                    (((float) (value
                               * 0.264172052))
                     * density)
                    / 2000;
                break;
            case 4: //value is already in tons
                break;
            case 5: //MmCF
                value =
                    (((float) ((value
                                * 1000000 /* cubic feet */)
                               * 7.48051948) /* gallons */)
                     * density)
                    / 2000;
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //Cubic Meters
                value =
                    (((float) (value
                               * 264.172052) /* gallons */)
                     * density)
                    / 2000;
                break;
            case 10: //Cubic feet
                value =
                    (((float) (value
                               * 7.48051948) /* gallons */)
                     * density)
                    / 2000;
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToMmCF(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 1. 	1 US gallon = 0.133680556 cubic feet
           * 2. 	1 US fluid ounce = 0.00104437934 cubic feet
           * 3. 	1 liters = 0.0353146667 cubic feet
           * 4. 	1 US gallon = 0.133680556 cubic feet
           * 9. 	1 cubic foot = 0.0283168466 cubic meters
           * 10. 	1 MMCF = 1,000,000 cubic feet
           */
        switch (fromUnit)
        {
            case 1: //gallons
                value =
                    (float) (value
                             * 0.133680556);
                break;
            case 2: //ounces
                value =
                    (float) (value
                             * 0.00104437934);
                break;
            case 3: //liters
                value =
                    (float) (value
                             * 0.0353146667);
                break;
            case 4: //tons
                value =
                    ((float) ((value
                               / (density
                                  / 2000 /* divide by 2000 to convert from lbs/gallon to tons/gallon */))
                              * 0.133680556 /*cubic feet*/))
                    / 1000000;
                break;
            case 5: //value is already in MmCF
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //Cubic Meters
                value =
                    ((float) (value
                              * 0.0283168466))
                    / 1000000;
                break;
            case 10: //Cubic feet
                value =
                    value
                    / 1000000;
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToLbsPerGallon(Float value, Integer fromUnit)
    {
        /*
           * Conversions used
           * ================================================================================
           * 7. 1 pounds / (1000 US gallon) = 0.001 pounds / US gallon
           * 8. 1 pounds / (1000 US gallon) = 0.00748051948 pounds / (cubic feet)
           */
        switch (fromUnit)
        {
            case 1: //gallons
                //won't happen
                break;
            case 2: //ounces
                //won't happen
                break;
            case 3: //liters
                //won't happen
                break;
            case 4: //tons
                //won't happen
                break;
            case 5: //MmCF
                //won't happen
                break;
            case 6: //value is already in Lbs/Gallon
                break;
            case 7: //Lbs/1000Gallon
                value =
                    (float) (value
                             * 0.001);
                break;
            case 8: //Lbs/MmCF
                value =
                    ((float) (value
                              * 0.00748051948))
                    / 1000000;
                break;
            case 9: //Cubic Meters
                //won't happen
                break;
            case 10: //Cubic feet
                //won't happen
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToLbsPer1000Gallon(Float value, Integer fromUnit)
    {
        /*
           * Conversions used
           * ================================================================================
           * 6. 1 pounds / US gallon = 1000 pounds / (1000 US gallon)
           * 8. (1 pounds) / (cubic feet) = 133.680556 pounds / (1000 US gallon)
           */
        switch (fromUnit)
        {
            case 1: //gallons
                //won't happen
                break;
            case 2: //ounces
                //won't happen
                break;
            case 3: //liters
                //won't happen
                break;
            case 4: //tons
                //won't happen
                break;
            case 5: //MmCF
                //won't happen
                break;
            case 6: //Lbs/Gallon
                value =
                    value
                    * 1000;
                break;
            case 7: //value is already in Lbs/1000Gallon
                break;
            case 8: //Lbs/MmCF
                value =
                    ((float) (value
                              * 133.680556
                              * 1000000));////??????????? correct?
                break;
            case 9: //Cubic Meters
                //won't happen
                break;
            case 10: //Cubic feet
                //won't happen
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToLbsPerMmCF(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 6. 1 pounds / US gallon = 7.48051948 pounds / (cubic feet)
           * 7. 1 pounds / (1000 US gallon) = 0.00748051948 pounds / (cubic feet)
           * and
           * 1 pounds / (cubic feet) = 1 000 000 pounds / (1 000 000 (cubic feet))
           */
        switch (fromUnit)
        {
            case 1: //gallons
                //won't happen
                break;
            case 2: //ounces
                //won't happen
                break;
            case 3: //liters
                //won't happen
                break;
            case 4: //tons
                //won't happen
                break;
            case 5: //MmCF
                //won't happen
                break;
            case 6: //Lbs/Gallon
                value =
                    (float) (value
                             * 7.48051948 /* pounds / (cubic feet)*/)
                    / 1000000;
                break;
            case 7: //Lbs/1000Gallon
                value =
                    (float) (value
                             * 0.00748051948 /* pounds / (cubic feet)*/)
                    / 1000000;
                break;
            case 8: //value is already in Lbs/MmCF
                break;
            case 9: //Cubic Meters
                //won't happen
                break;
            case 10: //Cubic feet
                //won't happen
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToCubicMeters(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 1. & 4.	1 US gallon = 0.00378541178 cubic meters
           * 2. 		1 US fluid ounce = 2.95735296 × 10^(-5) cubic meters
           * 3. 		1 liters = 0.001 cubic meters
           * 5. & 10. 1 cubic foot = 0.0283168466 cubic meters
           */
        switch (fromUnit)
        {
            case 1: //gallons
                value =
                    (float) (value
                             * 0.00378541178);
                break;
            case 2: //ounces
                value =
                    (float) (value
                             * 0.0000295735296);
                break;
            case 3: //liters
                value =
                    (float) (value
                             * 0.001);
                break;
            case 4: //tons
                value =
                    (float) ((value
                              / (density
                                 / 2000 /* divide by 2000 to convert from lbs/gallon to tons/gallon */))
                             * 0.00378541178);
                break;
            case 5: //MmCF
                value =
                    ((float) (value
                              * 0.0283168466))
                    / 1000000;
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //value is already in Cubic Meters
                break;
            case 10: //Cubic feet
                value =
                    (float) (value
                             * 0.0283168466);
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }

    private static Float convertToCubicFeet(Float value, Integer fromUnit, Float density)
    {
        /*
           * Conversions used
           * ================================================================================
           * 1. & 4. 	1 US gallon = 0.133680556 cubic feet
           * 2. 		1 US fluid ounce = 0.00104437934 cubic feet
           * 3. 		1 liters = 0.0353146667 cubic feet
           * 9. 		1 cubic meter = 35.3146667 cubic feet
           */
        switch (fromUnit)
        {
            case 1: //gallons
                value =
                    (float) (value
                             * 0.133680556);
                break;
            case 2: //ounces
                value =
                    (float) (value
                             * 0.00104437934);
                break;
            case 3: //liters
                value =
                    (float) (value
                             * 0.0353146667);
                break;
            case 4: //tons
                value =
                    (float) ((value
                              / (density
                                 / 2000 /* divide by 2000 to convert from lbs/gallon to tons/gallon */))
                             * 0.133680556);
                break;
            case 5: //MmCF
                value =
                    value
                    / 1000000;
                break;
            case 6: //Lbs/Gallon
                //won't happen
                break;
            case 7: //Lbs/1000Gallon
                //won't happen
                break;
            case 8: //Lbs/MmCF
                //won't happen
                break;
            case 9: //Cubic Meters
                value =
                    (float) (value
                             * 35.3146667);
                break;
            case 10: //value is already in Cubic feet
                break;
            case 11: //Months
                //won't happen
                break;
            case 12: //Tons/year
                //won't happen
                break;
            default:
                System.out
                    .println("Invalid selection.");
                break;
        }
        return value;
    }
}
