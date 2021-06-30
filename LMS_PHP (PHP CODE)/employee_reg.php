<?php

require ('connect.php');

// $username = "ali";
// $password = "ali123";
// $manager = 1;
// $j_date = "12-09-2020";


$username = $_POST["un"];
$password = $_POST["ps"];
// $manager = $_POST["manager"];
$j_date = $_POST["jd"];


// $sql = "INSERT INTO employee (emp_name, emp_password, emp_joining_date , m_id)
// VALUES ('$username', '$password', $j_date ,$manager' )";
// $result = mysqli_query($conn , $sql) or die ("Regsiter Failed");


$sql = "INSERT INTO manager (m_name, m_pass, m_joining_date )
VALUES ('$username', '$password', '$j_date' )";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

if ($result) {
  echo "Registered";
} else {
  echo "Failed";
}
?>