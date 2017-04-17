/*$(window).load(function(){
 $("#progress").hide();
 });*/

/*jQuery(window).load(function() {
 $("#progress").hide();
 });*/

jQuery(document).ready(function() {
	$("#progress").hide();
	$("#formID").submit(f);
});

f = function(event) {

	/* отключение стандартной отправки формы */
	event.preventDefault();
	$("#progress").show();

	/* собираем данные с элементов страницы: */
	var $form = $(this), v_il = $form.find('input[name="il"]').val(), v_ia = $form
			.find('input[name="ia"]').val(), v_si = $form.find(
			'input[name="si"]').val(), v_sj = $form.find('input[name="sj"]')
			.val(), v_sk = $form.find('input[name="sk"]').val(), v_d = $form
			.find('input[name="d"]').val(), v_k = $form.find('input[name="k"]')
			.val(), v_ks = $form.find('input[name="ks"]').val(), v_ka = $form
			.find('input[name="ka"]').val(), v_kd = $form.find(
			'input[name="kd"]').val(), v_n = $form.find('input[name="n"]')
			.val(), url1 = $form.attr('action');

	var dataPost = {
			il : $("input[name='il']").val(),
			ia : $("input[name='ia']").val(),
			li : $("input[name='li']").val(),
			lj : $("input[name='lj']").val(),
			lk : $("input[name='lk']").val(),
			si : $("input[name='si']").val(),
			sj : $("input[name='sj']").val(),
			sk : $("input[name='sk']").val(),
			d  : $("input[name='d']").val(),
			k  : $("input[name='k']").val(),
			ks : $("input[name='ks']").val(),
			ka : $("input[name='ka']").val(),
			kd : $("input[name='kd']").val(),
			n  : $("input[name='n']").val(),
	}
	
	 /*{
		"il" : v_il,
		"ia" : v_ia,
		"si" : v_si,
		"sj" : v_sj,
		"sk" : v_sk,
		"d" : v_d,
		"k" : v_k,
		"ks" : v_ks,
		"ka" : v_ka,
		"kd" : v_kd,
		"n" : v_n
	}*/
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		url : "/lab3/webapi/get/get1",
		dataType : "json",
		data : JSON.stringify(dataPost),
		beforeSend : function() {
			alert('Fetching.... ' + "/get");
		},
		success : function() {
			alert('Fetch Complete');
		},
		error : function() {
			alert('Error');
		},
		complete : function() {
			alert('Complete')
		},
		timeout: 5000
	});

	$("#progress").hide();
};