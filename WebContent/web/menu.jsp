<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"
           prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>

<ul class="nav">
    <li class="productbutton">
        <html:link action="/products" style="display: inline-block; padding-right: 2px; vertical-align: top;">
            Products
        </html:link>
        <div class="productbutton dropdown" style="display: inline-block;">
            <a href="#"
               style="padding-right: 7px; padding-top: 10px; display: inline-block;"
               class="dropdown-toggle"
               data-toggle="dropdown">
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu"
                style="left: -75px;">
                <li>
                    <html:link module="/"
                               action="/projectmanagement">
                        <strong>Permi</strong>Track<sub>MS4</sub>
                    </html:link>
                </li>
                <li>
                    <html:link module="/"
                               action="/inspection">
                        <strong>Permi</strong>Track<sub>ESC</sub>
                    </html:link>
                </li>
                <li>
                    <html:link module="/"
                               action="/emission">
                        <strong>Permi</strong>Track<sub>ENV</sub>
                    </html:link>
                </li>
                <%--
                <li>
                    <html:link module="/"
                               action="/gis">
                        <strong>Permi</strong>Track<sub>GIS</sub> (DVO)
                    </html:link>
                </li>
                --%>
                <li>
                    <html:link module="/"
                               action="/demo">
                        Try a demo
                    </html:link>
                </li>
            </ul>
        </div>
    </li>

    <li class="subscribebutton">
        <html:link action="/buy">
            Subscribe
        </html:link>
    </li>
    <li class="partnerbutton">
        <html:link action="/partner">
            Partners
        </html:link>
    </li>
    <li class="supportbutton dropdown">
        <html:link action="/contact" style="display: inline-block; padding-right: 2px; vertical-align: top;">
            Contact
        </html:link>
        <div class="supportbutton dropdown" style="display: inline-block;">
            <a href="#"
               style="padding-right: 7px; padding-top: 10px; display: inline-block;"
               class="dropdown-toggle"
               data-toggle="dropdown">
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu"
                style="left: -75px;">
                <li class="aboutbutton">
                    <html:link action="/help">
                        Help Desk
                    </html:link>
                </li>
                <li class="aboutbutton">
                    <html:link action="/about">
                        About
                    </html:link>
                </li>
            </ul>
        </div>
    </li>
</ul>
<div id="google-search" style="width: 300px; float:right; padding-top: 3px;">
    <script>
      (function() {
        var cx = '008454399505139270844:ivonwe6ywje';
        var gcse = document.createElement('script');
        gcse.type = 'text/javascript';
        gcse.async = true;
        gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') +
            '//www.google.com/cse/cse.js?cx=' + cx;
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(gcse, s);
      })();
    </script>
    <gcse:search></gcse:search>
</div>
