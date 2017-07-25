/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : /WebContent/js/
 * @ 파일명 : duplicator.join.js
 * @ 작성자 : 이재민(jaeminstar@naver.com)
 * @ 작성일 : 2017.4.25
 * @ 설명 : 회원가입시 학교 AutoComplete 와 비동기로 이메일, 닉네임 중복확인
 */

$(document).ready(function () {
	var $container = $('#container');
	var $form = $('#join');
	var _fn = {
		initiate: function () {
			$('#campusSearch').hide();
			_fn.loadCampusList(function ($campusList) {
				if ($campusList.length < 1) return false;
				$form.find('input[name="university"]').on('keyup', function (event) {
					_fn.suggestCampusList(event, $campusList, $(this).val());
				});
				$('#campusSuggest').on('click', 'li.campus', function () {
					_fn.chooseCampusFromSuggest($(this));
				});
			});
		},

		loadCampusList: function (callback) {
			$.ajax({
				url: '../js/getcampuslist.xml',
				type: 'POST',
				success: function (data) {
					var $campusList = $(data).find('campus');
					callback($campusList);
				}
			});
		},
		suggestCampusList: function (event, $campusList, name) {
			var $suggest = $('#campusSuggest');
			var nameForRegExp = new RegExp(name.toLowerCase());
			var $matchedCampus = $campusList.filter(function () {
				var thisName = $(this).attr('name').toLowerCase();
				return thisName.match(nameForRegExp);
			});
			if (!$matchedCampus.length && name.length > 1) { // 자모음 결합 문제로 인해 마지막 한 글자 제외
				nameForRegExp = new RegExp(name.toLowerCase().substring(0, name.length - 1));
				$matchedCampus = $campusList.filter(function () {
					var thisName = $(this).attr('name').toLowerCase();
					return thisName.match(nameForRegExp);
				});
			}
			$suggest.empty();
			if (!name.length || !$matchedCampus.length) {
				var $none = $('<li></li>').text('검색된 학교가 없습니다.').addClass('none').appendTo($suggest);
				$('<a></a>').attr('href', $suggest.data('noschool')).text('학교가 없으세요?').appendTo($none);
				return false;
			}
			$matchedCampus.each(function () {
				var $campus = $(this);
				var text = $campus.attr('name').replace(name, '<em>' + name + '</em>');
				var $li = $('<li></li>').html(text).data({
					'campus_id': $campus.attr('id'),
					'campus_name': $campus.attr('name')
				}).addClass('campus').appendTo($suggest);
				$('<a></a>').text('선택').appendTo($li);
			});
			$('#campusSearch').show();
		},
		chooseCampusFromSuggest: function ($campus) {
			if ($campus.length) {
				$form.find('input[name="campus_id"]').val($campus.data('campus_id'));
				$form.find('input[name="university"]').val($campus.data('campus_name')).attr('readonly', true);


			} else {
				$form.find('input[name="campus_id"]').val('');
			}
			$('#campusSearch').hide();
		},


	};
	_fn.initiate();
	
	
	
	$("#join").validate({
        errorElement: 'span',
        errorClass: 'description',
        rules: { email: {required:true, email:true,
    			 remote: {url: "check.email", type: "POST",	data: {
    				 	email:function(){
    					return $('#email').val();
    					}}},},
        	pwd: {required:true, minlength:5, maxlength:20},
        	pwd2: {required:true, equalTo:'#pwd'},
        	name: {required:true, minlength:2, maxlength:10},
        	nickname: {required:true, minlength:2, maxlength:10,
        			remote: {
        				url: "check.nickname",
        				type: "POST",
        				data: {
        					nickname:function(){
        						return $('#nickname').val();
        					}}}}},
        messages: {
        	email: {required:"필수입력 항목입니다", 
        			remote: "이미존재하는 이메일 입니다", 
        			email:"올바른 이메일형식이 아닙니다"},
        	pwd: {required:"필수입력 항목입니다", 
        		  minlength:"{0}글자 이상 입력하세요", 
        		  maxlength:"{0}자이하여야합니다"},
	        pwd2: {required:"필수입력 항목입니다", 
	        	   equalTo:"비밀번호가 일치하지 않습니다"},
	        name: {required:"필수입력 항목입니다", 
	        	   minlength:"{0}글자 이상 입력하세요", 
	        	   maxlength:"{0}자이하여야합니다"},
	        nickname: {required:"필수입력 항목입니다", 
	        	       minlength:"{0}글자 이상 입력하세요", 
	        	       maxlength:"{0}자이하여야합니다", 
	        	       remote:"중복되는 닉네임이 존재합니다"}
        }
    });
});
