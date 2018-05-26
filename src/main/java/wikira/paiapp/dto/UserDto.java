package wikira.paiapp.dto;

import lombok.Data;
import wikira.paiapp.model.ThingToDo;
import wikira.paiapp.validation.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    @Size(min=2, max=45)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min=2, max=65)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min=8)

    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    private Set<ThingToDo> things = new HashSet<>();

}
