package entities;


import entities.base.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Patient extends User {

    @OneToMany(mappedBy = "visitPatient",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Visit> patientVisitSet;

    @Override
    public String toString() {
        return "\npatient:{" +
                "\nid:"+getId()+
                "\nusername:" + username +
                "\npassword:" + password +
                "\nfirstname:" + firstname +
                "\nlastname:" + lastname +
                "\n}";
    }
}
