package inf.anibele.consultorioapi.service;

import inf.anibele.consultorioapi.dto.MedicoDTO;
import inf.anibele.consultorioapi.dto.RequestMedicoDTO;
import inf.anibele.consultorioapi.dto.mapper.MedicoMapper;
import inf.anibele.consultorioapi.model.Medico;
import inf.anibele.consultorioapi.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MedicoDTO create(RequestMedicoDTO request) {
        Medico medico = MedicoMapper.toEntity(request);
        return MedicoMapper.toDTO(repository.save(medico));
    }

    public List<MedicoDTO> findAll() {
        return repository.findAll().stream()
                .map(MedicoMapper::toDTO)
                .toList();
    }

    public Optional<MedicoDTO> findById(Long id) {
        return repository.findById(id).map(MedicoMapper::toDTO);
    }

    @Transactional
    public Optional<MedicoDTO> update(Long id, RequestMedicoDTO request) {
        return repository.findById(id).map(existingMedico -> {
            existingMedico.setName(request.name());
            existingMedico.setSpecialty(request.specialty());
            existingMedico.setCrm(request.crm());
            return MedicoMapper.toDTO(repository.save(existingMedico));
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
}
