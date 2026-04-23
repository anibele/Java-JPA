package inf.anibele.consultorioapi.controller;

import inf.anibele.consultorioapi.dto.MedicoDTO;
import inf.anibele.consultorioapi.dto.RequestMedicoDTO;
import inf.anibele.consultorioapi.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    // Criar Médico
    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody RequestMedicoDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // Listar Todos
    @GetMapping
    public List<MedicoDTO> getAll() {
        return service.findAll();
    }

    // Buscar medico por ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar Médico por id
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @RequestBody RequestMedicoDTO request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar Médico por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
