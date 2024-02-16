package com.example.demo.entitites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<PDF> pdfs;

}
