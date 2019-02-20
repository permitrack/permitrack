<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<div id="myCarousel"
     class="carousel slide">
    <div class="carousel-inner">
        <div class="item active">
            <img src="<html:rewrite module="/" page="/img/icons/original/ocean.jpg" />"
                 alt="">
            <div class="container">
                <div class="carousel-caption">
                    <h1>
                        PermiTrack
                    </h1>
                    <p class="lead">
                        Project, permit, inspection, and asset management for governmental permitting and regulation compliance
                    </p>
                    <html:link action="/products"
                               styleClass="btn btn-large btn-success">
                        Learn more
                    </html:link>
                    <html:link action="/contact"
                               styleClass="btn btn-large btn-success">
                        Subscribe
                    </html:link>
                    <html:link action="/loginPageAction"
                               styleClass="btn btn-large btn-primary">
                        Sign in
                    </html:link>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="<html:rewrite module="/" page="/img/icons/original/bigstock-Grass-And-Sky-With-Clouds-1784316.jpg" />"
                 alt="">
            <div class="container">
                <div class="carousel-caption">
                    <h1>
                        In the Cloud
                    </h1>
                    <p class="lead">
                        Our online, cloud-based solutions are modern, secure, convenient, and will save you time and money
                    </p>
                    <html:link action="/buy"
                               anchor="cloud"
                               styleClass="btn btn-large btn-success">
                        Learn more
                    </html:link>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="<html:rewrite module="/" page="/img/icons/original/bigstock-Ecology-and-behavior-of-human--26474447-carousel.jpg" />"
                 alt="">
            <div class="container">
                <div class="carousel-caption">
                    <h1>
                        Online Demo
                    </h1>
                    <p class="lead">
                        You can use our demo usernames to try any of the <strong>Permi</strong>Track applications
                    </p>
                    <html:link action="/demo"
                               styleClass="btn btn-large btn-success">
                        Try it
                    </html:link>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control"
       href="#myCarousel"
       data-slide="prev">&lsaquo;</a>
    <a class="right carousel-control"
       href="#myCarousel"
       data-slide="next">&rsaquo;</a>
</div>
<div class="container marketing">
    <%--
        <div class="row">
            <div class="span12">
                <div class="alert alert-block alert-danger">
                    <h3>
                        Please Note
                    </h3>
                    MyPermiTrack.com is currently experience issues with our SSL Certificate. We are urgently working on these issues and we sincerely apologize for any inconvenience. It is completely SAFE to proceed past the SSL Certificate Warning by clicking "Continue" or equivalent if your browser allows it.
                </div>

            </div>
        </div>
    --%>
    <div class="row">
        <div class="span4">
            <html:link action="/products">
                <img class="img-rounded"
                     data-src="holder.js/140x140"
                     alt="140x140"
                     style="width: 140px; height: 140px;"
                     src="<html:rewrite module="/" page="/img/icons/original/Icon-Environmental.png" />" />
            </html:link>
            <h2>
                Products &amp; Services
            </h2>
            <p>
                Track your NPDES stormwater permit (MS4), manage your Storm Water Pollution Prevention Plan (SWPPP), track your erosion control inspections (ESC) or hazardous air emissions (ENV)
            </p>
            <p>
                <html:link action="/products"
                           styleClass="btn">
                    Learn more &raquo;
                </html:link>
            </p>
        </div>
        <div class="span4">
            <html:link action="/buy">
                <img class="img-rounded"
                     data-src="holder.js/140x140"
                     alt="140x140"
                     style="width: 140px; height: 140px;"
                     src="<html:rewrite module="/" page="/img/icons/original/Icon-Finance.png" />" />
            </html:link>
            <h2>
                Sales &amp; Subscribe
            </h2>
            <p>
                Talk with a salesperson to receive more info, learn all the benefits of a subscription to <strong>Permi</strong>Track, contact our Marketing department, or find general information about My<strong>Permi</strong>Track.com
            </p>
            <p>
                <html:link action="/buy"
                           styleClass="btn">
                    Contact sales &raquo;
                </html:link>
            </p>
        </div>
        <div class="span4">
            <html:link action="/contact">
                <img class="img-rounded"
                     data-src="holder.js/140x140"
                     alt="140x140"
                     style="width: 140px; height: 140px;"
                     src="<html:rewrite module="/" page="/img/icons/original/Icon-Energy.png" />" />
            </html:link>
            <h2>
                Help &amp; Support
            </h2>
            <p>
                Get help with <strong>Permi</strong>Track, get answers to your frequently asked questions, find user manuals, submit an issue, or contact us for assistance
            </p>
            <div>
                <span>
                    <a href="mailto:support@mypermitrack.com">support@mypermitrack.com</a>
                </span>
                |
                <span><a href="tel:6122846331">612-284-6331</a></span>
            </div>
            <p>
                <html:link action="/contact"
                           styleClass="btn">
                    Get help &raquo;
                </html:link>
            </p>
        </div>
    </div>
    <!-- START THE FEATURETTES -->
    <hr class="featurette-divider">
    <div class="featurette">
        <img class="featurette-image pull-right"
             src="<html:rewrite module="/" page="/img/icons/original/hardhat_on_laptop_512.png" />"
             alt="" />
        <h2 class="featurette-heading">
            Subscription-based System.
            <span class="muted">Always On.</span>
        </h2>
        <p class="lead">
            <strong>Permi</strong>Track subscribers have access to automated information systems, technical support, and secure data, with no-hassle upgrades and enhancements. No software to buy. No individual licenses for users—give as many staff as you wish access to the system. Get all these features and much more for a low cost monthly subscription.
        </p>
    </div>
    <hr class="featurette-divider">
    <div class="featurette">
        <img class="featurette-image pull-left"
             src="<html:rewrite module="/" page="/img/icons/original/124502-matte-white-square-icon-arrows-arrow-dotted-down.png" />"
             alt="" />
        <h2 class="featurette-heading">
            The Difference.
            <span class="muted">We're here for you.</span>
        </h2>
        <p class="lead">
            What makes My<strong>Permi</strong>Track.com unique, when compared with our competitors, is our fully featured product and service offerings. Our commitment to our customers is to continually provide them with up to date technical information, tools, and advisory services necessary aiding them make critical business decisions, maximize their core business processes and improve productivity anytime, anywhere, on-demand.
        </p>
    </div>
    <%--
    <hr class="featurette-divider">
    <div class="featurette">
        <img class="featurette-image pull-right"
             src="<html:rewrite module="/" page="/img/icons/original/GIS-Minneapolis_Drive_Times_512.jpg" />"
             alt="" />
        <h2 class="featurette-heading">
            GIS solutions.
            <span class="muted">Powered by ESRI and Google Maps.</span>
        </h2>
        <p class="lead">
            Cost-effective, secure web-based GIS application for communities with limited time, resources, information technology systems and budgets
        </p>
    </div>
    --%>
    <%--<hr class="featurette-divider">--%>
    <!-- /END THE FEATURETTES -->
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
                      $('#myCarousel').carousel({
                                                    interval:false
                                                })
                  })
            }(window.jQuery)
        </script>
    </tiles:put>
</tiles:definition>


