$(function() {
	agree_CheckedChanged();
});

// 체크박스 체크 선택 또는 해제할 때 상태 업데이트
$("#delete").change(function() {
	agree_CheckedChanged();
});


function agree_CheckedChanged() {
	if ($("#delete").prop("checked")) {
		console.log("check");
		$("#next-button").prop("disabled", false);
	} else {
		console.log("uncheck");
		$("#next-button").prop("disabled", true);
	}


};

$(function() {

	$("#next-button").click(function() {
            // 확인, 취소버튼에 따른 후속 처리 구현
            swal({
                title: '확인', // 제목
                text: "정말 탈퇴 하시겠습니까?", // 내용
                type: 'question', // 종류
                confirmButtonText: 'Yes', // 확인버튼 표시 문구
                showCancelButton: true, // 취소버튼 표시 여부
                cancelButtonText: 'No', // 취소버튼 표시 문구
            }).then(function(result) { // 버튼이 눌러졌을 경우의 콜백 연결
                if (result.value) { // 확인 버튼이 눌러진 경우
                    swal('탈퇴', '성공적으로 탈퇴 되었습니다.', 'success');
                } else if (result.dismiss === 'cancel') { // 취소버튼이 눌러진 경우
                    swal('탈퇴', '탈퇴가 취소되었습니다.', 'error');
                }
            });
        });
});