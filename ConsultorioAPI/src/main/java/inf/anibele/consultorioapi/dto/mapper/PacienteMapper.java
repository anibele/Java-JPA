package inf.anibele.consultorioapi.dto.mapper;

import inf.anibele.consultorioapi.dto.PacienteDTO;
import inf.anibele.consultorioapi.dto.RequestPacienteDTO;
import inf.anibele.consultorioapi.model.Paciente;

public class PacienteMapper {

     //Converte o Request (entrada) para a Entity (banco).

    public static Paciente toEntity(RequestPacienteDTO request) {
        Paciente paciente = new Paciente();
        paciente.setName(request.name());
        paciente.setEmail(request.email());
        paciente.setCpf(request.cpf());
        return paciente;
    }


    //Converte a Entity (banco) para o DTO (saída).
    public static PacienteDTO toDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getName(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }
}
