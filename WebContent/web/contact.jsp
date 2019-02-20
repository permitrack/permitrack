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
                    <%--
                                        <img style="position: absolute; right: 0;"
                                             src="<html:rewrite module="/" page="/img/table/blurry.jpg" />"
                                             alt="">
                    --%>
                    Contact Us
                </h1>
                <p>
                    Contact us for Support, Sales, Marketing or any other questions you might have about our products, services, conferences or demos. We are here to help!
                </p>
                <p>
                    <html:link action="/buy"
                               styleClass="btn btn-success btn-large">
                        Sales &raquo;
                    </html:link>
                    <html:link action="/help"
                               styleClass="btn btn-success btn-large">
                        Support &amp; Help &raquo;
                    </html:link>
                    <html:link action="/about"
                               styleClass="btn btn-success btn-large">
                        About &raquo;
                    </html:link>
                    <html:link action="/help"
                               anchor="faq"
                               styleClass="btn btn-primary btn-large">
                        FAQ &raquo;
                    </html:link>
                </p>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="span4">
                <h2>
                    Sales
                </h2>
                <p>
                    Contact us about sales and subscription information.
                </p>
                <ul>
                    <li>Call <a href="tel:6122846331">612-284-6331</a></li>
                    <li>Email
                        <a href="mailto:sales@mypermitrack.com">sales@mypermitrack.com</a>
                    </li>
                </ul>
                <p>
                    <html:link styleClass="btn"
                               action="/buy">
                        Subscriptions &raquo;
                    </html:link>
                </p>
            </div>
            <div class="span4">
                <h2>
                    Support &amp; Help Desk
                </h2>
                <p>
                    Get Technical Support Or Make a Request
                </p>
                <ul>
                    <li>
                        Call <a href="tel:6122846331">612-284-6331</a>
                    </li>
                    <li>Email
                        <a href="mailto:support@mypermitrack.com">support@mypermitrack.com</a>
                    </li>
                    <li>
                        <a href="/sehsvc/ec_report?action=ecInspectionSubmitComment">
                            Submit Question
                        </a>
                    </li>
                </ul>
                <p>
                    <html:link action="/help"
                               styleClass="btn">
                        More &raquo;
                    </html:link>
                </p>
            </div>
            <div class="span4">
                <h2>
                    About Us
                </h2>
                <p>
                    My<strong>Permi</strong>Track.com products are developed and provided by a highly multidisciplined, professional software development staff and associated partners
                </p>
                <p>
                    <html:link action="/about"
                               styleClass="btn">
                        More &raquo;
                    </html:link>
                </p>
            </div>
        </div>
        <!-- START THE FEATURETTES -->
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/icons/original/MP900309182-512.jpg" />"
                 alt="">
            <h2 class="featurette-heading">Technical Support.
                <span class="muted">How can we help?</span>
            </h2>
            <p class="lead">
                For assistance with technical questions and/or issues regarding our applications, we offer product support Monday through Friday, 7:00 a.m. to 5:00 p.m. (CST), excluding major holidays.
            </p>
            <ul>
                <li>Call <a href="tel:6122846331">612-284-6331</a></li>
                <li>Email
                    <a href="mailto:support@mypermitrack.com">support@mypermitrack.com</a>
                </li>
            </ul>
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
                      $('.supportbutton').addClass("active"); //.carousel()
                  })
            }(window.jQuery)
        </script>
    </tiles:put>
</tiles:definition>


