/*

Author: Julijan Andjelic
Plugin name: spager
Description: simple jQuery plugin which allows you to paginate your table
             can be very useful when you want to split up large tables into
             smaller chunks
             
             
             usage:
             $("#someTable").jpager({
                    
                    items:    10,
                    ctrls:    "mycontrols",
                    animate:  false,
                    opts:     [5,10,20,30,50]
                    
                    })
*/
(function($) {
jQuery.fn.spager=function(settings) {

var controlsElement=settings.ctrls;
var itemsPerPage=settings.items;
var opts=settings.opts;
var animation=settings.animate;
var tg=$(this);
var rows=$(tg).children().find("tr");
var rowCount=rows.size();
if (opts==null) {
opts=[10,15,25,50];
}
if (itemsPerPage==null) {
itemsPerPage=opts[0];
}
jQuery.page=function(pg) {
          stP=itemsPerPage*(pg-1);
          enP=parseInt(stP)+parseInt(itemsPerPage)-1; 
$(rows).show();
$(rows).filter(":lt("+stP+")").hide();
$(rows).filter(":gt("+enP+")").hide();

          if (animation!=false) {
                    $(tg).animate({
                    height: 2,
                    duration: 500
                    })
          }
}
jQuery.generatePages=function(max) {
var page=0;
if (max!=null) {
          parseInt(itemsPerPage=max);
}
           $("#"+controlsElement+" #pages").empty();
           i=0;
           while (i<rowCount) {
           i+=itemsPerPage;
           page++;

           $("#"+controlsElement+" #pages").append(" <a onclick='$.page($(this).text()); $(this).parent().children().removeClass(); $(this).addClass(\"action\")' style='color: gray; font-size: 16px;'><U>"+page+"</U></a> ");   
                   
           }
lastPageProducts=rowCount-($("#"+controlsElement+" #pages a:last").text()*itemsPerPage);
if (lastPageProducts>0) {
page++;
}         
$("#"+controlsElement+"#pages a:eq(0)").addClass("action");
$.page(1);
}
$("#"+controlsElement).empty().append("<div id='pages'></div>").css("text-align","center");
$.generatePages();
return this;
}
})(jQuery);