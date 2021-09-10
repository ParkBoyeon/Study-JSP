<?php
	header("Content-Type: application/json; charset=UTF-8");
	header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS');

	$deptno = 0;

	if (isset($_GET['deptno'])) {
		$deptno = $_GET['deptno'];
	}

	$dname = '';
	$loc = '';

	switch ($deptno) {
		case '101': $dname = '컴퓨터공학과';	$loc = '1호관'; break;
		case '102': $dname = '멀티미디어학과';	$loc = '2호관'; break;
		case '201': $dname = '전자공학과';		$loc = '3호관'; break;
		case '202': $dname = '기계공학과';		$loc = '4호관'; break;
	}
?>
{"deptno": <?=$deptno?>, "dname": "<?=$dname?>", "loc": "<?=$loc?>"}