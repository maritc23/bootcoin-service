package com.nttdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BootCoinTransaction {

    private String id;

    private double bcAmount;
    private double price;

    private String status;

    private String buyer;
    private String seller;
}
