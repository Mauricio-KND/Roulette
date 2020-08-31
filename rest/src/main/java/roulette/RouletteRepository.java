package roulette;

import org.springframework.data.jpa.repository.JpaRepository;
import roulette.entities.RouletteEntity;

public interface RouletteRepository extends JpaRepository<RouletteEntity, Long> {

}
