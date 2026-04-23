package inf.anibele.consultorioapi.service;

import inf.anibele.consultorioapi.dto.ConsultaDTO;
import inf.anibele.consultorioapi.dto.RequestConsultaDTO;
import inf.anibele.consultorioapi.dto.mapper.ConsultaMapper;
import inf.anibele.consultorioapi.model.Consulta;
import inf.anibele.consultorioapi.model.Medico;
import inf.anibele.consultorioapi.model.Paciente;
import inf.anibele.consultorioapi.repository.ConsultaRepository;
import inf.anibele.consultorioapi.repository.MedicoRepository;
import inf.anibele.consultorioapi.repository.PacienteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteRepository pacienteRepository,
                           MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    public ConsultaDTO schedule(RequestConsultaDTO request) {
        // Regra de negocio 1: Validação de data retroativa
        if (request.appointmentDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é permitido agendar consultas para o passado.");
        }

        // Regra de negócio 2: Validação de valor ser maior que zero
        if (request.price().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor da consulta deve ser maior que zero.");
        }

        // Regra de negócio 3: Verificação de conflito de horário para o Médico
        if (consultaRepository.existsByDoctorIdAndAppointmentDate(request.doctorId(), request.appointmentDate())) {
            throw new RuntimeException("O médico já possui uma consulta agendada para este horário.");
        }

        // Regra de negócio 4: Verificação de conflito de horário para o Paciente
        if (consultaRepository.existsByPatientIdAndAppointmentDate(request.patientId(), request.appointmentDate())) {
            throw new RuntimeException("O paciente já possui uma consulta agendada para este horário.");
        }

        // Regra de negócio 5: Busca as entidades completas (Garante que existem no banco)
        Paciente paciente = pacienteRepository.findById(request.patientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
        Medico medico = medicoRepository.findById(request.doctorId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));

        // Mapeando o DTO para a entidade, configurando as relações e salvando
        Consulta consulta = ConsultaMapper.toEntity(request);
        consulta.setPatient(paciente);
        consulta.setDoctor(medico);

        return ConsultaMapper.toDTO(consultaRepository.save(consulta));
    }

    public List<ConsultaDTO> findAll() {
        return consultaRepository.findAll().stream()
                .map(ConsultaMapper::toDTO)
                .toList();
    }

}
