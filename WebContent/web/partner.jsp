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
                    Our Partners
                </h1>
                <p>
                    My<strong>Permi</strong>Track.com has a combined resource pool from its alliances that is comprised of approximately 650 engineers, architects, planners and scientists, with a full range of transportation, civil, environmental and structural engineering services; urban design, landscape architecture, community planning and architectural design; and technology and GIS related services.
                </p>
                <p>
                    <%--
                    <html:link styleClass="btn btn-success btn-large"
                               action="/buy">
                        For partners &raquo;
                    </html:link>
                    --%>
                    <html:link action="/help"
                               styleClass="btn btn-primary btn-large">
                        Become a partner &raquo;</html:link>
                </p>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="span4">
                <h2>
                    Partners
                </h2>
                <p>
                    My<strong>Permi</strong>Track.com is committed to the success of our partners and our partners' clients.
                </p>
                <p>
                    As a My<strong>Permi</strong>Track.com partner, your staff will be trained to fully utilize the PermiTrack tools to implement your clients' permits and address their individual permitting needs.
                </p>
            </div>
            <div class="span4">
                <h2>
                    Partner Benefits
                </h2>
                <p>
                    Whether your clients are public or private, permittee or permit agency, they will benefit from the accessibility of our web-based tools. Professional services firms and governmental agencies alike are focused on exceeding their clients' expectations.
                </p>
                <p>
                    <html:link href="#partnerlist"
                               styleClass="btn">
                        Partner list &raquo;
                    </html:link>
                </p>
            </div>
            <div class="span4">
                <h2>
                    Become a Partner
                </h2>
                <p>
                    Why should you become a partner with My<strong>Permi</strong>Track.com? The answer is simple. We help you satisfy your clients. You provide the high level of service, we simply provide the tools.
                </p>
                <p>
                    <html:link module="/"
                               action="/contact"
                               styleClass="btn">
                        Contact Us &raquo;
                    </html:link>
                </p>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="alert alert-success" style="margin-left: 10%; margin-right: 10%;">
            <strong>
                Interested in partnering?
            </strong>
                To explore a partnership, please <html:link module="/" action="/contact">Contact Us</html:link> and ask about our Partner Program.
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/icons/original/consulting-img1_512.jpg" />"
                 alt="">
            <h2 class="featurette-heading">Developing synergy.
                <span class="muted">With our partners.</span>
            </h2>
            <p class="lead">
                Our partners provide direct feedback that helps identify the future course of our development initiatives for our entire product offering. We cannot begin to imagine the requirements of all of our clients as they strive to fulfill the obligations of their respective permits. Without their assistance, <strong>Permi</strong>Track would not be the fully-functional product suite it is today.
            </p>
        </div>
        <a name="partnerlist"></a>
        <hr class="featurette-divider">
        <fieldset>
            <legend>
                Platinum Partners
            </legend>
        </fieldset>
        <div class="row">
            <div class="span6">
                <a href="http://www.mysagefire.com"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/sagefireweblogo1.png"
                                alt="" />
                </a>
                <h6>
                Sagefire
                </h6>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                On-Site Product Individual or Team Training
                <br>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Product Data Entry Specialists
                <br>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Data Business Analysts
                <br>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                GIS and Mapping Services
            </div>
            <div class="span6">
                <div class="well well-small">
                    <h5>
                        Contact Information
                    </h5>
                    <p>
                        (612) 284-6333
                    </p>
                    <p>
                        <a href="mailto:sales@mypermitrack.com">
                            sales@mypermitrack.com
                        </a>
                    </p>
                    <p>
                        Sagefire
                        <br>
                        100 North 6th Street – 710C
                        <br>
                        Minneapolis, MN 55403
                    </p>
                </div>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="row">
            <div class="span6">
                <a href="http://www.sehinc.com"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/whlogo.gif"
                                alt="" />
                </a>
                <h6>
                    SEH Inc.
                </h6>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Inspection Engineers
                <br>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Inspection Analysts
            </div>
            <div class="span6">
                <div class="well well-small">
                    <h5>
                        Contact Information
                    </h5>
                    <p>
                        612.758.6700
                    </p>
                    <p>
                        <a href="mailto:info@sehinc.com">
                            info@sehinc.com
                        </a>
                    </p>
                    <p>
                        <a href="http://www.sehinc.com/">
                            www.sehinc.com
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <hr class="featurette-divider">
        <fieldset>
            <legend>
                Gold Partners
            </legend>
        </fieldset>
        <div class="row">
            <div class="span6">
                <a href="http://www.rasmith.com"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/rasmith.jpg"
                                alt="" />
                </a>
                <h6>
                raSmith
                </h6>
                <html:image module="/"
                            page="/img/logo/partners/sehinspect.png"
                            alt="" />
                Sr. Sales &amp; Subscription Consultants
            </div>
            <div class="span6">
                <div class="well well-small">
                    <h5>
                        Contact Information
                    </h5>
                    <p>
                        (920) 731-8397 x3406
                    </p>
                    <p>
                        <a href="mailto:Jeff.Mazanec@rasmith.com">
                            Jeff.Mazanec@rasmith.com
                        </a>
                    </p>
                    <p>
                        <a href="http://www.rasmith.com"
                           target="_blank">
                            http://www.rasmith.com
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <hr class="featurette-divider">
        <fieldset>
            <legend>
                Bronze Partners
            </legend>
        </fieldset>
        <div class="row">
            <div class="span3">
                <a href="http://www.dot.ca.gov/"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/ct_logo_trans_new_sized_81x81.png"
                                alt="" />
                </a>
            </div>
            <div class="span3">
                <html:image module="/"
                            page="/img/logo/partners/luebbert.png"
                            alt="" />
            </div>
            <div class="span3">
                <a href="http://www.redbarnridge.com/"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/redbarn-sized.png"
                                alt="" />
                </a>
            </div>
            <div class="span3">
                <a href="http://www.meritprofessional.com/"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/mp-sized.png"
                                alt="" />
                </a>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="row">
            <div class="span3">
                <a href="http://www.stormwaterenvironmental.com/"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/seca-sized-81x81.png"
                                alt="" />
                </a>
            </div>
            <div class="span3">
                <a href="http://www.paladinenvironmentalconsulting.com"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/paladin.png"
                                alt="" />
                </a>
            </div>
            <div class="span3">
                <html:image module="/"
                            page="/img/logo/partners/strmusa.gif"
                            alt="" />
            </div>
            <div class="span3">
                <html:image module="/"
                            page="/img/logo/partners/greenlight-sized.png"
                            alt="" />
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="row">
            <div class="span3">
                <a href="http://www.cityofmadison.com/engineering/erosionPermit.cfm"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/Client-Madison-Logo-sized.png"
                                alt="" />
                </a>
            </div>
            <div class="span3">
                <a href="http://www.erosion.umn.edu/"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/umn.gif"
                                alt="" />
                </a>
            </div>
            <div class="span3">
                <a href="http://www.dot.state.mn.us/environment/erosioncontrol"
                   target="_blank">
                    <html:image module="/"
                                page="/img/logo/partners/mndot-sized-81x81.png"
                                alt="" />
                </a>
            </div>
            <div class="span3"></div>
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
                      $('.partnerbutton').addClass("active"); //.carousel()
                  })
            }(window.jQuery)
        </script>
    </tiles:put>
</tiles:definition>