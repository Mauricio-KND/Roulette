package roulette;

import roulette.entities.Result;

public class RouletteNotFoundException extends RuntimeException {

	public RouletteNotFoundException ( Long id ) {
		super(Result.Failed.name() + id);
	}
}
