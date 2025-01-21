package com.alura.foro.domain.soluciones;


import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Answer")
@Entity(name = "Solucion")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Solucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    @Column(name = "creationdate")
    private LocalDateTime creationDate = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idautor",referencedColumnName = "id")
    private Usuario user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtopico",referencedColumnName = "id")
    private Topico topico;

    public Solucion(DatosRegistroSolucion datosRegistroSolucion, Usuario user, Topico topico){
        this.answer = datosRegistroSolucion.answer();
        this.user = user;
        this.topico = topico;
    }

    public void actualizarAnswer(DatosActualizarSolucion datosActualizarSolucion){
        if(datosActualizarSolucion.answer() != null){
            this.answer = datosActualizarSolucion.answer();
        }
    }


}
