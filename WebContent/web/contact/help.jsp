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
                        Technical Support
                    </li>
                    <li>
                        <a href="#first">
                            Call us
                        </a>
                    </li>
                    <li>
                        <a href="#first">
                            Email us
                        </a>
                    </li>
                    <li>
                        <a href="#first">
                            Submit a ticket
                        </a>
                    </li>
                    <li class="nav-header">
                        Manuals
                    </li>
                    <li>
                        <html:link module="/"
                                   page="/web/manual/PermiTrack-ESC-Quick-Guide_Rel-4.1.0.pdf">
                            ESC Quick Guide
                        </html:link>
                    </li>
                    <li>
                        <html:link action="/manualescuser">
                            ESC User Manual
                        </html:link>
                    </li>
                    <li>
                        <html:link action="/manualescadmin">
                            ESC Administrator Manual
                        </html:link>
                    </li>
                    <li>
                        <html:link module="/"
                                   page="/web/manual/PermiTrack-MS4-Quick-Guide-Rel-4.1.0.pdf">
                            MS4 Quick Guide
                        </html:link>
                    </li>
                    <li>
                        <html:link module="/"
                                   page="/web/manual/PermiTrack-MS4-Client-User-Guide-Rel-4.1.0.pdf">
                            MS4 User Manual
                        </html:link>
                    </li>
                    <li>
                        <html:link module="/"
                                   page="/web/manual/PermiTrack-MS4-Client-Administrator-User-Guide-Rel-4.1.0.pdf">
                            MS4 Administrator Manual
                        </html:link>
                    </li>
                    <li>
                        <html:link action="/manualsettings">
                            Settings Manual (Admin)
                        </html:link>
                    </li>
                    <li class="nav-header">
                        3rd party resources
                    </li>
                    <li>
                        <a href="#thirdparty">MS4</a>
                    </li>
                    <li>
                        <a href="#thirdparty">ESC</a>
                    </li>
                    <li>
                        <a href="#thirdparty">ENV</a>
                    </li>
                </ul>
            </div>
            <!--/.well -->
        </div>
        <!--/span-->
        <div class="span9">
            <div class="hero-unit img-polaroid">
                <h1>
                    Help Desk
                </h1>
                <p>
                    We have the following resources available to you
                </p>
                <p>
                    <html:link action="/contact"
                               styleClass="btn btn-success btn-large">
                        Contact Us &raquo;
                    </html:link>
                    <html:link action="/buy"
                               styleClass="btn btn-success btn-large">
                        Sales &raquo;
                    </html:link>
                    <html:link action="/help"
                               anchor="faq"
                               styleClass="btn btn-primary btn-large">
                        FAQ &raquo;
                    </html:link>
                </p>
            </div>
            <!--/row-->
            <a name="first"></a>
            <div class="row-fluid">
                <div class="span4">
                    <h2>
                        Technical Support
                    </h2>
                    <p>
                        Contact us anytime with technical support issues. If we are unavailable in the moment we will get back to you as quickly as possible.
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
                        <html:link action="/contact"
                                   styleClass="btn">
                            Other options &raquo;
                        </html:link>
                    </p>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        3rd Party resources
                    </h2>
                    <p>
                        These links are provided for your convenience. Please note PermiTrack is not resposible for the contents of 3rd party resources.
                    </p>
                    <p>
                        <a class="btn"
                           href="#thirdparty">More &raquo;
                        </a>
                    </p>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        Frequently Asked Questions (FAQ)
                    </h2>
                    <p>
                        View some of our most frequently asked questions below.
                    </p>
                    <p>
                        <a class="btn"
                           href="#faq">FAQ &raquo;
                        </a>
                    </p>
                </div>
                <!--/span-->
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <h2>
                        ESC Manuals
                    </h2>
                    <%--<p>--%>
                    <ul>
                        <li>
                            <html:link module="/"
                                       page="/web/manual/PermiTrack-ESC-Quick-Guide_Rel-4.1.0.pdf">
                                ESC Quick Guide
                            </html:link>
                        </li>
                        <li>
                            <html:link action="/manualescuser">
                                ESC User Manual
                            </html:link>
                        </li>
                        <li>
                            <html:link action="/manualescadmin">
                                ESC Administrator Manual
                            </html:link>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        MS4 Manuals
                    </h2>
                    <ul>
                        <li>
                            <html:link module="/"
                                       page="/web/manual/PermiTrack-MS4-Quick-Guide-Rel-4.1.0.pdf">
                                MS4 Quick Guide
                            </html:link>
                        </li>
                        <li>
                            <html:link module="/"
                                       page="/web/manual/PermiTrack-MS4-Client-User-Guide-Rel-4.1.0.pdf">
                                MS4 User Manual
                            </html:link>
                        </li>
                        <li>
                            <html:link module="/"
                                       page="/web/manual/PermiTrack-MS4-Client-Administrator-User-Guide-Rel-4.1.0.pdf">
                                MS4 Administrator Manual
                            </html:link>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span4">
                    <h2>
                        Other Manuals
                    </h2>
                    <ul>
                        <li>
                            <html:link action="/manualsettings">
                                Settings Manual (Admin)
                            </html:link>
                        </li>
                    </ul>
                </div>
                <!--/span-->
            </div>
            <!--/row-->
            <a name="thirdparty"></a>
            <hr class="featurette-divider">
            <h2>
                3rd Party Notices
            </h2>
            <h4>
                MN - Minnesota
            </h4>
            <%--
                                            <p>

                                                    <img src="/siteimages/sw.png"
                                                         alt="">
                                            </p>
            --%>
            <h5>
                MS4
            </h5>
            <p>
                <%--
                TODO
                --%>
                <a href="/sehsvc/web/manual/2011-MPCA-MS4-Annual-Report-Notice-Coverltr.pdf"
                   target="_blank">
                    2011 MPCA MS4 Annual Report Notice
                </a>
            </p>
            <p>
                <a href="http://www.pca.state.mn.us/index.php/water/water-types-and-programs/stormwater/municipal-stormwater/annual-reports-for-municipal-separate-storm-sewer-systems-ms4.html"
                   target="_blank">
                    MS4 Annual Reports
                </a>
            </p>
            <%-- TODO section example
                        <section id="contents">
                                  <div class="page-header">
                                    <h1>3. What's included</h1>
                                  </div>
                                  <p class="lead">Bootstrap comes equipped with HTML, CSS, and JS for all sorts of things, but they can be summarized with a handful of categories visible at the top of the <a href="http://getbootstrap.com">Bootstrap documentation</a>.</p>

                                  <h2>Docs sections</h2>
                                  <h4><a href="http://twitter.github.com/bootstrap/scaffolding.html">Scaffolding</a></h4>
                                  <p>Global styles for the body to reset type and background, link styles, grid system, and two simple layouts.</p>
                                  <h4><a href="http://twitter.github.com/bootstrap/base-css.html">Base CSS</a></h4>
                                  <p>Styles for common HTML elements like typography, code, tables, forms, and buttons. Also includes <a href="http://glyphicons.com">Glyphicons</a>, a great little icon set.</p>
                                  <h4><a href="http://twitter.github.com/bootstrap/components.html">Components</a></h4>
                                  <p>Basic styles for common interface components like tabs and pills, navbar, alerts, page headers, and more.</p>
                                  <h4><a href="http://twitter.github.com/bootstrap/javascript.html">JavaScript plugins</a></h4>
                                  <p>Similar to Components, these JavaScript plugins are interactive components for things like tooltips, popovers, modals, and more.</p>

                                  <h2>List of components</h2>
                                  <p>Together, the <strong>Components</strong> and <strong>JavaScript plugins</strong> sections provide the following interface elements:</p>
                                  <ul>
                                    <li>Button groups</li>
                                    <li>Button dropdowns</li>
                                    <li>Navigational tabs, pills, and lists</li>
                                    <li>Navbar</li>
                                    <li>Labels</li>
                                    <li>Badges</li>
                                    <li>Page headers and hero unit</li>
                                    <li>Thumbnails</li>
                                    <li>Alerts</li>
                                    <li>Progress bars</li>
                                    <li>Modals</li>
                                    <li>Dropdowns</li>
                                    <li>Tooltips</li>
                                    <li>Popovers</li>
                                    <li>Accordion</li>
                                    <li>Carousel</li>
                                    <li>Typeahead</li>
                                  </ul>
                                  <p>In future guides, we may walk through these components individually in more detail. Until then, look for each of these in the documentation for information on how to utilize and customize them.</p>
                                </section>
            --%>
            <a name="faq"></a>
            <hr class="featurette-divider">
            <h2>
                FAQ
                <small>General Questions</small>
            </h2>
            <dl>
                <dt>
                    <span>What are the internet connection requirements?</span>
                </dt>
                <dd>
                    <span>You can connect from any device that supports and has an internet connection.</span>
                </dd>
                <dt>
                    <span>How can I learn more about your other web-based applications?</span>
                </dt>
                <dd>
                    <span>Further information may be provided by our Sales and Client Service contact, found on the Contact Us page.</span>
                </dd>
                <dt>
                    <span>Can I see a sample database for each of the available applications?</span>
                </dt>
                <dd>
                    <span>A guest login into a sample database of each of our applications,</span>
                    <span>Permi</span>
                    <span>Track</span>
                    <span>GIS</span>
                    <span>DataView Online</span>
                    <span>,&nbsp;</span>
                    <span>Permi</span>
                    <span>Track</span>
                    <span>ESC</span>
                    <span>&nbsp;,&nbsp;</span>
                    <span>Permi</span>
                    <span>Track</span>
                    <span>MS4</span>
                    <span>&nbsp;, and</span>
                    <span>Permi</span>
                    <span>Track</span>
                    <span>ENV</span>
                    <span>&nbsp;is available through our Sales and Client Service contact, found on the Contact Us page.</span>
                </dd>
                <dt>
                    <span>I looked at a particular sample database, can you provide a product demonstration?</span>
                </dt>
                <dd>
                    <span>Our staff are available to provide an in-depth demonstration of each application. Product demos may be performed in-person or via the web. Whatever works best for you. Please contact <html:link module="/"
                                                                                                                                                                                                                            action="/contact">Sales</html:link> to schedule a demonstration today.
                    </span>
                </dd>
                <dt>
                    <span>How do I contact Technical Support?</span>
                </dt>
                <dd>
                    <span>Technical Support is available for all of our applications via phone at
                        <a href="tel:6122846331">612-284-6331</a>
                          .
                    </span>
                </dd>
                <dt>
                    <span>When is Technical Support available?</span>
                </dt>
                <dd>
                    <span>Technical Support for all of our applications is provided Monday through Friday, 7:00 a.m. to 5:00 p.m. (CST), excluding major holidays.</span>
                </dd>
                <dt>
                    <span>If my data is unavailable, what do I do?</span>
                </dt>
                <dd>
                    <span>If you cannot sign in, the Technical Support tab within the application will also be unavailable. However, each of our clients are asked upon product initiation to store both the support phone number and support e-mail in a separate location, that will be available in case of an internet connectivity issue.</span>
                </dd>
                <dt>
                    <span>I forgot my password, how can I sign in?</span>
                </dt>
                <dd>
                    <span>If you forget your password, contact Technical Support as indicated in the Support tab.</span>
                </dd>
                <dt>
                    <span>Is my data backed up?</span>
                </dt>
                <dd>
                    <span>Yes. All data entered into any of our applications is routinely backed up on a daily basis.</span>
                </dd>
                <dt>
                    <span>Can I get a copy of the electronic backup file for my data?</span>
                </dt>
                <dd>
                    <span>Yes, although it is not a standard procedure. A formal request must be made and a backup disk will be created for all information entered to date.</span>
                </dd>
                <dt>
                    <span class="c3 c5">I have a content-specific question regarding an application, who can I call?</span>
                </dt>
                <dd>
                    <span>Each application is unique. We have assigned a content-specific professional to address content questions about each application. Content-specific questions do not apply to the technical workings of the respective application, rather they apply to the data entered by the client. Each professional has several years of experience as it relates to the individual application. See the Contact Us page for the appropriate application contact.</span>
                </dd>
                <dt>
                    <span>I heard that a certain application has new functionality, how do I get an update?</span>
                </dt>
                <dd>
                    <span>This is what separates web-based technology from stand-alone software packages. As a user of any of our applications, you will always be using the latest and greatest version of the application each time you sign in. Likewise, you will not be assessed the significant upgrade fees often charged by stand-alone software providers.</span>
                </dd>
                <dt>
                    <span>What internet browsers are supported?</span>
                </dt>
                <dd>
                    <span>The following list of internet browsers are presently supported:</span>
                    <ol>
                        <li>
                            Google Chrome latest version (Recommended)
                        </li>
                        <li>
                            Mozilla Firefox latest version (2nd choice)
                        </li>
                        <li>
                            Microsoft Internet Explorer latest version (3rd choice)
                        </li>
                        <li>
                            Microsoft Internet Explorer 8.x or less (the last resort)
                        </li>
                    </ol>
                </dd>
            </dl>
            <a name="faqnpdes"></a>
            <hr class="featurette-divider">
            <h2>
                FAQ
                <small>National Pollutant Discharge Elimination System (NPDES)</small>
            </h2>
            <dl>
                <dt>
                    What facets of the NPDES program can <strong>Permi</strong>Track help me with?
                </dt>
                <dd>
                    <strong>Permi</strong>Track presently offers two separate applications, <strong>Permi</strong>TrackMS4 and <strong>Permi</strong>TrackESC, that address the specific needs of the NPDES MS4 and Construction General Permits.
                </dd>
                <dt>
                    Is my organization too big/too small to use
                    <strong>Permi</strong>Track
                </dt>
                <dd>
                    <strong>Permi</strong>Track is presently deployed in a wide variety of clientele. We service Phase II MS4s as small as 700 residents in population, and one-person BMP companies inspecting a handful of projects, to larger Phase I communities and governmental agencies tracking 1,000s of projects/permits a year.
                </dd>
                <dt>
                    Is my data secure?
                </dt>
                <dd>
                    Yes. <strong>Permi</strong>Track provides 128-bit encryption and your data is stored at a remote data storage facility with a separate redundant back-up storage location. In summary, your laptop computer could be dropped in a puddle and your office burn to the ground, but your data would remain secure.
                </dd>
                <dt>
                    I would like to try <strong>Permi</strong>Track, but long-term commitments scare me. Do you have a trial offer?
                </dt>
                <dd>
                    Yes. Simply give us a call and we'll give you a temporary User ID and password. You can try <strong>Permi</strong>Track for free for 30 days to see if it is right for you. If you elect keep PermiTrack, our contracts are written and renewed on an annual basis.
                </dd>
                <dt>
                    How many users can I set up to access <strong>Permi</strong>Track?
                </dt>
                <dd>
                    It's up to you. You will be entitled to provide access to anyone who needs it. There is no additional fee.
                </dd>
                <dt>
                    See also
                </dt>
                <dd>
                    <ul>
                        <li>
                            <a href="http://cfpub.epa.gov/npdes/stormwater/swbasicinfo.cfm">
                                http://cfpub.epa.gov/npdes/stormwater/swbasicinfo.cfm
                            </a>
                        </li>
                        <li>
                            <a href="http://www.epa.gov/compliance/resources/publications/monitoring/cwa/inspections/npdesinspect/npdesmanual.html">
                                <strong>NPDES Compliance Inspection Manual</strong>
                            </a>
                        </li>
                    </ul>
                </dd>
            </dl>
        </div>
        <!--/span-->
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
