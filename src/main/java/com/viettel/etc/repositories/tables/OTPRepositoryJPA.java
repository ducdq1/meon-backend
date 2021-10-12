package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.OTPEntity;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
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
public interface OTPRepositoryJPA extends JpaRepository<OTPEntity, Integer> {
    OTPEntity getByPhoneAndIsActive(String phone, Integer isActive);
    OTPEntity getByPhoneAndOtpAndIsActive(String phone, String otp, Integer isActive);
}