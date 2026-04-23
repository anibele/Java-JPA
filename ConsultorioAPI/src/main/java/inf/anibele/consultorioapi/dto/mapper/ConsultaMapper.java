package inf.anibele.consultorioapi.dto.mapper;

import inf.anibele.consultorioapi.dto.ConsultaDTO;
import inf.anibele.consultorioapi.dto.RequestConsultaDTO;
import inf.anibele.consultorioapi.model.Consulta;
import inf.anibele.consultorioapi.model.Medico;
import inf.anibele.consultorioapi.model.Paciente;

public class ConsultaMapper {

    // Converte o DTO de entrada RequestConsultaDTO para a entidade Consulta.
    public static Consulta toEntity(RequestConsultaDTO request) {
        Consulta consulta = new Consulta();

        Paciente paciente = new Paciente();
        paciente.setId(request.patientId());

        Medico medico = new Medico();
        medico.setId(request.doctorId());

        consulta.setPatient(paciente);
        consulta.setDoctor(medico);
        consulta.setAppointmentDate(request.appointmentDate());
        consulta.setPrice(request.price());
        consulta.setStatus(request.status());
        consulta.setNotes(request.notes());

        return consulta;
    }

    // Converte a entidade Consulta para o DTO de saída ConsultaDTO.
    public static ConsultaDTO toDTO(Consulta consulta) {
        return new ConsultaDTO(
                consulta.getId(),
                PacienteMapper.toDTO(consulta.getPatient()),
                MedicoMapper.toDTO(consulta.getDoctor()),
                consulta.getAppointmentDate(),
                consulta.getStatus(),
                consulta.getPrice(),
                consulta.getNotes()
        );
    }
}
