package com.pnc.backend.controller;

import com.pnc.backend.dto.GeneralResponse;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.service.PreguntaOpcionMultipleService;
import com.pnc.backend.service.RespuestaOpcionMultipleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "https://learnsy-three.vercel.app/", allowCredentials = "true")
@RequestMapping("/api/question")
public class PreguntaOpcionMultipleController {
    private final PreguntaOpcionMultipleService preguntaOpcionMultipleService;

    @Autowired
    public PreguntaOpcionMultipleController(PreguntaOpcionMultipleService preguntaOpcionMultipleService) {
        this.preguntaOpcionMultipleService = preguntaOpcionMultipleService;
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> saveQuestion(@RequestBody @Valid PreguntaOpcionMultipleRequest preguntaOpcionMultipleRequest, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Question created", HttpStatus.CREATED, preguntaOpcionMultipleService.save(preguntaOpcionMultipleRequest));
    }

    @PutMapping()
    public ResponseEntity<GeneralResponse> updateQuestion(@RequestBody @Valid PreguntaOpcionMultipleUpdateRequest preguntaOpcionMultipleUpdateRequest, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Question updated", HttpStatus.OK, preguntaOpcionMultipleService.update(preguntaOpcionMultipleUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteQuestion(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        preguntaOpcionMultipleService.delete(id);
        return buildResponse("Question deleted", HttpStatus.OK, id);
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
