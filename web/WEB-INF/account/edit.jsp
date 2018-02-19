<%-- 
    Document   : edit
    Created on : Nov 9, 2015, 1:10:51 PM
    Author     : 664978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="/Includes/header.jsp" />
    <body>
        <h1 class="bg-primary">Edit My Account</h1>
        <div class="container-fluid">
        <form method="post" action="editAccount" class="form-horizontal">
           <div class="col-xs-4">
                <label for="firstName">First Name: </label>
                <input type="text" name="firstName" value="${currentUser.firstName}" class="form-control input-sm"><br>
                
                <label for="lastName">Last Name: </label>
                <input type="text" name="lastName" value="${currentUser.lastName}" class="form-control input-sm"><br>
                
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" class="form-control input-sm" value="${currentUser.email}"/><br>
            
                <label for="password">Password: </label>
                <input type="text" name="password" value="${currentUser.userPassword}" class="form-control input-sm"><br>
                
                <label for="rePassword">Reenter Password:</label>
                <input type="password" class="form-control input-sm" name="rePassword" value="${currentUser.userPassword}"><br />
            
                <label for="language">Default Language:</label>
                <select name="language" id="language">
                    <option value="eng">English</option>
                    <option value="fr">French</option>
                </select><br>
                
                <input type="checkbox" value="yes" name="recieveAlerts" <c:if test="${currentUser.recieveAlerts.equals('yes')}"> checked </c:if> >Yes I would like to receive email alerts when I am added a collaborator on a note.<br>
                
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Update Account" class="btn btn-default">
           </div>
        </form>
           <div class="bg-success"><h4>${message}</h4></div>
           <br><div class="bg-danger"><h4>${error_message}</h4></div>
        </div>
    </body>
</html>
