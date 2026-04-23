package inf.anibele.consultorioapi.repository;

import inf.anibele.consultorioapi.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Regra de negocio 1: Um médico não pode possuir duas consultas no mesmo horário
    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    // Regra de negocio 2: Um paciente não pode possuir duas consultas no mesmo horário
    boolean existsByPatientIdAndAppointmentDate(Long patientId, LocalDateTime appointmentDate);
}
