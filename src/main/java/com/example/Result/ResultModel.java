package com.example.Result;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name="result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String city;
    private String country;
    private String pincode;
    private int satScore;
    private boolean passed;


}
