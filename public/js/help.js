$(document).ready(function () {
  
	// if user clicked on button, the overlay layer or the dialogbox, close the dialog	
	//$('a.btn-ok, #dialog-overlay, #dialog-box').click(function () {		
    $('a.btn-ok').click(function () { 
		$('#dialog-overlay, #dialog-box, , #msg-box,  #msg-overlay').hide();		
		return false;
	});
	
	// if user resize the window, call the same function again
	// to make sure the overlay fills the screen and dialogbox aligned to center	
	$(window).resize(function () {
		
		//only do it if the dialog box is not hidden
		if (!$('#dialog-box').is(':hidden')) popup();		
	});	
	
        //square help div
        $(function() {
  var moveLeft = 20;
  var moveDown = 10;

  $('a#trigger').hover(function(e) {
    $('div#pop-up').show();
      //.css('top', e.pageY + moveDown)
      //.css('left', e.pageX + moveLeft)
      //.appendTo('body');
  }, function() {
    $('div#pop-up').hide();
  });

  $('a#trigger').mousemove(function(e) {
    $("div#pop-up").css('top', e.pageY + moveDown).css('left', e.pageX + moveLeft);
  });

});


});

//Popup dialog
function popup(message, div) {
		//alert($('#'+'header').width());
	// get the screen height and width  
	var maskHeight = $(document).height();  
	var maskWidth = $(document).width();
	
	// calculate the values for center alignment
	 var dialogTop =  (maskHeight/3);  
	var dialogLeft = (maskWidth/3); 
	
	// assign values to the overlay and dialog box
	 $('#'+div+'-overlay').show();
	$('#'+div+'-box').css({top:dialogTop, left:dialogLeft}).show();
	
	// display the message
	$('#'+div+'-message').html(message);
			
}
