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
        <header>
            <h1 class="bg-primary">Welcome to <span class="glyphicon glyphicon-pencil"></span>NotesKeeper!</h1>
             <div>
                <span>Not already using NotesKeeper? <a href="login?action=register">Click here to sign up today!</a></span>
            </div>
        </header>
        <br>
        <div class="container-fluid">
            
        <form method="post" action="login" class="form-horizontal">
            <div class="col-xs-4">
            <label>Username:</label> <input type="text" class="form-control input-sm" name="username" required><br />
            <label>Password:</label> <input type="password" class="form-control input-sm" name="password" required><br />
            <input type="submit" value="Login" class="btn btn-default"><br />
            <br><div class="bg-success"><h4>${message}</h4></div>
            <br><div class="bg-danger"><h4>${error_message}</h4></div>
            </div>
        </form>
        
        </div>
    </body>
</html>
