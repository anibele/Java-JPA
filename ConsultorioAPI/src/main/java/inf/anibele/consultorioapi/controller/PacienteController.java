package inf.anibele.consultorioapi.controller;

import inf.anibele.consultorioapi.dto.PacienteDTO;
import inf.anibele.consultorioapi.dto.RequestPacienteDTO;
import inf.anibele.consultorioapi.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    // Criar novo paciente
    @PostMapping
    public ResponseEntity<PacienteDTO> create(@RequestBody RequestPacienteDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // listar todos
    @GetMapping
    public List<PacienteDTO> getAll() {
        return service.findAll();
    }

    // procurar paciente pelo id
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // atualizar paciente pelo id
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody RequestPacienteDTO request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // deletar o paciente pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
