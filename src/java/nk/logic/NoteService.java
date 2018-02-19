/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nk.dataaccess.NoteRepository;
import nk.domain.Note;
import nk.domain.User;

/**
 *
 * @author 664978
 */
public class NoteService {

    public Note get(int noteId) throws Exception{
        
        NoteRepository nr = new NoteRepository();
        Note note = nr.getNote(noteId);
        return note;
        
    }
    
    
    public int update(int noteId, String contents)throws Exception{
        
        NoteRepository nr = new NoteRepository();
        Note note = nr.getNote(noteId);
        note.setContents(contents);
        return nr.update(note);
        
    }
    
    public int update(int noteId, String contents, String title)throws Exception{
        
        NoteRepository nr = new NoteRepository();
        Note note = nr.getNote(noteId);
        note.setContents(contents);
        note.setTitle(title);
        return nr.update(note);
        
    }
    
    public int addCollaborator(int noteId, List userList)throws Exception{
        
        NoteRepository nr = new NoteRepository();
        Note note = nr.getNote(noteId);
        note.setUserList(userList);
        return nr.update(note);
        
    }
    
    public void notifyNewCollaborator(HttpServletRequest request, int noteId, String newCollaborator) throws Exception{
        NoteRepository nr = new NoteRepository();
        Note note = nr.getNote(noteId);
        UserService us = new UserService();
        User user = us.get(newCollaborator);
        String template = null;
        if(user.getLanguage().equalsIgnoreCase("fr")){
            template = request.getServletContext().getRealPath("/WEB-INF/email-templates/collaborate_fr.html");
        }
        else{
            template = request.getServletContext().getRealPath("/WEB-INF/email-templates/collaborate.html");
        }
        HashMap<String, String> contents = new HashMap<>();
        contents.put("firstname", user.getFirstName());
        contents.put("username", note.getOwner().getUserName());
        contents.put("title", note.getTitle());
        
        EmailService.send(template, contents, user.getEmail(), "New Collaboration Alert");
    }
    
    public int delete(long noteId) throws Exception{
        
        NoteRepository nr = new NoteRepository();
        Note note = get((int)noteId);
        return nr.delete(note);
        
    }
    
    public int insert(String contents, HttpServletRequest request)throws Exception{
        
        HttpSession session = request.getSession();
        NoteRepository nr = new NoteRepository();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Note note = new Note(0,sqlDate, "Untitled", contents);
        User user = (User)session.getAttribute("currentUser");
        return nr.insert(note, user);
        
    }
    
    public int insert(String contents, String title, HttpServletRequest request)throws Exception{
        
        HttpSession session = request.getSession();
        NoteRepository nr = new NoteRepository();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Note note = new Note(0,sqlDate, title, contents);
        User user = (User)session.getAttribute("currentUser");
        return nr.insert(note, user);
        
    }
    
    public List getNonCollaborators(Note note) throws Exception{
        List<User> nonCollaborators = new ArrayList<User>();
        List<User> collaborators = note.getUserList();
        UserService us = new UserService();
        List<User> users = us.getAll();
        for(int i = 0; i<users.size(); i++){
            if(!isCollaborator(users.get(i), collaborators)){
                nonCollaborators.add(users.get(i));
            }
        }
        return nonCollaborators;
    }
    
    private boolean isCollaborator(User user, List<User> collaborators){
        for(int i = 0; i<collaborators.size(); i++){
            if(collaborators.get(i).getUserName().equalsIgnoreCase(user.getUserName())){
                return true;
            }
        }
        return false;
    }
}
