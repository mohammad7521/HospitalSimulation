package repositories;

import entities.Patient;
import org.hibernate.hql.internal.classic.PathExpressionParser;

import javax.persistence.Query;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PatientRepo extends BasicCrudImpl<Patient> {

    //based on username
    public Patient showInfo(String username) {

        List<Patient> result=new ArrayList<>();
        try(var session=sessionFactory.openSession()) {
            var trx=session.beginTransaction();

            try {
                var cb = session.getCriteriaBuilder();
                var cq = cb.createQuery(Patient.class);
                Root<Patient> root = cq.from(Patient.class);
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
