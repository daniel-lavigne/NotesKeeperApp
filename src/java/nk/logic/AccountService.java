/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nk.dataaccess.UserRepository;
import nk.domain.User;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    public boolean login(HttpServletRequest request, String username, String password) {
        HttpSession session = request.getSession();
        
        if (username == null || password == null) {
            session.invalidate();
            return false;
        }
        
        UserRepository ur = new UserRepository();
        User user = ur.getUser(username);
        if (user == null) {
            session.invalidate();
            return false;
        }
        
        if (!user.getUserPassword().equals(password)) {
            session.invalidate();
            return false;
        }
        
        session.setAttribute("currentUser", user);
        
        return true;
    }
    
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
