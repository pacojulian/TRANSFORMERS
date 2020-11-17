package com.aequilibrium.transformers.app.controller;

import com.aequilibrium.transformers.app.resources.TransformersResource;
import com.aequilibrium.transformers.app.services.TransformersService;
import com.aequilibrium.transformers.domain.model.Transformers;
import com.aequilibrium.transformers.representation.request.BattleRequest;
import com.aequilibrium.transformers.representation.request.CreateTransformersRequest;
import com.aequilibrium.transformers.representation.request.UpdateTransformerRequest;
import com.aequilibrium.transformers.representation.response.BattleResponse;
import com.aequilibrium.transformers.representation.response.TransformersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransformerController implements TransformersResource {
    private final TransformersService service;
    @Override
    public List<Transformers> getTransformers() {
        return service.getTransformers();
    }

    @Override
    public TransformersResponse createTransformer(CreateTransformersRequest request) {
        return service.createTransformer(request);
    }

    @Override
    public TransformersResponse modifyTransformer(UpdateTransformerRequest request) {
        return service.updateTransformer(request);
    }

    @Override
    public TransformersResponse deleteTransformer(String id) {
        return service.deleteTransformer(id);
    }

    @Override
    public BattleResponse battle(BattleRequest request) {
        return service.battle(request);
    }
}
