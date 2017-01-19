package com.mycompany.selection_comittie.dao;

import com.mycompany.selection_comittie.entity.Profession;
import com.mycompany.selection_comittie.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ProfessionDAO {
    
    public Long addProfession(Profession profession, Session session) {

        session.beginTransaction();
        Long result = (Long) session.save(profession);
        session.getTransaction().commit();
        
        return result;
    }
    
    public void updateProfession(Profession profession, Session session) {

        session.beginTransaction();
        session.saveOrUpdate(profession);
        session.getTransaction().commit();

    }
    
    public Profession getProfession(Long professionId, Session session) {

        session.beginTransaction();
        Profession result = (Profession) session.load(Profession.class, professionId);
        session.getTransaction().commit();
        
        return result;
    }
    
    public List<Profession> findProfession(Session session) {

        session.beginTransaction();
        List<Profession> result = session.createQuery("FROM Profession ORDER BY professionName").list();
        session.getTransaction().commit();
        
        return result;
    }
    
}
