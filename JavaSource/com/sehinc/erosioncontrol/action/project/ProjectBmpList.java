package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.value.project.ProjectBmpValue;
import org.apache.log4j.Logger;

import java.util.Hashtable;

public class ProjectBmpList
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectBmpList.class);
    private
    Hashtable
        bmps =
        new Hashtable();

    private ProjectBmpValue getBmp(int index)
    {
        if (this.bmps
                .get(new Integer(index))
            == null)
        {
            this.bmps
                .put(new Integer(index),
                     new ProjectBmpValue());
        }
        return (ProjectBmpValue) this.bmps
            .get(new Integer(index));
    }

    private void setBmp(int index, ProjectBmpValue bmp)
    {
        this.bmps
            .put(new Integer(index),
                 bmp);
    }

    public Hashtable getAllBmps()
    {
        return bmps;
    }

    public ProjectBmpValue getBmp0()
    {
        return getBmp(0);
    }

    public void setBmp0(ProjectBmpValue bmp)
    {
        setBmp(0,
               bmp);
    }

    public ProjectBmpValue getBmp1()
    {
        return getBmp(1);
    }

    public void setBmp1(ProjectBmpValue bmp)
    {
        setBmp(1,
               bmp);
    }

    public ProjectBmpValue getBmp2()
    {
        return getBmp(2);
    }

    public void setBmp2(ProjectBmpValue bmp)
    {
        setBmp(2,
               bmp);
    }

    public ProjectBmpValue getBmp3()
    {
        return getBmp(3);
    }

    public void setBmp3(ProjectBmpValue bmp)
    {
        setBmp(3,
               bmp);
    }

    public ProjectBmpValue getBmp4()
    {
        return getBmp(4);
    }

    public void setBmp4(ProjectBmpValue bmp)
    {
        setBmp(4,
               bmp);
    }

    public ProjectBmpValue getBmp5()
    {
        return getBmp(5);
    }

    public void setBmp5(ProjectBmpValue bmp)
    {
        setBmp(5,
               bmp);
    }

    public ProjectBmpValue getBmp6()
    {
        return getBmp(6);
    }

    public void setBmp6(ProjectBmpValue bmp)
    {
        setBmp(6,
               bmp);
    }

    public ProjectBmpValue getBmp7()
    {
        return getBmp(7);
    }

    public void setBmp7(ProjectBmpValue bmp)
    {
        setBmp(7,
               bmp);
    }

    public ProjectBmpValue getBmp8()
    {
        return getBmp(8);
    }

    public void setBmp8(ProjectBmpValue bmp)
    {
        setBmp(8,
               bmp);
    }

    public ProjectBmpValue getBmp9()
    {
        return getBmp(9);
    }

    public void setBmp9(ProjectBmpValue bmp)
    {
        setBmp(9,
               bmp);
    }

    public ProjectBmpValue getBmp10()
    {
        return getBmp(10);
    }

    public void setBmp10(ProjectBmpValue bmp)
    {
        setBmp(10,
               bmp);
    }

    public ProjectBmpValue getBmp11()
    {
        return getBmp(11);
    }

    public void setBmp11(ProjectBmpValue bmp)
    {
        setBmp(11,
               bmp);
    }

    public ProjectBmpValue getBmp12()
    {
        return getBmp(12);
    }

    public void setBmp12(ProjectBmpValue bmp)
    {
        setBmp(12,
               bmp);
    }

    public ProjectBmpValue getBmp13()
    {
        return getBmp(13);
    }

    public void setBmp13(ProjectBmpValue bmp)
    {
        setBmp(13,
               bmp);
    }

    public ProjectBmpValue getBmp14()
    {
        return getBmp(14);
    }

    public void setBmp14(ProjectBmpValue bmp)
    {
        setBmp(14,
               bmp);
    }

    public ProjectBmpValue getBmp15()
    {
        return getBmp(15);
    }

    public void setBmp15(ProjectBmpValue bmp)
    {
        setBmp(15,
               bmp);
    }

    public ProjectBmpValue getBmp16()
    {
        return getBmp(16);
    }

    public void setBmp16(ProjectBmpValue bmp)
    {
        setBmp(16,
               bmp);
    }

    public ProjectBmpValue getBmp17()
    {
        return getBmp(17);
    }

    public void setBmp17(ProjectBmpValue bmp)
    {
        setBmp(17,
               bmp);
    }

    public ProjectBmpValue getBmp18()
    {
        return getBmp(18);
    }

    public void setBmp18(ProjectBmpValue bmp)
    {
        setBmp(18,
               bmp);
    }

    public ProjectBmpValue getBmp19()
    {
        return getBmp(19);
    }

    public void setBmp19(ProjectBmpValue bmp)
    {
        setBmp(19,
               bmp);
    }

    public ProjectBmpValue getBmp20()
    {
        return getBmp(20);
    }

    public void setBmp20(ProjectBmpValue bmp)
    {
        setBmp(20,
               bmp);
    }

    public ProjectBmpValue getBmp21()
    {
        return getBmp(21);
    }

    public void setBmp21(ProjectBmpValue bmp)
    {
        setBmp(21,
               bmp);
    }

    public ProjectBmpValue getBmp22()
    {
        return getBmp(22);
    }

    public void setBmp22(ProjectBmpValue bmp)
    {
        setBmp(22,
               bmp);
    }

    public ProjectBmpValue getBmp23()
    {
        return getBmp(23);
    }

    public void setBmp23(ProjectBmpValue bmp)
    {
        setBmp(23,
               bmp);
    }

    public ProjectBmpValue getBmp24()
    {
        return getBmp(24);
    }

    public void setBmp24(ProjectBmpValue bmp)
    {
        setBmp(24,
               bmp);
    }

    public ProjectBmpValue getBmp25()
    {
        return getBmp(25);
    }

    public void setBmp25(ProjectBmpValue bmp)
    {
        setBmp(25,
               bmp);
    }

    public ProjectBmpValue getBmp26()
    {
        return getBmp(26);
    }

    public void setBmp26(ProjectBmpValue bmp)
    {
        setBmp(26,
               bmp);
    }

    public ProjectBmpValue getBmp27()
    {
        return getBmp(27);
    }

    public void setBmp27(ProjectBmpValue bmp)
    {
        setBmp(27,
               bmp);
    }

    public ProjectBmpValue getBmp28()
    {
        return getBmp(28);
    }

    public void setBmp28(ProjectBmpValue bmp)
    {
        setBmp(28,
               bmp);
    }

    public ProjectBmpValue getBmp29()
    {
        return getBmp(29);
    }

    public void setBmp29(ProjectBmpValue bmp)
    {
        setBmp(29,
               bmp);
    }

    public ProjectBmpValue getBmp30()
    {
        return getBmp(30);
    }

    public void setBmp30(ProjectBmpValue bmp)
    {
        setBmp(30,
               bmp);
    }

    public ProjectBmpValue getBmp31()
    {
        return getBmp(31);
    }

    public void setBmp31(ProjectBmpValue bmp)
    {
        setBmp(31,
               bmp);
    }

    public ProjectBmpValue getBmp32()
    {
        return getBmp(32);
    }

    public void setBmp32(ProjectBmpValue bmp)
    {
        setBmp(32,
               bmp);
    }

    public ProjectBmpValue getBmp33()
    {
        return getBmp(33);
    }

    public void setBmp33(ProjectBmpValue bmp)
    {
        setBmp(33,
               bmp);
    }

    public ProjectBmpValue getBmp34()
    {
        return getBmp(34);
    }

    public void setBmp34(ProjectBmpValue bmp)
    {
        setBmp(34,
               bmp);
    }

    public ProjectBmpValue getBmp35()
    {
        return getBmp(35);
    }

    public void setBmp35(ProjectBmpValue bmp)
    {
        setBmp(35,
               bmp);
    }

    public ProjectBmpValue getBmp36()
    {
        return getBmp(36);
    }

    public void setBmp36(ProjectBmpValue bmp)
    {
        setBmp(36,
               bmp);
    }

    public ProjectBmpValue getBmp37()
    {
        return getBmp(37);
    }

    public void setBmp37(ProjectBmpValue bmp)
    {
        setBmp(37,
               bmp);
    }

    public ProjectBmpValue getBmp38()
    {
        return getBmp(38);
    }

    public void setBmp38(ProjectBmpValue bmp)
    {
        setBmp(38,
               bmp);
    }

    public ProjectBmpValue getBmp39()
    {
        return getBmp(39);
    }

    public void setBmp39(ProjectBmpValue bmp)
    {
        setBmp(39,
               bmp);
    }

    public ProjectBmpValue getBmp40()
    {
        return getBmp(40);
    }

    public void setBmp40(ProjectBmpValue bmp)
    {
        setBmp(40,
               bmp);
    }

    public ProjectBmpValue getBmp41()
    {
        return getBmp(41);
    }

    public void setBmp41(ProjectBmpValue bmp)
    {
        setBmp(41,
               bmp);
    }

    public ProjectBmpValue getBmp42()
    {
        return getBmp(42);
    }

    public void setBmp42(ProjectBmpValue bmp)
    {
        setBmp(42,
               bmp);
    }

    public ProjectBmpValue getBmp43()
    {
        return getBmp(43);
    }

    public void setBmp43(ProjectBmpValue bmp)
    {
        setBmp(43,
               bmp);
    }

    public ProjectBmpValue getBmp44()
    {
        return getBmp(44);
    }

    public void setBmp44(ProjectBmpValue bmp)
    {
        setBmp(44,
               bmp);
    }

    public ProjectBmpValue getBmp45()
    {
        return getBmp(45);
    }

    public void setBmp45(ProjectBmpValue bmp)
    {
        setBmp(45,
               bmp);
    }

    public ProjectBmpValue getBmp46()
    {
        return getBmp(46);
    }

    public void setBmp46(ProjectBmpValue bmp)
    {
        setBmp(46,
               bmp);
    }

    public ProjectBmpValue getBmp47()
    {
        return getBmp(47);
    }

    public void setBmp47(ProjectBmpValue bmp)
    {
        setBmp(47,
               bmp);
    }

    public ProjectBmpValue getBmp48()
    {
        return getBmp(48);
    }

    public void setBmp48(ProjectBmpValue bmp)
    {
        setBmp(48,
               bmp);
    }

    public ProjectBmpValue getBmp49()
    {
        return getBmp(49);
    }

    public void setBmp49(ProjectBmpValue bmp)
    {
        setBmp(49,
               bmp);
    }

    public ProjectBmpValue getBmp50()
    {
        return getBmp(50);
    }

    public void setBmp50(ProjectBmpValue bmp)
    {
        setBmp(50,
               bmp);
    }

    public ProjectBmpValue getBmp51()
    {
        return getBmp(51);
    }

    public void setBmp51(ProjectBmpValue bmp)
    {
        setBmp(51,
               bmp);
    }

    public ProjectBmpValue getBmp52()
    {
        return getBmp(52);
    }

    public void setBmp52(ProjectBmpValue bmp)
    {
        setBmp(52,
               bmp);
    }

    public ProjectBmpValue getBmp53()
    {
        return getBmp(53);
    }

    public void setBmp53(ProjectBmpValue bmp)
    {
        setBmp(53,
               bmp);
    }

    public ProjectBmpValue getBmp54()
    {
        return getBmp(54);
    }

    public void setBmp54(ProjectBmpValue bmp)
    {
        setBmp(54,
               bmp);
    }

    public ProjectBmpValue getBmp55()
    {
        return getBmp(55);
    }

    public void setBmp55(ProjectBmpValue bmp)
    {
        setBmp(55,
               bmp);
    }

    public ProjectBmpValue getBmp56()
    {
        return getBmp(56);
    }

    public void setBmp56(ProjectBmpValue bmp)
    {
        setBmp(56,
               bmp);
    }

    public ProjectBmpValue getBmp57()
    {
        return getBmp(57);
    }

    public void setBmp57(ProjectBmpValue bmp)
    {
        setBmp(57,
               bmp);
    }

    public ProjectBmpValue getBmp58()
    {
        return getBmp(58);
    }

    public void setBmp58(ProjectBmpValue bmp)
    {
        setBmp(58,
               bmp);
    }

    public ProjectBmpValue getBmp59()
    {
        return getBmp(59);
    }

    public void setBmp59(ProjectBmpValue bmp)
    {
        setBmp(59,
               bmp);
    }

    public ProjectBmpValue getBmp60()
    {
        return getBmp(60);
    }

    public void setBmp60(ProjectBmpValue bmp)
    {
        setBmp(60,
               bmp);
    }

    public ProjectBmpValue getBmp61()
    {
        return getBmp(61);
    }

    public void setBmp61(ProjectBmpValue bmp)
    {
        setBmp(61,
               bmp);
    }

    public ProjectBmpValue getBmp62()
    {
        return getBmp(62);
    }

    public void setBmp62(ProjectBmpValue bmp)
    {
        setBmp(62,
               bmp);
    }

    public ProjectBmpValue getBmp63()
    {
        return getBmp(63);
    }

    public void setBmp63(ProjectBmpValue bmp)
    {
        setBmp(63,
               bmp);
    }

    public ProjectBmpValue getBmp64()
    {
        return getBmp(64);
    }

    public void setBmp64(ProjectBmpValue bmp)
    {
        setBmp(64,
               bmp);
    }

    public ProjectBmpValue getBmp65()
    {
        return getBmp(65);
    }

    public void setBmp65(ProjectBmpValue bmp)
    {
        setBmp(65,
               bmp);
    }

    public ProjectBmpValue getBmp66()
    {
        return getBmp(66);
    }

    public void setBmp66(ProjectBmpValue bmp)
    {
        setBmp(66,
               bmp);
    }

    public ProjectBmpValue getBmp67()
    {
        return getBmp(67);
    }

    public void setBmp67(ProjectBmpValue bmp)
    {
        setBmp(67,
               bmp);
    }

    public ProjectBmpValue getBmp68()
    {
        return getBmp(68);
    }

    public void setBmp68(ProjectBmpValue bmp)
    {
        setBmp(68,
               bmp);
    }

    public ProjectBmpValue getBmp69()
    {
        return getBmp(69);
    }

    public void setBmp69(ProjectBmpValue bmp)
    {
        setBmp(69,
               bmp);
    }

    public ProjectBmpValue getBmp70()
    {
        return getBmp(70);
    }

    public void setBmp70(ProjectBmpValue bmp)
    {
        setBmp(70,
               bmp);
    }

    public ProjectBmpValue getBmp71()
    {
        return getBmp(71);
    }

    public void setBmp71(ProjectBmpValue bmp)
    {
        setBmp(71,
               bmp);
    }

    public ProjectBmpValue getBmp72()
    {
        return getBmp(72);
    }

    public void setBmp72(ProjectBmpValue bmp)
    {
        setBmp(72,
               bmp);
    }

    public ProjectBmpValue getBmp73()
    {
        return getBmp(73);
    }

    public void setBmp73(ProjectBmpValue bmp)
    {
        setBmp(73,
               bmp);
    }

    public ProjectBmpValue getBmp74()
    {
        return getBmp(74);
    }

    public void setBmp74(ProjectBmpValue bmp)
    {
        setBmp(74,
               bmp);
    }

    public ProjectBmpValue getBmp75()
    {
        return getBmp(75);
    }

    public void setBmp75(ProjectBmpValue bmp)
    {
        setBmp(75,
               bmp);
    }

    public ProjectBmpValue getBmp76()
    {
        return getBmp(76);
    }

    public void setBmp76(ProjectBmpValue bmp)
    {
        setBmp(76,
               bmp);
    }

    public ProjectBmpValue getBmp77()
    {
        return getBmp(77);
    }

    public void setBmp77(ProjectBmpValue bmp)
    {
        setBmp(77,
               bmp);
    }

    public ProjectBmpValue getBmp78()
    {
        return getBmp(78);
    }

    public void setBmp78(ProjectBmpValue bmp)
    {
        setBmp(78,
               bmp);
    }

    public ProjectBmpValue getBmp79()
    {
        return getBmp(79);
    }

    public void setBmp79(ProjectBmpValue bmp)
    {
        setBmp(79,
               bmp);
    }

    public ProjectBmpValue getBmp80()
    {
        return getBmp(80);
    }

    public void setBmp80(ProjectBmpValue bmp)
    {
        setBmp(80,
               bmp);
    }

    public ProjectBmpValue getBmp81()
    {
        return getBmp(81);
    }

    public void setBmp81(ProjectBmpValue bmp)
    {
        setBmp(81,
               bmp);
    }

    public ProjectBmpValue getBmp82()
    {
        return getBmp(82);
    }

    public void setBmp82(ProjectBmpValue bmp)
    {
        setBmp(82,
               bmp);
    }

    public ProjectBmpValue getBmp83()
    {
        return getBmp(83);
    }

    public void setBmp83(ProjectBmpValue bmp)
    {
        setBmp(83,
               bmp);
    }

    public ProjectBmpValue getBmp84()
    {
        return getBmp(84);
    }

    public void setBmp84(ProjectBmpValue bmp)
    {
        setBmp(84,
               bmp);
    }

    public ProjectBmpValue getBmp85()
    {
        return getBmp(85);
    }

    public void setBmp85(ProjectBmpValue bmp)
    {
        setBmp(85,
               bmp);
    }

    public ProjectBmpValue getBmp86()
    {
        return getBmp(86);
    }

    public void setBmp86(ProjectBmpValue bmp)
    {
        setBmp(86,
               bmp);
    }

    public ProjectBmpValue getBmp87()
    {
        return getBmp(87);
    }

    public void setBmp87(ProjectBmpValue bmp)
    {
        setBmp(87,
               bmp);
    }

    public ProjectBmpValue getBmp88()
    {
        return getBmp(88);
    }

    public void setBmp88(ProjectBmpValue bmp)
    {
        setBmp(88,
               bmp);
    }

    public ProjectBmpValue getBmp89()
    {
        return getBmp(89);
    }

    public void setBmp89(ProjectBmpValue bmp)
    {
        setBmp(89,
               bmp);
    }

    public ProjectBmpValue getBmp90()
    {
        return getBmp(90);
    }

    public void setBmp90(ProjectBmpValue bmp)
    {
        setBmp(90,
               bmp);
    }

    public ProjectBmpValue getBmp91()
    {
        return getBmp(91);
    }

    public void setBmp91(ProjectBmpValue bmp)
    {
        setBmp(91,
               bmp);
    }

    public ProjectBmpValue getBm9p2()
    {
        return getBmp(92);
    }

    public void setBmp92(ProjectBmpValue bmp)
    {
        setBmp(92,
               bmp);
    }

    public ProjectBmpValue getBmp93()
    {
        return getBmp(93);
    }

    public void setBmp93(ProjectBmpValue bmp)
    {
        setBmp(93,
               bmp);
    }

    public ProjectBmpValue getBmp94()
    {
        return getBmp(94);
    }

    public void setBmp94(ProjectBmpValue bmp)
    {
        setBmp(94,
               bmp);
    }

    public ProjectBmpValue getBmp95()
    {
        return getBmp(95);
    }

    public void setBmp95(ProjectBmpValue bmp)
    {
        setBmp(95,
               bmp);
    }

    public ProjectBmpValue getBmp96()
    {
        return getBmp(96);
    }

    public void setBmp96(ProjectBmpValue bmp)
    {
        setBmp(96,
               bmp);
    }

    public ProjectBmpValue getBmp97()
    {
        return getBmp(97);
    }

    public void setBmp97(ProjectBmpValue bmp)
    {
        setBmp(97,
               bmp);
    }

    public ProjectBmpValue getBmp98()
    {
        return getBmp(98);
    }

    public void setBmp98(ProjectBmpValue bmp)
    {
        setBmp(98,
               bmp);
    }

    public ProjectBmpValue getBmp99()
    {
        return getBmp(99);
    }

    public void setBmp99(ProjectBmpValue bmp)
    {
        setBmp(99,
               bmp);
    }
}
