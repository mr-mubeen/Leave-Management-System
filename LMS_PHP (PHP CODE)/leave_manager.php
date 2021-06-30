<?php

include('connect.php');

$id = $_POST['mlid'];
$query="select * from leaves where L_m_id = '$id'";

$result= mysqli_query($conn,$query);

while($row=mysqli_fetch_assoc($result))
{
	$data[]=$row;
}

print(json_encode($data));

?>