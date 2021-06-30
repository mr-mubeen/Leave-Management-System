<?php

require "connect.php";


$username = $_POST["un"];
$password = $_POST["ps"];
// 

$sql = "select * from employee where emp_name= '$username' and emp_password = '$password'";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");
      
      if(mysqli_num_rows($result) > 0)
      {
          //  echo 'Successful';
          //  echo '</br>';
          $row=mysqli_fetch_assoc($result);
	      print(json_encode($row));

        //   echo "Successful";
          
      }
      else {
         
          echo 'Employee-Login-Failed';
      }

?>