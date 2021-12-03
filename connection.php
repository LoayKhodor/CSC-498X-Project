<?php
$server = "localhost";
$username = "root";
$password = "loaykhodor";
$dbname = "foodapp";
$connection = new mysqli($server, $username, $password, $dbname); 
if($connection -> connect_error){
	die(“Failed”);
}
?>
