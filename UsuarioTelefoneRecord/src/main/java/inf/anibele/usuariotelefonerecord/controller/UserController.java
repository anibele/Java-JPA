package inf.anibele.usuariotelefonerecord.controller;

import inf.anibele.usuariotelefonerecord.dto.CreateUserDTO;
import inf.anibele.usuariotelefonerecord.dto.UpdateUserDTO;
import inf.anibele.usuariotelefonerecord.dto.UserDTO;
import inf.anibele.usuariotelefonerecord.dto.UserMapper;
import inf.anibele.usuariotelefonerecord.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Injetamos o Service em vez do Repository
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // O endpoint para listar todos os usuários agora chama o Service, que retorna uma lista de UserDTOs
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        UserDTO savedUser = userService.createUser(createUserDTO);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateData) {
        return userService.updateUser(id, updateData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}