<?php
include("connection.php");
$values = explode(",", $_GET['login'] ?? "");
$name = $values[0];
$email = $values[1];
$password = hash("sha256",$values[2]);
$mysql = $connection -> prepare("INSERT INTO users(username, email, password) VALUES (?,?,?)");
$mysql->bind_param("sss" , $name, $email, $password);
$mysql->execute();
$mysql -> close();
$connection->close();
?>