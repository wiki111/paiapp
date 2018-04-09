package wikira.paiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wikira.paiapp.dto.UserDto;
import wikira.paiapp.model.User;
import wikira.paiapp.repositories.UserRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewAccount(UserDto accountDto){

        if(!emailExists(accountDto.getEmail())){
            User user = new User();
            user.setFirstName(accountDto.getFirstName());
            user.setLastName(accountDto.getLastName());
            user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            user.setEmail(accountDto.getEmail());
            user.setRole("ROLE_USER");
            return repository.save(user);
        }else{
            return null;
        }

    }

    private boolean emailExists(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
