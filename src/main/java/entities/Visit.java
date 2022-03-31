package entities;


import entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Visit extends BaseEntity {

    private DayOfWeek dayOfWeek;

    private VisitTime visitTime;

    @ManyToOne
    private Doctor doctorVisit;

    @ManyToOne
    private Patient visitPatient;

    @OneToOne
    private Prescription prescriptionVisit;

    @Override
    public String toString() {
        return "\nVisit:" +
                "id:"+getId()+
                "\ndayOfWeek=" + dayOfWeek.toString() +
                "\nvisitTime=" + visitTime.toString() +
                "\ndoctorVisit=" + doctorVisit.getFirstname() +"expert: "+doctorVisit.getExpert()+
                "\nprescription=" + prescriptionVisit +
                "}\n";
    }
}
