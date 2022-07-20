package com.sofka.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Object Relational Mapping (ORM)
 *
 * @version 1.0.0 2022/07/20
 * @author luis miguel russo tinjaca
 * @since 1.0.0
 */

@Data
@Entity
@Table(name = "contacto")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnt_id")
    private Long id;

    @Column(name = "cnt_nombre")
    private String name;

    @Column(name = "cnt_telefono")
    private String cellphone;

    @Column(name = "cnt_correo")
    private String email;

    @Column(name = "cnt_nacimiento")
    private String dob;

    @Column(name = "cnt_logic_delete")
    private String logicDelete;



}
