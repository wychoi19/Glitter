 //엑셀파일이 업로드 된 것이 맞는지 체크한다.
function checkFileType(filePath) {
            var fileFormat = filePath.split(".");
 
            if (fileFormat.indexOf("xls") > -1 || fileFormat.indexOf("xlsx") > -1) {
              return true;
              } else {
              return false;
            }
          }

//서버에 업로드한다.
function upload(){
	var file = $("#file").val();

	if (file == "" || file == null) {
		alert("파일을 선택해주세요.");

		return false;
	} else if (!checkFileType(file)) {
		alert("엑셀 파일만 업로드 가능합니다.");

		return false;
	}

	var form = $('#uploadForm')[0]
    var data = new FormData(form);
    console.log(form);
    $('#btnUpload').prop('disabled', true);
	
    $.ajax({
        type : 'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
        url: "/fileUpload.do",
		//url:"getDetailCityTopN.do",
        data: data,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$('#btnUpload').prop('disabled', false);
        	alert('success')
        },
        error: function (e) {
            $('#btnUpload').prop('disabled', false);
            alert('fail');
        }
    });


	

}

$( document ).ready(function() {
	$("#btnupload").on("click", function() {                          //업로드할 파일을 선택 할 경우 동작을 일으킵니다.

		var form = $('#uploadForm');

		form.ajaxSubmit({

			url: '/fileUpload.do',

			data: form.serialize(),                         //폼의 값들을 주소화하여 보내게 됩니다.

			type: 'POST',

			success: function(data) {

				$('.file').val('');                           //file input에 들어가 있는 값을 비워줍니다.

				location.reload();

			}

		});
	});
	});