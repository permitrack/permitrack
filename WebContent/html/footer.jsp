<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<footer class="footer">
    <div class="container">
        <ul class="nav nav-pills">
            <li>
                <html:link title="Our public web site, MyPermiTrack.com"
                           action="/home"
                           module="/" >
                    www.mypermitrack.com
                </html:link>
            </li>
            <li>
                <html:link module="/" action="/contact"
                           title="Get support or contact us">
                    Contact Us
                </html:link>
            </li>
            <li>
                <html:link module="/" action="/help"
                           title="Go to our Help Desk for manuals and more">
                    Help &amp; Support
                </html:link>
            </li>
            <li>
                <html:link module="/" action="/help" anchor="faq"
                           title="View our Frequently Asked Questions (FAQ)">
                    FAQ
                </html:link>
            </li>
            <li>
                <a href='https://portalsvc.sharepoint.com/sites/extranet'
                   title="Our extranet site for partners, customers and others in the PermiTrack universe">
                    Customer Portal
                </a>
            </li>
            <li>
                <a title="Sagefire"
                   href="http://www.mysagefire.com/">
                    <span class="muted">
                        Powered by
                    </span>
                    © Sagefire
                </a>
            </li>
        </ul>
    </div>
</footer>
