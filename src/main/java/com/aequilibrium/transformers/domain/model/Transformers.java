package com.aequilibrium.transformers.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TRANSFORMERS")
@Getter
@Setter
public class Transformers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TRANSFORMER")
    private Integer idTransformer;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FACTION")
    private String faction;

    @Column(name = "STRENGTH")
    private Integer strength;

    @Column(name = "INTELLIGENCE")
    private Integer intelligence;

    @Column(name = "SPEED")
    private Integer speed;

    @Column(name = "ENDURANCE")
    private Integer endurance;

    @Column(name = "RANK")
    private Integer rank;

    @Column(name = "COURAGE")
    private Integer courage;

    @Column(name = "FIREPOWER")
    private Integer firePower;

    @Column(name = "SKILL")
    private Integer skill;
}
