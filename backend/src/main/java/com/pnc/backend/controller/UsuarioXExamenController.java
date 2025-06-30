package com.pnc.backend.controller;

import com.pnc.backend.dto.GeneralResponse;
import com.pnc.backend.dto.request.examen.ExamenRequest;
import com.pnc.backend.dto.request.examen.ExamenUpdateRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamUpdateRequest;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.exceptions.UserNotTheSameException;
import com.pnc.backend.security.JwtTokenProvider;
import com.pnc.backend.service.ExamenService;
import com.pnc.backend.service.UsuarioService;
import com.pnc.backend.service.impl.UserXExamServiceImpl;
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
@RequestMapping("/api/usuarioxexamen")
public class UsuarioXExamenController {
    private final UserXExamServiceImpl userXExamService;
    private final UsuarioService usuarioService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsuarioXExamenController(UserXExamServiceImpl userXExamService, UsuarioService usuarioService, JwtTokenProvider jwtTokenProvider) {
        this.userXExamService = userXExamService;
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping()
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> saveUserXExam(@RequestBody @Valid UserXExamRequest userXExamRequest,@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String usernameOrEmail = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.findByUsernameOrEmail(usernameOrEmail);
        if(response.getRol().equals("usuario") &&response.getUsuarioId()!=userXExamRequest.getUserId()){
            throw new UserNotTheSameException("No puedes iniciar los examenes de otros estudiantes");
        }
        return buildResponse("Exam begin", HttpStatus.CREATED, userXExamService.save(userXExamRequest));
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> updateUserXExam(@RequestBody @Valid UserXExamUpdateRequest userXExamUpdateRequest,@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String usernameOrEmail = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.findByUsernameOrEmail(usernameOrEmail);
        if(response.getRol().equals("usuario") &&response.getUsuarioId()!=userXExamUpdateRequest.getUserId()){
            throw new UserNotTheSameException("No puedes terminar los examenes de otros estudiantes");
        }
        return buildResponse("Exam End", HttpStatus.OK, userXExamService.update(userXExamUpdateRequest));
    }

    @GetMapping("/{idMateria}/{idUsuario}/{idExamen}")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getUserExamMateria(@PathVariable Long idMateria,@PathVariable Long idUsuario,@PathVariable Long idExamen,@RequestHeader("Authorization") String authHeader ) {
        String token = authHeader.replace("Bearer ", "");
        String usernameOrEmail = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.findByUsernameOrEmail(usernameOrEmail);
        if(response.getRol().equals("usuario") &&response.getUsuarioId()!=idUsuario){
            throw new UserNotTheSameException("No puedes ver los examenes de otros estudiantes");
        }
        return buildResponse("Exam found", HttpStatus.OK, userXExamService.findByUsuarioIdAndExamenMateriaIdAndExamenId(idUsuario,idMateria,idExamen));
    }
    @GetMapping("/{idMateria}/{idUsuario}")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> getUserMateria(@PathVariable Long idMateria,@PathVariable Long idUsuario,@RequestHeader("Authorization") String authHeader ) {
        String token = authHeader.replace("Bearer ", "");
        String usernameOrEmail = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.findByUsernameOrEmail(usernameOrEmail);
        if(response.getRol().equals("usuario")  && response.getUsuarioId()!=idUsuario){
            throw new UserNotTheSameException("No puedes ver los examenes de otros estudiantes");
        }
        return buildResponse("Exams found", HttpStatus.OK, userXExamService.findByUsuarioIdAndExamenMateriaId(idUsuario,idMateria));
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
