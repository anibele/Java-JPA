package inf.anibele.usuariotelefonerecord.controller;

import inf.anibele.usuariotelefonerecord.dto.CreatePhoneDTO;
import inf.anibele.usuariotelefonerecord.dto.PhoneDTO;
import inf.anibele.usuariotelefonerecord.dto.PhoneMapper;
import inf.anibele.usuariotelefonerecord.model.Phone;
import inf.anibele.usuariotelefonerecord.repository.PhoneRepository;
import inf.anibele.usuariotelefonerecord.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    public PhoneController(PhoneRepository phoneRepository, UserRepository userRepository) {
        this.phoneRepository = phoneRepository;
        this.userRepository = userRepository;
    }
    // Para listar todos os telefones, convertemos a Iterable para Stream e depois para List
    @GetMapping
    public List<PhoneDTO> getAllPhones() {
        return StreamSupport.stream(phoneRepository.findAll().spliterator(), false)
                .map(PhoneMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDTO> getPhoneById(@PathVariable Long id) {
        return phoneRepository.findById(id)
                .map(PhoneMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PhoneDTO> createPhone(@PathVariable Long userId, @RequestBody CreatePhoneDTO createPhoneDTO) {
        return userRepository.findById(userId).map(user -> {
            Phone phone = PhoneMapper.toEntity(createPhoneDTO);
            phone.setUser(user);

            Phone savedPhone = phoneRepository.save(phone);
            return ResponseEntity.ok(PhoneMapper.toDTO(savedPhone));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}