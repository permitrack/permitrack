package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue;
import org.apache.log4j.Logger;

import java.util.Hashtable;

public class InspectionBmpList
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionBmpList.class);
    private
    Hashtable
        bmps =
        new Hashtable();

    private InspectionBmpValue getBmp(int index)
    {
        if (this.bmps
                .get(new Integer(index))
            == null)
        {
            this.bmps
                .put(new Integer(index),
                     new InspectionBmpValue());
        }
        return (InspectionBmpValue) this.bmps
            .get(new Integer(index));
    }

    private void setBmp(int index, InspectionBmpValue bmp)
    {
        this.bmps
            .put(new Integer(index),
                 bmp);
    }

    public Hashtable getAllBmps()
    {
        return bmps;
    }

    public InspectionBmpValue getBmp0()
    {
        LOG.debug("In InspectionBmpList.getBmp0()");
        return getBmp(0);
    }

    public void setBmp0(InspectionBmpValue bmp)
    {
        LOG.debug("In InspectionBmpList.setBmp0(InspectionBmpValue)");
        setBmp(0,
               bmp);
    }

    public InspectionBmpValue getBmp1()
    {
        return getBmp(1);
    }

    public void setBmp1(InspectionBmpValue bmp)
    {
        setBmp(1,
               bmp);
    }

    public InspectionBmpValue getBmp2()
    {
        return getBmp(2);
    }

    public void setBmp2(InspectionBmpValue bmp)
    {
        setBmp(2,
               bmp);
    }

    public InspectionBmpValue getBmp3()
    {
        return getBmp(3);
    }

    public void setBmp3(InspectionBmpValue bmp)
    {
        setBmp(3,
               bmp);
    }

    public InspectionBmpValue getBmp4()
    {
        return getBmp(4);
    }

    public void setBmp4(InspectionBmpValue bmp)
    {
        setBmp(4,
               bmp);
    }

    public InspectionBmpValue getBmp5()
    {
        return getBmp(5);
    }

    public void setBmp5(InspectionBmpValue bmp)
    {
        setBmp(5,
               bmp);
    }

    public InspectionBmpValue getBmp6()
    {
        return getBmp(6);
    }

    public void setBmp6(InspectionBmpValue bmp)
    {
        setBmp(6,
               bmp);
    }

    public InspectionBmpValue getBmp7()
    {
        return getBmp(7);
    }

    public void setBmp7(InspectionBmpValue bmp)
    {
        setBmp(7,
               bmp);
    }

    public InspectionBmpValue getBmp8()
    {
        return getBmp(8);
    }

    public void setBmp8(InspectionBmpValue bmp)
    {
        setBmp(8,
               bmp);
    }

    public InspectionBmpValue getBmp9()
    {
        return getBmp(9);
    }

    public void setBmp9(InspectionBmpValue bmp)
    {
        setBmp(9,
               bmp);
    }

    public InspectionBmpValue getBmp10()
    {
        return getBmp(10);
    }

    public void setBmp10(InspectionBmpValue bmp)
    {
        setBmp(10,
               bmp);
    }

    public InspectionBmpValue getBmp11()
    {
        return getBmp(11);
    }

    public void setBmp11(InspectionBmpValue bmp)
    {
        setBmp(11,
               bmp);
    }

    public InspectionBmpValue getBmp12()
    {
        return getBmp(12);
    }

    public void setBmp12(InspectionBmpValue bmp)
    {
        setBmp(12,
               bmp);
    }

    public InspectionBmpValue getBmp13()
    {
        return getBmp(13);
    }

    public void setBmp13(InspectionBmpValue bmp)
    {
        setBmp(13,
               bmp);
    }

    public InspectionBmpValue getBmp14()
    {
        return getBmp(14);
    }

    public void setBmp14(InspectionBmpValue bmp)
    {
        setBmp(14,
               bmp);
    }

    public InspectionBmpValue getBmp15()
    {
        return getBmp(15);
    }

    public void setBmp15(InspectionBmpValue bmp)
    {
        setBmp(15,
               bmp);
    }

    public InspectionBmpValue getBmp16()
    {
        return getBmp(16);
    }

    public void setBmp16(InspectionBmpValue bmp)
    {
        setBmp(16,
               bmp);
    }

    public InspectionBmpValue getBmp17()
    {
        return getBmp(17);
    }

    public void setBmp17(InspectionBmpValue bmp)
    {
        setBmp(17,
               bmp);
    }

    public InspectionBmpValue getBmp18()
    {
        return getBmp(18);
    }

    public void setBmp18(InspectionBmpValue bmp)
    {
        setBmp(18,
               bmp);
    }

    public InspectionBmpValue getBmp19()
    {
        return getBmp(19);
    }

    public void setBmp19(InspectionBmpValue bmp)
    {
        setBmp(19,
               bmp);
    }

    public InspectionBmpValue getBmp20()
    {
        return getBmp(20);
    }

    public void setBmp20(InspectionBmpValue bmp)
    {
        setBmp(20,
               bmp);
    }

    public InspectionBmpValue getBmp21()
    {
        return getBmp(21);
    }

    public void setBmp21(InspectionBmpValue bmp)
    {
        setBmp(21,
               bmp);
    }

    public InspectionBmpValue getBmp22()
    {
        return getBmp(22);
    }

    public void setBmp22(InspectionBmpValue bmp)
    {
        setBmp(22,
               bmp);
    }

    public InspectionBmpValue getBmp23()
    {
        return getBmp(23);
    }

    public void setBmp23(InspectionBmpValue bmp)
    {
        setBmp(23,
               bmp);
    }

    public InspectionBmpValue getBmp24()
    {
        return getBmp(24);
    }

    public void setBmp24(InspectionBmpValue bmp)
    {
        setBmp(24,
               bmp);
    }

    public InspectionBmpValue getBmp25()
    {
        return getBmp(25);
    }

    public void setBmp25(InspectionBmpValue bmp)
    {
        setBmp(25,
               bmp);
    }

    public InspectionBmpValue getBmp26()
    {
        return getBmp(26);
    }

    public void setBmp26(InspectionBmpValue bmp)
    {
        setBmp(26,
               bmp);
    }

    public InspectionBmpValue getBmp27()
    {
        return getBmp(27);
    }

    public void setBmp27(InspectionBmpValue bmp)
    {
        setBmp(27,
               bmp);
    }

    public InspectionBmpValue getBmp28()
    {
        return getBmp(28);
    }

    public void setBmp28(InspectionBmpValue bmp)
    {
        setBmp(28,
               bmp);
    }

    public InspectionBmpValue getBmp29()
    {
        return getBmp(29);
    }

    public void setBmp29(InspectionBmpValue bmp)
    {
        setBmp(29,
               bmp);
    }

    public InspectionBmpValue getBmp30()
    {
        return getBmp(30);
    }

    public void setBmp30(InspectionBmpValue bmp)
    {
        setBmp(30,
               bmp);
    }

    public InspectionBmpValue getBmp31()
    {
        return getBmp(31);
    }

    public void setBmp31(InspectionBmpValue bmp)
    {
        setBmp(31,
               bmp);
    }

    public InspectionBmpValue getBmp32()
    {
        return getBmp(32);
    }

    public void setBmp32(InspectionBmpValue bmp)
    {
        setBmp(32,
               bmp);
    }

    public InspectionBmpValue getBmp33()
    {
        return getBmp(33);
    }

    public void setBmp33(InspectionBmpValue bmp)
    {
        setBmp(33,
               bmp);
    }

    public InspectionBmpValue getBmp34()
    {
        return getBmp(34);
    }

    public void setBmp34(InspectionBmpValue bmp)
    {
        setBmp(34,
               bmp);
    }

    public InspectionBmpValue getBmp35()
    {
        return getBmp(35);
    }

    public void setBmp35(InspectionBmpValue bmp)
    {
        setBmp(35,
               bmp);
    }

    public InspectionBmpValue getBmp36()
    {
        return getBmp(36);
    }

    public void setBmp36(InspectionBmpValue bmp)
    {
        setBmp(36,
               bmp);
    }

    public InspectionBmpValue getBmp37()
    {
        return getBmp(37);
    }

    public void setBmp37(InspectionBmpValue bmp)
    {
        setBmp(37,
               bmp);
    }

    public InspectionBmpValue getBmp38()
    {
        return getBmp(38);
    }

    public void setBmp38(InspectionBmpValue bmp)
    {
        setBmp(38,
               bmp);
    }

    public InspectionBmpValue getBmp39()
    {
        return getBmp(39);
    }

    public void setBmp39(InspectionBmpValue bmp)
    {
        setBmp(39,
               bmp);
    }

    public InspectionBmpValue getBmp40()
    {
        return getBmp(40);
    }

    public void setBmp40(InspectionBmpValue bmp)
    {
        setBmp(40,
               bmp);
    }

    public InspectionBmpValue getBmp41()
    {
        return getBmp(41);
    }

    public void setBmp41(InspectionBmpValue bmp)
    {
        setBmp(41,
               bmp);
    }

    public InspectionBmpValue getBmp42()
    {
        return getBmp(42);
    }

    public void setBmp42(InspectionBmpValue bmp)
    {
        setBmp(42,
               bmp);
    }

    public InspectionBmpValue getBmp43()
    {
        return getBmp(43);
    }

    public void setBmp43(InspectionBmpValue bmp)
    {
        setBmp(43,
               bmp);
    }

    public InspectionBmpValue getBmp44()
    {
        return getBmp(44);
    }

    public void setBmp44(InspectionBmpValue bmp)
    {
        setBmp(44,
               bmp);
    }

    public InspectionBmpValue getBmp45()
    {
        return getBmp(45);
    }

    public void setBmp45(InspectionBmpValue bmp)
    {
        setBmp(45,
               bmp);
    }

    public InspectionBmpValue getBmp46()
    {
        return getBmp(46);
    }

    public void setBmp46(InspectionBmpValue bmp)
    {
        setBmp(46,
               bmp);
    }

    public InspectionBmpValue getBmp47()
    {
        return getBmp(47);
    }

    public void setBmp47(InspectionBmpValue bmp)
    {
        setBmp(47,
               bmp);
    }

    public InspectionBmpValue getBmp48()
    {
        return getBmp(48);
    }

    public void setBmp48(InspectionBmpValue bmp)
    {
        setBmp(48,
               bmp);
    }

    public InspectionBmpValue getBmp49()
    {
        return getBmp(49);
    }

    public void setBmp49(InspectionBmpValue bmp)
    {
        setBmp(49,
               bmp);
    }

    public InspectionBmpValue getBmp50()
    {
        return getBmp(50);
    }

    public void setBmp50(InspectionBmpValue bmp)
    {
        setBmp(50,
               bmp);
    }

    public InspectionBmpValue getBmp51()
    {
        return getBmp(51);
    }

    public void setBmp51(InspectionBmpValue bmp)
    {
        setBmp(51,
               bmp);
    }

    public InspectionBmpValue getBmp52()
    {
        return getBmp(52);
    }

    public void setBmp52(InspectionBmpValue bmp)
    {
        setBmp(52,
               bmp);
    }

    public InspectionBmpValue getBmp53()
    {
        return getBmp(53);
    }

    public void setBmp53(InspectionBmpValue bmp)
    {
        setBmp(53,
               bmp);
    }

    public InspectionBmpValue getBmp54()
    {
        return getBmp(54);
    }

    public void setBmp54(InspectionBmpValue bmp)
    {
        setBmp(54,
               bmp);
    }

    public InspectionBmpValue getBmp55()
    {
        return getBmp(55);
    }

    public void setBmp55(InspectionBmpValue bmp)
    {
        setBmp(55,
               bmp);
    }

    public InspectionBmpValue getBmp56()
    {
        return getBmp(56);
    }

    public void setBmp56(InspectionBmpValue bmp)
    {
        setBmp(56,
               bmp);
    }

    public InspectionBmpValue getBmp57()
    {
        return getBmp(57);
    }

    public void setBmp57(InspectionBmpValue bmp)
    {
        setBmp(57,
               bmp);
    }

    public InspectionBmpValue getBmp58()
    {
        return getBmp(58);
    }

    public void setBmp58(InspectionBmpValue bmp)
    {
        setBmp(58,
               bmp);
    }

    public InspectionBmpValue getBmp59()
    {
        return getBmp(59);
    }

    public void setBmp59(InspectionBmpValue bmp)
    {
        setBmp(59,
               bmp);
    }

    public InspectionBmpValue getBmp60()
    {
        return getBmp(60);
    }

    public void setBmp60(InspectionBmpValue bmp)
    {
        setBmp(60,
               bmp);
    }

    public InspectionBmpValue getBmp61()
    {
        return getBmp(61);
    }

    public void setBmp61(InspectionBmpValue bmp)
    {
        setBmp(61,
               bmp);
    }

    public InspectionBmpValue getBmp62()
    {
        return getBmp(62);
    }

    public void setBmp62(InspectionBmpValue bmp)
    {
        setBmp(62,
               bmp);
    }

    public InspectionBmpValue getBmp63()
    {
        return getBmp(63);
    }

    public void setBmp63(InspectionBmpValue bmp)
    {
        setBmp(63,
               bmp);
    }

    public InspectionBmpValue getBmp64()
    {
        return getBmp(64);
    }

    public void setBmp64(InspectionBmpValue bmp)
    {
        setBmp(64,
               bmp);
    }

    public InspectionBmpValue getBmp65()
    {
        return getBmp(65);
    }

    public void setBmp65(InspectionBmpValue bmp)
    {
        setBmp(65,
               bmp);
    }

    public InspectionBmpValue getBmp66()
    {
        return getBmp(66);
    }

    public void setBmp66(InspectionBmpValue bmp)
    {
        setBmp(66,
               bmp);
    }

    public InspectionBmpValue getBmp67()
    {
        return getBmp(67);
    }

    public void setBmp67(InspectionBmpValue bmp)
    {
        setBmp(67,
               bmp);
    }

    public InspectionBmpValue getBmp68()
    {
        return getBmp(68);
    }

    public void setBmp68(InspectionBmpValue bmp)
    {
        setBmp(68,
               bmp);
    }

    public InspectionBmpValue getBmp69()
    {
        return getBmp(69);
    }

    public void setBmp69(InspectionBmpValue bmp)
    {
        setBmp(69,
               bmp);
    }

    public InspectionBmpValue getBmp70()
    {
        return getBmp(70);
    }

    public void setBmp70(InspectionBmpValue bmp)
    {
        setBmp(70,
               bmp);
    }

    public InspectionBmpValue getBmp71()
    {
        return getBmp(71);
    }

    public void setBmp71(InspectionBmpValue bmp)
    {
        setBmp(71,
               bmp);
    }

    public InspectionBmpValue getBmp72()
    {
        return getBmp(72);
    }

    public void setBmp72(InspectionBmpValue bmp)
    {
        setBmp(72,
               bmp);
    }

    public InspectionBmpValue getBmp73()
    {
        return getBmp(73);
    }

    public void setBmp73(InspectionBmpValue bmp)
    {
        setBmp(73,
               bmp);
    }

    public InspectionBmpValue getBmp74()
    {
        return getBmp(74);
    }

    public void setBmp74(InspectionBmpValue bmp)
    {
        setBmp(74,
               bmp);
    }

    public InspectionBmpValue getBmp75()
    {
        return getBmp(75);
    }

    public void setBmp75(InspectionBmpValue bmp)
    {
        setBmp(75,
               bmp);
    }

    public InspectionBmpValue getBmp76()
    {
        return getBmp(76);
    }

    public void setBmp76(InspectionBmpValue bmp)
    {
        setBmp(76,
               bmp);
    }

    public InspectionBmpValue getBmp77()
    {
        return getBmp(77);
    }

    public void setBmp77(InspectionBmpValue bmp)
    {
        setBmp(77,
               bmp);
    }

    public InspectionBmpValue getBmp78()
    {
        return getBmp(78);
    }

    public void setBmp78(InspectionBmpValue bmp)
    {
        setBmp(78,
               bmp);
    }

    public InspectionBmpValue getBmp79()
    {
        return getBmp(79);
    }

    public void setBmp79(InspectionBmpValue bmp)
    {
        setBmp(79,
               bmp);
    }

    public InspectionBmpValue getBmp80()
    {
        return getBmp(80);
    }

    public void setBmp80(InspectionBmpValue bmp)
    {
        setBmp(80,
               bmp);
    }

    public InspectionBmpValue getBmp81()
    {
        return getBmp(81);
    }

    public void setBmp81(InspectionBmpValue bmp)
    {
        setBmp(81,
               bmp);
    }

    public InspectionBmpValue getBmp82()
    {
        return getBmp(82);
    }

    public void setBmp82(InspectionBmpValue bmp)
    {
        setBmp(82,
               bmp);
    }

    public InspectionBmpValue getBmp83()
    {
        return getBmp(83);
    }

    public void setBmp83(InspectionBmpValue bmp)
    {
        setBmp(83,
               bmp);
    }

    public InspectionBmpValue getBmp84()
    {
        return getBmp(84);
    }

    public void setBmp84(InspectionBmpValue bmp)
    {
        setBmp(84,
               bmp);
    }

    public InspectionBmpValue getBmp85()
    {
        return getBmp(85);
    }

    public void setBmp85(InspectionBmpValue bmp)
    {
        setBmp(85,
               bmp);
    }

    public InspectionBmpValue getBmp86()
    {
        return getBmp(86);
    }

    public void setBmp86(InspectionBmpValue bmp)
    {
        setBmp(86,
               bmp);
    }

    public InspectionBmpValue getBmp87()
    {
        return getBmp(87);
    }

    public void setBmp87(InspectionBmpValue bmp)
    {
        setBmp(87,
               bmp);
    }

    public InspectionBmpValue getBmp88()
    {
        return getBmp(88);
    }

    public void setBmp88(InspectionBmpValue bmp)
    {
        setBmp(88,
               bmp);
    }

    public InspectionBmpValue getBmp89()
    {
        return getBmp(89);
    }

    public void setBmp89(InspectionBmpValue bmp)
    {
        setBmp(89,
               bmp);
    }

    public InspectionBmpValue getBmp90()
    {
        return getBmp(90);
    }

    public void setBmp90(InspectionBmpValue bmp)
    {
        setBmp(90,
               bmp);
    }

    public InspectionBmpValue getBmp91()
    {
        return getBmp(91);
    }

    public void setBmp91(InspectionBmpValue bmp)
    {
        setBmp(91,
               bmp);
    }

    public InspectionBmpValue getBm9p2()
    {
        return getBmp(92);
    }

    public void setBmp92(InspectionBmpValue bmp)
    {
        setBmp(92,
               bmp);
    }

    public InspectionBmpValue getBmp93()
    {
        return getBmp(93);
    }

    public void setBmp93(InspectionBmpValue bmp)
    {
        setBmp(93,
               bmp);
    }

    public InspectionBmpValue getBmp94()
    {
        return getBmp(94);
    }

    public void setBmp94(InspectionBmpValue bmp)
    {
        setBmp(94,
               bmp);
    }

    public InspectionBmpValue getBmp95()
    {
        return getBmp(95);
    }

    public void setBmp95(InspectionBmpValue bmp)
    {
        setBmp(95,
               bmp);
    }

    public InspectionBmpValue getBmp96()
    {
        return getBmp(96);
    }

    public void setBmp96(InspectionBmpValue bmp)
    {
        setBmp(96,
               bmp);
    }

    public InspectionBmpValue getBmp97()
    {
        return getBmp(97);
    }

    public void setBmp97(InspectionBmpValue bmp)
    {
        setBmp(97,
               bmp);
    }

    public InspectionBmpValue getBmp98()
    {
        return getBmp(98);
    }

    public void setBmp98(InspectionBmpValue bmp)
    {
        setBmp(98,
               bmp);
    }

    public InspectionBmpValue getBmp99()
    {
        return getBmp(99);
    }

    public void setBmp99(InspectionBmpValue bmp)
    {
        setBmp(99,
               bmp);
    }
}
