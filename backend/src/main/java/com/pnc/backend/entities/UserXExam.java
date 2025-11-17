package com.pnc.backend.entities;

import com.pnc.backend.utils.id.UserExamId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuarioxexamen", schema = "public")
@ToString(exclude = {"usuario", "examen"})
@EqualsAndHashCode(exclude = {"usuario", "examen"})
public class UserXExam {

        @EmbeddedId
        private UserExamId id;

        @ManyToOne
        @MapsId("userId")
        @JoinColumn(name = "id_usuario")
        private Usuario usuario;

        @ManyToOne
        @MapsId("examId")
        @JoinColumn(name = "id_examen")
        private Examen examen;

        @Column(name = "Date_hour_begin")
        private LocalDateTime DateHourBegin;

        @Column(name = "Date_hour_end")
        private LocalDateTime DateHourEnd;

        @Column(name="calificacion")
        private Float calificacion;
}
