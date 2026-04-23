package inf.anibele.consultorioapi.dto;

import inf.anibele.consultorioapi.model.StatusConsulta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaDTO(
        Long id,
        PacienteDTO patient,
        MedicoDTO doctor,
        LocalDateTime appointmentDate,
        StatusConsulta status,
        BigDecimal price,
        String notes
) {
}
