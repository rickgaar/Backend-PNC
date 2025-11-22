package com.pnc.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"usuarios", "exams", "temas"})
@EqualsAndHashCode(exclude = {"usuarios", "exams", "temas"})
@Table(name = "materia", schema = "public")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String nombre;

    @Column(name = "visibility")
    private boolean visible;

    @Column(name = "image")
    private String imagen;

    @OneToMany(mappedBy = "materia",orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<Examen> exams = new ArrayList<>();

    @OneToMany(mappedBy = "materia",orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<Tema> temas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "usuario_materia",
        joinColumns = @JoinColumn(name = "materia_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;



}
