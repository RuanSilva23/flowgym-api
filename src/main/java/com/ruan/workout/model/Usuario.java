package com.ruan.workout.model;

import com.ruan.workout.model.Enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String password;
    private String name;
    private String email;
    private Integer idade;
    @Column(precision = 5, scale = 2)
    private BigDecimal pesoMeta;
    @Column(precision = 5, scale = 2)
    private BigDecimal altura;
    private RoleEnum role;
    private LocalDateTime dataCadastro;



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

}
