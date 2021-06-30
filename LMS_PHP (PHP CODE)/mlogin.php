<?php

require "connect.php";


$username = $_POST["mun"];
$password = $_POST["mps"];
// 

$sql = "select * from manager where m_name= '$username' and m_pass = '$password'";
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
         
          echo 'mFailed';
      }

?>