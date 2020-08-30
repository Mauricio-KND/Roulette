package roulette;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// tag::hateoas-imports[]

// end::hateoas-imports[]

@RestController
class RouletteController {

	private final RouletteRepository repository;

	RouletteController (RouletteRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/createRoulette")
	public EntityModel<Roulette> getId() {
		Roulette roulette = new Roulette();
		repository.save(roulette);
		return EntityModel.of(roulette, //
				linkTo(methodOn(RouletteController.class).getId()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@GetMapping("/openRoulette/{id}")
	public EntityModel<Roulette.Response> openRoulette (@PathVariable long id ) {
		Optional<Roulette> optionalRoulette = repository.findById(id);
		Roulette roulette = optionalRoulette.isPresent() ? optionalRoulette.get() : null;
		if (roulette != null) {
			roulette.setStatus("Open");
			Roulette.Response response = new Roulette.Response();
			response.result = Roulette.Result.Succeed.result;
			return EntityModel.of(response,
					linkTo(methodOn(RouletteController.class).openRoulette(id)).withSelfRel());
		}
		return EntityModel.of(new Roulette.Response());
	}

/*
	@PostMapping("/betOnRoulette")
	public EntityModel<Roulette> betOnRoulette(
			@RequestBody Long id_roulette,
			@RequestBody Boolean color,
			@RequestBody int betAmount
	) {
		Optional<Roulette> optionalRoulette = repository.findById(id_roulette);
		Roulette roulette = optionalRoulette.isPresent() ? optionalRoulette.get() : null;
		if (roulette != null) {
			roulette.setBetAmount(betAmount);
			roulette.setColor(color);
		}
		return EntityModel.of(roulette,
				linkTo(methodOn(RouletteController.class).getId()).withSelfRel());
	}
*/

	@PostMapping("/betOnRoulette")
	EntityModel<Roulette> betOnRoulette(
			@RequestBody Long id_roulette,
			@RequestBody int betNumber,
			@RequestBody int betAmount
	) {
		Optional<Roulette> optionalRoulette = repository.findById(id_roulette);
		Roulette roulette = optionalRoulette.isPresent() ? optionalRoulette.get() : null;
		if (roulette != null) {
			roulette.setBetAmount(betAmount);
			roulette.setBetNumber(betNumber);
		}
		return EntityModel.of(roulette,
				linkTo(methodOn(RouletteController.class).getId()).withSelfRel());
	}

//	@PostMapping("/employees")
//	Roulette newEmployee( @RequestBody Roulette newRoulette ) {
//		return repository.save(newRoulette);
//	}
//
//	// Single item
//
////	// tag::get-single-item[]
////	@GetMapping("/employees/{id}")
////	EntityModel<Roulette> one( @PathVariable Long id) {
////
////		Roulette roulette = repository.findById(id) //
////				.orElseThrow(() -> new RouletteNotFoundException(id));
////
//
////	}
//	// end::get-single-item[]
//
//	@PutMapping("/employees/{id}")
//	Roulette replaceEmployee( @RequestBody Roulette newRoulette, @PathVariable Long id) {
//
//		return repository.findById(id) //
//				.map(roulette -> {
//
//					return repository.save(roulette);
//				})
//				.orElseGet(() -> {
//					newRoulette.setId(id);
//					return repository.save(newRoulette);
//				});
//	}
//
//	@DeleteMapping("/employees/{id}")
//	void deleteEmployee(@PathVariable Long id) {
//		repository.deleteById(id);
//	}
}
