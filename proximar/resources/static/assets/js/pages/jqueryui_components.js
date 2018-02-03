/* ------------------------------------------------------------------------------
*
*  # jQuery UI Widgets
*
*  Specific JS code additions for jqueryui_components.html page
*
*  Version: 1.0
*  Latest update: Nov 12, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Dialogs
    // -------------------------

	$('#delete-confirmation-model').dialog({
		autoOpen:false,
		modal: true,
		classes: {
			"ui-dialog": "modal-dialog",
		    "ui-dialog-titlebar": "modal-header",
		    "ui-dialog-title": "modal-title",
		    "ui-dialog-titlebar-close": "close",
		    "ui-dialog-content": "modal-body",
		    "ui-dialog-buttonpane": "modal-footer"
		}
	});
	

    $('#delete-btn').click(function() {
        $('#delete-confirmation-model').dialog('open');
    });


});
