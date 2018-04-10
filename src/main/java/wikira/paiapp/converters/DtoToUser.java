package wikira.paiapp.converters;

import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import wikira.paiapp.dto.UserDto;
import wikira.paiapp.model.User;


@Component
public class DtoToUser implements Converter<UserDto, User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Synchronized
    @Nullable
    @Override
    public User convert(UserDto userDto) {
        if(userDto == null){
            return null;
        }

        final User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(userDto.getThings() != null && !userDto.getThings().isEmpty()){
            user.setThings(userDto.getThings());
        }

        return user;
    }
}
