package roulette;

class RouletteNotFoundException extends RuntimeException {

	RouletteNotFoundException ( Long id) {
		super("Could not find roulette " + id);
	}
}
