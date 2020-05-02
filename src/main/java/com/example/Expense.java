package com.example;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity
public class Expense {
    private String userId;
    private String name;
    private int cost;
    private String note;
    private String dateOfPurchase;
    private @Id @GeneratedValue Long id;

    public Expense() {
        System.out.println("generated Id is: " + id);
    }
}
