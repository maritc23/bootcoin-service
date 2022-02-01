package com.nttdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection="BootCoinUser")
public class BootCoinUser {
    @Id
    private String id= UUID.randomUUID().toString();

    private String identification; //DNI, CEX or Passport.
    private String phone;
    private String email;

    private double amount;
    private double money;

}
