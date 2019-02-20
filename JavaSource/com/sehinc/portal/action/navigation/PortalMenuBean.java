package com.sehinc.portal.action.navigation;

import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Iterator;

public class PortalMenuBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(PortalMenuBean.class);

    public PortalMenuBean()
    {
    }

    public String render(HttpServletRequest request)
    {
        return render(request,
                      "",
                      null);
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
    {
        return render(request,
                      "",
                      response);
    }

    public String render(HttpServletRequest request, String id, HttpServletResponse response)
    {
        String
            baseContext =
            request.getContextPath();
        LOG.debug("request.getContactPath() = "
                  + baseContext);
        PortalMenu
            portalMenu =
            (PortalMenu) request.getSession()
                .getAttribute(SessionKeys.PORTAL_MENU);
        if (portalMenu
            == null)
        {
            UserValue
                userValue =
                SessionService.getUserValue(request);
            portalMenu =
                PortalUtils.getPortalMenu(userValue,
                                          PortalUtils.getUserMarkup(request));
            request.getSession()
                .setAttribute(SessionKeys.PORTAL_MENU,
                              portalMenu);
            String
                currentItem =
                (String) request.getSession()
                    .getAttribute(SessionKeys.CURRENT_MENU_ITEM);
            if (currentItem
                != null)
            {
                portalMenu.setCurrentItemByName(currentItem);
            }
        }
        StringBuffer
            buffer =
            new StringBuffer();
        StringBuffer
            bufferChild =
            new StringBuffer();
        StringBuffer
            bufferLast =
            new StringBuffer();
        Iterator
            iter =
            portalMenu.getMenuItems()
                .iterator();
        buffer.append("<ul class='")
            .append(id.equalsIgnoreCase("image")
                        ? " thumbnails "
                        : " nav ")
            .append("'>");
        buffer.append("<li class='")
            .append(id.equalsIgnoreCase("image")
                        ? ""
                        : " dropdown ");
        bufferChild.append("<ul class='")
            .append(id.equalsIgnoreCase("image")
                        ? " thumbnails "
                        : " dropdown-menu ")
            .append("'>");
        boolean alreadyDidIt = false;
        while (iter.hasNext())
        {
            PortalMenuItem
                current =
                (PortalMenuItem) iter.next();
            if (!current.getCode().equals("SE") && !current.getCode().equals("AC"))
            {
                if (current.getName()
                    .equals(portalMenu.getCurrentItem()
                                .getName()))
                {
                    buffer.append(" active'>");
                    appendMainButton(buffer,
                                     current);
                }
                appendListItem(request,
                               bufferChild,
                               current,
                               "",
                               id,
                               response);
            }
            else
            {
                if (current.getName()
                        .equals(portalMenu.getCurrentItem()
                                    .getName())
                    || portalMenu.getCurrentItem()
                           .getName()
                       == null)
                {
                    if(!alreadyDidIt)
                    {
                        alreadyDidIt = true;
                        buffer.append("'>");
                        if (!id.equalsIgnoreCase("image"))
                        {
                            appendMainButton(buffer,
                                             "Apps");

                        }
                    }
                    appendListItem(request,
                                   bufferLast,
                                   current,
                                   ""
                                   + (current.getName()
                                          .equals(portalMenu.getCurrentItem()
                                                      .getName())
                                          ? " active "
                                          : ""),
                                   id,
                                   response);
                }
                else
                {
                    appendListItem(request,
                                   bufferLast,
                                   current,
                                   "",
                                   id,
                                   response);
                }
            }
        }
        bufferChild.append("</ul>");
        buffer.append(bufferChild);
        buffer.append("</li>");
        buffer.append(bufferLast);
        buffer.append("</ul>");
        return buffer.toString();
    }

    private void appendListItem(HttpServletRequest request, StringBuffer buffer, PortalMenuItem menuItem, String attr, String id, HttpServletResponse response)
    {
        buffer.append("<li class='")
            .append(id.equalsIgnoreCase("image")
                        ? " span3 "
                        : "")
            .append(attr)
            .append("'><a href=\"");
        buffer.append(PortalUtils.addContextAndMarkupPrefixAndEncode(menuItem.getModulePath()
                                                                     + menuItem.getPath(),
                                                                     request,
                                                                     response));
        buffer.append(MessageFormat.format("\"{1}>{0}",
                                           id.equalsIgnoreCase("image")
                                               ? "<img src='"
                                                 + menuItem.getImage()
                                                 + "' />"
                                               : "",
                                           id.equalsIgnoreCase("image")
                                               ? " class='thumbnail'"
                                               : ""));
        buffer.append(menuItem.getName());
        buffer.append("</a></li>");
    }

    private void appendMainButton(StringBuffer buffer, String portalMenuItem)
    {
        buffer.append("<a title='Choose an app' href='#' class='dropdown-toggle' data-toggle='dropdown'>")
            .append(portalMenuItem)
            .append("<b class='caret'></b>")
            .append("</a>");
    }

    private void appendMainButton(StringBuffer buffer, PortalMenuItem portalMenuItem)
    {
        appendMainButton(buffer,
                         portalMenuItem.getName());
    }
}
