package com.osorio.springboot.backend.apirest.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter


@Entity(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email", nullable = false , unique = true )
    private String email;

    @Column(name = "create_at")
    private LocalDate createAt;

    @PrePersist
    public void saveFecha(){
        this.setCreateAt(LocalDate.now());
    }

}
