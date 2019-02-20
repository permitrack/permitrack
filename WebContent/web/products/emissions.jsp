<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="hero-unit img-polaroid">
            <div class="container">
                <h1>
                    <img style="position: absolute; right: 0;"
                         src="<html:rewrite module="/" page="/img/icons/original/MC900438074.PNG" />"
                         alt="">
                    Environmental Air Quality (ENV)
                </h1>
                <p>
                    Facility, Asset and Emissions Management for VOCs, HAPs and other emissions (ENV)
                </p>
                <p>
                    <html:link styleClass="btn btn-success btn-large"
                               action="/contact">
                        Subscribe &raquo;
                    </html:link>
                    <html:link styleClass="btn btn-primary btn-large"
                               action="/demo">
                        Try it &raquo;
                    </html:link>
                </p>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="span4">
                <h2>
                    Track hazardous emissions
                </h2>
                <p>Make tracking and reporting easy with My<strong>Permi</strong>Track.com web-based emissions tracking system, <strong>Permi</strong>Track<sub>ENV</sub></p>
                <%--<p>The days of labor-intensive, expensive and often frustrating efforts to assemble the report can be replaced with a system that lets subscribers access information and submit a report with a few clicks of the mouse button and virtually from anywhere there is an Internet connection.</p>--%>
                <p>The <strong>Permi</strong>Track<sub>ENV</sub>system will help you meet compliance deadlines efficiently, on time and with less cost each year. The solution couldn't be simpler, and My<strong>Permi</strong>Track.com puts it all right on your desktop.</p>
                <%--<p>All these features and benefits mean you and your staff will save time and money while creating better results than ever before.</p>--%>
            </div>
            <div class="span4">
                <h2>
                    Permit and regulatory compliance
                </h2>
<%--
                <p>
                    <img src="./../web/images/AIR-Emissions-Polution-from-Mfg-Plant-26474447-300wx200h.png"
                         alt="">
                </p>
--%>
                <p><strong>Permi</strong>Track<sub>ENV</sub> is your solution for hazardous emissions tracking. Though there are many agencies that let you report your annual emissions online, this may not meet the minimum requirements of air emissions reporting. Tracking data is also a critical component to permit and regulatory compliance. <strong>Permi</strong>Track<sub>ENV</sub> allows you to manage your permitting data tracking needs and delivers on-demand calculations and reporting.</p>
            </div>
            <div class="span4">
                <h2>
                    VOC, HAP, and GHG and other pollutants
                </h2>
                <p>
                    Data tracking and analytics across facilities, by asset, process,sources, equipment, substances, SCC and more.
                    Periodic and annual report generation at the press of a button.
                </p>
<%--
                <ul>
                    <li>
                        Data tracking and analytics across facilities, by asset, process,sources, equipment, substances, SCC and more
                    </li>
                    <li>
                        Web-based technology: no investment in software or hardware needed
                    </li>
                    <li>
                        Regulated, secure access anywhere there is an internet connection
                    </li>
                    <li>
                        Quick status tracker: see what's done, and what needs to be done
                    </li>
                    <li>
                        Periodic and annual report generation at the press of a button
                    </li>
                </ul>
--%>
<%--
                <p>
                    <img src="./../web/images/bigstock-Environmental-Activist-In-The-7753055-smaller-150.png"
                         alt="">
                </p>
--%>
            </div>
        </div>
        <hr class="featurette-divider">
        <%--<hr class="featurette-divider">--%>
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/icons/original/bigstock-Ecology-and-behavior-of-human--26474447-512.jpg" />"
                 alt="">
            <h2 class="featurette-heading">
                <strong>Permi</strong>Track<sub>ENV</sub> tracks VOCs, HAPs, and GHGs.
                <span class="muted">And other pollutants.</span>
            </h2>
            <p class="lead">
                The days of labor-intensive, expensive and often frustrating efforts to assemble an Annual, Semi-Annual or other report can be replaced with a system that lets subscribers access information and submit a report with a few clicks of the mouse and virtually from anywhere there is an Internet connection.</p>
        </div>
        <%--<hr class="featurette-divider">--%>

    </div>
</div>
<tiles:definition id="scripts"
                  scope="request">
    <tiles:put name="scripts"
               type="string"
               direct="true">
        <script>
            !function ($)
            {
                $(function ()
                  {

                      $('.productbutton').addClass("active"); //.carousel()
                  })
            }(window.jQuery)
        </script>
    </tiles:put>
</tiles:definition>
