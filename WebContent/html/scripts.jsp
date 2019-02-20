<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld"
           prefix="html" %>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/inputs-ext/wysihtml5/bootstrap-wysihtml5-0.0.2/wysihtml5-0.3.0.js" />'></script>
<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"
        type="text/javascript"></script>
<script type="text/javascript">
    !window.jQuery
    && document.write('<script src="<html:rewrite module="/" page="/javascript/jquery-1.7.2.js" />" type="text/javascript"><\/script>');
</script>
<!-- respond.js - Shim for IE -->
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/respond.min.js" />'></script>
<!-- modernizr -->
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/vendor/modernizr-2.6.2.min.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/vendor/modernizr.cookies.js" />'></script>
<!-- jQuery UI -->
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery-ui-1.8.16.custom.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/moment.js" />'></script>
<!-- Bootstrap -->
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/bootstrap.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/inputs-ext/wysihtml5/bootstrap-wysihtml5-0.0.2/bootstrap-wysihtml5-0.0.2.min.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/bootstrap-editable.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/inputs-ext/wysihtml5/wysihtml5.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/bootstrap-fileupload.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jasny-bootstrap.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/select2.js" />'></script>
<!-- Common js for permitrack -->
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/common.js" />'></script>
<!-- Boilerplate "plugins" -->
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/plugins.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.tablesorter.min.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.cookie.2.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.hotkeys.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.jstree.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.ui.touch.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.sly.js" />'></script>
<script type="text/javascript"
        src='<html:rewrite module="/" page="/javascript/jquery.ba-resize.js" />'></script>
<script type="text/javascript">
    //<!--
    if (!Array.prototype.indexOf)
    {
      Array.prototype.indexOf = function(elt /*, from*/)
      {
        var len = this.length >>> 0;

        var from = Number(arguments[1]) || 0;
        from = (from < 0)
             ? Math.ceil(from)
             : Math.floor(from);
        if (from < 0)
          from += len;

        for (; from < len; from++)
        {
          if (from in this &&
              this[from] === elt)
            return from;
        }
        return -1;
      };
    }
    jQuery.fn.outerHTML
            = function (s)
    {
        return s
                ? this.before(s).remove()
                : jQuery("<p>").append(this.eq(0).clone()).html();
    };
/*
    $(function ()
      {
          if ($.browser.msie && navigator.userAgent.indexOf('Trident')!==-1){
          	*/
/* IE 8  specific code goes here. *//*

              $('legend').wrap('<fieldset></fieldset>');
          }
      });
*/
    $(function ()
      {
          var $dialog = $('#dialog').html('').dialog({
                                                         autoOpen:false,
                                                         resizable:false
                                                     });
      });
    /*
     http://groups.google.com/group/jstree/browse_thread/thread/eeaa773136de5358
     */
    $(function ()
      {
          $("#treeSearch").click(function ()
                                 {
                                     var treeSearch = $("#treeInput").val();
                                     if (treeSearch
                                             != "")
                                     {
                                         $("#treeView").jstree("search",
                                                               treeSearch);
                                     }
                                     else
                                     {
                                         $("#treeView").jstree("clear_search");
                                     }
                                     $('#frame').sly('reload');
                                 });
          $("#treeInput").keypress(function (event)
                                   {
                                       if (event.which
                                               == 13)
                                       {
                                           event.preventDefault();
                                           var treeSearch = $("#treeInput").val();
                                           if (treeSearch
                                                   != "")
                                           {
                                               $("#treeView").jstree("search",
                                                                     treeSearch);
                                           }
                                           else
                                           {
                                               $("#treeView").jstree("clear_search");
                                           }
                                           $('#frame').sly('reload');
                                       }
                                       //                                       return false;
                                   });
          $("#treeView").jstree({
                                    "plugins":
                                            [
                                                "themes",
                                                "html_data",
                                                "ui",
                                                "hotkeys",
                                                "cookies",
                                                "types",
                                                "search"
                                            ],
                                    /*
                                     "cookies":{
                                     "cookie_options":{
                                     "path":".."
                                     }
                                     },
                                     */
                                    "themes":{

                                        "theme":"default",
                                        "dots":false/*,

                                         "icons" : false*/

                                    },
                                    "ui":{
                                        "selected_parent_close":false
                                    },
                                    "search":{
                                        "case_insensitive":true
                                    },
                                    "core":{
                                        "animation":0,
                                        "open_parents":false
                                    },
                                    "types":{
                                        "types":{
                                            "GoalActivityLeaf":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/gear.gif"
                                                }
                                            },
                                            "GoalBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/small_pencil.gif"
                                                }
                                            },
                                            "GoalLeaf":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/small_pencil.gif"
                                                }
                                            },
                                            "MCMBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/award.gif"
                                                }
                                            },
                                            "PlanBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/city.gif"
                                                }
                                            },
                                            "BMPBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/star.gif"
                                                }
                                            },
                                            "GoalTemplateBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/small_pencil.gif"
                                                }
                                            },
                                            "MCMTemplateBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/award.gif"
                                                }
                                            },
                                            "PlanTemplateBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/city.gif"
                                                }
                                            },
                                            "BMPTemplateBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/star.gif"
                                                }
                                            },
                                            "BMPDBBranch":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/star.gif"
                                                }
                                            },
                                            "BMPDBGoalLeaf":{
                                                "icon":{
                                                    "image":"/sehsvc/img/icons/small_pencil.gif"
                                                }
                                            }
                                        }
                                    }
                                }).bind("reselect.jstree",
                                        function ()
                                        {
                                            $("#treeView").bind("select_node.jstree",
                                                                function (event, data)
                                                                {
                                                                    // 'data.rslt.obj' is the jquery extended node that was clicked
                                                                    if (data.rslt.obj[0].children[1].href
                                                                            != '')
                                                                    {
                                                                        window.location
                                                                                = data.rslt.obj[0].children[1].href;
                                                                    }
                                                                });
                                        }).bind("open_node.jstree",
                                                function (e, data)
                                                {
                                                    $('#frame').sly('reload');
                                                }).bind("close_node.jstree",
                                                        function (e, data)
                                                        {
                                                            $('#frame').sly('reload');
                                                        }).one("reopen.jstree",
                                                               function (event, data)
                                                               {
                                                                   $('#treeView').css("visibility",
                                                                                      "visible");
                                                                   setTreeView();
                                                                   $("#treeView").addTouch();
                                                               });
          $('#frame').sly({
                              horizontal:0,
                              scrollBy:250,
                              dragging:1,
                              dragHandle:1,
                              dynamicHandle:1,
                              speed:300,
                              scrollBar:".scrollBar"
                          });
          /*
           // TODO
           $('#frameH').sly({
           horizontal:1,
           scrollBy:250,
           dragging:1,
           speed:300,
           itemNav:'centered'
           });
           */
          $("[data-spy=affix]").parent().resize(function (e)
                                                {
                                                    $("[data-spy=affix]").css('width',
                                                                              $("[data-spy=affix]").parent().width()
                                                                                      - 38);
                                                    setTreeView();
                                                });
      });
    function setTreeView()
    {
        try
        {
            var theTree = $.jstree._focused();
            if (theTree
                    != null)
            {
                var selectedNode = theTree._get_node(null,
                                                     false);
                try
                {
                    if (!selectedNode
                            || selectedNode[0]
                            == theTree._get_children(-1)[0])
                    {
                        theTree.open_node(theTree._get_children(-1)[0],
                                          false,
                                          false);
                    }
                    else
                    {
                        var sly = $('#frame').sly().data('sly');
                        var pos = sly.getPos(selectedNode);
                        sly.slideTo(pos.center,
                                    true);
                    }
                }
                catch (e)
                {
                }
            }
        }
        catch (e)
        {
        }
    }//-->
</script>
<script type="text/javascript">
    //<!--
    $.fn.editable.defaults.mode
            = 'inline';
    $.fn.editable.defaults.emptytext
            = '(blank)';
    $.fn.editable.defaults.onblur
            = 'submit';//$.fn.editable.defaults.send = 'never';//-->
</script>
<%-- Legacy Cookie --%>
<%--
<script type="text/javascript">
    //<!--
    <%
        if(SessionService.getUserValue(request) != null)
        {
    %>
    SetCookie("<%= CommonConstants.PORTAL_USER %>",
              "<%= SessionService.getUserValue(request).getUsername() %>");
    <%
        }
    %>//-->
</script>
--%>
