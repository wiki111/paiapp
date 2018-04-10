package wikira.paiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wikira.paiapp.converters.DtoToUser;
import wikira.paiapp.dto.UserDto;
import wikira.paiapp.model.User;
import wikira.paiapp.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;


    private final DtoToUser dtoToUser;

    public UserService(DtoToUser dtoToUser) {
        this.dtoToUser = dtoToUser;
    }

    @Override
    public User registerNewAccount(UserDto accountDto){

        if(!emailExists(accountDto.getEmail())){
            User user = dtoToUser.convert(accountDto);
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

    public User findByUsername(String username){
        User user = repository.findByEmail(username);

        if(user != null){
            return user;
        }else{
            return null;
        }
    }

    public User findById(Long id){
        Optional<User> user = repository.findById(id);

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }
}
