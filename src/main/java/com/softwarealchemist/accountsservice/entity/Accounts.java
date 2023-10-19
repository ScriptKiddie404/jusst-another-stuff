package com.softwarealchemist.accountsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;

}
