<?php
//MUST
//get "rid" from GetDishByIng then ingredients from here
include("connection.php");
$rec_id = $_GET["id"];

$query = "SELECT * FROM recipe_ingredients WHERE rid=?";
$stmt = $connection->prepare($query);
$stmt ->bind_param("s",$rec_id);
$stmt->execute();
$result = $stmt->get_result();
$temp_array=[];
while($row = $result->fetch_assoc()){
$temp_array[] = $row;
}
$json = json_encode($temp_array);
print $json;
?>