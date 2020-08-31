package roulette.domain;

import roulette.RouletteNotFoundException;
import roulette.RouletteRepository;
import roulette.entities.BetEntity;
import roulette.entities.RouletteEntity;
import roulette.entities.Status;
import roulette.mapper.Mappers;
import roulette.presentation.Response;

import java.util.*;

public class Roulette {

    private List<BetEntity> listBetEntity = new ArrayList<>();
    private long id;
    private String status;

    public Roulette createRoulette( RouletteRepository repository ) {
        RouletteEntity rouletteEntity = new RouletteEntity();
        rouletteEntity = repository.save(rouletteEntity);
        return Mappers.rouletteMapper(rouletteEntity);
    }

    public void openRoulette(RouletteRepository repository, long id) {
        RouletteEntity rouletteEntity = getRouletteEntity(repository, id);
        rouletteEntity.setStatus(Status.OPEN.name());
        repository.save(rouletteEntity);
        Mappers.rouletteMapper(rouletteEntity);
    }

    public Response<String> closeRoulette( RouletteRepository repository, long id) {
        Response<String> response = new Response<>();
        int winnerNumber = winnerNumber();
        String winnerColor = winnerColor();
        int totalBet = 0;
        Map<Long, Boolean> map = new HashMap<>();
        RouletteEntity rouletteEntity = getRouletteEntity(repository, id);
        Roulette roulette = Mappers.rouletteMapper(rouletteEntity);
        for (BetEntity bet: roulette.getListBet()) {
            totalBet = totalBet + bet.getBetAmount();
            if (bet.getBetNumber() == winnerNumber) {
                map.put(bet.getUserEntity().getId(), true);
            }
            String color = bet.getColor() ? "Red" : "Black";
            if (color.equals(winnerColor)) {
                map.put(bet.getUserEntity().getId(), true);
            }
        }
        rouletteEntity.setStatus(Status.CLOSED.name());
        repository.save(rouletteEntity);
        Mappers.rouletteMapper(rouletteEntity);
        response.setData("Total Amount bet: "
                + totalBet+" , Winner number "
                + winnerNumber + " ,Winner color " + winnerColor +
                "Winners: " + map.toString());

        return response;
    }

    public RouletteEntity getRouletteEntity ( RouletteRepository repository, long id) {
        return repository.findById(id).orElseThrow(()
                -> new RouletteNotFoundException(id));
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

    private int winnerNumber() {
        Random random = new Random();
        return random.nextInt(37);
    }

    private String winnerColor() {
        Random random = new Random();
        return random.nextBoolean() ? "Red" : "Black";
    }
}
