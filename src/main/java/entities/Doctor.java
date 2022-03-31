package entities;


import entities.base.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Doctor extends User {


    @ManyToOne
    private Clinic doctorClinic;
    private String expert;


    @OneToMany(mappedBy = "doctorVisit",cascade = CascadeType.ALL)
    private Set<Visit> doctorVisitSet;

    @Override
    public String toString() {
        return "\nDoctor:{" +
                "\nid:"+getId()+
                "\nusername:" + username +
                "\npassword:" + password +
                "\nfirstname:" + firstname +
                "\nlastname:" + lastname +
                "\nclinic: " +doctorClinic +
                "\n}";
    }
}
