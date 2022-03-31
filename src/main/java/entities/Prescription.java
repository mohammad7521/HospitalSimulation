package entities;


import entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Prescription extends BaseEntity {

    private String description;


    @OneToOne(mappedBy = "prescriptionVisit",cascade = CascadeType.ALL)
    private Visit visitPrescription;
}
