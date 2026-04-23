package inf.anibele.consultorioapi.dto;

import inf.anibele.consultorioapi.model.StatusConsulta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RequestConsultaDTO(
        Long patientId,
        Long doctorId,
        //para evitar erros de formatação de data
        @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime appointmentDate,
        StatusConsulta status,
        BigDecimal price,
        String notes
) {
}
