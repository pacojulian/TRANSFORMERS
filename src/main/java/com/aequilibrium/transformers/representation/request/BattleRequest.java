package com.aequilibrium.transformers.representation.request;

import lombok.Data;

import java.util.List;

@Data
public class BattleRequest {
    private List<CreateTransformersRequest> transformers;
}
