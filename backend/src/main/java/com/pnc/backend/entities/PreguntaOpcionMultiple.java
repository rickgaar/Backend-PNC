package com.pnc.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Pregunta_opcion_multiple", schema = "public")
@ToString(exclude = {"examen", "respuestaOpcionMultipleList"})
@EqualsAndHashCode(exclude = {"examen", "respuestaOpcionMultipleList"})
public class PreguntaOpcionMultiple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "image")
    private String image;

    @Column(name = "statement")
    private String statement;

    @ManyToOne
    @JoinColumn(name = "id_exam",referencedColumnName = "id_exam")
    private  Examen examen;

    @OneToMany(mappedBy = "preguntaOpcionMultiple", orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<RespuestaOpcionMultiple> respuestaOpcionMultipleList = new ArrayList<>();
}
