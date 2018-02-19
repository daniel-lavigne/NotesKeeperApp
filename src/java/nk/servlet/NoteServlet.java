/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nk.domain.Note;
import nk.domain.User;
import nk.logic.AccountService;
import nk.logic.NoteService;
import nk.logic.UserService;

/**
 *
 * @author 664978
 */
public class NoteServlet extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            AccountService as = new AccountService();
            as.logout(request);
            response.sendRedirect("login");
            return;
        }
        if (action != null && action.equals("viewAccount")){
            response.sendRedirect("viewAccount");
            return;
        }
        if (action != null && action.equals("notes")){
            response.sendRedirect("notes");
            return;
        }
        List noteList = null;
        List collaborations = null;
        List userList = null;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        UserService us = new UserService();
        try {
            User user2 = us.get(user.getUserName());
            noteList = user2.getNoteList();
            collaborations = user2.getNoteColaborationList();
            userList = us.getAll();
            request.setAttribute("notes", noteList);
            request.setAttribute("collaborations",collaborations);
            request.setAttribute("users", userList);
            if(user.getLanguage().equalsIgnoreCase("fr")){
                    getServletContext().getRequestDispatcher("/WEB-INF/notes/view_fr.jsp").forward(request, response);
                }
            else{
                getServletContext().getRequestDispatcher("/WEB-INF/notes/view.jsp").forward(request, response);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String action = request.getParameter("action");
        
        String noteId = request.getParameter("noteId");
        String title = request.getParameter("noteTitle");
        String content = request.getParameter("content");
        String sDate = request.getParameter("dateCreated");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        
        NoteService ns = new NoteService();
        
        if (action == null)
        {
            action = "";
        }
        
        switch (action){
            case "view":
                try {
                    Note selectedNote = ns.get(Integer.parseInt(noteId));
                    List userList = ns.getNonCollaborators(selectedNote);
                    request.setAttribute("nonCollaborators", userList);
                    request.setAttribute("selectedNote", selectedNote);
                }
                catch (Exception ex) { 
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("error_message", "Erreur: La note ne pouvait pas être vus!");
                    }
                    else{
                        request.setAttribute("error_message", "Error: Note could not be viewed!");
                    }
                }
                break;

            case "add":
                try {
                    if(title != null && !title.equals("")){
                        ns.insert(content, title, request);
                    }
                    else{
                        ns.insert(content, request);
                    }
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("message", "Note ajoutée.");
                    }
                    else{
                        request.setAttribute("message", "Note added.");
                    }
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("error_message", "Erreur: La note pas pu être ajouté!");
                    }
                    else{
                        request.setAttribute("error_message", "Error: Note could not be added!");
                    }
                }
                break;

            case "delete":
                try {
                    ns.delete(Long.parseLong(noteId));
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("message", "La note a été supprimé.");
                    }
                    else{
                        request.setAttribute("message", "Note deleted.");
                    }
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("error_message", "Erreur: La note pas pu être supprimé!");
                    }
                    else{
                        request.setAttribute("error_message", "Error: Note could not be deleted!");
                    }
                }
                break;

            case "edit":

                try {
                    //String noteToEdit = request.getParameter("noteIdToEdit");
                    if(title != null && !title.equals("")){
                        ns.update(Integer.parseInt(noteId), content, title);
                    }
                    else{
                        ns.update(Integer.parseInt(noteId), content);
                    }
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("message", "Note mise à jour.");
                    }
                    else{
                        request.setAttribute("message", "Note updated.");
                    }
                    
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("error_message", "Erreur: la note ne pouvait pas être mis à jour!");
                    }
                    else{
                        request.setAttribute("error_message", "Error: Note could not be updated!");
                    }
                    
                }
                break;

            case "addCollaborator":

                try {
                    Note note = ns.get(Integer.parseInt(noteId));
                    List newUserList = note.getUserList();
                    UserService us = new UserService();
                    String newCollaborator = request.getParameter("newCollaborator");
                    newUserList.add(us.get(newCollaborator));
                    ns.addCollaborator(Integer.parseInt(noteId),newUserList);
                    User uNewCollab = us.get(newCollaborator);
                    if(uNewCollab.getRecieveAlerts().equalsIgnoreCase("yes")){
                        ns.notifyNewCollaborator(request, Integer.parseInt(noteId), newCollaborator);
                    }
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("message", "Collaborateur ajouté.");
                    }
                    else{
                        request.setAttribute("message", "Collaborator added.");
                    }
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("error_message", "Erreur: Collaborateur pas pu être ajouté!");
                    }
                    else{
                        request.setAttribute("error_message", "Error: Collaborator could not be added!");
                    }
                }
                break;

            case "deleteCollaborator":

                try {
                    Note note = ns.get(Integer.parseInt(noteId));
                    List newUserList = note.getUserList();
                    UserService us = new UserService();
                    String oldCollaborator = request.getParameter("oldCollaborator");
                    newUserList.remove(us.get(oldCollaborator));
                    ns.addCollaborator(Integer.parseInt(noteId),newUserList );
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("message", "Collaborateur enlevés.");
                    }
                    else{
                        request.setAttribute("message", "Collaborator removed.");
                    }
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                         request.setAttribute("error_message", "Erreur: Collaborateur pas pu être supprimé!");
                    }
                    else{
                        request.setAttribute("error_message", "Error: Collaborator could not be removed!");
                    }
                    request.setAttribute("message", "Error: Collaborator could not be added " + ex.toString());
                }
                break;

            default:
                break;
        }
    
        List noteList = null;
        List collaborations = null;
        List userList = null;
        
        UserService us = new UserService();
        try {
            User user2 = us.get(user.getUserName());
            noteList = user2.getNoteList();
            collaborations = user2.getNoteColaborationList();
            userList = us.getAll();
            request.setAttribute("notes", noteList);
            request.setAttribute("collaborations",collaborations);
            request.setAttribute("users", userList);
            if(user.getLanguage().equalsIgnoreCase("fr")){
                    getServletContext().getRequestDispatcher("/WEB-INF/notes/view_fr.jsp").forward(request, response);
                }
            else{
                getServletContext().getRequestDispatcher("/WEB-INF/notes/view.jsp").forward(request, response);
            }
        } 
        catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }//end doPost()

    private boolean hasAccess(int noteId , HttpServletRequest request) throws Exception{
        NoteService ns = new NoteService();
        Note note = ns.get(noteId);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        //boolean b = true;
        if(!note.getUserList().contains(user)){
            
            if(!note.getOwner().getUserName().equals(user.getUserName())){
                return false;
            }
        }
        return true;
    }
}
