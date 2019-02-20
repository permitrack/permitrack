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
                        Sales
                    </li>
                    <li>
                        <a href="#pricing">
                            Subscription Pricing
                        </a>
                    </li>
                    <li>
                        <a href="#cloud">
                            Cloud-based
                        </a>
                    </li>
                    <li>
                        <a href="#clients">
                            Client testimonials
                        </a>
                    </li>
                    <li class="nav-header">
                        Products
                    </li>
                    <li>
                        <a href="#products">
                            Stormwater+ (MS4)
                        </a>
                    </li>
                    <li>
                        <a href="#products">
                            Erosion Control (ESC)
                        </a>
                    </li>
                    <li>
                        <a href="#products">
                            Air Quality (ENV)
                        </a>
                    </li>
                    <%--
                    <li>
                        <a href="#products2">
                            DataView Online (GIS/DVO)
                        </a>
                    </li>
                    --%>
                    <li class="nav-header">
                        Services &amp; Solutions
                    </li>
                    <li>
                        <html:link action="/inspection" anchor="services">
                            Inspection Services
                        </html:link>
                    </li>
                    <li>
                        <a href="#government">
                            Government Solutions
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <div class="hero-unit img-polaroid">
                <h1>
                    Sales and Subscriptions
                </h1>
                <p>
                    Purchase a PermiTrack subscription to one or more of our products
                </p>
                <p>
                    <html:link module="/"
                               action="/demo"
                               styleClass="btn btn-success btn-large">
                        Try it first &raquo;
                    </html:link>
                </p>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <h4>
                        Subscription Info
                    </h4>
                    <p>
                        Contact our sales department to find out more information about subscription costs, demos, or anything else we can help you with.
                    </p>
                    <ul>
                        <li>
                            Call <a href="tel:6122846331">612-284-6331</a>
                        </li>
                        <li>
                            Email
                            <a href="mailto:sales@mypermitrack.com">sales@mypermitrack.com</a>
                        </li>
                    </ul>
                </div>
                <div class="span4">
                    <h4>
                        Cloud-based
                    </h4>
                    <p>
                        Get all these features and much more for a low cost monthly subscription using cloud-based online access.
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#cloud">
                            Learn more &raquo;
                        </html:link>
                    </p>
                </div>
                <div class="span4">
                    <h4>
                        Client Testimonials
                    </h4>
                    <p>
                        PermiTrack has established a vast array of clientele that benefit from our cost-effective web-based technologies.
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#clients">
                            See testimonials &raquo;
                        </html:link>
                    </p>
                </div>
            </div>
            <a name="products"></a>
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
                        PermiTrack MS4
                    </h4>
                    <p>
                        Project Management for Municipal Storm Sewer Permits (MS4)
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#pricing">
                            Pricing &raquo;
                        </html:link>
                        <html:link styleClass="btn"
                                   action="/demo">
                            Try it &raquo;
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
                        PermiTrack ESC
                    </h4>
                    <p>
                        Project and Inspection Management for Erosion Control (ESC)
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#pricing">
                            Pricing &raquo;
                        </html:link>
                        <html:link styleClass="btn"
                                   action="/demo">
                            Try it &raquo;
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
                        PermiTrack ENV
                    </h4>
                    <p>
                        Facility, Asset and Emissions Management for VOCs, HAPs and other emissions (ENV)
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#pricing">
                            Pricing &raquo;
                        </html:link>
                        <html:link styleClass="btn"
                                   action="/demo">
                            Try it &raquo;
                        </html:link>
                    </p>
                </div>
            </div>
            <a name="products2"></a>
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
                        PermiTrack DVO
                    </h4>
                    <p>
                        Our full-service GIS consulting services include GIS training, full system and database design and implementation, customized GIS applications and data acquisition.
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#pricing">
                            Pricing &raquo;
                        </html:link>
                        <html:link styleClass="btn"
                                   action="/demo">
                            Try it &raquo;
                        </html:link>
                    </p>
                </div>
                --%>
                <div class="span6">
                    <div>
                        <span style="position: absolute;">
                            <html:link action="/inspection"
                                       anchor="services">
                                <img src="<html:rewrite module="/" page="/img/icons/original/images/Logo-PermiTrackESC-Small.png" />"
                                     alt="">
                            </html:link>
                        </span>
                        <html:link action="/inspection"
                                   anchor="services">
                            <img class="img-circle"
                                 style="margin-top: 40px; margin-bottom: 25px;"
                                 src="<html:rewrite module="/" page="/img/icons/original/bigstock-Worker-in-front-of-a-laptop-co-12649748-200x300.png" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Inspection Services
                    </h4>
                    <p>
                        We are proud to offer a full suite of Stormwater/MS4 compliance inspection services.
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   action="/inspection"
                                   anchor="services">
                            Learn More &raquo;
                        </html:link>
                    </p>
                </div>
                <div class="span6">
                    <div>
                        <html:link href="#government">
                            <img style="margin-top: 10px;"
                                 src="<html:rewrite module="/" page="/img/icons/original/Icon-Government_288.png" />"
                                 alt="">
                        </html:link>
                    </div>
                    <h4>
                        Government Solutions
                    </h4>
                    <p>
                        Get assistance with your Federal, State, Regional and Municipal regulations, permitting, initiatives and policies.
                    </p>
                    <p>
                        <html:link styleClass="btn"
                                   href="#government">
                            Learn more &raquo;
                        </html:link>
                    </p>
                </div>
            </div>
            <a name="pricing"></a>
            <hr class="featurette-divider">
            <h2>
                Subscription Pricing
            </h2>
            <p class="lead">
                MyPermiTrack.com subscribers have access to automated information systems, technical support, and secure data, with no-hassle upgrades and enhancements. No software to buy. No individual licenses for users—give as many staff as you wish access to the system.
            </p>
            <p>
                Please contact Sales at 866.830.3388 or
                <a href="mailto:sales@mypermitrack.com">email</a>
                to learn more about our subscription pricing.
            </p>
            <%--
            <strong>Permi</strong>Track subscription fees are variable to reflect the respective size of the client, thus making the applications affordable to a wider array of users. In addition, our fees may be billed on a monthly, quarterly, or annual basis, minimizing significant capital outlay on behalf of your organization. Our fees are broken down as follows:
            --%>
            <%--
            <p>
                Initiation Fee
            </p>
            <p>
                This variable fee is based upon client size and SWPPP complexity. This fee will vary between $1,500 and $5,000 for the PermiTrackESC product, and $1,495 and $2,495 for the
                <strong>Permi</strong>Track<sub>MS4</sub>
                product. A specific cost estimate will be provided to the client prior to Initiation. The Initiation Fee includes import of the existing SWPPP, data conversion, and training on the
                application(s). Additional services may include BMP import and review, or the development of a BMP library for ongoing use.
            </p>
            <p>
                Per-Permit/Project Fee (<strong>Permi</strong>Track<sub>ESC</sub> only)
            </p>
            <p>
                Fees are assessed upon permit/project creation only. Once a permit/project is created, the fee is assessed, based upon an agreed-upon fee schedule. Typical fees range from $75 to $250 per permit/project, and are cumulatively billed quarterly.
            </p>
            <p>
                This fee is all-inclusive, and you will not be assessed for the particular permit/project again. It includes all database access, data storage and maintenance costs, support, and product enhancements for the life of the permit.
            </p>
            <p>
                Ongoing Service Fee (<strong>Permi</strong>Track<sub>MS4</sub> only)
            </p>
            <p>
                Fees are based on a sliding scale depending upon respective size of the client, and range from $50 to $500/month.
            </p>
            <p>
                As with the Per-Permit/Project Fee associated with <strong>Permi</strong>Track<sub>ESC</sub>, this fee is all-inclusive as well. It includes all database access, data storage and maintenance costs, support, and product enhancements for the duration of the client's contract term.
            </p>
            --%>
            <a name="cloud"></a>
            <hr class="featurette-divider">
            <h2>
                Benefits of the Cloud
            </h2>
            <p>
                <b>
                    Vendor’s Responsibility
                </b>
                Cloud-based application providers are responsible for managing and maintaining both the software and hardware components of the application. The network issues such as data redundancy, data backup and recovery are also planned and managed by the vendors. They upgrade the software on regular intervals.
            </p>
            <p>
                <b>
                    Ownership
                </b>
                Since Could-based vendors charge a set price per user per month, the firms don’t have to pay extra money for modules they don’t even use. It literally removes the maintenance, end user support, and administration costs of the software. The implementation and customization costs of utilizing the cloud are also lower than the traditional software. All this results in a very low total cost of ownership (TCO).
            </p>
            <p>
                <b>
                    Scalability
                </b>
                Cloud-based applications offer you more scalability. As your needs grow, the system will grow. This gives you easy and economical access to many programs while allowing for future growth.
            </p>
            <p>
                <b>
                    Regular Updates
                </b>
                Cloud-based apps are regularly upgrade so that system admins don’t have to engage in installing and maintaining applications.
            </p>
            <p>
                <b>
                    Easy Access
                </b>
                A major advantage of cloud-based applications is that they can easily and quickly be accessed from anywhere with a web browser or other smart device. This gives users a great facility even when they are at home or in another country. They can access real time synchronized applications from Laptops and Smart Phones.
            </p>
            <a name="government"></a>
            <hr class="featurette-divider">
            <h2>
                Government &amp; Multi-Regional Agency Solutions
            </h2>
            <p class="lead">
                The challenge for a multi-regional agency is to be able to interpret Federal, State, Regional and Municipal information, regulations, permitting, initiatives and policies to develop balanced and effective programs.
            </p>
            <p>
                My<strong>Permi</strong>Track.com has expert networks of environmental engineers and advisory services; technical and GIS databases; and integrated energy, economic, security and defense and environmental information systems that help agencies inform and refine their decision-making processes.
            </p>
            <%--
            <html:image module="/"
                        page="/img/icons/original/GIS-3d_Valley_LiDAR_smaller_100.png"
                        alt="" />
            --%>
            <%--
            <p>
                <html:image module="/"
                            page="/img/icons/original/bigstock-Grass-And-Sky-With-Clouds-1784316-300wx200h.png"
                            alt="" />
            </p>
            --%>
            <h5>
                Industry Challenges
            </h5>
            <ul>
                <li>
                    Unfunded government mandates in place for mandatory and designated compliance
                </li>
                <li>
                    Compliance with EPA’s Clean Water Act (CWA) and Clean Air Act (CAA)
                </li>
                <li>
                    Integrating solutions across multiple agencies
                </li>
                <li>
                    Mandatory compliance monitoring (Federal CAA – NSPS, NESHAPS, OECA-RCRA)
                </li>
                <li>
                    Reporting expectations and quality data requirements of EPA/OECA
                </li>
                <li>
                    Following Best Practices for Compliance Monitoring
                </li>
                <li>
                    Consideration of overburdened communities within multi-community initiatives – Environmental Justice
                </li>
            </ul>
            <a name="clients"></a>
            <hr class="featurette-divider">
            <h2>
                A vast array of <strong>Permi</strong>Track clientele
            </h2>
            <p class="lead">
                From professional consultants assisting municipalities with their annual MS4 report, to independent best management practice companies recording their inspection data for real-time access by their clients and permitting agencies alike.
            </p>
            <p>
                All of the products in the
                <strong>Permi</strong>Track suite have been developed to address the specific needs of the EPA-mandated, NPDES General Construction and MS4 permits. However, our customers using the
                <strong>Permi</strong>Track applications vary significantly, and represent several different sectors within the NPDES permit.
            </p>
            <p>
                From professional consultants assisting municipalities with their annual MS4 report, to independent best management practice companies recording their inspection data for real-time access by their clients and permitting agencies alike,
                <strong>Permi</strong>Track has established a vast array of clientele that benefit from our cost-effective web-based technologies.
            </p>
            <p>
                Here is a small sample of satisfied
                <strong>Permi</strong>Track clients, along with links to their respective websites and testimonials. For additional contacts in your area, please
                <html:link module="/"
                           action="/contact">
                    Contact Us
                </html:link>
            </p>
            <div class="row-fluid">
                <div class="span3">
                    <a href="http://www.ci.north-saint-paul.mn.us/"
                       target="_blank">
                        <html:image module="/"
                                    page="/img/logo/partners/client-nstpaul-logo.gif"
                                    alt="" />
                    </a>
                </div>
                <div class="span6">
                    <strong>City of North St. Paul, MN</strong>
                    <p>
                        "I have used <strong>Permi</strong>Track for over five years now. The savings in both time and cost for me and my staff over that time-frame has been significant. In addition, the development staff has been very helpful in customizing the application to meet our needs."
                    </p>
                    <p>
                        Mr. Dave Kotilinek
                        <br>
                        City Engineer
                    </p>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span3">
                    <a href="http://www.cityofmadison.com/"
                       target="_blank">
                        <html:image module="/"
                                    page="/img/logo/partners/Client-Madison-Logo-sized.png"
                                    alt="" />
                    </a>
                </div>
                <div class="span6">
                    <p>
                        <strong>City of Madison, WI</strong>
                    </p>
                    "<strong>Permi</strong>Track's interactive Google map allows for real-time access by our citizens and staff to ongoing inspection data records in an easy-to-use and recognizable format."
                    <p>
                        <br>
                        Mr. Greg Fries
                        <br>
                        Assistant City Engineer
                    </p>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span3">
                    <a href="http://www.redbarnridge.com/"
                       target="_blank">
                        <html:image module="/"
                                    page="/img/logo/partners/redbarn-sized.png"
                                    alt="" />
                    </a>
                </div>
                <div class="span6">
                    <strong>Red Barn Ridge, LLC</strong>
                    <p>
                        "We are a smaller, established private inspection company. <strong>Permi</strong>Track<sub>ESC</sub> allows us to maintain all of our inspection records in one secure, easily-accessible location for a very affordable price, and eliminates my concerns about data storage and backup."
                    </p>
                    <p>
                        Mr. Paul Schwinghammer
                        <br>
                        Owner
                    </p>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span3">
                    <a href="http://www.roanokeva.gov/"
                       target="_blank">
                        <html:image module="/"
                                    page="/img/logo/partners/client-roanoke-logo.gif"
                                    alt="" />
                    </a>
                </div>
                <div class="span6">
                    <strong>City of Roanoke, VA</strong>
                    <p>
                        "We are coming to <strong>Permi</strong>Track from a stand-alone, CD-based system. The web-based platform of <strong>Permi</strong>Track<sub>MS4</sub> allows for greater data access by staff across our organization."
                    </p>
                    <p>
                        Mr. Christopher Blakeman
                        <br>
                        Environmental Coordinator
                    </p>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span3">
                    <a href="http://www.ci.southlake.tx.us/"
                       target="_blank">
                        <html:image module="/"
                                    page="/img/logo/partners/client-southlaketx-logo.gif"
                                    alt="" />
                    </a>
                </div>
                <div class="span6">
                    <strong>City of Southlake, TX</strong>
                    <p>
                        "The City of Southlake needed to improve on communication with our building community while not burdening inspectors with paper reviews, chasing down responsible parties, or creating more work. <strong>Permi</strong>Track helps us do that by putting everything in one place. Meanwhile, our building community can quickly update our inspectors of the status of their site, report issues, and review our inspectors' comments on their site. All in just a click or two. Our building community has expressed to us that they find the program helpful and easy to use."
                    </p>
                    <p>
                        Ms. Christi Upton
                        <br>
                        Environmental Coordinator
                    </p>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span3">
                    <a href="http://www.stormwaterenvironmental.com/"
                       target="_blank">
                        <html:image module="/"
                                    page="/img/logo/partners/seca-sized-81x81.png"
                                    alt="" />
                    </a>
                </div>
                <div class="span6">
                    <strong>Stormwater Environmental Compliance Alliance (SECA)</strong>
                    <p>
                        "The BMP-centric nature of the <strong>Permi</strong>Track<sub>ESC</sub> application allows me to customize my inspection items on a project-by-project basis, saving me valuable time on each inspection."
                    </p>
                    <p>
                        Mr. JW Lemons
                        <br>
                        Owner
                    </p>
                </div>
            </div>
            <a name="privacy"></a>
            <hr class="featurette-divider">
            <h2>
                Privacy Policy
            </h2>
            <p class="lead">
                At My<strong>Permi</strong>Track.com, we are keenly aware of the trust you place in us and our responsibility to protect your privacy.
            </p>
            <p>
                As part of this responsibility, we let you know what information we collect when you use our products and services, why we collect it and how we use it to improve your experience.
            </p>
            <p>
                We have five privacy principles that describe how we approach privacy and user information across all of our products:
            </p>
            <ul>
                <li>
                    Use information to provide our users with valuable products and services.
                </li>
                <li>
                    Develop products that reflect strong privacy standards and practices.
                </li>
                <li>
                    Make the collection of personal information transparent.
                </li>
                <li>
                    Give users meaningful choices to protect their privacy.
                </li>
                <li>

                    Be a responsible steward of the information we hold.
                </li>
            </ul>
            <p>
                We hope the following policy will help you understand how SEH collects, uses, and safeguards the personal information you provide on our website.
            </p>
            <div class="well">
                <h4>
                    PermiTrack Privacy Policy
                </h4>
                <p>
                    <strong>
                        Information Collection </strong>
                </p>
                <p>
                    If you browse the
                    My<strong>Permi</strong>Track.com
                    website, you do so anonymously. We do not collect personal information or your e-mail address. We do log your IP address (the Internet address of your computer) to give us an idea of which parts of our website you visit and how long you spend there. But we do not link your IP address to anything personally identifiable. The only other information automatically provided to us is the type of computer and operating system you are using. Your browser supplies us with this information.
                </p>
                <p>
                    <strong>
                        Information Use </strong>
                </p>
                <p>
                    If you provide us with your e-mail or postal address,
                    My<strong>Permi</strong>Track.com
                    will not sell or provide the information you furnish to a third party for any reason.
                </p>
                <p>
                    <strong>
                        Data Security </strong>
                </p>
                <p>
                    As the portal applications and databases that reside on this site are secured, a user id and password are required to enter the applications.
                </p>
                <p>
                    My<strong>Permi</strong>Track.com is fully committed to data security
                </p>
                <p>
                    To prevent unauthorized access, maintain data accuracy and ensure the appropriate use of information, we have put in place physical, electronic and managerial procedures to safeguard and secure the information you provide to us online. However, while we strive to protect your personal information, we cannot ensure the security of the information you transmit to us. In this regard, we urge you to take every precaution to protect your personal data while you are on the Internet. At a minimum, we encourage you to change your passwords often, using a combination of letters and numbers and make sure that you are using a secure browser as you surf the Internet.
                </p>
            </div>
            <p>
                This information was created to provide you with easy-to-understand information about our products and policies to help you make more informed choices about which products you use, how to use them and what information you provide to us.
            </p>
            <p>
                My<strong>Permi</strong>Track.com privacy policy is subject to change at any time without notice. This page will always reflect our most up-to-date policy.
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
                      $('.subscribebutton').addClass("active");
                  })
            }(window.jQuery)
        </script>
    </tiles:put>
</tiles:definition>
