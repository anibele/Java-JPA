package inf.anibele.consultorioapi.service;

import inf.anibele.consultorioapi.dto.PacienteDTO;
import inf.anibele.consultorioapi.dto.RequestPacienteDTO;
import inf.anibele.consultorioapi.dto.mapper.PacienteMapper;
import inf.anibele.consultorioapi.model.Paciente;
import inf.anibele.consultorioapi.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PacienteDTO create(RequestPacienteDTO request) {
        Paciente paciente = PacienteMapper.toEntity(request);
        return PacienteMapper.toDTO(repository.save(paciente));
    }

    @Transactional
    public Optional<PacienteDTO> update(Long id, RequestPacienteDTO request) {
        return repository.findById(id).map(existingPaciente -> {
            existingPaciente.setName(request.name());
            existingPaciente.setEmail(request.email());
            existingPaciente.setCpf(request.cpf());
            return PacienteMapper.toDTO(repository.save(existingPaciente));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PacienteDTO> findById(Long id) {
        return repository.findById(id).map(PacienteMapper::toDTO);
    }

    public List<PacienteDTO> findAll() {
        return repository.findAll().stream()
                .map(PacienteMapper::toDTO)
                .toList();
    }
}