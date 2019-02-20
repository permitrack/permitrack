package com.sehinc.dataview;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class MailingLabelBuilder
{
    private static
    Logger
        LOG =
        Logger.getLogger(MailingLabelBuilder.class);
    private
    Document
        document;
    private
    PdfWriter
        writer;
    // configure document for Avery 5160
    int
        topMargin =
        36;
    int
        bottomMargin =
        36;
    float
        rightMargin =
        14f;
    float
        leftMargin =
        14f;
    int
        maximumColumns =
        3;
    int
        maximumRows =
        10;
    int
        horizontalSpace =
        12;
    int
        verticalSpace =
        2;
    int
        labelTopBottomMargin =
        18;
    int
        labelLeftRightMargin =
        15;
    int
        currentRow =
        1;
    int
        currentColumn =
        1;
    float
        labelWidth;
    float
        labelHeight;
    /*
        public MailingLabelBuilder() throws DocumentException, IOException {
            this(new FileOutputStream("c:/temp/HelloWorld" + System.currentTimeMillis() + ".pdf"));
        }
    */

    public MailingLabelBuilder(OutputStream output)
        throws DocumentException, IOException
    {
        document =
            new Document(PageSize.LETTER);
        document.setMargins(0,
                            0,
                            0,
                            0);
        writer =
            PdfWriter.getInstance(document,
                                  output);
        document.addTitle("Mailing Labels");
        document.addAuthor("SEH, Inc.");
        document.addSubject("Mailing Labels.");
        document.addKeywords("metadata");
        document.addCreator("DataView Online");
        labelWidth =
            findLabelWidth();
        labelHeight =
            findLabelHeight();
        document.open();
    }

    public void buildLabels(List labelEntries)
        throws DocumentException
    {
        Iterator
            labels =
            labelEntries.iterator();
        try
        {
            while (labels.hasNext())
            {
                List
                    label =
                    (List) labels.next();
                addLabel(label);
            }
            trimPage();
            while (currentColumn
                   <= maximumColumns)
            {
                clearLabelSpace();
                currentColumn++;
            }
        }
        catch (IOException e)
        {
            LOG.debug("Exception while building labels",
                      e);
        }
        document.close();
    }

    private float findLabelHeight()
    {
        return (document.getPageSize()
                    .getHeight()
                - (topMargin
                   + bottomMargin)
                - ((maximumRows
                    - 1)
                   * verticalSpace))
               / maximumRows;
    }

    private float findLabelWidth()
    {
        return (document.getPageSize()
                    .getWidth()
                - (rightMargin
                   + leftMargin)
                - ((maximumColumns
                    - 1)
                   * horizontalSpace))
               / maximumColumns;
    }

    private void addLabel(List label)
        throws DocumentException, IOException
    {
        if (currentRow
            > maximumRows)
        {
            trimPage();
            LOG.debug(" Writing new page: "
                      + document.newPage());
            currentRow =
                1;
        }
        addToPage(label);
        currentColumn =
            currentColumn
            + 1;
        if (currentColumn
            > maximumColumns)
        {
            currentRow =
                currentRow
                + 1;
            currentColumn =
                1;
        }
    }

    private void trimPage()
        throws DocumentException, IOException
    {
        PdfContentByte
            cb =
            writer.getDirectContent();
        setColorWhite(cb);
        float
            width =
            labelWidth
            + horizontalSpace;
        cb.rectangle(width
                     * 3,
                     0,
                     labelLeftRightMargin,
                     document.getPageSize()
                         .getHeight());
        cb.closePathFillStroke();
        setColorBlack(cb);
        cb.beginText();
        BaseFont
            bf =
            BaseFont.createFont(BaseFont.HELVETICA,
                                BaseFont.CP1252,
                                BaseFont.NOT_EMBEDDED);
        cb.setFontAndSize(bf,
                          11);
        String
            text =
            "To ensure proper formatting of these mailing labels, be sure to set your printer margins to 'None' when printing.";
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
                           text,
                           35,
                           document.getPageSize()
                               .getHeight()
                           - 30,
                           0);
        cb.endText();
    }

    private void addToPage(List label)
        throws DocumentException, IOException
    {
        float
            x =
            (currentColumn
             - 1)
            * (labelWidth
               + horizontalSpace)
            + labelLeftRightMargin;
        float
            y =
            (maximumRows
             - (currentRow
                - 1))
            * (labelHeight
               + verticalSpace)
            + labelTopBottomMargin;
        PdfContentByte
            cb =
            writer.getDirectContent();
        clearLabelSpace();
        setColorBlack(cb);
        cb.beginText();
        BaseFont
            bf =
            BaseFont.createFont(BaseFont.HELVETICA,
                                BaseFont.CP1252,
                                BaseFont.NOT_EMBEDDED);
        cb.setFontAndSize(bf,
                          10);
        for (
            int
                i =
                0; i
                   < label.size(); i++)
        {
            String
                text =
                label.get(i)
                    .toString();
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
                               text,
                               x,
                               y
                               - (i
                                  * 12),
                               0);
        }
        cb.endText();
    }

    private PdfContentByte clearLabelSpace()
    {
        float
            x =
            (currentColumn
             - 1)
            * (labelWidth
               + horizontalSpace)
            + labelLeftRightMargin;
        float
            y =
            (maximumRows
             - (currentRow
                - 1))
            * (labelHeight
               + verticalSpace)
            + labelTopBottomMargin;
        // drawing these rectangles is a super-hack because I couldn't figure out how to clip text. This keeps text from a previous
        // label from "overdrawing" into a neighboring label.
        PdfContentByte
            cb =
            writer.getDirectContent();
        setColorWhite(cb);
        cb.rectangle(x
                     - labelLeftRightMargin,
                     y
                     - labelHeight
                     + labelTopBottomMargin,
                     labelWidth,
                     labelHeight);
        cb.closePathFillStroke();
        return cb;
    }

    private void setColorWhite(PdfContentByte cb)
    {
        cb.setRGBColorStrokeF(255f,
                              255f,
                              255f);
        cb.setRGBColorFillF(255f,
                            255f,
                            255f);
    }

    private void setColorBlack(PdfContentByte cb)
    {
        cb.setRGBColorStrokeF(0,
                              0,
                              0);
        cb.setRGBColorFillF(0,
                            0,
                            0);
    }
}
