package roulette.domain;

import roulette.RouletteRepository;
import roulette.entities.BetEntity;
import roulette.entities.RouletteEntity;
import roulette.mapper.Mappers;

import java.util.ArrayList;
import java.util.List;

public class Roulette {

    private List<BetEntity> listBetEntity = new ArrayList<>();
    private long id;
    private String status;

    public Roulette createRoulette( RouletteRepository repository ) {
        RouletteEntity rouletteEntity = new RouletteEntity();
        rouletteEntity = repository.save(rouletteEntity);
        return Mappers.rouletteMapper(rouletteEntity);
    }

    public Roulette getRoulette (RouletteRepository repository, long id) {
        repository.findById(id).get();
        RouletteEntity roulette = repository.findById(id).get();

        return Mappers.rouletteMapper(repository.findById(id).get());
    }

    public List<BetEntity> getListBet () {
        return listBetEntity;
    }

    public void setListBet ( List<BetEntity> listBetEntity ) {
        this.listBetEntity = listBetEntity;
    }

    public long getId () {
        return id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus ( String status ) {
        this.status = status;
    }
}
