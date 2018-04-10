package wikira.paiapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import wikira.paiapp.dto.UserDto;
import wikira.paiapp.model.User;

@Component
public class UserToDto implements Converter<User, UserDto>{



    @Override
    public UserDto convert(User user) {

        final UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());

        return null;
    }
}
