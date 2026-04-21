package inf.anibele.usuariotelefonerecord.service;

import inf.anibele.usuariotelefonerecord.dto.CreateUserDTO;
import inf.anibele.usuariotelefonerecord.dto.UpdateUserDTO;
import inf.anibele.usuariotelefonerecord.dto.UserDTO;
import inf.anibele.usuariotelefonerecord.dto.UserMapper;
import inf.anibele.usuariotelefonerecord.model.User;
import inf.anibele.usuariotelefonerecord.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Metodo para criar um novo usuário a partir do DTO de criação
    @Transactional
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        // 1. Converte DTO para Entidade
        User user = UserMapper.toEntity(createUserDTO);

        // 2. Configura o vínculo bidirecional (Essencial para o JPA salvar a FK)
        if (user.getPhones() != null) {
            user.getPhones().forEach(phone -> phone.setUser(user));
        }

        // 3. Salva o usuário (o CascadeType.ALL cuidará dos telefones)
        User savedUser = userRepository.save(user);

        // 4. Retorna o DTO de saída
        return UserMapper.toDTO(savedUser);
    }

    public List<UserDTO> findAll() {
        // Busca todos, converte para stream e mapeia para DTO
        return ((List<User>) userRepository.findAll()).stream()
                .map(UserMapper::toDTO)
                .toList();
    }
    // Busca por ID, mapeia para DTO e retorna Optional
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO);
    }
    // Deleta por ID, retorna true se deletou, false se não encontrou
    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // Atualiza um usuário existente com os dados do DTO de atualização
    @Transactional
    public Optional<UserDTO> updateUser(Long id, UpdateUserDTO updateUserDTO) {
        return userRepository.findById(id).map(existingUser -> {
            // Aplica o mapeamento reverso
            UserMapper.updateEntityFromDTO(updateUserDTO, existingUser);

            // Salva a entidade atualizada
            User savedUser = userRepository.save(existingUser);

            // Retorna o DTO de saída
            return UserMapper.toDTO(savedUser);
        });
    }

}