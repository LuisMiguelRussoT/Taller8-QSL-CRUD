package com.sofka.dao;

import com.sofka.domain.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactDao extends CrudRepository<Contact, Long> {

    @Modifying
    @Query("update Contact cnt set cnt.name = :name where cnt.id = :id")
    public void updateName(
            @Param(value = "id") Long id,
            @Param(value = "name") String name
    );

    @Modifying
    @Query("update Contact cnt set cnt.cellphone = :cellphone where cnt.id = :id")
    public void updateCellphone(
            @Param(value = "id") Long id,
            @Param(value = "cellphone") String cellphone
    );

    @Modifying
    @Query("update Contact cnt set cnt.email = :email where cnt.id = :id")
    public void updateEmail(
            @Param(value = "id") Long id,
            @Param(value = "email") String email
    );


    @Modifying
    @Query("update Contact cnt set cnt.dob = :dob where cnt.id = :id")
    public void updateDob(
            @Param(value = "id") Long id,
            @Param(value = "dob") String dob
    );

    @Modifying
    @Query("update Contact cnt set cnt.logicDelete = :logicDelete where cnt.id = :id")
    public void logicDelete(
            @Param(value = "id") Long id,
            @Param(value = "logicDelete") String logicDelete
    );



}
