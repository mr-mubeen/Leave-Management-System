<?php

require ('connect.php');


$eid = $_POST['eid'];
$m_id = $_POST['mid'];
$nt = $_POST['nt'];
$sd = $_POST['sd'];
$tp = $_POST['lt'];

// $sd = "2020/05/20";
// $ed = "2020:05:25";

// $sd = "$sd";
// $ed = "$ed";

// echo $sd;
// echo "</br>";
// echo  $ed;


// $eid = 2;
// $m_id = 1;
// $nt = "dasd";
// $sd = "2021-05-13";
// $ed = "2021-05-17";
// $tp = 'PL';

// $sd = 2020-05-20;
// $ed = 2020-05-25;

// function dateDifference($sd, $ed)
// {
//     // calulating the difference in timestamps 
//     $diff = strtotime($sd) - strtotime($ed);
     
//     // 1 day = 24 hours 
//     // 24 * 60 * 60 = 86400 seconds
//     return ceil(abs($diff / 86400));
// }



//  $sum = dateDifference($sd, $ed);



$sql1 = "select * from employee where e_id = '$eid'";
$result1 = mysqli_query($conn , $sql1) or die ("Regsiter Failed");
      
  if(mysqli_num_rows($result1) > 0)
   {
    while($row = mysqli_fetch_assoc($result1))
    { 
      if ( $tp == 'SL' )
      {
        // $sum = dateDifference("$sd", "$ed");
        if($row['SL'] >= $sd  )
        {
          $sql = "INSERT INTO leaves (l_e_id, L_m_id, note ,  L_s_date  , type  )
          VALUES ('$eid', '$m_id', '$nt' , '$sd' , '$tp' )";
          $result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

              if ($result) {

                
                $total = $row['SL'];
                $upd= $total-$sd;
                $up = "update employee SET SL = '$upd' WHERE e_id = '$eid'";
               echo "Leave-Registered";

               } 
              else 
               {
                  echo "Leave-Failed";
                }
          
        }
        else
         {

          echo "Leave-Failed";
             }
         }

      if ( $tp == 'PL' )
      {
        
        if($row['PL'] >= $sd  )
        {
          $sql = "INSERT INTO leaves (l_e_id, L_m_id, note ,  L_s_date , type  )
          VALUES ('$eid', '$m_id', '$nt' , '$sd' , '$tp' )";
          $result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

              if ($result) {

                
                $total = $row['PL'];
                $upd= $total-$sd;
                $up = "update employee SET PL = '$upd' WHERE e_id = '$eid'";
               echo "Leave-Registered";

               } 
              else 
               {
                  echo "Leave-Failed";
                }
          
        }
        else
        {

          echo "Leave-Failed";
            }
      }

      if ( $tp == 'CL' )
      {
       
        if($row['CL'] >= $sd  )
        {
          $sql = "INSERT INTO leaves (l_e_id, L_m_id, note ,  L_s_date ,  type  )
           VALUES ('$eid', '$m_id', '$nt' , '$sd' , '$tp' )";
          $result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

              if ($result) {

                
                $total = $row['CL'];
                $upd= $total-$sd;
                $up = "update employee SET CL = '$upd' WHERE e_id = '$eid'";
               echo "Leave-Registered";

               } 
              else 
               {
                  echo "Leave-Failed";
                }
          
        }
        else
        {

          echo "Leave-Failed";
                
              

      }
      
    }
          
         
   }
  }


?>