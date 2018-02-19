/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.logic;

import java.util.List;
import nk.dataaccess.RoleRepository;
import nk.dataaccess.UserRepository;
import nk.domain.Role;
import nk.domain.User;

/**
 *
 * @author 664978
 */
public class RoleService {
    
    public int addUser(int roleId, String username) throws Exception{
        RoleRepository rr = new RoleRepository();
        UserRepository ur = new UserRepository();
        Role role = rr.getRole(roleId);
        User user = ur.getUser(username);
        List<User> userList = role.getUserList();
        List<Role> roleList = user.getRoleList();
        if(!userList.contains(user) || !roleList.contains(role)){
            userList.add(user);
            roleList.add(role);
            role.setUserList(userList);
            user.setRoleList(roleList);
            return rr.update(role, user);
        }
        else{
            return 0;
        } 
    }
    
    public int removeUser(int roleId, String username) throws Exception{
        RoleRepository rr = new RoleRepository();
        UserService us = new UserService();
        Role role = rr.getRole(roleId);
        User user = us.get(username);
        List<User> userList = role.getUserList();
        List<Role> roleList = user.getRoleList();
        
        if(userList.contains(user) || roleList.contains(role)){
            userList.remove(user);
            roleList.remove(role);
            role.setUserList(userList);
            user.setRoleList(roleList);
            return rr.update(role, user);
        }
        else{
            return 0;
        }
    }
}
