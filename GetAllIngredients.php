<?php
include("connection.php");
$query = "SELECT DISTINCT * FROM recipe_ingredients";
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