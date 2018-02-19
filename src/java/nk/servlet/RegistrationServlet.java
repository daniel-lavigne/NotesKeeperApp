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
import nk.logic.UserService;

/**
 *
 * @author 664978
 */
public class RegistrationServlet extends HttpServlet {


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
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String language = request.getParameter("language");
        String alert = request.getParameter("recieveAlerts");
        
        if(username == null || username.equals("") || firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || email == null || email.equals("")
            || password == null || password.equals("") || rePassword == null || rePassword.equals("")){
            request.setAttribute("message", "Please be sure to fill out all fields");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        else if(!password.equals(rePassword)){
            request.setAttribute("message", "Please make sure both password fields match");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        else{
            UserService us = new UserService();
            AccountService as = new AccountService();
            try {
                if(alert==null || alert.equals("")){
                    alert = "no";
                }
                us.insert(username, firstname, lastname, email, password, language, alert);
                if(as.login(request, username, password)){
                    User user = us.get(username);
                    List<Role> role = null;
                    role = user.getRoleList();
                    HttpSession session = request.getSession();
                    if(role.size() > 0){
                        session.setAttribute("isAdmin", "Yes");
                        response.sendRedirect("admin");   
                    }
                    else{
                        response.sendRedirect("notes");
                     }
                }
                else{
                    request.setAttribute("message", "Thank you for registering to NotesKeeper!");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            } catch (Exception ex) {
               request.setAttribute("message", "Unexpected Error occured");
               getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }
    }

}
