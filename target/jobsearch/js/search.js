$(document).ready(function() {
	
	$('a[id*=lnk-]').click(function() {
		
		var div = "#" + $(this).attr('id').replace(/lnk/i, "div");
		if ($(this).text() == 'Show') {
			$(this).html('Hide');
			$(div).css('display', 'block');
			
		} else {
			$(this).html('Show');
			$(div).css('display', 'none');
			
		}
	});

	var list = [];
	$("#companyFilter").hide();
	$("#companyFilter option").each(function() {
		list.push($(this).val());
	});
	
	var companyNames = new Bloodhound({
	    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    local: $.map(list, function (comp) {
	        return {
	            name: comp
	        };
	    })
	});
	companyNames.initialize();
	
	$('#inputCompany').tagsinput({
//		source: function(query) {
//			return list;
//		}
		itemValue: 'name',
		typeaheadjs: {
		    name: 'companyNames',
		    displayKey: 'name',
		    //valueKey: 'name',
		    source: companyNames.ttAdapter()
		  },
	});
	$('.twitter-typeahead').css({ position: 'fixed' });
	
	$('#inputCompany').on('itemAdded', function(event) {
		$("#companyFilter option[value=" + event.item.name +"]").attr("selected","selected");
	});
});

function goBack() {
	window.location = 'search';
}
