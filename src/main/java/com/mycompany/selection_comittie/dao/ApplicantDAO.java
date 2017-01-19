package com.mycompany.selection_comittie.dao;

import com.mycompany.selection_comittie.entity.Applicant;
import com.mycompany.selection_comittie.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ApplicantDAO {
    
    public Long addApplicant(Applicant applicant, Session session) {
        
        session.beginTransaction();        
        Long result = (Long) session.save(applicant);        
        session.getTransaction().commit();
        
        return result;
    }
    
    public void updateApplicant(Applicant applicant, Session session) {

        session.beginTransaction();
        session.saveOrUpdate(applicant);
        session.getTransaction().commit();

    }
    
    public Applicant getApplicant(Long applicantId, Session session) {

        session.beginTransaction();
        Applicant result = (Applicant) session.load(Applicant.class, applicantId);
        session.getTransaction().commit();

        
        return result;
    }
    
    public List<Applicant> findApplicant(Session session) {

        session.beginTransaction();
        List<Applicant> result = session.createQuery("FROM Applicant ORDER BY lastName, firstName").list();
        session.getTransaction().commit();

        
        return result;
    }
        
}
