package com.aequilibrium.transformers.domain.builder;

import com.aequilibrium.transformers.domain.model.Transformers;
import com.aequilibrium.transformers.representation.request.CreateTransformersRequest;
import com.aequilibrium.transformers.representation.request.UpdateTransformerRequest;
import org.springframework.stereotype.Component;

@Component
public class TransformersBuilder {
    public Transformers buildTransformers(CreateTransformersRequest request){
        Transformers transformers = new Transformers();
        transformers.setStrength(request.getStrength());
        transformers.setIntelligence(request.getIntelligence());
        transformers.setSpeed(request.getSpeed());
        transformers.setEndurance(request.getEndurance());
        transformers.setCourage(request.getCourage());
        transformers.setFirePower(request.getFirePower());
        transformers.setSkill(getSkill(request));
        transformers.setName(request.getName());
        transformers.setRank(request.getRank());
        transformers.setFaction(request.getFaction().toUpperCase());
        return transformers;
    }

    public Transformers updateTransformer(UpdateTransformerRequest request, Transformers transformersUpdate){
        transformersUpdate.setStrength(request.getStrength()!= null? request.getStrength():transformersUpdate.getStrength());
        transformersUpdate.setIntelligence(request.getIntelligence() != null? request.getIntelligence():transformersUpdate.getIntelligence());
        transformersUpdate.setSpeed(request.getSpeed()!= null? request.getSpeed():transformersUpdate.getSpeed());
        transformersUpdate.setEndurance(request.getEndurance()!= null? request.getEndurance():transformersUpdate.getEndurance());
        transformersUpdate.setCourage(request.getCourage()!= null? request.getCourage():transformersUpdate.getCourage());
        transformersUpdate.setFirePower(request.getFirePower()!= null? request.getFirePower():transformersUpdate.getFirePower());
        transformersUpdate.setSkill(updateSkill(request, transformersUpdate));
        return transformersUpdate;
    }

    private Integer getSkill(CreateTransformersRequest request){
        return request.getStrength() + request.getIntelligence() + request.getSpeed() + request.getEndurance()+
                request.getFirePower();
    }

    private Integer updateSkill(UpdateTransformerRequest request, Transformers transformersUpdate){
        Integer strength = request.getStrength() != null? request.getStrength(): transformersUpdate.getStrength();
        Integer intelligence = request.getIntelligence() != null? request.getIntelligence(): transformersUpdate.getIntelligence();
        Integer speed = request.getSpeed() != null? request.getSpeed(): transformersUpdate.getSpeed();
        Integer endurance = request.getEndurance() != null? request.getEndurance(): transformersUpdate.getEndurance();
        Integer firePower = request.getFirePower() != null? request.getFirePower(): transformersUpdate.getFirePower();
        return strength + intelligence +speed+endurance + firePower;
    }
}
