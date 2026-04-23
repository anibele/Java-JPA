package inf.anibele.consultorioapi.dto.mapper;

import inf.anibele.consultorioapi.dto.MedicoDTO;
import inf.anibele.consultorioapi.dto.RequestMedicoDTO;
import inf.anibele.consultorioapi.model.Medico;

public class MedicoMapper {

      //Converte o RequestMedicoDTO para a entidade Medico.
    public static Medico toEntity(RequestMedicoDTO request) {
        Medico medico = new Medico();
        medico.setName(request.name());
        medico.setSpecialty(request.specialty());
        medico.setCrm(request.crm());
        return medico;
    }


     //Converte a entidade Medico para o MedicoDTO de saída.
    public static MedicoDTO toDTO(Medico medico) {
        return new MedicoDTO(
                medico.getId(),
                medico.getName(),
                medico.getSpecialty(),
                medico.getCrm()
        );
    }
}
