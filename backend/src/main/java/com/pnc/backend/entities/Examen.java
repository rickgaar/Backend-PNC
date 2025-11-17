package com.pnc.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "examen", schema = "public")
@ToString(exclude = {"preguntaOpcionMultipleList", "attempts"})
@EqualsAndHashCode(exclude = {"preguntaOpcionMultipleList", "attempts"})
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exam")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "visibility")
    private Boolean isVisible;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "Date_hour_begin")
    private LocalDateTime DateHourBegin;

    @Column(name = "Date_hour_end")
    private LocalDateTime DateHourEnd;

    @ManyToOne
    @JoinColumn(name = "id_materia",referencedColumnName = "id")
    private Materia materia;

    @OneToMany(mappedBy = "examen",orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<PreguntaOpcionMultiple> preguntaOpcionMultipleList = new ArrayList<>();

    @OneToMany(mappedBy = "examen",orphanRemoval = true,cascade = CascadeType.REMOVE)
    private Set<UserXExam> attempts = new HashSet<>();
}
