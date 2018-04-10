package wikira.paiapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ThingToDo> things = new HashSet<>();

    public User addThing(ThingToDo thingToDo){
        thingToDo.setUser(this);
        this.things.add(thingToDo);
        return this;
    }
}
