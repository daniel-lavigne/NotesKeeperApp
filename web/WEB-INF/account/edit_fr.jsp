<%-- 
    Document   : edit
    Created on : Nov 9, 2015, 1:10:51 PM
    Author     : 664978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="/Includes/header_fr.jsp" />
    <body>
        <h1 class="bg-primary">Modifier Mon Compte</h1>
        <div class="container-fluid">
        <form method="post" action="editAccount" class="form-horizontal">
           <div class="col-xs-4">
                <label>Prenom: </label>
                <input type="text" name="firstName" value="${currentUser.firstName}" class="form-control input-sm"><br>
                
                <label>Nom de Famille: </label>
                <input type="text" name="lastName" value="${currentUser.lastName}" class="form-control input-sm"><br>
                
                <label for="email">Courriel:</label>
                <input type="email" name="email" id="email" class="form-control input-sm" value="${currentUser.email}"/><br>
                
                <label>Mot De Pass: </label>
                <input type="text" name="password" value="${currentUser.userPassword}" class="form-control input-sm"><br>
                
                <label for="rePassword">Retaper le mot de passe:</label>
                <input type="password" class="form-control input-sm" name="rePassword" value="${currentUser.userPassword}"><br />
            
                <label for="language">Language defaut:</label>
                <select name="language" id="language">
                    <option value="fr">Francais</option>
                    <option value="eng">Englais</option>
                </select><br>
                
                <input type="checkbox" value="yes" name="recieveAlerts" <c:if test="${currentUser.recieveAlerts.equals('yes')}"> checked </c:if> >Oui, je souhaite recevoir des alertes par courriel lorsque je suis ajouté un collaborateur sur une note<br>
                
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Update Account" class="btn btn-default">
           </div>
        </form>
           <div class="bg-success"><h4>${message}</h4></div>
           <br><div class="bg-danger"><h4>${error_message}</h4></div>
        </div>
    </body>
</html>
