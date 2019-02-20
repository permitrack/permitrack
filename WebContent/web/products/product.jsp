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
                    PermiTrack Product Suite
                </h1>
                <p>
                    Stormwater (MS4). Erosion Control (ESC). Air Quality (ENV). Geographic Information Systems (GIS).
                </p>
                <p>
                    <html:link styleClass="btn btn-success btn-large"
                               action="/buy">
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
                    With <strong>Permi</strong>Track<sub>MS4</sub> you can easily administer your program, maintain and centralize records and file annual reports to meet NPDES storm water permit regulations.
                </p>
                <p>
                    <html:link styleClass="btn"
                               action="/projectmanagement">
                        More &raquo;
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
                    <strong>Permi</strong>Track<sub>ESC</sub> allows you or your staff in any location to load and view erosion/sediment control permit information.
                </p>
                <p>
                    <html:link styleClass="btn"
                               action="/inspection">
                        More &raquo;
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
                    <strong>Permi</strong>Track<sub>ENV</sub> allows to track data and run reports across facilities, by asset, process,sources, equipment, substances, SCC and more.
                </p>
                <p>
                    <html:link styleClass="btn"
                               action="/emission">
                        More &raquo;
                    </html:link>
                </p>
            </div>
            <%--
            <div class="span3">
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
                    Geographic Information Systems (GIS) solutions powered by ESRI©
                </h4>
                <p>
                    <strong>Permi</strong>Track<sub>GIS</sub> provides cost-effective, secure web-based GIS application for communities with limited time, resources, information technology systems and budgets.
                </p>
                <p>
                    <html:link styleClass="btn"
                               action="/gis">
                        More &raquo;
                    </html:link>
                </p>
            </div>
            --%>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/icons/original/Icon-Environmental.png" />"
                 alt="">
            <h2 class="featurette-heading">
                Manage your projects, permits, inspections, and partners.
                <span class="muted">For starters.</span>
            </h2>
            <p class="lead">
                <strong>Permi</strong>Track products will help you meet your regulatory and compliance needs efficiently, on time and with low cost.
            </p>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-left"
                 src="<html:rewrite module="/" page="/img/icons/original/Icon-Water-Resources.png" />"
                 alt="">
            <h2 class="featurette-heading">
                Regulatory and compliance reporting.
                <span class="muted">Never easier.</span>
            </h2>
            <p class="lead">
                With the increasing number of unfunded government mandates in place for mandatory and designated regulatory and compliance reporting, finding a cost-effective and time-efficient way to prepare and file your annual and periodic reports to be in compliance with permit regulations became imperative.
            </p>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/icons/original/Icon-Education.png" />"
                 alt="">
            <h2 class="featurette-heading">
                <strong>Permi</strong>Track is the solution.
                <span class="muted">We have answers.</span>
            </h2>
            <p class="lead">
                A solution so simple and innovative, the EPA included it in the Center for Environmental Industry and Technology, a virtual trade show for stormwater technologies.
            </p>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-left"
                 src="<html:rewrite module="/" page="/img/icons/original/Icon-GIS.png" />"
                 alt="">
            <h2 class="featurette-heading">Consider My<strong>Permi</strong>Track.com as your online virtual office.
                <span class="muted"></span>
            </h2>
            <p class="lead">
                Consider My<strong>Permi</strong>Track.com as your online virtual office where you can store all of your activity and goal information pertaining to your permit needs. Agendas, memos, minutes, photos, graphics, and even maps and GIS data can be attached and viewed by anyone to whom you provide access.
            </p>
        </div>
        <hr class="featurette-divider">
        <div class="row">
            <div class="span6">
                <h4>
                    Features and Benefits
                </h4>
                <ul>
                    <li>Web-based technology - no investment in software or hardware needed</li>
                    <li>Automatic alerts - manage your permit proactively with automatic e-mail alerting of permit-related goals and activities</li>
                    <li>Regulated access anywhere there is an internet connection</li>
                    <li>Data security</li>
                    <li>Quick status tracker-see what's done, and what needs to be done</li>
                    <li>Annual report generator-your report at the press of a button</li>
                    <li>Through out Partner Alliances, we can also provide or connect you to other additional products and services.</li>
                </ul>
            </div>
            <div class="span6">
                <h4>
                    Additional service offerings and Partners
                </h4>
                <ul>
                    <li>Hands-on, on-site training for your staff</li>
                    <li>Site Inspectors Resources for Hire</li>
                    <li>Data entry analyist to enter your data for you</li>
                    <li>On-call experts who will answer your reporting or technology questions</li>
                    <li>Custom software to automate equipment data collection directly into one of our products</li>
                    <li>Please visit the Services and Products sections of this site for a vast number or other services offerings My<strong>Permi</strong>Track.com may suggest.</li>
                </ul>
            </div>
        </div>
        <h2>
            Product Solutions
        </h2>
        <p>
            <html:link action="/projectmanagement">
                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackMS4-Small.png" />"
                     alt="">
            </html:link>
            Permit tracking system. Store activity and goal information pertaining to your permits; agendas, memos, minutes, photos, graphics; maps and GIS data and generate automatic alerts to manage permits and SWPPPs. Annual report generator. This can be implemented for all EPA permit tracking.
        </p>
        <br>
        <p>
            <html:link action="/inspection">
                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackESC-Small.png" />"
                     alt="">
            </html:link>
            Erosion Sediment Control - Inspection solution to record observations in the field that are automatically entered into the database. Residents/citizens also can view all active construction projects within the community and file a concern. Streamlines communication and gives you online access to permits, inspection reports, and site specific message boards.
        </p>
        <br>
        <p>
            <html:link action="/emission">
                <img src="<html:rewrite module="/" page="/img/icons/original/Logo-PermiTrackENV-Small.png" />"
                     alt="">
            </html:link>
            Monitor and report Hazardous emissions. Ensure secure data and with Quick Status Tracker. Implement Best Practices for Compliance Monitoring. Generate Annual and periodic reports.
        </p>
        <%--
        <br>
        <p>
            <html:link action="/gis">
                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackGIS-Small.png" />"
                     alt="">
            </html:link>
            Easily view GIS data, and build custom maps to answer everyday questions. DVO GIS is an application for communities with limited time, resources, information technology systems and budgets. Far ranging uses for all environmental tracking and asset management.
        </p>
        --%>
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


