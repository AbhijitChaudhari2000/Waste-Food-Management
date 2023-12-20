<?php

if(isset($_POST['RESULT_TYPE'])){
require_once('function.php');
$result_type=$_POST['RESULT_TYPE'];
switch($result_type){
    case "REGISTRATIONDONOR":
        $username=$_POST['username'];
        $emailid=$_POST['emailid'];
        $password=$_POST['password'];
        $contact=$_POST['contact'];
        $address=$_POST['address'];  
        $result= inserCustomer($username, $emailid, $password, $contact, $address);
        $outresult=array();
        if($result==1){
            $outresult["success"]=1;
            $outresult["message"]="Insert Successfull";
            echo json_encode($outresult);
        }else{
            $outresult["success"]=0;
            $outresult["message"]="Insert Failed";
            echo json_encode($outresult);
        }
       
        break;
        case "REGISTRATIONWORKER":
            $username=$_POST['username'];
            $emailid=$_POST['emailid'];
            $password=$_POST['password'];
            $contact=$_POST['contact'];
            $address=$_POST['address']; 
            $ngolicen=$_POST['ngolicen']; 
            $result= insertworker($username, $emailid, $password, $contact, $address,$ngolicen);
            $outresult=array();
            if($result==1){
                $outresult["success"]=1;
                $outresult["message"]="Insert Successfull";
                echo json_encode($outresult);
            }else{
                $outresult["success"]=0;
                $outresult["message"]="Insert Failed";
                echo json_encode($outresult);
            }
           
            break;
            
            case "LOGIN":
                $sp1 = $_POST['sp1'];
                $username=$_POST['username'];
                $password=$_POST['password'];
            $result= login($username,$password,$sp1);
            $outresult=array();
            if($result==1){
                $outresult["success"]=1;
                $outresult["message"]="LogIn Successfull";
                echo json_encode($outresult);
            }else{
                $outresult["success"]=0;
                $outresult["message"]="LogIn Failed";
                echo json_encode($outresult);
            }
           echo json_encode($result);
           break;
           case "ADDNGO":
            $name=$_POST['name'];
            $ndetail=$_POST['ndetail'];
            $wname=$_POST['wname'];
            $wphone=$_POST['wphone'];
            $address=$_POST['address']; 
        
            $result= addngo($name, $ndetail, $wname, $wphone, $address);
            $outresult=array();
            if($result==1){
                $outresult["success"]=1;
                $outresult["message"]="Insert Successfull";
                echo json_encode($outresult);
            }else{
                $outresult["success"]=0;
                $outresult["message"]="Insert Failed";
                echo json_encode($outresult);
            }
           
            break;
            case "DONATION":
                $namedonor=$_POST['namedonor'];
                $idngo=$_POST['idngo'];
                $donate=$_POST['donate'];
                $address=$_POST['address'];
                $contact=$_POST['contact'];
        
                $result= donation($namedonor, $idngo, $donate,$address, $contact);
                $outresult=array();
                if($result==1){
                    $outresult["success"]=1;
                    $outresult["message"]="Insert Successfull";
                    echo json_encode($outresult);
                }else{
                    $outresult["success"]=0;
                    $outresult["message"]="Insert Failed";
                    echo json_encode($outresult);
                }
               
                break;
                case "GETNGO":
                    $result= ngoinfo();
                    echo json_encode($result);
                    break; 
                    
                    
                        case "GETNGODETAIL":
                            $id=$_POST['id'];
                            $result= getngo($id);
                            echo json_encode($result);
                             break;
                             case "GETDONATION":
                                $result= donorinfo();
                                echo json_encode($result);
                                break;  
                                case "GETCERT":
                                    $result= cert();
                                    echo json_encode($result);
                                    break;  
                                    case "ADDCER":
                                        $name=$_POST['name'];
                                        $date=$_POST['date'];
                                      
                                        $result= addcer($name, $date);
                                        $outresult=array();
                                        if($result==1){
                                            $outresult["success"]=1;
                                            $outresult["message"]="Insert Successfull";
                                            echo json_encode($outresult);
                                        }else{
                                            $outresult["success"]=0;
                                            $outresult["message"]="Insert Failed";
                                            echo json_encode($outresult);
                                        }
                                       
                                        break;  

}
}
?>