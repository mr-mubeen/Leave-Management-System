<?php

include('connect.php');

$id = $_POST['employeeid'];
$query="select * from employee where e_id = '$id'";

$result= mysqli_query($conn,$query);

while($row=mysqli_fetch_assoc($result))
{
	$data[]=$row;
}

print(json_encode($data));

?>