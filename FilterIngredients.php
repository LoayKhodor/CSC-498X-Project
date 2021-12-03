<?php
//I dont think i will use this
include("connection.php");
$rec_ing = $_GET["ing"];

$query = "SELECT DISTINCT * FROM recipe_ingredients WHERE Ringredient LIKE \"%" . $rec_ing ."%\"";
$stmt = $connection->prepare($query);
//$stmt ->bind_param("s",$rec_ing);
$stmt->execute();
$result = $stmt->get_result();
$temp_array=[];
while($row = $result->fetch_assoc()){
$temp_array[] = $row;
}
$json = json_encode($temp_array);
print $json;
?>