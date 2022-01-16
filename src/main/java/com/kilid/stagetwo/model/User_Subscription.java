package com.kilid.stagetwo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity()
@Table(name = "User_Subscription" , schema = "userManagment")
public class User_Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer user_id;
    private Date expiry_date;

}
