package com.example.demojpa;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity(name = "t_sys_user")
public class User implements Serializable {

    @Id
    @Column
    private Long id;


    @Column
    private String username;
}