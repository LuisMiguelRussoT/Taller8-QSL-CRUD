package com.sofka.dao;

import com.sofka.domain.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Data Access Object (DAO)
 *
 * @version 1.0.0 2022/07/20
 * @author luis miguel russo tinjaca
 * @since 1.0.0
 */

public interface ContactDao extends CrudRepository<Contact, Long> {

    /**
     * Update name
     * @param id Long
     * @param name String
     */

    @Modifying
    @Query("update Contact cnt set cnt.name = :name where cnt.id = :id")
    public void updateName(
            @Param(value = "id") Long id,
            @Param(value = "name") String name
    );

    /**
     * Update lastname
     * @param id Long
     * @param cellphone String
     */

    @Modifying
    @Query("update Contact cnt set cnt.cellphone = :cellphone where cnt.id = :id")
    public void updateCellphone(
            @Param(value = "id") Long id,
            @Param(value = "cellphone") String cellphone
    );

    /**
     * Update phone
     * @param id Long
     * @param email String
     */

    @Modifying
    @Query("update Contact cnt set cnt.email = :email where cnt.id = :id")
    public void updateEmail(
            @Param(value = "id") Long id,
            @Param(value = "email") String email
    );

    /**
     * Update email
     * @param id Long
     * @param dob String
     */

    @Modifying
    @Query("update Contact cnt set cnt.dob = :dob where cnt.id = :id")
    public void updateDob(
            @Param(value = "id") Long id,
            @Param(value = "dob") String dob
    );

    /**
     * Update email
     * @param id Long
     * @param logicDelete String
     */

    @Modifying
    @Query("update Contact cnt set cnt.logicDelete = :logicDelete where cnt.id = :id")
    public void logicDelete(
            @Param(value = "id") Long id,
            @Param(value = "logicDelete") String logicDelete
    );



}
