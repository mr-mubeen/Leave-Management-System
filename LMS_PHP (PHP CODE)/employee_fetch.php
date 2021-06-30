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

$id = $_POST['id'];

$sql = "select * from employee where e_id = '$id' ";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

if (mysqli_num_rows($result) > 0) {
  $return_arr['employee'] = array();
  while($row = mysqli_fetch_assoc($result))
  {
    array_push($return_arr['employee'] , array(
      'e_id' => $row['emp_name'] 
//, 
  //    'emp_name' => $row['emp_name'],
    //  'emp_joining_date' => $row['emp_joining_date'] , 
      //'m_id' => $row['m_id']
      
      
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