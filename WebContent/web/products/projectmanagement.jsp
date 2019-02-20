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
                         src="<html:rewrite module="/" page="/img/icons/original/MC900437640.PNG" />"
                         alt="">
                    Stormwater+ (MS4)
                </h1>
                <p>
                    Plan, MCM, BMP, Goal and Goal Activity Tracking for Municipal Separate Storm Sewer Systems (MS4) programs (NPDES, SWPPP)
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
                    Track your NPDES stormwater permit
                </h2>
                <p>
                    With <strong>Permi</strong>Track<sub>MS4</sub> you can easily administer your program, maintain and centralize records and file annual reports to meet NPDES storm water permit regulations. Easily, efficiently and cost effectively.
                </p>
                <p>
                <ul>
                    <li>Access anywhere anytime</li>
                    <li>Automatic action alerts</li>
                    <li>Centralized SWMP records</li>
                    <li>Action item accountability</li>
                    <li>Report generator</li>
                    <li>Communicate and share information</li>
                </ul>
                <!-- <a class="btn" href="#">View details &raquo;</a></p> -->
            </div>
            <div class="span4">
                <h2>Watch a video about our MS4 product</h2>
                <div class="img-rounded">
                    <iframe width="360"
                            height="250"
                            src="//www.youtube.com/embed/8fDkNXJRwPE"
                            frameborder="0"
                            allowfullscreen></iframe>
                </div>
            </div>
            <div class="span4">
                <h2>
                    Get the help you need with your SWPPP
                </h2>
                <p>
                    If you need help updating your Storm Water Pollution Prevention Plan (SWPPP), meeting your proposed goals, completing your scheduled activities, or have other compliance questions, we are here to help!
                </p>
                <p>
                    Simply
                    <html:link module="/"
                               action="buy">click here for subscription information</html:link>
                   or contact Sales at 866.830.3388 <%--TODO 800 number--%>
                </p>
            </div>
        </div>
        <hr class="featurette-divider">
        <%--<hr class="featurette-divider">--%>
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/icons/original/bigstock-Drainage-Culvert-8162803.jpg" />"
                 alt="">
            <h2 class="featurette-heading">
                <strong>Permi</strong>Track<sub>MS4</sub> simplifies your life.
                <span class="muted">SWPPP &amp; NPDES.</span>
            </h2>
            <p class="lead">
                <strong>Permi</strong>Track<sub>MS4</sub> simplifies storage and sharing of permit activity information. Agendas, memos, minutes, photos, graphics, CAD maps and data are all easily accessible and viewed on your computer or mobile device. And your annual report production is just a few clicks away. Anywhere, anytime, securely.
            </p>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-left"
                 src="<html:rewrite module="/" page="/img/icons/original/pile_of_folders_1600_clr_6706_2.png" />"
                 alt="" />
            <h2 class="featurette-heading">
                Cloud-based access.
                <span class="muted">Fully mobile.</span>
            </h2>
            <p class="lead">
                Consider it your online virtual file cabinet where you can store all of your activity and goal information pertaining to your permit. Agendas, memos, minutes, photos, graphics, and even maps and GIS data can be attached and viewed by anyone to whom you provide access.
            </p>
        </div>
        <hr class="featurette-divider">
        <div class="row-fluid">
            <div class="span6">
                <h4>
                    <strong>Who is covered under the NPDES Stormwater Program?</strong></h4>
                <p>
                    NPDES regulates stormwater discharges from three potential sources: municipal separate storm sewer systems (MS4s), construction activities, and industrial activities. For more information, see the EPA's website.
                </p>
                <h4>
                    <strong>I can't keep track of my stormwater permit requirements. How can </strong><strong>Permi</strong>Track<strong> help?</strong></h4>
                <p>
                    Let <strong>Permi</strong>Track handle the details of your stormwater program. There are countless details to keep track of between the MS4 and General Construction permits. Let the database notify you with helpful e-mail reminders when you need to sweep the streets, facilitate a public meeting, or conduct an after-rainfall inspection.
                </p>
            </div>
            <div class="span6">
                <h4>
                    What is a Municipal Separate Storm Sewer System or MS4?
                </h4>
                <p>
                    An MS4 is a conveyance or system of conveyances that is:
                <ol>
                    <li>
                        Owned by a state, city, town, village, or other public entity that discharges to waters of the U.S.
                    </li>
                    <li>
                        Designed or used to collect or convey stormwater (including storm drains, pipes, ditches, etc.)
                    </li>
                    <li>
                        Not a combined sewer
                    </li>
                    <li>
                        Not part of a Publicly Owned Treatment Works (sewage treatment plant)
                    </li>
                </ol>
                </p>
                <h4>
                    <strong>How can I afford a tool to assist my stormwater management efforts?</strong></h4>
                <p>
                    By maintaining a centralized database and providing your staff with an Internet accessible tool, <strong>Permi</strong>Track allows you to do more with less by effectively managing your stormwater program remotely. No more coordination meetings. Give your staff access and let them work together online.
                </p>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="row-fluid">
            <div class="span6">
                <h3>
                    Resources
                </h3>
                <p class="lead">
                    A few helpful links to commonly viewed pages from popular MS4/Stormwater web sites
                </p>
                <%--
                                        <div>
                                            <strong>Permi</strong>Track<sub>MS4</sub> Informational Flyer
                                        </div>
                                        <div>
                                            <strong>Permi</strong>Track<sub>MS4</sub><strong> Published Reports</strong>
                                        </div>
                --%>
            </div>
            <div class="span6">
                <h3>
                    Examples
                </h3>
                <p class="lead">
                    A few examples of the reports produced by <strong>Permi</strong>Track<sub>MS4</sub>
                </p>
                <ul>
                    <li>
                        <a href="/sehsvc/web/manual/ANNUAL_REPORT_07.pdf">City of Madison, WI</a>
                    </li>
                    <li>
                        <a href="/sehsvc/web/manual/no_st_paul_2006.pdf">City of North St. Paul, MN</a>
                    </li>
                    <li>
                        <a href="/sehsvc/web/manual/2008newscplanreport.pdf">Northeast Wisconsin Stormwater Consortium</a>
                    </li>
                    <li>
                        <a href="/sehsvc/web/manual/SAMPLEAnnualReport.pdf">Sample Annual Report</a>
                    </li>
                </ul>
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
