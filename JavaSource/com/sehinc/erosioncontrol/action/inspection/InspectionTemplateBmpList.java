package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.value.inspection.InspectionTemplateBmpValue;
import org.apache.log4j.Logger;

import java.util.Hashtable;

public class InspectionTemplateBmpList
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateBmpList.class);
    private
    Hashtable
        bmps =
        new Hashtable();

    private InspectionTemplateBmpValue getBmp(int index)
    {
        if (this.bmps
                .get(new Integer(index))
            == null)
        {
            this.bmps
                .put(new Integer(index),
                     new InspectionTemplateBmpValue());
        }
        return (InspectionTemplateBmpValue) this.bmps
            .get(new Integer(index));
    }

    private void setBmp(int index, InspectionTemplateBmpValue bmp)
    {
        this.bmps
            .put(new Integer(index),
                 bmp);
    }

    public Hashtable getAllBmps()
    {
        return bmps;
    }

    public InspectionTemplateBmpValue getBmp0()
    {
        return getBmp(0);
    }

    public void setBmp0(InspectionTemplateBmpValue bmp)
    {
        setBmp(0,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp1()
    {
        return getBmp(1);
    }

    public void setBmp1(InspectionTemplateBmpValue bmp)
    {
        setBmp(1,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp2()
    {
        return getBmp(2);
    }

    public void setBmp2(InspectionTemplateBmpValue bmp)
    {
        setBmp(2,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp3()
    {
        return getBmp(3);
    }

    public void setBmp3(InspectionTemplateBmpValue bmp)
    {
        setBmp(3,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp4()
    {
        return getBmp(4);
    }

    public void setBmp4(InspectionTemplateBmpValue bmp)
    {
        setBmp(4,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp5()
    {
        return getBmp(5);
    }

    public void setBmp5(InspectionTemplateBmpValue bmp)
    {
        setBmp(5,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp6()
    {
        return getBmp(6);
    }

    public void setBmp6(InspectionTemplateBmpValue bmp)
    {
        setBmp(6,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp7()
    {
        return getBmp(7);
    }

    public void setBmp7(InspectionTemplateBmpValue bmp)
    {
        setBmp(7,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp8()
    {
        return getBmp(8);
    }

    public void setBmp8(InspectionTemplateBmpValue bmp)
    {
        setBmp(8,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp9()
    {
        return getBmp(9);
    }

    public void setBmp9(InspectionTemplateBmpValue bmp)
    {
        setBmp(9,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp10()
    {
        return getBmp(10);
    }

    public void setBmp10(InspectionTemplateBmpValue bmp)
    {
        setBmp(10,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp11()
    {
        return getBmp(11);
    }

    public void setBmp11(InspectionTemplateBmpValue bmp)
    {
        setBmp(11,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp12()
    {
        return getBmp(12);
    }

    public void setBmp12(InspectionTemplateBmpValue bmp)
    {
        setBmp(12,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp13()
    {
        return getBmp(13);
    }

    public void setBmp13(InspectionTemplateBmpValue bmp)
    {
        setBmp(13,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp14()
    {
        return getBmp(14);
    }

    public void setBmp14(InspectionTemplateBmpValue bmp)
    {
        setBmp(14,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp15()
    {
        return getBmp(15);
    }

    public void setBmp15(InspectionTemplateBmpValue bmp)
    {
        setBmp(15,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp16()
    {
        return getBmp(16);
    }

    public void setBmp16(InspectionTemplateBmpValue bmp)
    {
        setBmp(16,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp17()
    {
        return getBmp(17);
    }

    public void setBmp17(InspectionTemplateBmpValue bmp)
    {
        setBmp(17,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp18()
    {
        return getBmp(18);
    }

    public void setBmp18(InspectionTemplateBmpValue bmp)
    {
        setBmp(18,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp19()
    {
        return getBmp(19);
    }

    public void setBmp19(InspectionTemplateBmpValue bmp)
    {
        setBmp(19,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp20()
    {
        return getBmp(20);
    }

    public void setBmp20(InspectionTemplateBmpValue bmp)
    {
        setBmp(20,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp21()
    {
        return getBmp(21);
    }

    public void setBmp21(InspectionTemplateBmpValue bmp)
    {
        setBmp(21,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp22()
    {
        return getBmp(22);
    }

    public void setBmp22(InspectionTemplateBmpValue bmp)
    {
        setBmp(22,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp23()
    {
        return getBmp(23);
    }

    public void setBmp23(InspectionTemplateBmpValue bmp)
    {
        setBmp(23,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp24()
    {
        return getBmp(24);
    }

    public void setBmp24(InspectionTemplateBmpValue bmp)
    {
        setBmp(24,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp25()
    {
        return getBmp(25);
    }

    public void setBmp25(InspectionTemplateBmpValue bmp)
    {
        setBmp(25,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp26()
    {
        return getBmp(26);
    }

    public void setBmp26(InspectionTemplateBmpValue bmp)
    {
        setBmp(26,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp27()
    {
        return getBmp(27);
    }

    public void setBmp27(InspectionTemplateBmpValue bmp)
    {
        setBmp(27,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp28()
    {
        return getBmp(28);
    }

    public void setBmp28(InspectionTemplateBmpValue bmp)
    {
        setBmp(28,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp29()
    {
        return getBmp(29);
    }

    public void setBmp29(InspectionTemplateBmpValue bmp)
    {
        setBmp(29,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp30()
    {
        return getBmp(30);
    }

    public void setBmp30(InspectionTemplateBmpValue bmp)
    {
        setBmp(30,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp31()
    {
        return getBmp(31);
    }

    public void setBmp31(InspectionTemplateBmpValue bmp)
    {
        setBmp(31,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp32()
    {
        return getBmp(32);
    }

    public void setBmp32(InspectionTemplateBmpValue bmp)
    {
        setBmp(32,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp33()
    {
        return getBmp(33);
    }

    public void setBmp33(InspectionTemplateBmpValue bmp)
    {
        setBmp(33,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp34()
    {
        return getBmp(34);
    }

    public void setBmp34(InspectionTemplateBmpValue bmp)
    {
        setBmp(34,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp35()
    {
        return getBmp(35);
    }

    public void setBmp35(InspectionTemplateBmpValue bmp)
    {
        setBmp(35,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp36()
    {
        return getBmp(36);
    }

    public void setBmp36(InspectionTemplateBmpValue bmp)
    {
        setBmp(36,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp37()
    {
        return getBmp(37);
    }

    public void setBmp37(InspectionTemplateBmpValue bmp)
    {
        setBmp(37,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp38()
    {
        return getBmp(38);
    }

    public void setBmp38(InspectionTemplateBmpValue bmp)
    {
        setBmp(38,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp39()
    {
        return getBmp(39);
    }

    public void setBmp39(InspectionTemplateBmpValue bmp)
    {
        setBmp(39,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp40()
    {
        return getBmp(40);
    }

    public void setBmp40(InspectionTemplateBmpValue bmp)
    {
        setBmp(40,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp41()
    {
        return getBmp(41);
    }

    public void setBmp41(InspectionTemplateBmpValue bmp)
    {
        setBmp(41,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp42()
    {
        return getBmp(42);
    }

    public void setBmp42(InspectionTemplateBmpValue bmp)
    {
        setBmp(42,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp43()
    {
        return getBmp(43);
    }

    public void setBmp43(InspectionTemplateBmpValue bmp)
    {
        setBmp(43,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp44()
    {
        return getBmp(44);
    }

    public void setBmp44(InspectionTemplateBmpValue bmp)
    {
        setBmp(44,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp45()
    {
        return getBmp(45);
    }

    public void setBmp45(InspectionTemplateBmpValue bmp)
    {
        setBmp(45,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp46()
    {
        return getBmp(46);
    }

    public void setBmp46(InspectionTemplateBmpValue bmp)
    {
        setBmp(46,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp47()
    {
        return getBmp(47);
    }

    public void setBmp47(InspectionTemplateBmpValue bmp)
    {
        setBmp(47,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp48()
    {
        return getBmp(48);
    }

    public void setBmp48(InspectionTemplateBmpValue bmp)
    {
        setBmp(48,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp49()
    {
        return getBmp(49);
    }

    public void setBmp49(InspectionTemplateBmpValue bmp)
    {
        setBmp(49,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp50()
    {
        return getBmp(50);
    }

    public void setBmp50(InspectionTemplateBmpValue bmp)
    {
        setBmp(50,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp51()
    {
        return getBmp(51);
    }

    public void setBmp51(InspectionTemplateBmpValue bmp)
    {
        setBmp(51,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp52()
    {
        return getBmp(52);
    }

    public void setBmp52(InspectionTemplateBmpValue bmp)
    {
        setBmp(52,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp53()
    {
        return getBmp(53);
    }

    public void setBmp53(InspectionTemplateBmpValue bmp)
    {
        setBmp(53,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp54()
    {
        return getBmp(54);
    }

    public void setBmp54(InspectionTemplateBmpValue bmp)
    {
        setBmp(54,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp55()
    {
        return getBmp(55);
    }

    public void setBmp55(InspectionTemplateBmpValue bmp)
    {
        setBmp(55,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp56()
    {
        return getBmp(56);
    }

    public void setBmp56(InspectionTemplateBmpValue bmp)
    {
        setBmp(56,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp57()
    {
        return getBmp(57);
    }

    public void setBmp57(InspectionTemplateBmpValue bmp)
    {
        setBmp(57,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp58()
    {
        return getBmp(58);
    }

    public void setBmp58(InspectionTemplateBmpValue bmp)
    {
        setBmp(58,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp59()
    {
        return getBmp(59);
    }

    public void setBmp59(InspectionTemplateBmpValue bmp)
    {
        setBmp(59,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp60()
    {
        return getBmp(60);
    }

    public void setBmp60(InspectionTemplateBmpValue bmp)
    {
        setBmp(60,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp61()
    {
        return getBmp(61);
    }

    public void setBmp61(InspectionTemplateBmpValue bmp)
    {
        setBmp(61,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp62()
    {
        return getBmp(62);
    }

    public void setBmp62(InspectionTemplateBmpValue bmp)
    {
        setBmp(62,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp63()
    {
        return getBmp(63);
    }

    public void setBmp63(InspectionTemplateBmpValue bmp)
    {
        setBmp(63,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp64()
    {
        return getBmp(64);
    }

    public void setBmp64(InspectionTemplateBmpValue bmp)
    {
        setBmp(64,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp65()
    {
        return getBmp(65);
    }

    public void setBmp65(InspectionTemplateBmpValue bmp)
    {
        setBmp(65,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp66()
    {
        return getBmp(66);
    }

    public void setBmp66(InspectionTemplateBmpValue bmp)
    {
        setBmp(66,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp67()
    {
        return getBmp(67);
    }

    public void setBmp67(InspectionTemplateBmpValue bmp)
    {
        setBmp(67,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp68()
    {
        return getBmp(68);
    }

    public void setBmp68(InspectionTemplateBmpValue bmp)
    {
        setBmp(68,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp69()
    {
        return getBmp(69);
    }

    public void setBmp69(InspectionTemplateBmpValue bmp)
    {
        setBmp(69,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp70()
    {
        return getBmp(70);
    }

    public void setBmp70(InspectionTemplateBmpValue bmp)
    {
        setBmp(70,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp71()
    {
        return getBmp(71);
    }

    public void setBmp71(InspectionTemplateBmpValue bmp)
    {
        setBmp(71,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp72()
    {
        return getBmp(72);
    }

    public void setBmp72(InspectionTemplateBmpValue bmp)
    {
        setBmp(72,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp73()
    {
        return getBmp(73);
    }

    public void setBmp73(InspectionTemplateBmpValue bmp)
    {
        setBmp(73,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp74()
    {
        return getBmp(74);
    }

    public void setBmp74(InspectionTemplateBmpValue bmp)
    {
        setBmp(74,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp75()
    {
        return getBmp(75);
    }

    public void setBmp75(InspectionTemplateBmpValue bmp)
    {
        setBmp(75,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp76()
    {
        return getBmp(76);
    }

    public void setBmp76(InspectionTemplateBmpValue bmp)
    {
        setBmp(76,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp77()
    {
        return getBmp(77);
    }

    public void setBmp77(InspectionTemplateBmpValue bmp)
    {
        setBmp(77,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp78()
    {
        return getBmp(78);
    }

    public void setBmp78(InspectionTemplateBmpValue bmp)
    {
        setBmp(78,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp79()
    {
        return getBmp(79);
    }

    public void setBmp79(InspectionTemplateBmpValue bmp)
    {
        setBmp(79,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp80()
    {
        return getBmp(80);
    }

    public void setBmp80(InspectionTemplateBmpValue bmp)
    {
        setBmp(80,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp81()
    {
        return getBmp(81);
    }

    public void setBmp81(InspectionTemplateBmpValue bmp)
    {
        setBmp(81,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp82()
    {
        return getBmp(82);
    }

    public void setBmp82(InspectionTemplateBmpValue bmp)
    {
        setBmp(82,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp83()
    {
        return getBmp(83);
    }

    public void setBmp83(InspectionTemplateBmpValue bmp)
    {
        setBmp(83,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp84()
    {
        return getBmp(84);
    }

    public void setBmp84(InspectionTemplateBmpValue bmp)
    {
        setBmp(84,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp85()
    {
        return getBmp(85);
    }

    public void setBmp85(InspectionTemplateBmpValue bmp)
    {
        setBmp(85,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp86()
    {
        return getBmp(86);
    }

    public void setBmp86(InspectionTemplateBmpValue bmp)
    {
        setBmp(86,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp87()
    {
        return getBmp(87);
    }

    public void setBmp87(InspectionTemplateBmpValue bmp)
    {
        setBmp(87,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp88()
    {
        return getBmp(88);
    }

    public void setBmp88(InspectionTemplateBmpValue bmp)
    {
        setBmp(88,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp89()
    {
        return getBmp(89);
    }

    public void setBmp89(InspectionTemplateBmpValue bmp)
    {
        setBmp(89,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp90()
    {
        return getBmp(90);
    }

    public void setBmp90(InspectionTemplateBmpValue bmp)
    {
        setBmp(90,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp91()
    {
        return getBmp(91);
    }

    public void setBmp91(InspectionTemplateBmpValue bmp)
    {
        setBmp(91,
               bmp);
    }

    public InspectionTemplateBmpValue getBm9p2()
    {
        return getBmp(92);
    }

    public void setBmp92(InspectionTemplateBmpValue bmp)
    {
        setBmp(92,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp93()
    {
        return getBmp(93);
    }

    public void setBmp93(InspectionTemplateBmpValue bmp)
    {
        setBmp(93,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp94()
    {
        return getBmp(94);
    }

    public void setBmp94(InspectionTemplateBmpValue bmp)
    {
        setBmp(94,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp95()
    {
        return getBmp(95);
    }

    public void setBmp95(InspectionTemplateBmpValue bmp)
    {
        setBmp(95,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp96()
    {
        return getBmp(96);
    }

    public void setBmp96(InspectionTemplateBmpValue bmp)
    {
        setBmp(96,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp97()
    {
        return getBmp(97);
    }

    public void setBmp97(InspectionTemplateBmpValue bmp)
    {
        setBmp(97,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp98()
    {
        return getBmp(98);
    }

    public void setBmp98(InspectionTemplateBmpValue bmp)
    {
        setBmp(98,
               bmp);
    }

    public InspectionTemplateBmpValue getBmp99()
    {
        return getBmp(99);
    }

    public void setBmp99(InspectionTemplateBmpValue bmp)
    {
        setBmp(99,
               bmp);
    }
}
