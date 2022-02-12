<?php include("db.php");?>
<?php
session_start();
if(!isset($_SESSION['email'])){
    header("Location: index.html");
}
if(isset($_POST['submit'])){
    $email = $_SESSION['email'];
    $comment = $_POST['comment'];
    date_default_timezone_set("Asia/Kolkata");
    $date = date('Y-m-d H:i:s');
    $q = "INSERT INTO comments VALUES('$email','$comment','$date')";
    $res = mysqli_query($con,$q);
    if($res){
        //header("Location: Comments.php");
    }
    else{
        echo "Signup Failed";
    }
    
}
?>
<html lang="en">
    <head>
        <title>Comment Page</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="commentmain">
        <div class="commentbox">
        <div class="logout-btn"> 
                <h3> Hello, <?php echo $_SESSION["email"] ?> <a href="logout.php" class="clinks">Logout</a><h3>
        </div>
        <form  method="post" target="_self">
            <div class="text-main">
                <div class="fg">
                    <label>What would you like to share with world?</label>
                </div>
               <!-- <div class="fg">
                    <label>Your Name</label>
                    <input type="text" class="form-control" name="subject">
                </div> -->
                <div class="fg">
                    <label>Enter your Comment</label>
                    <textarea class="form-control" rows="6" name = "comment"></textarea>
                </div>
                <div class="fg">
                    <button type="submit" class="btn btn-primary" name = "submit">Submit</button>
                </div>
            </div>
        </form> 
        <form method="GET"> 
            <div class="display-filter-btn">
                    <button class="btn btn-primary" name="filter">Filter</button>
            </div>
        </form>
        <div class="display-comments">
            <div class="display-comment-sub1">
                <ul class="comment-list">
                <?php include("db.php");  
                if(isset($_GET['filter'])){
                    $email1 = $_SESSION["email"];
                    $q2 = "SELECT * FROM comments where email = '$email1' order by date desc";
                    $res2 = mysqli_query($con,$q2);
                    $rows = mysqli_num_rows($res2);
                    while($row1 = mysqli_fetch_array($res2)){
                    ?>
                        <li class="comment">
                            <div class="comment-body">
                                <span class="text-muted pull-right">
                                    <small class="text-muted"><?php echo $row1["date"]; ?></small>
                                </span>
                                <strong class="text-success"><?php echo $row1["email"]; ?></strong>
                                <p><?php echo $row1["comment"]; ?>
                                </p>
                            </div>
                        </li>
                    <?php  }
                 
                }
                else {
                $q2 = "SELECT * FROM comments ORDER BY date DESC; ";
                $res2 = mysqli_query($con,$q2);
                $rows = mysqli_num_rows($res2);
                while($row1 = mysqli_fetch_array($res2)){
                ?>
                    <li class="comment">
                        <div class="comment-body">
                            <span class="text-muted pull-right">
                                <small class="text-muted"><?php echo $row1["date"]; ?></small>
                            </span>
                            <strong class="text-success"><?php echo $row1["email"]; ?></strong>
                            <p><?php echo $row1["comment"]; ?>
                            </p>
                        </div>
                    </li>
                <?php  }
                }
                 ?>

                </ul>
            </div>
            
        </div>  
    </div>  
    </body>
</html>