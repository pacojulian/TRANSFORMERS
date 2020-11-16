package com.aequilibrium.transformers.representation.request;

import lombok.Data;

@Data
public class CreateTransformersRequest {
    private String name;
    private String faction;
    private Integer strength;
    private Integer intelligence;
    private Integer speed;
    private Integer endurance;
    private Integer rank;
    private Integer courage;
    private Integer firePower;
}
