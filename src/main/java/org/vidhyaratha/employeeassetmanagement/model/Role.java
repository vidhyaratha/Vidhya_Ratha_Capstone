package org.vidhyaratha.employeeassetmanagement.model;

import lombok.*;
import jakarta.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "role")
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "role")
    private String name;

    public Role(String role) {
        this.name = name;
    }


}

