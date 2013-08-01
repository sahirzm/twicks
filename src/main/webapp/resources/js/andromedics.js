function loadForm(url, container, data) {
	$.ajax({
		url : BASE_URL + url,
		type : "get",
		data : data,
		success : function(data) {
			$(container).html(data);
		}
	});
}

function submitForm(form, url, container) {
	$.ajax({
		url : BASE_URL + url,
		type : "POST",
		data : $(form).serialize(),
		success : function(data) {
			try {
				var json = $.parseJSON(data);
				$.each($(form+" div.error"), function(value) {
					$(this).removeClass("error");
				});
				$.each($(form +" input"), function(value){
					$(this).tooltip("destroy");
				});
				$.each($(form +" textarea"), function(value){
					$(this).tooltip("destroy");
				});
				$.each($(form +" select"), function(value){
					$(this).tooltip("destroy");
				});
				$.each(json, function(key, value) {
					$('#'+key).parent().addClass("error");
					$('#'+key).tooltip({
						trigger: "focus",
						title: value,
						placement: "bottom"
					});
				});
			} catch (ex) {
				$(container).html(data);
			}
		}
	});
}
function changeHeading(heading) {
	$('#pageHeading .pageHeading').html(heading);
}

function changeTitle(title) {
	document.title = title;
}
$(function() {
	$(document).ajaxSend(function(event, request, settings) {
		$('#ajaxIndicator').show();
	});

	$(document).ajaxError(function(jqXHR, textStatus, errorThrown) {
		console.log(errorThrown);
		alert("Some Error occured..");
	});

	$(document).ajaxComplete(function(event, request, settings) {
		$('#ajaxIndicator').hide();
	});
});