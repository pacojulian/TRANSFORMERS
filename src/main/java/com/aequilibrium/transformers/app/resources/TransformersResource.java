package com.aequilibrium.transformers.app.resources;

import com.aequilibrium.transformers.representation.request.BattleRequest;
import com.aequilibrium.transformers.representation.request.CreateTransformersRequest;
import com.aequilibrium.transformers.representation.request.UpdateTransformerRequest;
import com.aequilibrium.transformers.representation.response.BattleResponse;
import com.aequilibrium.transformers.representation.response.TransformersResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@Api(value = "Transformers Service")
public interface TransformersResource {

    /**
     *
     * @param id of the transformer
     * @return Transformer attributes
     */
    @ApiOperation(value = "Get Transformers description", response = TransformersResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/transformers")
    TransformersResponse getTransformerById(@RequestParam("id") String id);

    /**
     *
     * @param request to create a transformer
     * @return api operation if successful
     */
    @ApiOperation(value = "Create a transformer", response = TransformersResponse.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(value = "/transformers")
    TransformersResponse createTransformer(@RequestBody CreateTransformersRequest request);

    /**
     *
     * @param request to Update the transformer Information.
     * @return transformer api operation
     */
    @ApiOperation(value = "Update Transformer information", response = TransformersResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping(value = "/transformers")
    TransformersResponse modifyTransformer(@RequestBody UpdateTransformerRequest request);

    /**
     *
     * @param id of the transformer
     * @return  api operation of the transformer
     */
    @ApiOperation(value = "Delete Transformer by an ID", response = TransformersResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping(value = "/transformers")
    TransformersResponse deleteTransformer(@RequestParam("id") String id);

    /**
     *
     * @param request of a battle
     * @return the number of battles
     */
    @ApiOperation(value = "Battle of Tranformers", response = BattleResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(value = "/transformers/battle")
    BattleResponse battle(@RequestBody BattleRequest request);

}
