package com.mappy.accountservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
}
