package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Doctors
 *
 * @author ToolGen
 * @date Thu Sep 23 08:19:38 ICT 2021
 */
@Repository
public interface UsersRepositoryJPA extends JpaRepository<UsersEntity, Integer> {
    @Query(value = "SELECT * FROM USERS WHERE PHONE = :phone AND PASS = :pass AND IS_ACTIVE =1 ", nativeQuery = true)
    UsersEntity getUserByPhoneAndPass(String phone,String pass);

    @Query(value = "SELECT * FROM USERS WHERE PHONE = :phone ", nativeQuery = true)
    UsersEntity getUserByPhone(String phone);

    UsersEntity getByPhoneAndIsActive(String phone,Integer isActive);

    UsersEntity getByIdAndIsActive(Integer id,Integer isActive);

}