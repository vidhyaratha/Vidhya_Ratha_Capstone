package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "employeeassets")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAssets {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assetAssignedDate")
    private String assetAssignedDate;

    @Column(name = "approvedAdminName")
    private String approvedAdminName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empId", referencedColumnName = "empId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "assetId", referencedColumnName = "assetId"),
            @JoinColumn(name = "assetName", referencedColumnName = "assetName"),
            @JoinColumn(name = "assetType", referencedColumnName = "assetType")
    })
    private Asset asset;

}
