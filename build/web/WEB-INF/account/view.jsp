<%-- 
    Document   : view
    Created on : Nov 9, 2015, 1:10:40 PM
    Author     : 664978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <c:import url="/Includes/header.jsp" />
    <body>
        <div class="container-fluid">
        <h1 class="bg-primary">My Account</h1>
        <form method="post" action="viewAccount" class="form-horizontal">
        <div>
            <table class="table table-striped">
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                </tr>
                <tr>
                    <td>${currentUser.userName}</td>
                    <td>${currentUser.firstName}</td>
                    <td>${currentUser.lastName}</td>
                    <td>${currentUser.email}</td>
                    <td>${currentUser.userPassword}</td>
                </tr>
            </table>
        </div>
        </form>
            <br><div class="bg-success"><h4>${message}</h4></div>
            <br><div class="bg-danger"><h4>${error_message}</h4></div>
        <p>
            <a href="viewAccount?action=editAccount">Edit Account Information</a>
        </p>
        <p>
            <a href="viewAccount?action=deleteAccount">Delete My Account</a>
        </p>
        </div>
    </body>
</html>
