package com.alura.foro.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "User")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String userName;
    private String name;
    private String password;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario){
        this.userName= datosRegistroUsuario.userName();
        this.name = datosRegistroUsuario.name();
        this.password = datosRegistroUsuario.password();
    }
    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario){
        if(datosActualizarUsuario.name() != null){
            this.name = datosActualizarUsuario.name();
        }
        if(datosActualizarUsuario.userName() != null){
            this.userName = datosActualizarUsuario.name();
        }
        if(datosActualizarUsuario.password() != null){
            this.password = datosActualizarUsuario.name();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return userName;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    public Long getId() {
        return id;
    }


    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
