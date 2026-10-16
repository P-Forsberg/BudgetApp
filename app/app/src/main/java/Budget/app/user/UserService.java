package Budget.app.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDTO dto) {
        if(dto.name == null || dto.name.isBlank()){
            throw new RuntimeException("Please enter username");
        }
        if (dto.mail == null || dto.mail.isBlank()){
            throw new RuntimeException("Mail may not be empty!");
        }
        if (!dto.mail.contains("@")){
            throw new RuntimeException("Not a real email, missing @");
        }
        User user = new User();
        user.setName(dto.name);
        user.setMail(dto.mail);

        userRepository.save(user);
        return user;
    }
}
