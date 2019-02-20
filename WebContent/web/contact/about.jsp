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
                        About Us
                    </li>
                    <li>
                        <a href="#first">
                            Designed for you
                        </a>
                    </li>
                    <li>
                        <html:link module="/"
                                   action="/partner">
                            Strong partnerships
                        </html:link>
                    </li>
                    <li>
                        <a href="#career">
                            Careers
                        </a>
                    </li>
                    <li class="nav-header">
                        Contact Us
                    </li>
                    <li>
                        <html:link module="/"
                                   action="/buy">
                            Sales
                        </html:link>
                    </li>
                    <li>
                        <html:link module="/"
                                   action="/help">
                            Support
                        </html:link>
                    </li>
                    <li class="nav-header">
                        Locations
                    </li>
                    <li>
                        <a href="#hq">
                            Headquarters
                        </a>
                    </li>
                </ul>
            </div>
            <!--/.well -->
        </div>
        <!--/span-->
        <div class="span9">
            <div class="hero-unit img-polaroid">
                <h1>
                    About Us
                </h1>
                <p>
                    Learn more about PermiTrack and MyPermiTrack.com
                </p>
                <html:link action="/contact"
                           styleClass="btn btn-success btn-large">
                    Contact Us &raquo;
                </html:link>
                <html:link action="/buy"
                           styleClass="btn btn-success btn-large">
                    Sales &raquo;
                </html:link>
                <html:link action="/help"
                           styleClass="btn btn-primary btn-large">
                    Help &raquo;
                </html:link>
                <html:link action="/help"
                           anchor="faq"
                           styleClass="btn btn-primary btn-large">
                    FAQ &raquo;
                </html:link>
            </div>
            <a name="first"></a>
            <div class="row-fluid">
                <div class="span4">
                    <h2>
                        Designed for you
                    </h2>
                    <p>
                        My<strong>Permi</strong>Track.com products are developed and provided by a highly multidisciplined, professional software development staff and associated partners.
                    </p>
                    <p>
                        <a class="btn"
                           href="#">
                            More &raquo;
                        </a>
                    </p>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        Strong partnerships
                    </h2>
                    <p>
                        My<strong>Permi</strong>Track.com has a combined resource pool from its alliances that is comprised of approximately 650 engineers, architects, planners and scientists.
                    </p>
                    <p>
                        <html:link action="/partner"
                                   styleClass="btn">
                            More &raquo;
                        </html:link>
                    </p>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        Careers
                    </h2>
                    <p>
                        Interested in a career with MyPermiTrack.com?
                    </p>
                    <p>
                        <a class="btn"
                           href="#career">
                            More &raquo;
                        </a>
                    </p>
                </div>
                <!--/span-->
            </div>
            <!--/row-->
            <div class="row-fluid">
                <div class="span4">
                    <h2>
                        Subscriptions
                    </h2>
                    <p>
                        My<strong>Permi</strong>Track.com subscribers have access to automated information systems, technical support, and secure data, with no-hassle upgrades and enhancements. No software to buy. Get all these features and much more for a low minimal monthly subscription rate.
                    </p>
                    <p>
                        <html:link action="/buy"
                                   styleClass="btn">
                            More &raquo;
                        </html:link>
                    </p>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        Sales &amp; Service
                    </h2>
                    <p>
                        Contact us about sales and subscription information.
                    </p>
                    <ul>
                        <li>
                            Call
                            <a href="tel:6122846331">612-284-6331</a>
                        </li>
                        <li>
                            Email
                            <a href="mailto:sales@mypermitrack.com">sales@mypermitrack.com</a>
                        </li>
                    </ul>
                    <p>
                        <html:link action="/buy"
                                   styleClass="btn">
                            More &raquo;
                        </html:link>
                    </p>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        Support &amp; Help Desk
                    </h2>
                    <p>
                        Get Technical Support Or Make a Request
                    </p>
                    <ul>
                        <li>
                            Call
                            <a href="tel:6122846331">612-284-6331</a>
                        </li>
                        <li>
                            Email
                            <a href="mailto:support@mypermitrack.com">support@mypermitrack.com</a>
                        </li>
                    </ul>
                    <p>
                        <html:link action="/help"
                                   styleClass="btn">
                            More &raquo;
                        </html:link>
                    </p>
                </div>
                <!--/span-->
            </div>
            <!--/row-->
            <a name="hq"></a>
            <hr class="featurette-divider">
            <h2>
                Headquarters
            </h2>
            <h3>
                My<strong>Permi</strong>Track.com
            </h3>
            <p>
                431 1st Ave. N.
            </p>
            <p>
                Suite 410
            </p>
            <p>
                Minneapolis, MN 55401
            </p>
            <a name="career"></a>
            <hr class="featurette-divider">
            <h2>
                Careers with <strong>Permi</strong>Track
            </h2>
            <p class="lead">
                My<strong>Permi</strong>Track.com
                has continued to grow over the years as a result of our greatest asset — our people.
            </p>
            <p>Our success comes from the talents, creativity and technical abilities of our professionals. While client needs and technologies may change,
               My<strong>Permi</strong>Track.com’s commitment to our employees is unwavering.
               My<strong>Permi</strong>Track.com is one company with multiple office locations, offering our employees both urban and rural office settings; each with its own unique culture. Our diverse clientele and our wide range of services offer employees the opportunity to work on challenging projects for both public and private-sector clients in a team setting.
            </p>
            <h4>
                What We Offer
            </h4>
            <p>
                A reflection of
                My<strong>Permi</strong>Track.com's commitment to our employees is clearly demonstrated through our:
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Professional development opportunities
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Flexible work schedules
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Employee stock ownership and profit sharing
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Health and dental — 100 percent paid single coverage
            </p>
            <h4>
                Current Career Opportunities
            </h4>
            <p>
                At
                My<strong>Permi</strong>Track.com, we are always looking for energetic, motivated and talented individuals with the following skills:
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Project Management
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Business Analysis
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Technical Architecture
            </p>
            <p>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Sr. Development with Web-based technologies
            </p>
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

