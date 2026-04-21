package inf.anibele.usuariotelefonerecord.dto;

import java.util.List;

public record UpdateUserDTO(
        String name,
        String email,
        List<CreatePhoneDTO> phones
) {}
