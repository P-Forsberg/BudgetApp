package Budget.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
}
