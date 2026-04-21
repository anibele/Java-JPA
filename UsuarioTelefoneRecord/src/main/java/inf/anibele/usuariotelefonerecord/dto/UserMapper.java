package inf.anibele.usuariotelefonerecord.dto;
import inf.anibele.usuariotelefonerecord.model.Phone;
import inf.anibele.usuariotelefonerecord.model.User;
import java.util.ArrayList;

public class UserMapper {
    // Mapeia um CreateUserDTO para a entidade User
    public static User toEntity(CreateUserDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());

        // Convertemos os DTOs de telefone para entidades
        if (dto.phones() != null) {
            user.setPhones(new ArrayList<>(dto.phones().stream()
                    .map(PhoneMapper::toEntity)
                    .toList()));
        }
        return user;
    }
    // Mapeia uma entidade User para um UserDTO
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhones().stream().map(PhoneMapper::toDTO).toList()
        );
    }
    // Atualiza uma entidade User com os dados de um UpdateUserDTO
    public static void updateEntityFromDTO(UpdateUserDTO dto, User user) {
        if (dto.name() != null) user.setName(dto.name());
        if (dto.email() != null) user.setEmail(dto.email());

        // Para os telefones, a melhor estratégia em um Update simples
        // é limpar a lista antiga e adicionar os novos (devido ao orphanRemoval = true)
        if (dto.phones() != null) {
            user.getPhones().clear();
            dto.phones().forEach(phoneDto -> {
                Phone phone = PhoneMapper.toEntity(phoneDto);
                phone.setUser(user);
                user.getPhones().add(phone);
            });
        }
    }

}