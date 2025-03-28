package com.fokou.banking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Contact extends AbstractEntity{

    private String firstname;
    private String lastname;
    private String email;
    private String iban;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
