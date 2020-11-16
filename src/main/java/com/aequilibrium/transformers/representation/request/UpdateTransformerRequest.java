package com.aequilibrium.transformers.representation.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateTransformerRequest {
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
