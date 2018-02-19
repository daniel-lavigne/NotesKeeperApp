<%-- 
    Document   : login
    Created on : Nov 9, 2015, 1:04:04 PM
    Author     : 664978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>NotesKeeper</title>
    </head>
    <body>
        
        <div class="container-fluid">
            <h1 class="bg-primary">Register to <span class="glyphicon glyphicon-pencil"></span>NotesKeeper!</h1>
        <form method="post" action="register" class="form-horizontal">
            <div class="col-xs-4">
            <label for="firstname">First Name:</label>
            <input type="text" name="firstname" id="firstname" class="form-control input-sm" required/><br>
                    
            <label for="lastname">Last Name:</label>
            <input type="text" name="lastname" id="lastname" class="form-control input-sm" required/><br>
            
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" class="form-control input-sm" required/><br>
            
            <label for="username">Username:</label> 
            <input type="text" class="form-control input-sm" name="username" required><br />
            
            <label for="password">Password:</label>
            <input type="password" class="form-control input-sm" name="password" required><br />
            
            <label for="rePassword">Reenter Password:</label>
            <input type="password" class="form-control input-sm" name="rePassword" required><br />
            
            <label for="language">Default Language:</label>
            <select name="language" id="language">
                <option value="eng">English</option>
                <option value="fr">French</option>
            </select><br>
            <input type="checkbox" value="yes" name="recieveAlerts">Yes I would like to receive email alerts when I am added a collaborator on a note.<br>
            <input type="submit" value="Register" class="btn btn-default"><br />
            <br><div class="bg-success"><h4>${message}</h4></div>
            <br><div class="bg-danger"><h4>${error_message}</h4></div>
            </div>
        </form>
        </div>
    </body>
</html>
