$(document).ready(function(){
    $("#btnsubmit").click(function(){
        if($("#name").val()==""){
            alert("담당자명을 입력해주세요.");
            $("#name").focus();
            return false;
        }else if($("#phone").val() ==""){
            alert("담당자 번호를 입력해주세요.");
            $("#phone").focus();
            return false;
        }else if($("#title").val() ==""){
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }else if($("#website").val() ==""){
            alert("관련 웹사이트를 입력해주세요.");
            $("#website").focus();
            return false;
        }else if($("#category").val() ==""){
            alert("카테고리를 선택해주세요.");
            $("#category").focus();
            return false;
        }else if($("#start_date").val() ==""){
            alert("기간을 설정해주세요");
            $("#start_date").focus();
            return false;
        }else if($("#end_date").val() ==""){
            alert("기간을 설정해주세요");
            $("#end_date").focus();
            return false;
        }else if($("#place").val() ==""){
            alert("장소(기관명)을 입력해주세요");
            $("#place").focus();
            return false;
        }else if($("#contents").val() ==""){
            alert("상세내용을 입력해주세요");
            $("#contents").focus();
            return false;
        }else if($("#prizemoney").val() ==""){
            alert("이벤트(혜택) / 시상금액");
            $("#prizemoney").focus();
            return false;
        }else if($("#fileuploade").val() ==""){
            alert("이미지를 첨부해주세요.");
            $("#prizemoney").focus();
            return false;
        }else{
        	$("#container").submit();
        }
        
    });
});

	$(document).ready(
			function() {
				var fileTarget = $('.filename .upload-hidden');
				fileTarget.on('change', function() {
					// 값이 변경되면 
					if (window.FileReader) {
						// modern browser 
						var filename = $(this)[0].files[0].name;
					} else {
						// old IE 
						var filename = $(this).val().split('/').pop().split(
								'\\').pop();
						// 파일명만 추출 
					}
					// 추출한 파일명 삽입 
					$(this).siblings('.filetext').val(filename);
				});
			});