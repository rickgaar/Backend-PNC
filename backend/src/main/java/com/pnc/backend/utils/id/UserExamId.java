package com.pnc.backend.utils.id;

import com.pnc.backend.entities.Examen;
import com.pnc.backend.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserExamId implements Serializable {
    @Column(name = "id_usuario")
    private Long userId;

    @Column(name = "id_examen")
    private Long examId;
}
