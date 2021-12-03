<?php
//if time permits needed for filter drowdown
include("connection.php");
$query = "SELECT DISTINCT category FROM dishes";
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