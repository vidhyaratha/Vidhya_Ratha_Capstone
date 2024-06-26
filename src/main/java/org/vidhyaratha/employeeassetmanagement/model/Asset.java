package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "assets")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assetId")
    private String assetId;

    @Column(name = "assetType")
    private String assetType;

    @Column(name = "assetName")
    private String assetName;

    @Column(name = "status")
    private String status;

    @Column(name = "assetCreatedDate")
    private String assetCreatedDate;

}
