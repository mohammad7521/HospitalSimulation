package repositories;

import entities.Visit;

import javax.management.Query;
import java.util.List;

public class VisitRepo extends BasicCrudImpl<Visit> {


    //show visiting history of a patient
    public List<Visit> patientVisits(int patientId){

        var session=sessionFactory.openSession();
        String query="select a from Visit a where visitPatient.id=:patientId";
        var hql=session.createQuery(query);
        hql.setParameter("patientId",patientId );
        return hql.getResultList();

    }



    //show available visit times of a doctor
    public List<Visit> doctorAvailableVisit(int doctorId){

        var session=sessionFactory.openSession();
        String query="select a from Visit a where doctorVisit.id=:doctorId and visitPatient=null";
        var hql=session.createQuery(query);
        hql.setParameter("doctorId",doctorId );
        return hql.getResultList();
    }



    //show all visit times of a doctor
    public List<Visit> allVisitDoctor(int doctorId){

        var session=sessionFactory.openSession();
        String query="select a from Visit a where doctorVisit.id=:doctorId";
        var hql=session.createQuery(query);
        hql.setParameter("doctorId",doctorId );
        return hql.getResultList();
    }



    //show list of visits which are treated by the doctor but the prescription has bot been set yet.
    public List<Visit> notPrescriptedVisit(int doctorId){
        var session=sessionFactory.openSession();
        String query="select a from Visit a where doctorVisit.id=:doctorId and visitPatient!=null and prescriptionVisit=null ";
        var hql=session.createQuery(query);
        hql.setParameter("doctorId",doctorId );
        return hql.getResultList();
    }
}
