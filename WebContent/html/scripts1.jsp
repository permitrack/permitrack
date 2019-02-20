<%@ page import="com.sehinc.common.CommonConstants" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<tiles:insert attribute="scripts" />
<script type="text/javascript">
    var errors;
    var accordians = 0;
    $(function ()
      {
          $('.warn-delete').bind('click',
                                 function (event)
                                 {
                                     bCancel
                                             = true;
                                     return confirm('Are you sure you want to delete this item?');
                                 });
      });
    $(function ()
      {
          $('[data-type="select"]').each(function (index)
                                         {
                                             var that = this;
                                             $(this).editable({
                                                                  <%
                                                                      if(pageContext.getAttribute(CommonConstants.MODE) == null || !pageContext.getAttribute(CommonConstants.MODE).equals("create"))
                                                                      {
                                                                  %>
                                                                  value:getDataValue($(that).attr('id')),
                                                                  <%
                                                                      }
                                                                  %>
                                                                  source:getDataSource($(that).attr('id'))

                                                              });
                                         });
          $('[data-type]').each(function (index)
                                {
                                    var that = this;
                                    $(this).editable('option',
                                                     'validate',
                                                     function (v)
                                                     {
                                                         return validateField(v,
                                                                              $(that).parents("form").get(0),
                                                                              $(that).attr('id'));
                                                     });
                                });
//          $('[data-type="date"]').editable({
//                                               format: 'yyyy-mm-dd',
//                                               viewformat: 'mm/dd/yyyy'
                                               /*,
               viewformat: 'mm/dd/yyyy'*//*,
               datepicker: {
               weekStart: 1
               }*/
//                                           });
          /*
           $('[data-type="wysihtml5"]').editable({
           wysihtml5:{
           "image":false, //Button to insert an image. Default true,
           "stylesheets":false
           }
           });
           */
      });
    $(function ()
      {
          /*
           Override the function that allows us to hook into Struts-generated errors
           http://api.jquery.com/Types/?rdfrom=http%3A%2F%2Fdocs.jquery.com%2Fmw%2Findex.php%3Ftitle%3DTypes%26redirect%3Dno#Proxy_Pattern
           */
          if (typeof jcv_handleErrors
                  != "undefined")
          {
              var proxied = jcv_handleErrors;
              jcv_handleErrors
                      = function ()
              {
                  console.log(this,
                              arguments);
                  errors
                          = arguments[0];
                  if ($('[data-type]').size()
                          < 1)
                  {
                      return proxied.apply(this,
                                           arguments);
                  }
              };
          }
      });
    $(function ()
      {
          $(".myAccordian").each(function (index)
                                 {
                                     accordians++;
                                     var that = this;
                                     var next = $(this).next();
                                     $(this).wrap('<a class="accordion-toggle" data-toggle="collapse" href="#collapse'
                                                          + accordians
                                                          + '"></a>').parent().wrap('<div class="accordion-heading"></div>').parent().wrap('<div class="accordion-group"></div>').parent().append(next.wrap('<div id="collapse'
                                                                                                                                                                                                                    + accordians
                                                                                                                                                                                                                    + '" class="accordion-body in collapse"><div class="accordion-inner"></div></div>').parent().parent()).wrap('<div class="accordion"></div>');
                                 });
      });
/* TODO
    $(function ()
      {
     $('form:first *:input[type!=hidden]:first').focus();
      });
*/
    $(function ()
      {
          window.setTimeout(function() { $(".navbar-fixed-top .alert").alert('close'); }, 8000);
      });
    function validateAll(form)
    {
        var result = true;
        for (var err in $('[data-type]').editable('validate'))
        {
            $("#"
                      + err).editable('show',
                                      'closeAll',
                                      false);
            $(".editableform").submit();
            result
                    = false;
        }
        return result;
    }
    function validateField(valu, form, field)
    {
        if ($('a#'
                      + field).attr('data-type')
                    == 'date'
                    && valu
                != null
                && valu
                != "")
        {
            valu
                    = moment(valu).utc().format('MM/DD/YYYY');
        }
        $(form[field]).val(valu);
        errors
                = new Array();
        validateForm(form);
        var formName = jcv_retrieveFormName(form);
        var validations =
                [
                    typeof window[formName
                            + '_required']
                            == 'function'
                            ? eval('new '
                                           + formName
                                           + '_required()')
                            : null,
                    typeof window[formName
                            + '_maxlength']
                            == 'function'
                            ? eval('new '
                                           + formName
                                           + '_maxlength()')
                            : null,
                    typeof window[formName
                            + '_IntegerValidations']
                            == 'function'
                            ? eval('new '
                                           + formName
                                           + '_IntegerValidations()')
                            : null,
                    typeof window[formName
                            + '_intRange']
                            == 'function'
                            ? eval('new '
                                           + formName
                                           + '_intRange()')
                            : null,
                    typeof window[formName
                            + '_custom']
                            == 'function'
                            ? eval('new '
                                           + formName
                                           + '_custom()')
                            : null
                ];
        for (var index = 0; index
                < validations.length; index++)
        {
            var validation = validations[index];
            if (validation
                    != null)
            {
                for (var error in validation)
                {
                    if (validation[error][0]
                            == field)
                    {
                        var message = validation[error][1];
                        if (errors.indexOf(message)
                                >= 0)
                        {
                            // $("input[value='Save']").attr('disabled', true);
                            return message;
                        }
                    }
                }
            }
        }
        //        $("input[value='Save']").removeAttr('disabled');
        return null;
    }
</script>
