/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.dataaccess;

import javax.persistence.EntityManager;
import nk.domain.Role;
import nk.domain.User;

/**
 *
 * @author 664978
 */
public class RoleRepository {
    public int update(Role role, User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(role);
            em.merge(user);
           
            em.getTransaction().commit();
            return 1;
        }
        finally{
            em.close();
        }
    }
    
    public Role getRole(int id){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Role role = em.find(Role.class, id);
            return role;
        }
        finally{
            em.close();
        }
    }
}
