package inf.anibele.consultorioapi.dto;

public record PacienteDTO(
        Long id,
        String name,
        String email,
        String cpf
) {
}
