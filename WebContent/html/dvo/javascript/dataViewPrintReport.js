var reportReply;
var reportOpener;
var reportWindow;
function reportInit()
{
    if (reportWindow.window.init
        == null)
    {
        setTimeout('reportInit()',
                   250);
    }
    else
    {
        reportWindow.window.init(reportReply,
                                 reportOpener);
        reportWindow.window.focus();
    }
}
function PrintReport()
{
    hideLayer("measureBox");
    t =
    parent.MapFrame;
    reportReply =
    t.myLastParcelSelected;
    reportOpener =
    t;
    parent.MapFrame.getMapForSize(395,
                                  390);
    reportWindow =
    open(appDir
             + "parcelReport.jsp",
         "ParcelReport");
    setTimeout('reportInit()',
               250);
}