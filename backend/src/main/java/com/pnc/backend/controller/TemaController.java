package com.pnc.backend.controller;

import com.pnc.backend.dto.request.tema.TemaRequest;
import com.pnc.backend.dto.request.tema.TemaUpdateRequest;
import com.pnc.backend.dto.response.GeneralResponse;
import com.pnc.backend.dto.response.tema.TemaResponse;
import com.pnc.backend.entities.Tema;
import com.pnc.backend.service.TemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/tema")
public class TemaController {
    @Autowired
    private TemaService temaService;

    @PostMapping(value = "/{materiaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GeneralResponse> crearTemaConArchivo(
            @PathVariable Long materiaId,
            @RequestPart("archivo") MultipartFile archivo,
            @RequestParam("nombre") String nombre,
            @RequestParam("visibilidad") boolean visibilidad,
            @RequestHeader("Authorization") String authHeader) throws IOException {

        TemaResponse temaRes = temaService.save(TemaRequest.builder().nombre(nombre).visibilidad(visibilidad).idMateria(materiaId).build(), archivo);
        return buildResponse("Tema registrado exitosamente", HttpStatus.OK, temaRes);
    }

    @GetMapping("/{id}/archivo/view")
    public ResponseEntity<ByteArrayResource> verArchivo(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) throws IOException {

            TemaResponse tema = temaService.findById(id);

            byte[] contenidoArchivo = temaService.getFileByTemaId(id);
            ByteArrayResource resource = new ByteArrayResource(contenidoArchivo);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(contenidoArchivo.length)
                    .body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateTema(@PathVariable Long id, @Valid @RequestBody TemaUpdateRequest request, @RequestHeader("Authorization") String authHeader) {
            TemaResponse tema = temaService.updateTema(id, request);
        return buildResponse("Temas actualizado exitosamente", HttpStatus.OK, tema);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> eliminarTema(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        temaService.delete(id);
        return buildResponse("Tema eliminado exitosamente", HttpStatus.OK, null);
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
