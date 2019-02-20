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
                         src="<html:rewrite module="/" page="/img/icons/original/MC900440106.PNG" />"
                         alt="">
                    DataView Online &amp; GIS
                </h1>
                <p>
                    Geographic Information Systems (GIS) solutions powered by ESRI<sub>©</sub>
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
                    GIS solutions
                </h2>
                <p>
                    In a world of information overload, it’s hard to keep track of it all. A Geographical Information System (GIS) helps incorporate hardware, software and data for capturing, managing, analyzing and displaying all forms of geographically referenced information.
                </p>
            </div>
            <div class="span4">
                <h2>
                    Design, implementation and training
                </h2>
                <p>
                    Our full-service GIS consulting services include GIS training, full system and database design and implementation, customized GIS applications and data acquisition.
                </p>
            </div>
            <div class="span4">
                <h2>
                    Technologies
                </h2>
                <p>
                    We understand government, public utility and private-sector needs and how GIS can best serve those needs across a variety of platforms, devices and software, including GO! Sync Mobile GIS; Cityworks Anywhere; ArcPad custom applications; Bluetooth; GPS data collection; ArcSDE GDBs; DBMS: Microsoft SQL Server, PostgreSQL, SQL Server Express and Google Maps API custom mashups.
                </p>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="featurette">
            <img class="featurette-image pull-right"
                 src="<html:rewrite module="/" page="/img/screenshot/GIS-map-512.png" />"
                 alt="">
            <h2 class="featurette-heading">
                <%--<strong>Permi</strong>Track<sub>GIS</sub> = --%>Geospatial Information Management solutions.
                <span class="muted">Need a map or two?</span>
            </h2>
            <p class="lead">
                Easily view GIS data, and build custom maps to answer everyday questions. DVO GIS is an application for communities with limited time, resources, information technology systems and budgets. Far ranging uses for all environmental tracking and asset management.
            </p>
        </div>
        <%--<hr class="featurette-divider">--%>
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
