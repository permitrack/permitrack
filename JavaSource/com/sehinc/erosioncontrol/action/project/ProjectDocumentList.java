package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.value.project.ProjectDocumentValue;
import org.apache.log4j.Logger;

import java.util.Hashtable;

public class ProjectDocumentList
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectDocumentList.class);
    private
    Hashtable
        documents =
        new Hashtable();

    private ProjectDocumentValue getDocument(int index)
    {
        if (this.documents
                .get(new Integer(index))
            == null)
        {
            this.documents
                .put(new Integer(index),
                     new ProjectDocumentValue());
        }
        return (ProjectDocumentValue) this.documents
            .get(new Integer(index));
    }

    private void setDocument(int index, ProjectDocumentValue document)
    {
        this.documents
            .put(new Integer(index),
                 document);
    }

    public Hashtable getAllDocuments()
    {
        return documents;
    }

    public ProjectDocumentValue getDocument0()
    {
        return getDocument(0);
    }

    public void setDocument0(ProjectDocumentValue document)
    {
        setDocument(0,
                    document);
    }

    public ProjectDocumentValue getDocument1()
    {
        return getDocument(1);
    }

    public void setDocument1(ProjectDocumentValue document)
    {
        setDocument(1,
                    document);
    }

    public ProjectDocumentValue getDocument2()
    {
        return getDocument(2);
    }

    public void setDocument2(ProjectDocumentValue document)
    {
        setDocument(2,
                    document);
    }

    public ProjectDocumentValue getDocument3()
    {
        return getDocument(3);
    }

    public void setDocument3(ProjectDocumentValue document)
    {
        setDocument(3,
                    document);
    }

    public ProjectDocumentValue getDocument4()
    {
        return getDocument(4);
    }

    public void setDocument4(ProjectDocumentValue document)
    {
        setDocument(4,
                    document);
    }

    public ProjectDocumentValue getDocument5()
    {
        return getDocument(5);
    }

    public void setDocument5(ProjectDocumentValue document)
    {
        setDocument(5,
                    document);
    }

    public ProjectDocumentValue getDocument6()
    {
        return getDocument(6);
    }

    public void setDocument6(ProjectDocumentValue document)
    {
        setDocument(6,
                    document);
    }

    public ProjectDocumentValue getDocument7()
    {
        return getDocument(7);
    }

    public void setDocument7(ProjectDocumentValue document)
    {
        setDocument(7,
                    document);
    }

    public ProjectDocumentValue getDocument8()
    {
        return getDocument(8);
    }

    public void setDocument8(ProjectDocumentValue document)
    {
        setDocument(8,
                    document);
    }

    public ProjectDocumentValue getDocument9()
    {
        return getDocument(9);
    }

    public void setDocument9(ProjectDocumentValue document)
    {
        setDocument(9,
                    document);
    }

    public ProjectDocumentValue getDocument10()
    {
        return getDocument(10);
    }

    public void setDocument10(ProjectDocumentValue document)
    {
        setDocument(10,
                    document);
    }

    public ProjectDocumentValue getDocument11()
    {
        return getDocument(11);
    }

    public void setDocument11(ProjectDocumentValue document)
    {
        setDocument(11,
                    document);
    }

    public ProjectDocumentValue getDocument12()
    {
        return getDocument(12);
    }

    public void setDocument12(ProjectDocumentValue document)
    {
        setDocument(12,
                    document);
    }

    public ProjectDocumentValue getDocument13()
    {
        return getDocument(13);
    }

    public void setDocument13(ProjectDocumentValue document)
    {
        setDocument(13,
                    document);
    }

    public ProjectDocumentValue getDocument14()
    {
        return getDocument(14);
    }

    public void setDocument14(ProjectDocumentValue document)
    {
        setDocument(14,
                    document);
    }

    public ProjectDocumentValue getDocument15()
    {
        return getDocument(15);
    }

    public void setDocument15(ProjectDocumentValue document)
    {
        setDocument(15,
                    document);
    }

    public ProjectDocumentValue getDocument16()
    {
        return getDocument(16);
    }

    public void setDocument16(ProjectDocumentValue document)
    {
        setDocument(16,
                    document);
    }

    public ProjectDocumentValue getDocument17()
    {
        return getDocument(17);
    }

    public void setDocument17(ProjectDocumentValue document)
    {
        setDocument(17,
                    document);
    }

    public ProjectDocumentValue getDocument18()
    {
        return getDocument(18);
    }

    public void setDocument18(ProjectDocumentValue document)
    {
        setDocument(18,
                    document);
    }

    public ProjectDocumentValue getDocument19()
    {
        return getDocument(19);
    }

    public void setDocument19(ProjectDocumentValue document)
    {
        setDocument(19,
                    document);
    }
}
