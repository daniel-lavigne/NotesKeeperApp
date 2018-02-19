/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.logic;

import java.util.ArrayList;
import java.util.List;
import nk.dataaccess.UserRepository;
import nk.domain.Role;
import nk.domain.User;

public class UserService {
    public User get(String userName) throws Exception {
        UserRepository ur = new UserRepository();
        return ur.getUser(userName);
    }
  
    //This method must either handle, or throw SQLException
    public List<User> getAll() throws Exception {
        UserRepository ur = new UserRepository();
        return ur.getAll();
    }
                                                       
    public int update(String userName, String firstName, String lastName, String email, String userPassword) throws Exception {
        UserRepository ur = new UserRepository();
        User user = ur.getUser(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserPassword(userPassword);
        user.setEmail(email);
    	return ur.update(user);
    }
     
     public int update(String userName, String firstName, String lastName, String email, String userPassword, String language, String alert) throws Exception {
        UserRepository ur = new UserRepository();
        User user = ur.getUser(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserPassword(userPassword);
        user.setEmail(email);
        user.setLanguage(language);
        user.setRecieveAlerts(alert);
    	return ur.update(user);
    }
     
    public int delete(String userName) throws Exception {
        UserRepository ur = new UserRepository();
    	User user = ur.getUser(userName);
    	return ur.delete(user);
    }
    
    public int insert(String userName, String firstName, String lastName, String email, String userPassword, String language, String recieveAlerts) throws Exception {
        UserRepository ur = new UserRepository();
    	User user = new User(userName, firstName, lastName, email, userPassword, language, recieveAlerts);
    	return ur.insert(user);
    }
}
