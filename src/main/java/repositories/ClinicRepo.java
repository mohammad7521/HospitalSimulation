package repositories;

import entities.Clinic;
import entities.Doctor;

import java.util.List;

public class ClinicRepo extends BasicCrudImpl<Clinic> {


    //show doctors of a clinic
    public List<Doctor> getDoctorList(int clinicId){

        var session=sessionFactory.openSession();
        String query="select a from Doctor a inner join Clinic b on b.id=a.doctorClinic.id where b.id=:clinicId";
        var hql=session.createQuery(query);
        hql.setParameter("clinicId",clinicId );
        return hql.getResultList();
    }
}
