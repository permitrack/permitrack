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
                         src="<html:rewrite module="/" page="/img/icons/original/MC900437630.PNG" />"
                         alt="">
                    Erosion Control (ESC)
                </h1>
                <p>
                    Project and Inspection Management for Erosion Control (ESC)
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
                    Erosion Control (ESC) inspections
                </h2>
                <p><strong>Permi</strong>Track<sub>ESC</sub> allows you or your staff in any location to load and view erosion/sediment control permit information.</p>
                <p>
                <ul>
                    <li>Project-specific inspections</li>
                    <li>Administer contractor self-inspections</li>
                    <li>View project information and inspection history</li>
                    <li>Create live inspection reports</li>
                    <li>Issues overdue inspection alerts</li>
                    <li>Public access/feedback opportunity</li>
                </ul>
                <!-- <a class="btn" href="#">View details &raquo;</a></p> -->
            </div>
            <div class="span4">
                <h2>
                    Watch a video about our ESC product
                </h2>
                <iframe width="285"
                        height="262"
                        src="//www.youtube.com/embed/9w5k92lwub4"
                        frameborder="0"
                        allowfullscreen></iframe>
            </div>
            <div class="span4">
                <h2>Online access, fully mobile</h2>
                <p>
                    <strong>Permi</strong>Track<sub>ESC</sub> also provides easy access to the information using an accessible public map interface. Users view all active construction projects' key information and saved inspection records.
                </p>
                <p>
                    If a problem is reported, <strong>Permi</strong>Track<sub>ESC</sub> immediately alerts the responsible person to conduct a site visit to or complete other actions to maintain permit compliance. Save time and money, and improve your erosion control program.
                </p>
                <%--
                                <p>
                                    <img src="./../web/images/map-150wx100h.png">
                                </p>
                --%>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/screenshot/ESC-Project-List-Moble-512.png" />"
                 alt="">
            <h2 class="featurette-heading">
                Cloud-based access.
                <span class="muted">Fully mobile.</span>
            </h2>
            <p>Using a hand-held tablet, smart phone or laptop computer, field personnel can visit a site, create an inspection report, take and save photos, view the site's erosion control inspection history, and record observations that are automatically entered into the database. No paper to track or file. No re-entering information, with immediate availability to any authorized user with internet access.</p>
            <p><strong>Permi</strong>Track<sub>ESC</sub> allows permitting authorities to save money while helping permittees go above and beyond the basic requirements of their erosion control permits, maximizing inspections.</p>
        </div>
        <hr class="featurette-divider">
        <div class="row-fluid">
            <div class="span6">
                <h3>
                    <%--<html:link action="/inspection">--%>
                    <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackESC-Small.png" />"
                         alt="">
                    Benefits
                    <%--</html:link>--%>
                </h3>
                <ul>
                    <li>
                        Provides inspection staff with countless project-sorting criteria
                    </li>
                    <li>
                        Facilitates proactive communications among inspection staff, permittees, and the public
                    </li>
                    <li>
                        Manages all project-related documents in one accessible location
                    </li>
                    <li>
                        Allows inspection personnel to cover more ground with fewer staff
                    </li>
                    <li>
                        Creates inspection reminders based on rainfall events
                    </li>
                    <li>
                        Stores all data on a secure, remote server with daily systematic backups
                    </li>
                </ul>
            </div>
            <div class="span6">
                <h3>
                    Resources
                </h3>
                <ul>
                    <li>
                        <a href="/sehsvc/web/manual/2012-Brochure-PermiTrack-ESC.pdf">
                            <strong>Permi</strong>TrackESC flyer (3.3 MB PDF)
                        </a>
                    </li>
                    <li>
                        <a href="/sehsvc/web/manual/SAMPLEAnnualReport.pdf">
                            Sample<strong>Permi</strong>Track<sub>ESC</sub></a>
                        Report
                    </li>
                    <li>
                        Inspection Report (277 KB PDF)
                    </li>
                    <li>
                        Sample <strong>Permi</strong>TrackESC Project Report (6 KB PDF)
                    </li>
                    <li>
                        <a href="/sehsvc/ec_report?action=ecProjectMapView&amp;client_id=BFJTMyJXCqA%3D1slgkfdyou8%3D&amp;detail=RGY">Sample <strong>Permi</strong>Track<sub>ESC</sub> Google<sub>TM</sub> Project Map</a>
                    </li>
                </ul>
            </div>
        </div>
        <a name="features"></a>
        <hr class="featurette-divider">
        <div class="row-fluid">
            <div class="span12">
                <h3>
                    <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackESC-Small.png" />"
                         alt="">
                    Latest Features
                </h3>
                <ul>
                    <li>
                        You can now set Overdue Notifications on a per-project basis, including "number of days since last inspection" and the "email message" that is sent out.
                        Previously, this could only be set on or off for all projects.
                    </li>
                    <li>
                        The project map has been updated to take into account the "number of days since last inspection" that you can set at either a global level or per-project level.
                        Previously, the map showed yellow for 7 days and red for 13 days. It now uses the "initial days" and "secondary days", respectively, if they are set.
                    </li>
                    <li>
                        You can now send out an Inspection Report at any time for a given inspection. There is a button at the top of the Inspection View page to "Email".
                        Previously, you could only send out the inspection report immediately after entering or editing an inspection.
                    </li>
                    <li>
                        The inspection view page and inspection report have been updated to display uploaded images better.
                        Previously, images were too small and were rotated incorrectly in some cases.
                    </li>
                    <li>
                        The inspection report now has links at the top that will bring you to the Project List and Inspection View pages within PermiTrack.
                        This enables anyone that receives the report via email to quickly go online and view further information about the project or inspection.
                    </li>
                    <li>
                        You can now export all of your ESC data in a Microsoft Excel format for custom analysis. There is a new option within the Reports tab that enables this.
                    </li>
                </ul>
            </div>
        </div>
        <a name="services"></a>
        <hr class="featurette-divider">
        <h2>
            Inspection Services
        </h2>
        <p class="lead">
            You have a plan. We can help you monitor, administer, and report on the plan.
        </p>
        <p>
            Whether you have already developed your SWPPP or not, our Compliance Alliance Services Program offers the ongoing site monitoring that is required for implementing your SWPPP to the satisfaction of regulators. Our dedicated team of inspectors provides the on-site expertise you need to stay on track. Our teams use
            <strong>Permi</strong>Track<sub>ESC</sub> inspection software to streamline communication and give you online access to permits, SWPPPs, inspection reports, and site specific message boards.
        </p>
        <p>
            <html:image module="/"
                        page="/img/icons/original/bigstock-Worker-in-front-of-a-laptop-co-12649748-200x300.png"
                        alt="" />
        </p>
        <p>
            By subscribing to our Stormwater Compliance Inspection Services, we remove the headaches associated with stormwater compliance so you can focus on your core business. Additionally, our compliance inspection team is geared towards educating our clients to uncover any inefficient or wasteful erosion and sediment control practices. Identifying and changing these practices can result in significant savings. In particular, identifying poor quality installations and repeated incidents of damage to Best Management Practices (BMPs) by contractors on a site has the potential to create enough savings to make the inspection program self-funding.
        </p>
        <h4>
            MyInspector stormwater compliance inspection services include
        </h4>
        <p>
            <html:image module="/"
                        page="/img/logo/partners/sehinspect.png"
                        alt="" />
            Providing highly trained, experienced and/or certified personnel to perform inspections in accordance with applicable local and state regulating authority
        </p>
        <p>
            <html:image module="/"
                        page="/img/logo/partners/sehinspect.png"
                        alt="" />
            Reviewing the Plan during each regular inspection to make sure the current site activity is recorded properly
        </p>
        <p>
            <html:image module="/"
                        page="/img/logo/partners/sehinspect.png"
                        alt="" />
            Working with site managers to update the Plan to reflect changes in site conditions and construction progress or phasing
        </p>
        <p>
            <html:image module="/"
                        page="/img/logo/partners/sehinspect.png"
                        alt="" />
            Preparing an inspection report onsite and making it available electronically
        </p>
        <p>
            <html:image module="/"
                        page="/img/logo/partners/sehinspect.png"
                        alt="" />
            Providing streamlined notification of non-compliance issues observed in the field to your designated contact and the BMP vendor of your choice
        </p>
        <p>
            <html:image module="/"
                        page="/img/logo/partners/sehinspect.png"
                        alt="" />
            Maintaining an ongoing database of inspection and corrective action reports through
            <strong>Permi</strong>Track<sub>ESC</sub>
        </p>
        <p>
            We are proud to offer a full suite of Stormwater/MS4 compliance inspection services. For more information, simply
            <a href="mailto:myinspector@mynpdespermit.com">email us</a>
            or call 866.830.3388.
        </p>
        <p>
            In addition,
            <strong>Permi</strong>Track partners with several respected compliance firms across the United States. Please visit our
            <html:link module="/"
                       action="/partner">
                Partners
            </html:link>
            page to view a qualified list of partners who may be able to assist you.
        </p>
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
