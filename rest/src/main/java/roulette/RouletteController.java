package roulette;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import roulette.domain.Bet;
import roulette.domain.Roulette;
import roulette.entities.Result;
import roulette.entities.RouletteEntity;
import roulette.mapper.Mappers;
import roulette.presentation.RequestBetOnRoulette;
import roulette.presentation.Response;

@RestController
class RouletteController {

    private final RouletteRepository repository;

    RouletteController ( RouletteRepository repository ) {
        this.repository = repository;
    }

    @PostMapping("/createRoulette")
    public Response<Long> getId () {
        Response<Long> response = new Response<>();
        response.setData(new Roulette().createRoulette(repository).getId());
        return response;
    }

    @PostMapping("/openRoulette")
    public Response<String> openRoulette ( @RequestParam long id ) {
        new Roulette().openRoulette(repository, id);
        Response<String> response = new Response<>();
        response.setData(Result.Succeed.name() + id);
        return response;
    }


    @PostMapping("/betOnRoulette")
    public Response<String> betOnRoulette (
            @RequestBody RequestBetOnRoulette request,
            @RequestHeader long userId
    ) {
        return new Bet().betOnRoulette(repository, request);
    }

    @PostMapping("/closeRoulette")
    public Response<String> closeRoulette ( @RequestParam long id ) {
        return new Roulette().closeRoulette(repository, id);
    }

    @GetMapping("/getRoulettes")
    public Response<List<Roulette>> getRoulettes () {
        List<Roulette> rouletteList = new ArrayList<>();
        for (RouletteEntity rouletteEntity : repository.findAll()) {
            rouletteList.add(Mappers.rouletteMapper(rouletteEntity));
        }
        Response<List<Roulette>> response = new Response<>();
        response.setData(rouletteList);
        return response;
    }
}
