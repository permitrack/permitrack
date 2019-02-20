<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<div class="container">
    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav"
                 data-spy="affix">
                <ul class="nav nav-list">
                    <li class="nav-header">
                        Online Demos
                    </li>
                    <li>
                        <a href="#first">
                            Stormwater+ (MS4)
                        </a>
                    </li>
                    <li>
                        <a href="#first">
                            Erosion Control (ESC)
                        </a>
                    </li>
                    <li>
                        <a href="#first">
                            Air Quality (ENV)
                        </a>
                    </li>
                    <%--
                    <li>
                        <a href="#second">
                            DataView Online (DVO)
                        </a>
                    </li>
                    --%>
                    <li class="nav-header">
                        Other demos
                    </li>
                    <li>
                        <a href="#second">
                            On-site Demo
                        </a>
                    </li>
                    <li>
                        <a href="#second">
                            Online Teleconference
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <div class= "hero-unit img-polaroid">
                <h1>
                    Product Demos
                </h1>
                <p>
                    You may use the information below to try a live demo of any of our products. Sign in using the link in the upper right or the button below.
                </p>
                <p>
                    <html:link action="/loginPageAction"
                               styleClass="btn btn-large btn-primary"
                               target="_blank">
                        Sign in &raquo;
                    </html:link>
                </p>
            </div>
            <a name="first"></a>
            <div class="row-fluid">
                <div class="span4">
                    <div>
                        <span style="position: absolute;">
                            <html:link action="/projectmanagement">
                                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackMS4-Small.png" />"
                                     alt="">
                            </html:link>
                        </span>
                        <html:link action="/projectmanagement">
                            <img src="<html:rewrite module="/" page="/img/icons/original/MC900437640.PNG" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Project Management for Municipal Storm Sewer Permits (MS4)
                    </h4>
                    <p>
                        User ID:
                        <code>swguest</code>
                        Password:
                        <code>storm</code>
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   action="/projectmanagement">
                            Product page &raquo;
                        </html:link>
                    </p>
                </div>
                <div class="span4">
                    <div>
                        <span style="position: absolute;">
                            <html:link action="/inspection">
                                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackESC-Small.png" />"
                                     alt="">
                            </html:link>
                        </span>
                        <html:link action="/inspection">
                            <img src="<html:rewrite module="/" page="/img/icons/original/MC900437630.PNG" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Project and Inspection Management for Erosion Control (ESC)
                    </h4>
                    <p>
                        User ID:
                        <code>escguest</code>
                        Password:
                        <code>runoff</code>
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   action="/inspection">
                            Product page &raquo;
                        </html:link>
                    </p>
                </div>
                <div class="span4">
                    <div>
                        <span style="position: absolute;">
                            <html:link action="/emission">
                                <img src="<html:rewrite module="/" page="/img/icons/original/Logo-PermiTrackENV-Small.png" />"
                                     alt="">
                            </html:link>
                        </span>
                        <html:link action="/emission">
                            <img src="<html:rewrite module="/" page="/img/icons/original/MC900438074.PNG" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Facility, Asset and Emissions Management for VOCs, HAPs and other emissions (ENV)
                    </h4>
                    <p>
                        User ID:
                        <code>envguest</code>
                        Password:
                        <code>hazmat</code>
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   action="/emission">
                            Product page &raquo;
                        </html:link>
                    </p>
                </div>
            </div>
            <a name="second"></a>
            <div class="row-fluid">
                <%--
                <div class="span4">
                    <div>
                        <span style="position: absolute;">
                            <html:link action="/gis">
                                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackGIS-Small.png" />"
                                     alt="">
                            </html:link>
                        </span>
                        <html:link action="/gis">
                            <img src="<html:rewrite module="/" page="/img/icons/original/MC900440106.PNG" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Geographic Information Systems (GIS)
                    </h4>
                    <p>
                        User ID:
                        <code>dvoguest</code>
                        Password:
                        <code>gismap</code>
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   action="/gis">
                            Product page &raquo;
                        </html:link>
                    </p>
                </div>
                --%>
                <div class="span6">
                    <div>
                        <html:link action="/buy">
                            <img class="img-circle"
                                 style="margin-top: 40px; margin-bottom: 32px;"
                                 src="<html:rewrite module="/" page="/img/icons/original/MP900341935-300wx214h.png" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        On-site or Online Demo
                    </h4>
                    <p>
                        Our sales staff would be happy to provide an on-site demo or via online teleconference
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   action="/buy">
                            More &raquo;
                        </html:link>
                    </p>
                </div>
                <div class="span6">
                    <div>
                        <html:link action="/buy">
                            <img src="<html:rewrite module="/" page="/img/screenshot/ESC-Project-Inspections-List-Default-250.png" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Subscription Information
                    </h4>
                    <p>
                        Contact our sales department to find out more information about subscription costs, demos, or anything else we can help you with.
                    </p>
                    <ul>
                        <li>
                            Call <a href="tel:6122846331">612-284-6331</a>
                        </li>
                        <li>
                            Email <a href="mailto:sales@mypermitrack.com">sales@mypermitrack.com</a>
                        </li>
                    </ul>
                    <html:link styleClass="btn"
                               action="/buy">
                        Subscribe &raquo;
                    </html:link>
                </div>
            </div>
        </div>
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
