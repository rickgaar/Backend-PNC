package com.pnc.backend.controller;

import com.pnc.backend.dto.GeneralResponse;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.examen.ExamenRequest;
import com.pnc.backend.dto.request.examen.ExamenUpdateRequest;
import com.pnc.backend.service.ExamenService;
import com.pnc.backend.service.PreguntaOpcionMultipleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "https://learnsy-three.vercel.app", allowCredentials = "true")
@RequestMapping("/api/exam")
public class ExamenController {
    private final ExamenService examenService;

    @Autowired
    public ExamenController(ExamenService examenService) {
        this.examenService = examenService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> saveExam(@RequestBody @Valid ExamenRequest examenRequest, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Exam created", HttpStatus.CREATED, examenService.save(examenRequest));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getExam(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Exam found", HttpStatus.CREATED, examenService.findById(id));
    }

    @PutMapping()
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> updateExam(@RequestBody @Valid ExamenUpdateRequest examenUpdateRequest, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Exam updated", HttpStatus.OK, examenService.update(examenUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> deleteExam(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        examenService.delete(id);
        return buildResponse("Exam deleted", HttpStatus.OK, id);
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
}
