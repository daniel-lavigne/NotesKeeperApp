/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nk.domain.Role;
import nk.domain.User;
import nk.logic.AccountService;
import nk.logic.RoleService;
import nk.logic.UserService;

/**
 *
 * @author 664978
 */
public class AdminServlet extends HttpServlet {

    
    

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
        UserService us = new UserService();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            AccountService as = new AccountService();
            as.logout(request);
            response.sendRedirect("login");
            return;
        }
        if (action != null && action.equals("admin")){
            response.sendRedirect("admin");
            return;
        }
        List userList = null;
        try {
            userList = us.getAll();
        } catch (Exception ex) {
            request.setAttribute("message", ex.toString());
        }
        
        request.setAttribute("users", userList);
        if(user.getLanguage().equalsIgnoreCase("fr")){
                    getServletContext().getRequestDispatcher("/WEB-INF/admin/users_fr.jsp").forward(request, response);
                }
        else{
            getServletContext().getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
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
        
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String isAdmin = request.getParameter("isAdmin");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        UserService us = new UserService();
        
        if (action == null)
        {
            action = "";
        }
        switch (action) {
            case "view":
                try {
                    User selectedUser = us.get(username);
                    request.setAttribute("selectedUser", selectedUser);
                    request.setAttribute("error_message", null);
                } catch (Exception ex) {
                }   
                break;
                
            case "add":
                try {
                    us.insert(username, firstname, lastname, email, password, "eng", "yes");
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("message", "Utilisateur ajouté."); 
                    }
                    else{
                        request.setAttribute("message", "User added.");
                    }
                    RoleService rs = new RoleService(); 
                   if(isAdmin == null || isAdmin.isEmpty()){
                       rs.removeUser(1, username);
                    }
                    else{
                        rs.addUser(1, username);
                    }
                    request.setAttribute("error_message", null);
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("message", "Erreur: l'utilisateur n'a pas pu être ajouté!"); 
                    }
                    else{
                        request.setAttribute("message", "Error: User could not be added!");
                    }
                   request.setAttribute("message", null); 
                }   
                break;
                
            case "delete":
                try {
                    us.delete(username);
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("message", "Utilisateur supprimé."); 
                    }
                    else{
                        request.setAttribute("message", "User deleted.");
                    }
                    request.setAttribute("error_message", null);
                } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("message", "Erreur: l'utilisateur ne peut pas être supprimé!"); 
                    }
                    else{
                       request.setAttribute("message", "Error: User could not be deleted!"); 
                    }
                    request.setAttribute("message", null); 
                }   
                break;
                
            case "edit":
                try {
                   
                    us.update(username, firstname, lastname, email, password);
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("message", "Utilisateur supprimé."); 
                    }
                    else{
                        request.setAttribute("message", "User updated.");
                    }
                    RoleService rs = new RoleService(); 
                    if(isAdmin == null || isAdmin.isEmpty()){
                       rs.removeUser(1, username);
                    }
                    else{
                        rs.addUser(1, username);
                    }
                    request.setAttribute("error_message", null);
                } 
                catch (Exception ex) {
                     if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("error_message", "Erreur: l'utilisateur ne peut pas être mis à jour!"); 
                    }
                     else{
                        request.setAttribute("error_message", "Error: User could not be updated!" + ex.toString());
                    }
                    request.setAttribute("message", null); 
                }  
                break;
                
            default:
                break;
        }
        
        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            
        }
        
        if(user.getLanguage().equalsIgnoreCase("fr")){
            getServletContext().getRequestDispatcher("/WEB-INF/admin/users_fr.jsp").forward(request, response);
        }
        else{
            getServletContext().getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
        }  
    
    }

}
