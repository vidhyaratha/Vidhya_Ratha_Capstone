package org.vidhyaratha.employeeassetmanagement.model;

import lombok.*;
import jakarta.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }


}

