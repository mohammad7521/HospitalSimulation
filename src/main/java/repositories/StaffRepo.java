package repositories;

import entities.Patient;
import entities.Staff;

import javax.persistence.Query;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StaffRepo extends BasicCrudImpl<Staff>{

    //based on username
    public Staff showInfo(String username) {

        List<Staff> result=new ArrayList<>();
        try(var session=sessionFactory.openSession()) {
            var trx=session.beginTransaction();

            try {
                var cb = session.getCriteriaBuilder();
                var cq = cb.createQuery(Staff.class);
                Root<Staff> root = cq.from(Staff.class);
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
