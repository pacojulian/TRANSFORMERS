package com.aequilibrium.transformers.app.services.impl;

import com.aequilibrium.transformers.app.exceptions.NotFoundException;
import com.aequilibrium.transformers.app.exceptions.TransactionException;
import com.aequilibrium.transformers.app.exceptions.ValidationException;
import com.aequilibrium.transformers.app.services.TransformersService;
import com.aequilibrium.transformers.domain.builder.TransformersBuilder;
import com.aequilibrium.transformers.domain.model.Transformers;
import com.aequilibrium.transformers.domain.repository.TransformersRepository;
import com.aequilibrium.transformers.representation.request.BattleRequest;
import com.aequilibrium.transformers.representation.request.CreateTransformersRequest;
import com.aequilibrium.transformers.representation.request.UpdateTransformerRequest;
import com.aequilibrium.transformers.representation.response.BattleResponse;
import com.aequilibrium.transformers.representation.response.Survivors;
import com.aequilibrium.transformers.representation.response.TransformersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.aequilibrium.transformers.utils.Constants.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransformersServiceImpl implements TransformersService {
    private final TransformersBuilder builder;
    private final TransformersRepository repository;

    private int battles = 0;
    private int winsAutobots = 0;
    private int winsDecepticons = 0;

    private ArrayList<Transformers> autobots = new ArrayList<>();
    private ArrayList<Transformers> decepticons = new ArrayList<>();

    private ArrayList<Survivors> autobotsSurvivorsList = new ArrayList<>();
    private ArrayList<Survivors> decepticonsSurvivorsList = new ArrayList<>();

    @Override
    public TransformersResponse createTransformer(CreateTransformersRequest request) {
        log.info("Saving Transformer {}", request.getName());
        TransformersResponse response = new TransformersResponse();
        Transformers transformers = builder.buildTransformers(request);
        try {
            repository.save(transformers);
            response.setMessage(SUCCESS_MESSAGE);

        } catch (Exception e) {
            log.error("Transaction failed on inserting transformer");
            log.error(e.getMessage());
            throw new TransactionException(ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public TransformersResponse updateTransformer(UpdateTransformerRequest request) {
        log.info("Updating transformer {}", request.getName());
        TransformersResponse response = new TransformersResponse();
        Transformers transformers = repository.findByName(request.getName().toUpperCase());
        if(transformers == null){
            log.error("Transformer Not found");
            throw new NotFoundException(TRANSFORMER_NOT_FOUND);
        }
         transformers = builder.updateTransformer(request, transformers);
        try {
            repository.save(transformers);
            response.setMessage(SUCCESS_MESSAGE);

        } catch (Exception e) {
            log.error("Transaction failed on updating transformer");
            log.error(e.getMessage());
            throw new TransactionException(ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public TransformersResponse deleteTransformer(String name) {
        log.info("Deleting  transformer {}", name);
        TransformersResponse response = new TransformersResponse();
        Transformers transformers = repository.findByName(name);
        if(transformers == null){
            throw new NotFoundException(TRANSFORMER_NOT_FOUND);
        }
        try {
            repository.delete(transformers);
            response.setMessage(DELETE_SUCCESS);
        }catch (Exception e){
            log.error("Error Deleting transformer");
            log.error(e.getMessage());
            throw new TransactionException(ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public BattleResponse battle(BattleRequest request) {
        sortRequest(request);
        for(int i=0; i< decepticons.size(); i++){
            if(decepticons.get(i).getName().equals(PREDA_KING) && autobots.get(i).getName().equals(OPTIMUS_PRIME)){
                throw new ValidationException(GODS_FIGHT);
            }else if (autobots.get(i).getName().equals(OPTIMUS_PRIME)){
                winsAutobots(decepticons.get(i),i);
                break;
            }else if (decepticons.get(i).getName().equals(PREDA_KING)){
                winsDecepticons(autobots.get(i),i);
                break;
            }
            if(decepticons.get(i).getSkill() >= autobots.get(i).getSkill() + 3){
                winsDecepticons(autobots.get(i),i);
                break;
            }else if(decepticons.get(i).getSkill().equals(autobots.get(i).getSkill())){
                battles ++;
            }else if(decepticons.get(i).getSkill() +3 >= autobots.get(i).getSkill()){
                winsAutobots(decepticons.get(i),i);
                break;
            }
            if(decepticons.get(i).getCourage() >= autobots.get(i).getCourage() + 4 &&
                    decepticons.get(i).getStrength() >= autobots.get(i).getStrength() + 3){
                winsDecepticons(autobots.get(i),i);
                break;

            }else if(decepticons.get(i).getCourage() + 4 >= autobots.get(i).getCourage() &&
                    decepticons.get(i).getStrength() + 3 >= autobots.get(i).getStrength()){
                winsAutobots(decepticons.get(i),i);
                break;
            }
        }
        BattleResponse response = buildResponse();
        freeMemory();
        return response;
    }

    @Override
    public List<Transformers> getTransformers() {
        log.info("Obtaining all transformers");
        return repository.findAll();
    }

    private void sortRequest(BattleRequest request){
        request.getTransformers().forEach(p ->{
            Transformers transformers = builder.buildTransformers(p);
            try {
                repository.save(transformers);
            }catch (Exception e){
                log.error(e.getMessage());
                throw new TransactionException(ERROR_MESSAGE);
            }
            if(p.getFaction().equals(AUTOBOTS)){
                autobots.add(transformers);
            }else{
                decepticons.add(transformers);
            }

        });
        //Sort Arrays of autoBots and decepticons
        autobots.sort(Comparator.comparingInt(Transformers::getRank));
        decepticons.sort(Comparator.comparingInt(Transformers::getRank));
        //fight 1v1
        if(autobots.isEmpty() || decepticons.isEmpty()){
            throw new ValidationException("There Where no battles");
        }
    }
    private void winsAutobots(Transformers decepticonsModel,int i){
        winsAutobots ++;
        battles ++;
        decepticons.remove(i);
        decepticonsModel.setAlive(true);
        repository.save(decepticonsModel);
    }
    
    private void winsDecepticons(Transformers autobotsModel,int i){
        winsDecepticons ++;
        battles ++;
        autobots.remove(i);
        autobotsModel.setAlive(true);
        repository.save(autobotsModel);
    }

    private BattleResponse buildResponse(){
        BattleResponse response = new BattleResponse();
        response.setBattles(battles);
        if(winsAutobots > winsDecepticons){
            response.setWinner(AUTOBOTS);
        }else {
            response.setWinner(DECEPTICONS);
        }

        autobots.forEach(a ->{
            Survivors survivorsAutobots = new Survivors();
            survivorsAutobots.setName(a.getName());
            autobotsSurvivorsList.add(survivorsAutobots);

        });
        decepticons.forEach(d ->{
            Survivors survivorsDecepticons = new Survivors();
            survivorsDecepticons.setName(d.getName());
            decepticonsSurvivorsList.add(survivorsDecepticons);
        });
        response.setSurvivorsAutobots(autobotsSurvivorsList);
        response.setSurvivorsDecepticons(decepticonsSurvivorsList);
        return response;
    }
    private void freeMemory(){
        battles = 0;
        winsAutobots = 0;
        winsDecepticons = 0;
        autobots = new ArrayList<>();
        decepticons = new ArrayList<>();
        autobotsSurvivorsList = new ArrayList<>();
        decepticonsSurvivorsList = new ArrayList<>();
    }
}
