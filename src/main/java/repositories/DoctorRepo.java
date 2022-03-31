package repositories;

import entities.Doctor;
import entities.Staff;
import services.DoctorServices;

import javax.persistence.Query;
import javax.persistence.criteria.Root;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepo extends BasicCrudImpl<Doctor> {

    //based on username
    public Doctor showInfo(String username) {

        List<Doctor> result=new ArrayList<>();
        try(var session=sessionFactory.openSession()) {
            var trx=session.beginTransaction();

            try {
                var cb = session.getCriteriaBuilder();
                var cq = cb.createQuery(Doctor.class);
                Root<Doctor> root = cq.from(Doctor.class);
                cq.select(root).where(cb.equal(root.get("username"), username));
                Query query = session.createQuery(cq);
                result = query.getResultList();
                trx.commit();
                session.close();

            }catch (Exception e){
                trx.rollback();
            }

        }
        return result.get(0);
    }
}
