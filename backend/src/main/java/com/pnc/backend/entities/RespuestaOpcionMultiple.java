package com.pnc.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Respuesta_opcion_multiple", schema = "public")
public class RespuestaOpcionMultiple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "correct")
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "id_pregunta_opcion_multiple",referencedColumnName = "id")
    private  PreguntaOpcionMultiple preguntaOpcionMultiple;
}
