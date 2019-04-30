package com.example.demojpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


//@ApiModel(value = "实体表")
@Data
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_sys_user")
@Where(clause = "is_delete != 1")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.example.demojpa.User")
public class User implements Serializable {

    @Id
    @Column
    private Long id;


    @Column
    private String username;
}