package com.pnc.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "materias")
@EqualsAndHashCode(exclude = "materias")
@Table(name = "usuario", schema = "public")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "token")
    private String token;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role rol;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Materia> materias = new HashSet<>();

    @OneToMany(mappedBy = "usuario",orphanRemoval = true,cascade = CascadeType.REMOVE)
    private Set<UserXExam> attempts = new HashSet<>();
}
