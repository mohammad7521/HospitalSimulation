package entities.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor

public abstract class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


}
