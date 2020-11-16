package com.aequilibrium.transformers.representation.response;

import lombok.Data;

import java.util.List;

@Data
public class BattleResponse {
    private String winner;
    private int battles;
    private List<Survivors> survivorsAutobots;
    private List<Survivors> survivorsDecepticons;

}
