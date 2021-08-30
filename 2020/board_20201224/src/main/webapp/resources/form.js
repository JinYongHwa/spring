$(document).ready(function() {
		$('#summernote').summernote({
			height:400
		});
		
		$("#form").submit(function(){
			
			var title=$("[name='title']").val()
			if(title.length==0){
				window.alert("제목을 입력해주세요")
				$("[name='title']").focus()
				return false
			}
			var writer=$("[name='writer']").val()
			if(writer.length==0){
				window.alert("작성자명을 입력해주세요")
				$("[name='writer']").focus()
				return false
			}
			if($("#summernote").summernote("isEmpty")){
				window.alert("내용을 입력해주세요")
				$("#summernote").summernote("focus")
				return false
			}
			
			return true
		})
	});