package com.aequilibrium.transformers;

import com.aequilibrium.transformers.app.exceptions.NotFoundException;
import com.aequilibrium.transformers.app.exceptions.ValidationException;
import com.aequilibrium.transformers.app.services.impl.TransformersServiceImpl;
import com.aequilibrium.transformers.domain.builder.TransformersBuilder;
import com.aequilibrium.transformers.domain.repository.TransformersRepository;
import com.aequilibrium.transformers.representation.request.BattleRequest;
import com.aequilibrium.transformers.representation.request.CreateTransformersRequest;
import com.aequilibrium.transformers.representation.request.UpdateTransformerRequest;
import com.aequilibrium.transformers.representation.response.BattleResponse;
import com.aequilibrium.transformers.representation.response.TransformersResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.aequilibrium.transformers.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TransformersApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private TransformersRepository repository;

    @Autowired
    TransformersBuilder builder;

    @Autowired
    TransformersServiceImpl transformersService = new TransformersServiceImpl(builder,repository);


    @Test
     void testCreateTransformer(){
        CreateTransformersRequest request = createTransformersRequest();

        TransformersResponse response = transformersService.createTransformer(request);
        Assert.notNull(response,"Empty Response");
    }

    @Test
    void updateTransformer(){
        UpdateTransformerRequest request = updateTransformerRequest();
        TransformersResponse response = transformersService.updateTransformer(request);
        Assert.notNull(response,"Invalid Request");
    }

    @Test
    void testUpdateTransformerException(){
        UpdateTransformerRequest request = updateTransformerRequest();
        request.setName("ARCEE");
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            transformersService.updateTransformer(request);
        });
        Assert.hasText(TRANSFORMER_NOT_FOUND,exception.getMessage());

    }

    @Test
    void TestDelete(){
        TransformersResponse response = transformersService.deleteTransformer("IRONHIDE");
        Assert.notNull(response,"Invalid Name");
    }

    @Test
    void TestDeleteNotFound(){
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            transformersService.deleteTransformer("RATCHET");
        });
        Assert.hasText(TRANSFORMER_NOT_FOUND,exception.getMessage());
    }

    @Test
    void TestBattle(){
        BattleRequest request = battleRequestValid();
        BattleResponse response = transformersService.battle(request);
        Assert.hasText(response.getWinner(),DECEPTICONS);
    }

    @Test
    void TestBattleException(){
        BattleRequest request = battleRequestGods();
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            transformersService.battle(request);
        });
        Assert.hasText(GODS_FIGHT,exception.getMessage());
    }

    public CreateTransformersRequest createTransformersRequest(){
        CreateTransformersRequest request = new CreateTransformersRequest();
        request.setCourage(2);
        request.setName("OPTIMUS");
        request.setFaction("AUTOBOTS");
        request.setStrength(8);
        request.setIntelligence(8);
        request.setSpeed(8);
        request.setEndurance(8);
        request.setRank(8);
        request.setCourage(8);
        request.setFirePower(8);
        return request;
    }

    public UpdateTransformerRequest updateTransformerRequest(){
        UpdateTransformerRequest request = new UpdateTransformerRequest();
        request.setCourage(2);
        request.setName("JAZZ");
        request.setFaction("AUTOBOTS");
        request.setStrength(8);
        request.setIntelligence(8);
        request.setSpeed(8);
        request.setEndurance(8);
        request.setRank(8);
        request.setCourage(8);
        request.setFirePower(8);
        return request;

    }

    public BattleRequest battleRequestValid(){
        BattleRequest request = new BattleRequest();
        List<CreateTransformersRequest> transformers = new ArrayList<>();
        CreateTransformersRequest request1 = new CreateTransformersRequest();
        CreateTransformersRequest request2 = new CreateTransformersRequest();
        CreateTransformersRequest request3 = new CreateTransformersRequest();

        request1.setName("SOUNDWAVE");
        request1.setCourage(8);
        request1.setFaction("DECEPTICONS");
        request1.setStrength(9);
        request1.setIntelligence(2);
        request1.setSpeed(6);
        request1.setEndurance(7);
        request1.setRank(5);
        request1.setCourage(6);
        request1.setFirePower(10);

        request2.setCourage(2);
        request2.setName("BLUESTREAK");
        request2.setFaction("AUTOBOTS");
        request2.setStrength(2);
        request2.setIntelligence(2);
        request2.setSpeed(2);
        request2.setEndurance(2);
        request2.setRank(1);
        request2.setCourage(2);
        request2.setFirePower(2);

        request3.setCourage(4);
        request3.setName("HUBCAP");
        request3.setFaction("AUTOBOTS");
        request3.setStrength(4);
        request3.setIntelligence(4);
        request3.setSpeed(4);
        request3.setEndurance(4);
        request3.setRank(4);
        request3.setCourage(4);
        request3.setFirePower(4);

        transformers.add(request1);
        transformers.add(request2);
        transformers.add(request3);
        request.setTransformers(transformers);

        return request;
    }

    public BattleRequest battleRequestGods(){
        BattleRequest request = new BattleRequest();
        List<CreateTransformersRequest> transformers = new ArrayList<>();
        CreateTransformersRequest request1 = new CreateTransformersRequest();
        CreateTransformersRequest request2 = new CreateTransformersRequest();

        request1.setName(PREDA_KING);
        request1.setCourage(8);
        request1.setFaction("DECEPTICONS");
        request1.setStrength(9);
        request1.setIntelligence(2);
        request1.setSpeed(6);
        request1.setEndurance(7);
        request1.setRank(5);
        request1.setCourage(6);
        request1.setFirePower(10);

        request2.setCourage(2);
        request2.setName(OPTIMUS_PRIME);
        request2.setFaction("AUTOBOTS");
        request2.setStrength(2);
        request2.setIntelligence(2);
        request2.setSpeed(2);
        request2.setEndurance(2);
        request2.setRank(1);
        request2.setCourage(2);
        request2.setFirePower(2);

        transformers.add(request1);
        transformers.add(request2);
        request.setTransformers(transformers);

        return request;
    }
}
