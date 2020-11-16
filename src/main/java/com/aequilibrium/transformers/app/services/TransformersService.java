package com.aequilibrium.transformers.app.services;

import com.aequilibrium.transformers.representation.request.BattleRequest;
import com.aequilibrium.transformers.representation.request.CreateTransformersRequest;
import com.aequilibrium.transformers.representation.request.UpdateTransformerRequest;
import com.aequilibrium.transformers.representation.response.BattleResponse;
import com.aequilibrium.transformers.representation.response.TransformersResponse;

public interface TransformersService {

    /**
     *
     * @param request To create a transformer
     * @return code operation if the creation was successfully
     */
    TransformersResponse createTransformer(CreateTransformersRequest request);

    /**
     *
     * @param request to update a transformer
     * @return code operation if the update was successfully
     */
    TransformersResponse updateTransformer(UpdateTransformerRequest request);

    /**
     *
     * @param name fo the transformer to delete
     * @return code operation if the delete of the transformer was successfully
     */
    TransformersResponse deleteTransformer(String name);

    /**
     * @param  request of a battle
     * @return battle status.
     */
    BattleResponse battle(BattleRequest request);
}
