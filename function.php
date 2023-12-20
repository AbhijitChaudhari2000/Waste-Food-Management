<?php
 require_once('db_connection.php');

 function inserCustomer($username, $emailid, $password, $contact, $address){
    global $conn;
 

     $stmt = $conn->prepare("INSERT INTO `registration`(`username`, `emailid`,`password`, `contact`, `address`) VALUES (?, ?, ?, ?, ?) ");
     if($stmt){
         $stmt->bind_param("sssss",$username,$emailid,$password,$contact,$address );
    if($stmt-> execute()){
             return 1;
    }else{
        return 0;
    }
     }
    else{
            return 0;
    }
    }
    function insertworker($username, $emailid, $password, $contact, $address,$ngolicen){
        global $conn;
     
    
         $stmt = $conn->prepare("INSERT INTO `registrationworker`(`username`, `emailid`,`password`, `contact`, `address`,`ngolicen`) VALUES (?, ?, ?, ?, ?, ?) ");
         if($stmt){
             $stmt->bind_param("ssssss",$username,$emailid,$password,$contact,$address,$ngolicen );
        if($stmt-> execute()){
                 return 1;
        }else{
            return 0;
        }
         }
        else{
                return 0;
        }
        }
        
        function login($username,$password,$sp1){
            global $conn;
            $stmt ="";
            if($sp1=="DONOR")
            $stmt = $conn->prepare("SELECT count(*) as login FROM `registration` WHERE `username` =? && `password`=?");
            else
            $stmt = $conn->prepare("SELECT count(*) as login FROM `registrationworker` WHERE `username` =? && `password`=?");
                if($stmt){
                    $stmt->bind_param("ss",$username,$password);
            if($stmt-> execute()){
            $result = $stmt->get_result();
            $row = $result->fetch_assoc();
            
            if($row['login']==1)
            return 1;
            else
            return $conn->error;
            
             }
             else{
                return $conn->error;
             }
             
            
            }else{
                return $conn->error;
            }
             
             }
             function addngo($name, $ndetail, $wname, $wphone, $address){
                global $conn;
             
            
                 $stmt = $conn->prepare("INSERT INTO `addngo`(`name`, `ndetail`,`wname`, `wphone`, `address`) VALUES ( ?, ?, ?, ?, ?) ");
                 if($stmt){
                     $stmt->bind_param("sssss",$name, $ndetail, $wname, $wphone, $address );
                if($stmt-> execute()){
                         return 1;
                }else{
                    return 0;
                }
                 }
                else{
                       
                        return 0;
                }
                }
                function donation($namedonor, $idngo, $donate, $address, $contact){
                    global $conn;
                 
                
                     $stmt = $conn->prepare("INSERT INTO `donation`(`namedonor`, `idngo`,`donate`, `address`, `contact`) VALUES (?, ?, ?, ?, ?) ");
                     if($stmt){
                         $stmt->bind_param("sssss",$namedonor, $idngo, $donate, $address, $contact );
                    if($stmt-> execute()){
                             return 1;
                    }else{
                        return 0;
                    }
                     }
                    else{
                            return 0;
                    }
                    } 
                    function ngoinfo(){
                        global $conn;
                         $stmt = $conn->prepare("SELECT * FROM addngo");
                         if($stmt){
                        if($stmt-> execute()){
                        $result = $stmt->get_result();
                        $finalarray=array();
                        while($row = $result->fetch_assoc()){
                            $temparry= array("id"=>$row["id"],"name"=>$row["name"],"address"=>$row["address"],"wphone"=>$row["wphone"]);
                            array_push($finalarray,$temparry);
                        }
                        return $finalarray;
                        }else{
                            return $conn ->error;
                        }
                         
                         }
                        else{
                            return $conn ->error;
                        }
                        }
                        function getngo($id){
                            global $conn;
                            $stmt = $conn-> prepare("SELECT *from addngo where id = ?");
                            if($stmt){
                                $stmt->bind_param("i",$id);
                               if($stmt-> execute()){
                               $result = $stmt->get_result();
                          
                              $finalarray=array();
                               $row = $result->fetch_assoc();
                                  return $row;
                            
                               }else{
                                   return 0;
                               }
                                
                            }
                               else{
                                return 0;
                              
                               }
                            }
                               
                            function donorinfo(){
                                global $conn;
                                 $stmt = $conn->prepare("SELECT * FROM donation");
                                 if($stmt){
                                if($stmt-> execute()){
                                $result = $stmt->get_result();
                                $finalarray=array();
                                while($row = $result->fetch_assoc()){
                                    $temparry= array("id"=>$row["id"],"namedonor"=>$row["namedonor"],"idngo"=>$row["idngo"],"donate"=>$row["donate"],"contact"=>$row["contact"],"address"=>$row["address"]);
                                    array_push($finalarray,$temparry);
                                }
                                return $finalarray;
                                }else{
                                    return $conn ->error;
                                }
                                 
                                 }
                                else{
                                    return $conn ->error;
                                }
                                }
                                function cert(){
                                    global $conn;
                                     $stmt = $conn->prepare("SELECT * FROM certificate");
                                     if($stmt){
                                    if($stmt-> execute()){
                                    $result = $stmt->get_result();
                                    $finalarray=array();
                                    while($row = $result->fetch_assoc()){
                                        $temparry= array("id"=>$row["id"],"name"=>$row["name"],"date"=>$row["date"]);
                                        array_push($finalarray,$temparry);
                                    }
                                    return $finalarray;
                                    }else{
                                        return $conn ->error;
                                    }
                                     
                                     }
                                    else{
                                        return $conn ->error;
                                    }
                                    }
                                    function addcer($name, $date){
                                        global $conn;
                                     
                                    
                                         $stmt = $conn->prepare("INSERT INTO `certificate`(`name`, `date`) VALUES (?, ?) ");
                                         if($stmt){
                                             $stmt->bind_param("ss",$name, $date );
                                        if($stmt-> execute()){
                                                 return 1;
                                        }else{
                                            return 0;
                                        }
                                         }
                                        else{
                                                return 0;
                                        }
                                        } 
   



?>