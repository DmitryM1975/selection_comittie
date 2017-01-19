
import com.mycompany.selection_comittie.dao.ApplicantDAO;
import com.mycompany.selection_comittie.dao.ProfessionDAO;
import com.mycompany.selection_comittie.dao.SubjectDAO;
import com.mycompany.selection_comittie.entity.Applicant;
import com.mycompany.selection_comittie.entity.Profession;
import com.mycompany.selection_comittie.entity.Subject;
import com.mycompany.selection_comittie.utils.HibernateUtil;

import java.util.List;
import junit.framework.TestCase;
import org.hibernate.Session;
 

public class AppTest extends TestCase {
    
    
    public void testApp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        SubjectDAO subjectDAO = new SubjectDAO();
        ProfessionDAO professionDAO = new ProfessionDAO();
        ApplicantDAO applicantDAO = new ApplicantDAO();
        
        // Добавление новых предметов
        Subject subject = new Subject();
        subject.setSubjectName("Математика");
        subjectDAO.addSubject(subject, session);
        
        subject = new Subject();
        subject.setSubjectName("Химия");
        subjectDAO.addSubject(subject, session);
        
        subject = new Subject();
        subject.setSubjectName("Логика");
        subjectDAO.addSubject(subject, session);
        
        // Печать списка преметов
        System.out.println("List of SUBJECTS");
        System.out.println("------------------------------------------------");
        List<Subject> sbList = subjectDAO.findSubject(session);
        // В списке вы увидите, что предметы пока не привязаны к профессиям - количество = 0
        for (Subject curSubject : sbList) {
            System.out.println(curSubject.getSubjectId() + ":" + 
                               curSubject.getSubjectName() +". Number of profession:" + 
                               curSubject.getProfessionList().size());
        }
        
        // Теперь добавим профессии
        Profession profession = new Profession();
        profession.setProfessionName("Программист");
        // Список предметов, которые надо сдавать для этой профессии
        // Обратите внимание, что в классе Profession мы создаем пустой список
        // чтобы не было NullPointerException
        for (Subject curSubject : sbList) {
            if (curSubject.getSubjectName().equals("Математика") || 
                curSubject.getSubjectName().equals("Логика")) {
                
                profession.getSubjectList().add(curSubject);
            }
        }
        professionDAO.addProfession(profession, session);
        
        profession = new Profession();
        profession.setProfessionName("Биолог");
        professionDAO.addProfession(profession, session);
        for (Subject curSubject : sbList) {
            if (curSubject.getSubjectName().equals("Математика") || 
                curSubject.getSubjectName().equals("Химия")) {
                
                profession.getSubjectList().add(curSubject);        
            }
        }
        professionDAO.addProfession(profession, session);
        
        // Смотрим список профессий
        System.out.println();
        System.out.println("List of PROFESSIONS");
        System.out.println("------------------------------------------------");
        List<Profession> prList = professionDAO.findProfession(session);
        for (Profession curProfession : prList) {
            System.out.println(curProfession.getProfessionId() + ":" + 
                               curProfession.getProfessionName() + ":" +
                               curProfession.getSubjectList().size());
        }
        
        // Еще раз смотрим список предметов
        System.out.println();
        System.out.println("List of SUBJECTS");
        System.out.println("------------------------------------------------");
        sbList = subjectDAO.findSubject(session);
        // В списке вы увидите, что предметы теперь привязаны к профессиям - количество > 0
        for (Subject curSubject : sbList) {
            System.out.println(curSubject.getSubjectId() + ":" + curSubject.getSubjectName() +
                    ". Number of profession:" + curSubject.getProfessionList().size());
        }

        // А теперь создадим новых абитуриентов
        Applicant applicant = new Applicant();
        applicant.setFirstName("Дмитрий");
        applicant.setMiddleName("Леонидович");
        applicant.setLastName("Малянов");
        // Задаем профессию
        applicant.setProfession(prList.get(0));
        applicant.setEntranceYear(2009);
        applicantDAO.addApplicant(applicant, session);
        
        
        applicant = new Applicant();
        applicant.setFirstName("Наталья");
        applicant.setMiddleName("Алексеевна");
        applicant.setLastName("Каширская");
        // Задаем профессию
        applicant.setProfession(prList.get(1));
        applicant.setEntranceYear(2009);
        applicantDAO.addApplicant(applicant, session);

        // Смотрим список абитуриентов
        System.out.println();
        System.out.println("List of APPLICANTS");
        System.out.println("------------------------------------------------");
        List<Applicant> apList = applicantDAO.findApplicant(session);
        for (Applicant curApplicant : apList) {
            System.out.println(curApplicant.getFirstName() + ":" + curApplicant.getLastName() + 
               " - " + curApplicant.getProfession().getProfessionName());
        // Если убрать комментарий, то получим сообщене об ошибке - коллекция не инициализирована
        // Но еще можно посмотреть комментарий в StudentDAO (метод findApplicant()).
        //System.out.println(a.getProfession().getSubjectList().size());
        }
        session.close();
    }
    
}
