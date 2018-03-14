<?php

include_once('confi.php');

 //$qur = pg_query($db_conn, "select name, email, status from `users` where ID='$uid'");
$
 $result =array();
$arr = pg_fetch_array($result, 0, PGSQL_NUM);
// $json = array("status" => 1, "info" => $result);
 
 pg_close($db_conn);
 
 /* Output header */
 header('Content-type: application/json');
 echo json_encode($json);

