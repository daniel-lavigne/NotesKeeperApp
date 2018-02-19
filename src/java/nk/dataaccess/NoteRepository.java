/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.dataaccess;

import javax.persistence.EntityManager;
import nk.domain.Note;
import nk.domain.User;

/**
 *
 * @author 664978
 */
public class NoteRepository {

    public int insert(Note note, User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            note.setOwner(user);
            user.getNoteList().add(note);
            em.getTransaction().begin();
            em.merge(user);
            em.persist(em.merge(note));
            em.getTransaction().commit();
            return 1;
        }
        finally {
            em.close();
        }
    }
    
    public int update(Note note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User owner = note.getOwner();
            em.getTransaction().begin();
            em.merge(note);
            em.getTransaction().commit();
            return 1;
        } 
        finally {
            em.close();
        }
    }
    
    public Note getNote(int noteID){
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteID);
            return note;
        } 
        finally {
            em.close();    
        }  
    }
    
    public int delete(Note note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User owner = note.getOwner();
            owner.getNoteList().remove(note);
            em.getTransaction().begin();
            em.merge(owner);
            em.remove(em.merge(note));
            em.getTransaction().commit();
            return 1;
        } 
        finally {
            em.close();
        }

    }
    
}
