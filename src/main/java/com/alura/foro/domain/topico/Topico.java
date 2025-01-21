package com.alura.foro.domain.topico;

import com.alura.foro.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Topic")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Column(name = "cursename")
    private String curseName;
    private String title;
    @Column(name = "creationdate")
    private LocalDateTime creationDate = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idautor",referencedColumnName = "id")
    private Usuario user;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario usuario){
        this.message = datosRegistroTopico.message();
        this.title = datosRegistroTopico.title();
        this.curseName = datosRegistroTopico.curseName();
        this.user = usuario;
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico){
        if(datosActualizarTopico.message() != null){
            this.message = datosActualizarTopico.message();
        }

        if(datosActualizarTopico.title() != null){
            this.title = datosActualizarTopico.title();
        }

        if(datosActualizarTopico.curseName() != null){
            this.curseName = datosActualizarTopico.curseName();
        }
    }

}
