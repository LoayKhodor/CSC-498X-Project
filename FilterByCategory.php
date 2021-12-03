<?php
//if time permits
include("connection.php");
$cat = $_GET['cat'];
$query = "SELECT name from dishes WHERE category =\"".$cat."\"";
$stmt = $connection->prepare($query);
$stmt->execute();
$result = $stmt->get_result();
$temp_array=[];
while($row = $result->fetch_assoc()){
$temp_array[] = $row;
}
$json = json_encode($temp_array);
print $json;
?>