package entities;


import entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Medicine extends BaseEntity {

    private String name;

    private MedicineUsage usage;

    @ManyToOne
    private Prescription medicinePrescription;
}
