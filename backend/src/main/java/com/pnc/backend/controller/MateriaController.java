package com.pnc.backend.controller;

import com.pnc.backend.dto.request.materia.MateriaRequest;
import com.pnc.backend.dto.request.materia.MateriaUpdateRequest;
import com.pnc.backend.dto.response.GeneralResponse;
import com.pnc.backend.dto.response.examen.ExamenResponse;
import com.pnc.backend.dto.response.materia.MateriaResponse;
import com.pnc.backend.dto.response.notas.MateriaExamenUsuarioResponse;
import com.pnc.backend.dto.response.tema.TemaResponse;
import com.pnc.backend.exceptions.MateriaNotFoundException;
import com.pnc.backend.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://learnsy-three.vercel.app", allowCredentials = "true")
@RequestMapping("/api/materia")
public class MateriaController {
    private final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(GeneralResponse.builder()
                .message(message)
                .status(status.value())
                .data(data)
                .uri(uri)
                .time(LocalDate.now())
                .build());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getMaterias(@RequestHeader("Authorization") String authHeader) {
        List<MateriaResponse> materias = materiaService.findAll();
        if (materias.isEmpty()) {
            throw new MateriaNotFoundException("No se encontraron materias");
        }
        return buildResponse("Materias encontradas exitosamente", HttpStatus.OK, materias);
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> saveMateria(@RequestBody @Valid MateriaRequest materia, @RequestHeader("Authorization") String authHeader){
        return buildResponse("Materia creada exitosamente", HttpStatus.CREATED, materiaService.save(materia));
    }

    @PutMapping()
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> updateMateria(@RequestBody @Valid MateriaUpdateRequest materia, @RequestHeader("Authorization") String authHeader){
        materiaService.findById(materia.getId());
        return buildResponse("Materia actualizada", HttpStatus.OK, materiaService.update(materia));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> deleteMateria(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        MateriaResponse materia = materiaService.findById(id);
        materiaService.delete(id);
        return buildResponse("Materia eliminada exitosamente", HttpStatus.OK, materia);
    }

    @PatchMapping("/visibility/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> toggleVisibility(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        return buildResponse("Visibilidad actualizada", HttpStatus.OK, materiaService.toggleVisibility(id));
    }

    @GetMapping("/{id}/exams")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getExams(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        List<ExamenResponse> exams = materiaService.findExams(id);
        return buildResponse("Exams found", HttpStatus.OK, exams);
    }

    @GetMapping("/{id}/temas")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getTemas(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        List<TemaResponse> temas = materiaService.findTemas(id);
        return buildResponse("Temas found", HttpStatus.OK, temas);
    }

    @PostMapping("/{idMateria}/usuario/{idUsuario}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> addUser( @PathVariable Long idMateria, @PathVariable Long idUsuario, @RequestHeader("Authorization") String authHeader) {
        materiaService.addUser(idMateria, idUsuario);
        return buildResponse("Usuario agregado a la materia exitosamente", HttpStatus.OK, null);
    }

    @DeleteMapping("/{idMateria}/usuario/{idUsuario}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> removeUser(@PathVariable Long idMateria, @PathVariable Long idUsuario, @RequestHeader("Authorization") String authHeader) {
        materiaService.removeUser(idMateria, idUsuario);
        return buildResponse("Usuario eliminado de la materia exitosamente", HttpStatus.OK, null);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getMateriaDetails(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        MateriaResponse response = materiaService.findWithDetails(id);
        response.getUsuarios().forEach(u -> {
            if (u.getAvatar() != null && !u.getAvatar().isEmpty() && !u.getAvatar().startsWith("http")) {
                u.setAvatar("http://localhost:8080/api/user/avatar/" + u.getAvatar());
            }
        });
        return buildResponse("Materia encontrada exitosamente", HttpStatus.OK, response);
    }

    @GetMapping("/examenes-usuarios/{id}")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getExamenesConUsuarios(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {

        MateriaExamenUsuarioResponse response = materiaService.getExamenesConUsuarios(id);
        return buildResponse("Exámenes y usuarios obtenidos exitosamente", HttpStatus.OK, response);
    }

}
