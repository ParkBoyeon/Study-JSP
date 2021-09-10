<?php header("Content-Type: application/json; charset=UTF-8"); ?>
<?php
	$user_id = '';
	$user_pw = '';

	if (isset($_POST['user_id'])) {
		$user_id = $_POST['user_id'];
	}

	if (isset($_POST['user_pw'])) {
		$user_pw = $_POST['user_pw'];
	}

	$result = "FAIL";
	if ( $user_id == "ajax" && $user_pw == "123qwe!@#" ) {
		$result = "OK";
	}
?>
{ "result" : "<?=$result?>" } 