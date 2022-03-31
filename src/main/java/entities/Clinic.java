package entities;


import entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@OnDelete(action = OnDeleteAction.CASCADE)
public class Clinic extends BaseEntity {

    private String name;
    private String address;

    @OneToMany(mappedBy = "doctorClinic",cascade = CascadeType.ALL)
    private Set<Doctor> doctorList;



    public String toStringWithDoctorList() {
        return "\nClinic:" +
                "\nid:"+ getId()+
                "\nname: " + name +
                "\naddress: " + address +
                "\ndoctors"+doctorList+
                "\n}";
    }

    public String toString() {
        return "\nClinic:" +
                "id"+ getId()+
                "\nname: " + name +
                "\naddress: " + address +
                "\n}";
    }



    public void addDoctors(Doctor doctor){
        doctorList.add(doctor);
    }
}
