/*변수 선언*/


var id = document.querySelector('#new_id');

var pw1 = document.querySelector('#new_pw');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#new_pw_img1');

var pw2 = document.querySelector('#new_pw_re');
var pwImg2 = document.querySelector('#new_pw_re_img1');
var pwMsgArea = document.querySelector('.new_pass');

var userName = document.querySelector('#new_name');

var yy = document.querySelector('#yy');
var mm = document.querySelector('#mm');
var dd = document.querySelector('#dd');

var gender = document.querySelector('#new_gender');

var email = document.querySelector('#new_email');

var mobile = document.querySelector('#new_mobile');

var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/


id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
yy.addEventListener("focusout", isBirthCompleted);
mm.addEventListener("focusout", isBirthCompleted);
dd.addEventListener("focusout", isBirthCompleted);
gender.addEventListener("focusout", function() {
	if (gender.value === "성별") {
		error[5].style.display = "block";
	} else {
		error[5].style.display = "none";
	}
})
email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);





/*콜백 함수*/


function checkId() {
	var idPattern = /[a-zA-Z0-9_-]{5,20}/;
	if (id.value == "") {
		error[0].innerHTML = "필수 정보입니다.";
		error[0].style.display = "block";
	} else if (!idPattern.test(id.value)) {
		error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		error[0].style.display = "block";
	} else {
		error[0].innerHTML = "사용 가능 한 아이디 입니다.";
		error[0].style.color = "#08A600";
		error[0].style.display = "block";
	}
}

function checkPw() {
	var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
	if (pw1.value == "") {
		error[1].innerHTML = "필수 정보입니다.";
		error[1].style.display = "block";
	} else if (!pwPattern.test(pw1.value)) {
		error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		pwMsg.innerHTML = "사용불가";
		pwMsgArea.style.paddingRight = "93px";
		error[1].style.display = "block";

		pwMsg.style.display = "block";
		pwImg1.src = "../assets/img/login_new_not_use.png";
	} else {
		error[1].style.display = "none";
		pwMsg.innerHTML = "안전";
		pwMsg.style.display = "block";
		pwMsg.style.color = "#03c75a";
		pwImg1.src = "../assets/img/login_new_safe.png";
	}
}

function comparePw() {
	if (pw2.value == pw1.value && pw2.value != "") {
		pwImg2.src = "../assets/img/login_new_check_enable.png";
		error[2].style.display = "none";
	} else if (pw2.value !== pw1.value) {
		pwImg2.src = "../assets/img/login_new_check_not.png";
		error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
		error[2].style.display = "block";
	}

	if (pw2.value == "") {
		error[2].innerHTML = "필수 정보입니다.";
		error[2].style.display = "block";
	}
}

function checkName() {
	var namePattern = /^[ㄱ-ㅎ가-힣]*$/;
	if (userName.value == "") {
		error[3].innerHTML = "필수 정보입니다.";
		error[3].style.display = "block";
	} else if (!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
		error[3].innerHTML = "한글만 사용하세요. (특수기호, 공백 사용 불가)";
		error[3].style.display = "block";
	} else {
		error[3].style.display = "none";
	}
}


function isBirthCompleted() {
	var yearPattern = /[0-9]{4}/;

	if (!yearPattern.test(yy.value)) {
		error[4].innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
		error[4].style.display = "block";
	} else {
		isMonthSelected();
	}


	function isMonthSelected() {
		if (mm.value == "월") {
			error[4].innerHTML = "태어난 월을 선택하세요.";
		} else {
			isDateCompleted();
		}
	}

	function isDateCompleted() {
		if (dd.value == "") {
			error[4].innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
		} else {
			isBirthRight();
		}
	}
}



function isBirthRight() {
	var datePattern = /\d{1,2}/;
	if (!datePattern.test(dd.value) || Number(dd.value) < 1 || Number(dd.value) > 31) {
		error[4].innerHTML = "생년월일을 다시 확인해주세요.";
	} else {
		checkAge();
	}
}

function checkAge() {
	if (Number(yy.value) < 1920) {
		error[4].innerHTML = "정말이세요?";
		error[4].style.display = "block";
	} else if (Number(yy.value) > 2020) {
		error[4].innerHTML = "미래에서 오셨군요. ^^";
		error[4].style.display = "block";
	} else if (Number(yy.value) > 2005) {
		error[4].innerHTML = "만 14세 미만의 어린이는 보호자 동의가 필요합니다.";
		error[4].style.display = "block";
	} else {
		error[4].style.display = "none";
	}
}


function isEmailCorrect() {
	var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	if (email.value == "") {
		error[6].innerHTML = "필수 정보입니다.";
		error[6].style.display = "block";
	} else if (!emailPattern.test(email.value)) {
		error[6].innerHTML = "이메일 주소를 다시 확인해주세요.";
		error[6].style.display = "block";
	} else {
		error[6].style.display = "none";
	}

}

function checkPhoneNum() {
	var isPhoneNum = /([01]{2})([01679]{1})([0-9]{4})([0-9]{4})/;

	if (mobile.value == "") {
		error[7].innerHTML = "필수 정보입니다.";
		error[7].style.display = "block";
	} else if (!isPhoneNum.test(mobile.value)) {
		error[7].innerHTML = "형식에 맞지 않는 번호입니다.";
		error[7].style.display = "block";
	} else {
		error[7].style.display = "none";
	}


}

$(function() { /** 버튼 클릭시 이벤트 */
        $("#id_uniq_check").click(function() {
            // 입력값을 취득하고, 내용의 존재여부를 검사한다.
            var user_id_val = $("#new_id").val();

            if (!user_id_val) { // 입력되지 않았다면?
                alert("아이디를 입력하세요."); // <-- 메시지 표시
                $("#new_id").focus(); // <-- 커서를 강제로 넣기
                return false; // <-- 실행 중단
            }

            // 위의 if문을 무사히 통과했다면 내용이 존재한다는 의미이므로,
            // 입력된 내용을 Ajax를 사용해서 웹 프로그램에게 전달한다.
            $.post("../api/id_unique_check.php", { user_id: user_id_val }, function(req) {
                // 사용 가능한 아이디인 경우 --> req = { result: "OK" }
                // 사용 불가능한 아이디인 경우 --> req = { result: "FAIL" }
                if (req.result == 'OK') {
                    alert("사용 가능한 아이디 입니다.");
                } else {
                    alert("사용할 수 없는 아이디 입니다.");
                    $("#new_id").val("");
                    $("#new_id").focus();
                }
            }); // end $.get
        }); // end click
    });