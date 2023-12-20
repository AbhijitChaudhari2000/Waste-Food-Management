<?php
 $servername = "localhost";
 $username = "root";
 $password = "";
$dbname = "wastefoodmanagment";

 $conn =new mysqli( $servername, $username, $password,$dbname );
 if($conn ->connect_error)
 echo ">connect_error";




?>