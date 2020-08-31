package roulette.domain;

import roulette.RouletteRepository;
import roulette.entities.BetEntity;
import roulette.entities.RouletteEntity;
import roulette.entities.Status;
import roulette.entities.UserEntity;
import roulette.mapper.Mappers;
import roulette.presentation.RequestBetOnRoulette;
import roulette.presentation.Response;

import java.util.ArrayList;
import java.util.List;

public class Bet {

    private Long id;
    private Roulette roulette;
    private User user;
    private  boolean color = true; //True=Red, False=Black
    public int betAmount = 0; //0-10000
    public int betNumber = 0; //0-36

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Roulette getRoulette () {
        return roulette;
    }

    public void setRoulette ( Roulette roulette ) {
        this.roulette = roulette;
    }

    public User getUser () {
        return user;
    }

    public void setUser ( User user ) {
        this.user = user;
    }

    public boolean isColor () {
        return color;
    }

    public void setColor ( boolean color ) {
        this.color = color;
    }

    public int getBetAmount () {
        return betAmount;
    }

    public void setBetAmount ( int betAmount ) {
        this.betAmount = betAmount;
    }

    public int getBetNumber () {
        return betNumber;
    }

    public void setBetNumber ( int betNumber ) {
        this.betNumber = betNumber;
    }

    public Response<String> betOnRoulette( RouletteRepository repository,  RequestBetOnRoulette request) {
        RouletteEntity rouletteEntity = new Roulette().getRouletteEntity(repository, request.getRouletteId());
        Response<String> response = new Response<>();

        if (rouletteEntity.getStatus().equals(Status.OPEN.name())) {
            boolean isColor = request.getBetType() == 0;
            BetEntity betEntity = new BetEntity();
            betEntity.setBetAmount(request.getRouletteBetAmount());
            if (isColor) {
                betEntity.setColor(request.getColor());
            } else {
                betEntity.setBetNumber(request.getRouletteBetNumber());
            }
            betEntity.setRouletteEntity(rouletteEntity);
            betEntity.setUserEntity(new User().createUser());
            Mappers.rouletteMapper(rouletteEntity).getListBet().add(betEntity);
            List<BetEntity> betEntities = new ArrayList<>();
            betEntities.add(betEntity);
            Mappers.rouletteMapper(rouletteEntity).setListBet(betEntities);
            repository.save(rouletteEntity);

            response.setData("Bet Done!, on roulette" + request.getRouletteId() + "Good Luck :)");

            return response;
        } else {
            response.setData("Roulette "+ request.getRouletteId() + " is not open, look for another roulette" );
            return response;
        }

    }
}
