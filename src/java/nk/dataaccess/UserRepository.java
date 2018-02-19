package nk.dataaccess;


import java.util.List;
import javax.persistence.EntityManager;
import nk.domain.User;

public class UserRepository {

    public int insert(User user)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
    }

    public int update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
    }

//Group 7
    public List<User> getAll()  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).
                    getResultList();
            return users;
        } finally {
            em.close();    
        }
    }

    public User getUser(String userName)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, userName);
            return user;
        } finally {
            em.close();    
        }
    }

    public int delete(User user)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.merge(user));
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
   }
}
