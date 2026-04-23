package inf.anibele.consultorioapi.controller;

import inf.anibele.consultorioapi.dto.ConsultaDTO;
import inf.anibele.consultorioapi.dto.RequestConsultaDTO;
import inf.anibele.consultorioapi.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    // Agendar uma nova consulta a partir do DTO de requisição
    @PostMapping
    public ResponseEntity<ConsultaDTO> schedule(@RequestBody RequestConsultaDTO request) {
        ConsultaDTO response = service.schedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


     // listar todas as consultas agendadas.
    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAll() {
        List<ConsultaDTO> response = service.findAll();
        return ResponseEntity.ok(response);
    }
}
