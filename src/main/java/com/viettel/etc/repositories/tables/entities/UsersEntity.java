package com.viettel.etc.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Action_log
 *
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UsersEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "PHONE")
    String phone;

    @Column(name = "FULL_NAME")
    String fullName;

    @Column(name = "AVATAR")
    String avatar;

    @JsonIgnore
    @Column(name = "PASS")
    String pass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}