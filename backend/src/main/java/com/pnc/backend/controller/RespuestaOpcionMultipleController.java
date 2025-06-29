package com.pnc.backend.controller;

import com.pnc.backend.dto.GeneralResponse;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.entities.RespuestaOpcionMultiple;
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
@RequestMapping("/api/response")
public class RespuestaOpcionMultipleController {

    private final RespuestaOpcionMultipleService respuestaOpcionMultipleService;

    @Autowired
    public RespuestaOpcionMultipleController(RespuestaOpcionMultipleService respuestaOpcionMultipleService) {
        this.respuestaOpcionMultipleService = respuestaOpcionMultipleService;
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> saveResponse(@RequestBody @Valid RespuestaOpcionMultipleRequest respuestaOpcionMultiple, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Response created", HttpStatus.CREATED, respuestaOpcionMultipleService.save(respuestaOpcionMultiple));
    }

    @PutMapping()
    public ResponseEntity<GeneralResponse> updateResponse(@RequestBody @Valid RespuestaOpcionMultipleUpdateRequest respuestaOpcionMultipleUpdateRequest, @RequestHeader("Authorization") String authHeader) {

        return buildResponse("Response updated", HttpStatus.OK, respuestaOpcionMultipleService.update(respuestaOpcionMultipleUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteResponse(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        respuestaOpcionMultipleService.delete(id);
        return buildResponse("Response deleted", HttpStatus.OK, id);
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
