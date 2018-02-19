<%-- 
    Document   : view
    Created on : Nov 9, 2015, 1:09:19 PM
    Author     : 664978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="/Includes/header_fr.jsp" />
    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
    <body>
        <div class="container-fluid">
        <div class="panel panel-default ">
            <div class="panel-body bg-primary">
                <h1>Les note de ${currentUser.firstName}</h1>
            </div>
        </div>
        <h2>Mes Notes:</h2>
        <div id="main">
            <div id="notes">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Note ID</th>
                        <th>Date Creee</th>
                        <th>Titre</th>
                        
                        <th>Collaborateurs</th>
                        <th>Effacer</th>
                        <th>Modifier</th>
                    </tr> 
                    </thead>
                    <tbody>
                         <c:forEach var="item" items="${notes}" varStatus="count">
                         <tr>
                             <td>${item.noteID}</td>
                             <td>${item.dateCreated}</td>
                              <td>
                                 <span data-toggle="popover" data-trigger="hover" data-content="Cliquez pour voir la note">
                                    <a href="#NoteCount${count.count}" data-toggle="collapse" data-trigger="hover" data-content="Some content">${item.title}</a>
                                 </span>
                                 <div id="NoteCount${count.count}" class="collapse">
                                     ${item.contents}
                                 </div>
                             </td>
                             <td>
                                <ul>
                                    <c:forEach var="item2" items="${item.userList}">
                                        <li>${item2.userName}</li>
                                    </c:forEach>
                                </ul>
                             </td>
                             <td>
                                <form action="notes" method="post">
                                  <input type="hidden" name="noteId" value="${item.noteID}">
                                  <input type="hidden" name="action" value="delete">
                                  <input type="submit" value="Effacer" class="btn btn-default">
                              </form>
                            </td>
                            <td>
                              <form action="notes" method="post">
                                  <input type="hidden" name="noteId" value="${item.noteID}">
                                  <input type="hidden" name="action" value="view">
                                  <input type="submit" value="Modifier" class="btn btn-default">
                              </form>
                             </td>
                         </tr>
                         </c:forEach>
                    </tbody>
                </table>
                
                <h2>Mes Collaborations:</h2>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Note ID:</th>
                        <th>Date Creee:</th>
                        <th>Titre:</th>
                        
                        <th>Propriétaire:</th>
                        <th>Modifier</th>   
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${collaborations}" varStatus="count">
                         <tr>
                             
                             <td>${item.noteID}</td>
                             <td>${item.dateCreated}</td>
                              <td>
                                 <span data-toggle="popover" data-trigger="hover" data-content="Cliquez pour voir la note">
                                    <a href="#CollabCount${count.count}" data-toggle="collapse" data-trigger="hover" data-content="Some content">${item.title}</a>
                                 </span>
                                 <div id="CollabCount${count.count}" class="collapse">
                                     ${item.contents}
                                 </div>
                             </td>
                             <td>${item.owner.userName}</td>
                            <td>
                              <form action="notes" method="post">
                                  <input type="hidden" name="noteId" value="${item.noteID}">
                                  <input type="hidden" name="action" value="view">
                                  <input type="submit" value="Modifier" class="btn btn-default">
                              </form>
                             </td>
                         </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            
        </div>
        
        <c:if test="${selectedNote == null}">
            <div>
                <h2>Creer Un Note</h2>
                <form method="post" action="notes" class="form-horizontal">
                    <div class="fieldset">
                        <label for="noteId">Titre:</label>
                        <input type="text" name="noteTitle" id="noteTitle" class="form-control input-sm"> 
                    </div>
                    <div class="fieldset">
                        <label for="content">Contenu:</label><br>
                        <textarea name="content" id="content" cols="50" rows="5"></textarea>
                    </div>
                    <div class="fieldset">
                        <input type="hidden" name="action" value="add">
                        <input type="submit" value="Save" class="btn btn-default"/>
                    </div>
                    
                </form>
                 
            </div>
        </c:if>
        
        <c:if test="${selectedNote != null}">
            <div>
                <h2>Modifier Note</h2>
                
                <form method="post" action="notes" class="form-horizontal">
                    <div class="col-xs-4">
                    <div class="fieldset">
                        <label for="noteId">Titre:</label>
                        <input type="text" name="noteTitle" id="noteTitle" value="${selectedNote.title}" class="form-control input-sm"> 
                    </div>
                     <div class="fieldset">
                        <label for="content">Contenu:</label><br>
                        <textarea name="content" id="content" cols="50" rows="5">${selectedNote.contents}</textarea>
                    </div>
                    <div class="fieldset">
                        <input type="hidden" name="noteId" value="${selectedNote.noteID}">
                        <input type="hidden" name="action" value="edit">
                        <input type="submit" value="Save" class="btn btn-default"/>
                    </div>

                    </div>
                    
                </form>
                <c:if test="${selectedNote.owner.userName.equals(currentUser.userName)}">
                <table class="table table-striped">
                        <tr>
                            <td>
                                <form method="post" action="notes" class="form-horizontal">
                                <div class="fieldset">
                                <select class="form-control" id="sel1" name="newCollaborator">
                                    <option value="none">Aucun</option>
                                    <c:forEach var="item" items="${nonCollaborators}">
                                        <c:if test="${!item.userName.equals(currentUser.userName)}">
                                            <option value="${item.userName}">${item.userName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                </div>
                                
                                <div class="fieldset">
                                    <input type="hidden" name="noteId" value="${selectedNote.noteID}">
                                    <input type="hidden" name="action" value="addCollaborator">
                                    <input type="submit" value="Ajouter Collaborateur" class="btn btn-default"/>
                                </div>
                                </form>
                            </td>
                    
                            <td>
                                <form method="post" action="notes" class="form-horizontal">
                                <div class="fieldset">
                                    <select class="form-control" id="sel1" name="oldCollaborator">
                                        <option value="none">Aucun</option>
                                        <c:forEach var="item" items="${selectedNote.userList}">
                                            
                                                <option value="${item.userName}">${item.userName}</option>
                                            
                                        </c:forEach>
                                    </select>
                                </div>
                               
                                <div class="fieldset">
                                    <input type="hidden" name="noteId" value="${selectedNote.noteID}">
                                    <input type="hidden" name="action" value="deleteCollaborator">
                                    <input type="submit" value="Supprimer Collaborateur" class="btn btn-default"/>
                                </div>
                                </form>
                            </td>
                        </tr>
                    </table>
                    </c:if>
            </div>
        </c:if>
        <div class="bg-success"><h4>${message}</h4></div>
        <br><div class="bg-danger"><h4>${error_message}</h4></div>
        </div>
        <script>
            $(document).ready(function(){
                $('[data-toggle="popover"]').popover(); 
            });
        </script>
    </body>
</html>
