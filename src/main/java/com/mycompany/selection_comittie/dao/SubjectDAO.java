package com.mycompany.selection_comittie.dao;

import com.mycompany.selection_comittie.entity.Subject;
import com.mycompany.selection_comittie.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class SubjectDAO {
    
    public Long addSubject(Subject subject, Session session) {

        session.beginTransaction();
        Long result = (Long) session.save(subject);
        session.getTransaction().commit();
        
        return result;
    }
    
    public void updateSubject(Subject subject, Session session) {

        session.beginTransaction();
        session.saveOrUpdate(subject);
        session.getTransaction().commit();

    }
    
    public Subject getSubject(Long subjectId, Session session) {
 
        session.beginTransaction();
        Subject result = (Subject) session.load(Subject.class, subjectId);
        session.getTransaction().commit();
        
        return result;
    }
    
    public List<Subject> findSubject(Session session) {

        session.beginTransaction();
        List<Subject> result = session.createQuery("FROM Subject ORDER BY subjectName").list();
        session.getTransaction().commit();
        
        return result;
    }    
}
