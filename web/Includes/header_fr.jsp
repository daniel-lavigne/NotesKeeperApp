<%-- 
    Document   : header
    Created on : Nov 13, 2015, 11:17:54 AM
    Author     : 664978
--%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>NotesKeeper</title>
    </head>
    
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="admin?action=admin"> <span class="glyphicon glyphicon-pencil"></span>NotesKeeper</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="notes?action=notes">Mes Notes</a></li>
                <li><a href="notes?action=viewAccount">Mon Compte</a></li>
                
                <c:if test="${isAdmin != null}">
                    <li><a href="admin?action=admin">Examiner Tous les Utilisateurs</a></li>
                </c:if>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
                
                <li><a href="notes?action=logout"><span class="glyphicon glyphicon-log-out"></span>Quite</a></li>
            </ul>
        </div>
        </div>
    </nav><br><br>