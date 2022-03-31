package entities.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@MappedSuperclass
public abstract class User extends BaseEntity {


    @Column(unique = true)
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;

}

