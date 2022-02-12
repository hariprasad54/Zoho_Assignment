<?php
include("db.php");
include("aes.php");
function alert($msg) {
    echo "<script type='text/javascript'>alert('$msg'); window.location.href='signup.html';</script>";
}
if(isset($_POST['signup'])){
    $email = $_POST ['email'];
    $password = $_POST['password'];
    $secretkey = $_POST['secretkey'];
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo alert("Invalid email format");
    }
    else {
        if(empty($email) || empty($password) || empty($secretkey)){
            echo alert("Enter All Fields to Complete SignUp");
        }
        else {
            $q1 = mysqli_query($con, "SELECT * FROM users where email = '$email'");
            $rn = mysqli_num_rows($q1);
            if($rn != 0){
                echo alert("Email Id Already Exists. Login using your credentials");
            }
            else {
                $enc_pwd = encr($password);
                $enc_sec_key = encr($secretkey);
                $q = "INSERT INTO users VALUES('$email','$enc_pwd','$enc_sec_key')";
                $res = mysqli_query($con,$q);
                if($res){
                    header("Location: index.html");
                }
                else{
                    echo alert("Signup Failed");
                }
            }
        }
    }
}

?>