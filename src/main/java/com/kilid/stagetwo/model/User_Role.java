package com.kilid.stagetwo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "User_Role" , schema = "userManagment")
@NoArgsConstructor
@AllArgsConstructor
public class User_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer user_id;
    private String  role;


}
