package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.value.project.ProjectContactValue;
import org.apache.log4j.Logger;

import java.util.Hashtable;

public class ProjectContactList
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectContactList.class);
    private
    Hashtable
        contacts =
        new Hashtable();

    private ProjectContactValue getContact(int index)
    {
        if (this.contacts
                .get(new Integer(index))
            == null)
        {
            this.contacts
                .put(index,
                     new ProjectContactValue());
        }
        return (ProjectContactValue) this.contacts
            .get(new Integer(index));
    }

    private void setContact(int index, ProjectContactValue contact)
    {
        this.contacts
            .put(index,
                 contact);
    }

    public Hashtable getAllContacts()
    {
        return contacts;
    }

    public ProjectContactValue getContact0()
    {
        return getContact(0);
    }

    public void setContact0(ProjectContactValue contact)
    {
        setContact(0,
                   contact);
    }

    public ProjectContactValue getContact1()
    {
        return getContact(1);
    }

    public void setContact1(ProjectContactValue contact)
    {
        setContact(1,
                   contact);
    }

    public ProjectContactValue getContact2()
    {
        return getContact(2);
    }

    public void setContact2(ProjectContactValue contact)
    {
        setContact(2,
                   contact);
    }

    public ProjectContactValue getContact3()
    {
        return getContact(3);
    }

    public void setContact3(ProjectContactValue contact)
    {
        setContact(3,
                   contact);
    }

    public ProjectContactValue getContact4()
    {
        return getContact(4);
    }

    public void setContact4(ProjectContactValue contact)
    {
        setContact(4,
                   contact);
    }

    public ProjectContactValue getContact5()
    {
        return getContact(5);
    }

    public void setContact5(ProjectContactValue contact)
    {
        setContact(5,
                   contact);
    }

    public ProjectContactValue getContact6()
    {
        return getContact(6);
    }

    public void setContact6(ProjectContactValue contact)
    {
        setContact(6,
                   contact);
    }

    public ProjectContactValue getContact7()
    {
        return getContact(7);
    }

    public void setContact7(ProjectContactValue contact)
    {
        setContact(7,
                   contact);
    }

    public ProjectContactValue getContact8()
    {
        return getContact(8);
    }

    public void setContact8(ProjectContactValue contact)
    {
        setContact(8,
                   contact);
    }

    public ProjectContactValue getContact9()
    {
        return getContact(9);
    }

    public void setContact9(ProjectContactValue contact)
    {
        setContact(9,
                   contact);
    }

    public ProjectContactValue getContact10()
    {
        return getContact(10);
    }

    public void setContact10(ProjectContactValue contact)
    {
        setContact(10,
                   contact);
    }

    public ProjectContactValue getContact11()
    {
        return getContact(11);
    }

    public void setContact11(ProjectContactValue contact)
    {
        setContact(11,
                   contact);
    }

    public ProjectContactValue getContact12()
    {
        return getContact(12);
    }

    public void setContact12(ProjectContactValue contact)
    {
        setContact(12,
                   contact);
    }

    public ProjectContactValue getContact13()
    {
        return getContact(13);
    }

    public void setContact13(ProjectContactValue contact)
    {
        setContact(13,
                   contact);
    }

    public ProjectContactValue getContact14()
    {
        return getContact(14);
    }

    public void setContact14(ProjectContactValue contact)
    {
        setContact(14,
                   contact);
    }

    public ProjectContactValue getContact15()
    {
        return getContact(15);
    }

    public void setContact15(ProjectContactValue contact)
    {
        setContact(15,
                   contact);
    }

    public ProjectContactValue getContact16()
    {
        return getContact(16);
    }

    public void setContact16(ProjectContactValue contact)
    {
        setContact(16,
                   contact);
    }

    public ProjectContactValue getContact17()
    {
        return getContact(17);
    }

    public void setContact17(ProjectContactValue contact)
    {
        setContact(17,
                   contact);
    }

    public ProjectContactValue getContact18()
    {
        return getContact(18);
    }

    public void setContact18(ProjectContactValue contact)
    {
        setContact(18,
                   contact);
    }

    public ProjectContactValue getContact19()
    {
        return getContact(19);
    }

    public void setContact19(ProjectContactValue contact)
    {
        setContact(19,
                   contact);
    }

    public ProjectContactValue getContact20()
    {
        return getContact(20);
    }

    public void setContact20(ProjectContactValue contact)
    {
        setContact(20,
                   contact);
    }

    public ProjectContactValue getContact21()
    {
        return getContact(21);
    }

    public void setContact21(ProjectContactValue contact)
    {
        setContact(21,
                   contact);
    }

    public ProjectContactValue getContact22()
    {
        return getContact(22);
    }

    public void setContact22(ProjectContactValue contact)
    {
        setContact(22,
                   contact);
    }

    public ProjectContactValue getContact23()
    {
        return getContact(23);
    }

    public void setContact23(ProjectContactValue contact)
    {
        setContact(23,
                   contact);
    }

    public ProjectContactValue getContact24()
    {
        return getContact(24);
    }

    public void setContact24(ProjectContactValue contact)
    {
        setContact(24,
                   contact);
    }

    public ProjectContactValue getContact25()
    {
        return getContact(25);
    }

    public void setContact25(ProjectContactValue contact)
    {
        setContact(25,
                   contact);
    }

    public ProjectContactValue getContact26()
    {
        return getContact(26);
    }

    public void setContact26(ProjectContactValue contact)
    {
        setContact(26,
                   contact);
    }

    public ProjectContactValue getContact27()
    {
        return getContact(27);
    }

    public void setContact27(ProjectContactValue contact)
    {
        setContact(27,
                   contact);
    }

    public ProjectContactValue getContact28()
    {
        return getContact(28);
    }

    public void setContact28(ProjectContactValue contact)
    {
        setContact(28,
                   contact);
    }

    public ProjectContactValue getContact29()
    {
        return getContact(29);
    }

    public void setContact29(ProjectContactValue contact)
    {
        setContact(29,
                   contact);
    }

    public ProjectContactValue getContact30()
    {
        return getContact(30);
    }

    public void setContact30(ProjectContactValue contact)
    {
        setContact(30,
                   contact);
    }

    public ProjectContactValue getContact31()
    {
        return getContact(31);
    }

    public void setContact31(ProjectContactValue contact)
    {
        setContact(31,
                   contact);
    }

    public ProjectContactValue getContact32()
    {
        return getContact(32);
    }

    public void setContact32(ProjectContactValue contact)
    {
        setContact(32,
                   contact);
    }

    public ProjectContactValue getContact33()
    {
        return getContact(33);
    }

    public void setContact33(ProjectContactValue contact)
    {
        setContact(33,
                   contact);
    }

    public ProjectContactValue getContact34()
    {
        return getContact(34);
    }

    public void setContact34(ProjectContactValue contact)
    {
        setContact(34,
                   contact);
    }

    public ProjectContactValue getContact35()
    {
        return getContact(35);
    }

    public void setContact35(ProjectContactValue contact)
    {
        setContact(35,
                   contact);
    }

    public ProjectContactValue getContact36()
    {
        return getContact(36);
    }

    public void setContact36(ProjectContactValue contact)
    {
        setContact(36,
                   contact);
    }

    public ProjectContactValue getContact37()
    {
        return getContact(37);
    }

    public void setContact37(ProjectContactValue contact)
    {
        setContact(37,
                   contact);
    }

    public ProjectContactValue getContact38()
    {
        return getContact(38);
    }

    public void setContact38(ProjectContactValue contact)
    {
        setContact(38,
                   contact);
    }

    public ProjectContactValue getContact39()
    {
        return getContact(39);
    }

    public void setContact39(ProjectContactValue contact)
    {
        setContact(39,
                   contact);
    }

    public ProjectContactValue getContact40()
    {
        return getContact(40);
    }

    public void setContact40(ProjectContactValue contact)
    {
        setContact(40,
                   contact);
    }

    public ProjectContactValue getContact41()
    {
        return getContact(41);
    }

    public void setContact41(ProjectContactValue contact)
    {
        setContact(41,
                   contact);
    }

    public ProjectContactValue getContact42()
    {
        return getContact(42);
    }

    public void setContact42(ProjectContactValue contact)
    {
        setContact(42,
                   contact);
    }

    public ProjectContactValue getContact43()
    {
        return getContact(43);
    }

    public void setContact43(ProjectContactValue contact)
    {
        setContact(43,
                   contact);
    }

    public ProjectContactValue getContact44()
    {
        return getContact(44);
    }

    public void setContact44(ProjectContactValue contact)
    {
        setContact(44,
                   contact);
    }

    public ProjectContactValue getContact45()
    {
        return getContact(45);
    }

    public void setContact45(ProjectContactValue contact)
    {
        setContact(45,
                   contact);
    }

    public ProjectContactValue getContact46()
    {
        return getContact(46);
    }

    public void setContact46(ProjectContactValue contact)
    {
        setContact(46,
                   contact);
    }

    public ProjectContactValue getContact47()
    {
        return getContact(47);
    }

    public void setContact47(ProjectContactValue contact)
    {
        setContact(47,
                   contact);
    }

    public ProjectContactValue getContact48()
    {
        return getContact(48);
    }

    public void setContact48(ProjectContactValue contact)
    {
        setContact(48,
                   contact);
    }

    public ProjectContactValue getContact49()
    {
        return getContact(49);
    }

    public void setContact49(ProjectContactValue contact)
    {
        setContact(49,
                   contact);
    }
}
