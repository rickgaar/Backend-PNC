package com.pnc.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tema")
@ToString(exclude = "materia")
@EqualsAndHashCode(exclude = "materia")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long temaId;

    @Column(name = "nombre")
    private String nombre;

    //Nombre del archivo pdf
    @Column(name = "nombreArchivo")
    private String nombreArchivo;

    @Column(name = "filePath")
    private String filePath;

    @Column(name = "visibilidad")
    private Boolean visibilidad;

    @ManyToOne
    @JoinColumn(name = "id_materia",referencedColumnName = "id")
    private Materia materia;
}
