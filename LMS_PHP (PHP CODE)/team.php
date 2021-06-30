<?php

include('connect.php');

$id = $_POST['id'];
$query="select * from employee where m_name = '$id'";

$result= mysqli_query($conn,$query);

while($row=mysqli_fetch_assoc($result))
{
	$data[]=$row;
}

print(json_encode($data));

?>