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
    private double cost;
    private String note;
    private String dateOfPurchase;
    private @Id @GeneratedValue Long id;

    public Expense() {
        System.out.println("generated Id is: " + id);
    }

    public String getAttributes() {
        String output = "userId VARCHAR (50), name VARCHAR (50), cost DOUBLE PRECISION, note VARCHAR(50), dateOfPurchase VARCHAR(50)," +
                "id integer PRIMARY KEY";
        return output;
    }
}
