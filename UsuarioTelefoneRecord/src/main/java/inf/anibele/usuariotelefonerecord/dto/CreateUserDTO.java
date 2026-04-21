package inf.anibele.usuariotelefonerecord.dto;

import java.util.List;

public record CreateUserDTO(String name, String email, List<CreatePhoneDTO> phones) {}

