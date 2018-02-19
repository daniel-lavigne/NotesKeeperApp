/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nk.domain.User;
import nk.logic.AccountService;
import nk.logic.UserService;

/**
 *
 * @author 664978
 */

public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        try{
            User user = (User)session.getAttribute("currentUser");
            if(user != null){
            if (action != null && action.equals("logout")) {
                AccountService as = new AccountService();
                as.logout(request);
                response.sendRedirect("login");
                return;
            }
            if (action != null && action.equals("editAccount")){
                
                response.sendRedirect("editAccount");
                return;
            }
            if(action != null && action.equals("deleteAccount")){
                
                //User user = (User)session.getAttribute("currentUser");
                UserService us = new UserService();
                try {
                    us.delete(user.getUserName());
                     AccountService as = new AccountService();
                    as.logout(request);
                    response.sendRedirect("login");
                    return;
                } catch (Exception ex) {
                    request.setAttribute("message", "Account Information could not be updated");
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        getServletContext().getRequestDispatcher("/WEB-INF/account/view_fr.jsp").forward(request, response);
                    }
                    else{
                        getServletContext().getRequestDispatcher("/WEB-INF/account/view.jsp").forward(request, response);
                    }
                   // Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String servletPath = request.getServletPath();
            if("/viewAccount".equalsIgnoreCase(servletPath)){
                if(user.getLanguage().equalsIgnoreCase("fr")){
                    getServletContext().getRequestDispatcher("/WEB-INF/account/view_fr.jsp").forward(request, response);
                }
                else{
                    getServletContext().getRequestDispatcher("/WEB-INF/account/view.jsp").forward(request, response);
                } 
                 return;
            }
             if("/editAccount".equalsIgnoreCase(servletPath)){
                if(user.getLanguage().equalsIgnoreCase("fr")){
                    getServletContext().getRequestDispatcher("/WEB-INF/account/edit_fr.jsp").forward(request, response);
                }
                else{
                    getServletContext().getRequestDispatcher("/WEB-INF/account/edit.jsp").forward(request, response);
                }
                 return;
            }
             else{
                response.sendRedirect("notes");
                return; 
             }
            }
            else{
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
        catch(Exception e){
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("edit")){
            String newFirstName = request.getParameter("firstName");
            String newLastName = request.getParameter("lastName");
            String newEmail = request.getParameter("email");
            String newPassword = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            String language = request.getParameter("language");
            String alert = request.getParameter("recieveAlerts");
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("currentUser");
            UserService us = new UserService();
            if(!newPassword.equals(rePassword)){
                request.setAttribute("message", "Please make sure both password fields match");
                 if(user.getLanguage().equalsIgnoreCase("fr")){
                    getServletContext().getRequestDispatcher("/WEB-INF/account/edit_fr.jsp").forward(request, response);
                }
                else{
                    getServletContext().getRequestDispatcher("/WEB-INF/account/edit.jsp").forward(request, response);
                }
            }
            else{
                try {
                    user.setFirstName(newFirstName);
                    user.setLastName(newLastName);
                    user.setEmail(newEmail);
                    user.setUserPassword(newPassword);
                    user.setLanguage(language);
                    if(alert==null || alert.equals("")){
                        alert = "no";
                    }
                    user.setRecieveAlerts(alert);
                    us.update(user.getUserName(), newFirstName, newLastName, newEmail, newPassword, language, alert);
                    session.setAttribute("currentUser", user);
                    response.sendRedirect("viewAccount");
                    return;
                    } 
                catch (Exception ex) {
                    if(user.getLanguage().equalsIgnoreCase("fr")){
                        request.setAttribute("message", "Informations sur le compte n'a pas pu être mis à jour");
                        getServletContext().getRequestDispatcher("/WEB-INF/account/edit_fr.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("message", "Account Information could not be updated");
                        getServletContext().getRequestDispatcher("/WEB-INF/account/edit.jsp").forward(request, response);
                    }
                }
            }
        }
                 
    }

}
