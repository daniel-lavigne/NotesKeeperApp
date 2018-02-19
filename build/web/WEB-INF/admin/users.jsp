<%-- 
    Document   : users
    Created on : Nov 9, 2015, 1:09:01 PM
    Author     : 664978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <c:import url="/Includes/header.jsp" />
    <body>
        <div class="container-fluid">
        <h1 class="bg-primary">Manage Users</h1>
        <h2>Users</h2>
        <div id="main">
            <div id="users">
                <table class="table table-hover">
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Edit</th>
                    </tr>

                    <c:forEach var="item" items="${users}">
                        <tr>
                            <td><c:out value="${item.userName}" /></td>
                            <td>${item.firstName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.email}</td>
                            <td>${item.userPassword}</td>
                            <td>
                                <c:if test="${!empty item.roleList}">Admin</c:if>
                                
                            </td>
                            <td>
                              <form action="admin" method="post">
                                  <input type="hidden" name="username" value="${item.userName}">
                                  <input type="hidden" name="action" value="delete">
                                  <input type="submit" value="Delete" <c:if test="${item.userName.equals(currentUser.userName)}">disabled="disabled"</c:if> class="btn btn-default">
                              </form>
                            </td>
                            <td>
                              <form action="admin" method="post">
                                  <input type="hidden" name="username" value="${item.userName}">
                                  <input type="hidden" name="action" value="view">
                                  <input type="submit" value="Edit" class="btn btn-default">
                              </form>
                          </td>

                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
        
        <c:if test="${selectedUser == null}">
            <div>
                <h2>Add User</h2>
                <form method="post" action="admin" class="form-horizontal">
                    <div class="col-xs-4">
                    
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username" class="form-control input-sm" /><br>
                   
                        <label for="username">First Name:</label>
                        <input type="text" name="firstname" id="firstname" class="form-control input-sm" /><br>
                    
                        <label for="username">Last Name:</label>
                        <input type="text" name="lastname" id="lastname" class="form-control input-sm" /><br>
                    
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" class="form-control input-sm" /><br>
                    
                        <label for="access">Email:</label>
                        <input type="text" name="email" id="email" class="form-control input-sm" /><br>
                        
                        <label for="isAdmin">Admin:</label><br>
                        <input type="checkbox" name="isAdmin" value="yes"> Make this user a NotesKeeper admin.<br>
                        
                        <input type="hidden" name="action" value="add">
                        <input type="submit" value="Save" class="btn btn-default"/>
                    </div>
                </form>
                
            </div>
        </c:if>
                                      
        <c:if test="${selectedUser != null}">
            
                <h2>Edit ${selectedUser.userName}</h2>
                <form method="post" action="admin" class="form-horizontal">
                    <div class="col-xs-4">
                    
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username" class="form-control input-sm" value="${selectedUser.userName}" readonly="readonly"/><br>

                        <label for="firstname">First Name:</label>
                        <input type="text" name="firstname" id="firstname" class="form-control input-sm" value="${selectedUser.firstName}" /><br>
                    
                        <label for="lastname">Last Name:</label>
                        <input type="text" name="lastname" id="lastname" class="form-control input-sm" value="${selectedUser.lastName}" /><br>
                    
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" class="form-control input-sm" value="${selectedUser.userPassword}" /><br>
                    
                        <label for="email">Email:</label>
                        <input type="text" name="email" id="email" class="form-control input-sm" value="${selectedUser.email}" /><br>
                    
                        <label for="isAdmin">Admin:</label><br>
                        <input type="checkbox" name="isAdmin" value="yes"  <c:if test="${!empty selectedUser.roleList}"> checked </c:if> > Make this user a NotesKeeper admin.<br>
                        
                        <input type="hidden" name="action" value="edit">
                        <input type="submit" value="Save" class="btn btn-default"/>

                    </div>
                </form>
                           
        </c:if>
        </div>
        <br><div class="bg-success"><h4>${message}</h4></div>
        <br><div class="bg-danger"><h4>${error_message}</h4></div>
    </body>
</html>
