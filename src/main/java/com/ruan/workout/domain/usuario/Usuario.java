package com.ruan.workout.domain.usuario;

import com.ruan.workout.domain.usuario.enums.RoleEnum;
import com.ruan.workout.domain.usuario.enums.StatusUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String usuario;
    private String password;
    private String name;

    @Column(unique = true)
    private String email;
    private Integer idade;
    @Column(precision = 5, scale = 2)
    private BigDecimal pesoMeta;
    @Column(precision = 5, scale = 2)
    private BigDecimal altura;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    @Enumerated(EnumType.STRING)
    private StatusUsuario status;



    public Usuario(String usuario, String password,String name, String email, Integer idade) {
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.email = email;
        this.idade = idade;
    }

    public Usuario() {
        // Construtor vazio necessário para o JPA
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
