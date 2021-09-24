/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exhibernate3;

import java.util.List;
//import javax.persistence.Query;
import org.hibernate.query.Query;
import model.Aluno;
import model.Disciplina;
import model.Professor;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author leleb
 */
public class ExHibernate3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Aluno al1 = new Aluno (123,"Marcos");
        Aluno al2 = new Aluno (312,"Gabriel");
        Aluno al3 = new Aluno (432,"Leticia");
     // Aluno al4 = new Aluno (432,""); teste com mesmo ra e null
        
          Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                session.save(al1);
                session.save(al2);
                session.save(al3);
               // session.save(al4);
                
               // session.delete(al2);
            session.getTransaction().commit();  
        
        Disciplina d1 = new Disciplina ("História da Arte",20);
        Disciplina d2 = new Disciplina ("Matenática aplicada",55);
        Disciplina d3 = new Disciplina ("História do Brasil",18);
        
            session.beginTransaction();
                session.save(d1);
                session.save(d2);
                session.save(d3);
                
                session.delete(d1);
            session.getTransaction().commit();
        
        Professor p1 = new Professor ("João Carlos","joaoCarlos@gmail.com");
        Professor p2 = new Professor ("Carla Dias","Carlinha@gmail.com");
        Professor p3 = new Professor ("Marta de Morais","ma002Morais@gmail.com");
      //Professor p4 = new Professor ("Maria da Silva","ma002Morais@gmail.com"); teste com msm email
        
            session.beginTransaction();
                session.save(p1);
                session.save(p2);
                session.save(p3);
              //  session.save(p4);
                
                session.update(p3);
            session.getTransaction().commit();
            
            
        // buscar apenas 1 aluno no bd
            Aluno al4 = (Aluno) session.get(Aluno.class,123);
            System.out.println(" RA: "+ al4.getRa() + " Nome: " + al4.getNome());
            
        // buscar vários alunos no bd
            String hql  = "from Aluno";
            Query query = session.createQuery(hql);
            List <Aluno> listaAl = query.list();
            for (Aluno al: listaAl)
                System.out.println("\n===== RA: "+ al.getRa()+ " Nome: " + al.getNome());
            
            HibernateUtil.shutdown();
    }
}
