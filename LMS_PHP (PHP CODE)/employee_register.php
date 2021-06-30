<?php

require ('connect.php');

// $username = "ali";
// $password = "ali123";
// $manager = 1;
// $j_date = "12-09-2020";


// // $username = $_POST["un"];
// // $password = $_POST["ps"];
// // $manager = $_POST["manager"];
// // $j_date = $_POST["jd"];


// $sql = "INSERT INTO employee (emp_name, emp_password, emp_joining_date , m_id)
// VALUES ('$username', '$password', $manager' , $j_date)";
// $result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

// if ($result) {
//   echo "Registered";
// } else {
//   echo "Failed";
// }


$sql = "select * from manager";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

if (mysqli_num_rows($result) > 0) {
  $return_arr['manager'] = array();
  while($row = mysqli_fetch_assoc($result))
  {
    array_push($return_arr['manager'] , array(
      'm_id' => $row['m_id'] , 
      'm_name' => $row['m_name']
    ));
    

    
    // $a = $row['m_name'];

    // echo $a ;
    // echo "</br>";
  }

  echo json_encode($return_arr);
} else {
  echo "not found";
}




?>