package roulette;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import roulette.domain.Bet;
import roulette.domain.Roulette;
import roulette.entities.Result;
import roulette.entities.RouletteEntity;
import roulette.presentation.RequestBetOnRoulette;
import roulette.presentation.Response;

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
	@PostMapping("/createRoulette")
	public Response<Long> getId() {
		Response<Long> response = new Response<>();
		response.setData(new Roulette().createRoulette(repository).getId());
		return response;
	}

	@PostMapping("/openRoulette")
	public Response<Long> openRoulette ( @RequestParam long id ) {

		Response<Long> response = new Response<>();
		response.setData(id);
		return response;
	}


	@PostMapping("/betOnRoulette")
	public Response<String> betOnRoulette(
			@RequestBody RequestBetOnRoulette request
	) {
		Response<String> response = new Response<>();
		response.setData(request.getRouletteBetAmount() +"");
		return response;
	}


//	@PostMapping("/betOnRoulette")
//	EntityModel<RouletteEntity> betOnRoulette(
//			@RequestBody Long id_roulette,
//			@RequestBody int betNumber,
//			@RequestBody int betAmount
//	) {
//		Optional<RouletteEntity> optionalRoulette = repository.findById(id_roulette);
//		RouletteEntity rouletteEntity = optionalRoulette.isPresent() ? optionalRoulette.get() : null;
//		if (rouletteEntity != null) {
//
//		}
//		return EntityModel.of(rouletteEntity,
//				linkTo(methodOn(RouletteController.class).getId()).withSelfRel());
//	}

//	@PostMapping("/employees")
//	RouletteEntity newEmployee( @RequestBody RouletteEntity newRoulette ) {
//		return repository.save(newRoulette);
//	}
//
//	// Single item
//
////	// tag::get-single-item[]
////	@GetMapping("/employees/{id}")
////	EntityModel<RouletteEntity> one( @PathVariable Long id) {
////
////		RouletteEntity roulette = repository.findById(id) //
////				.orElseThrow(() -> new RouletteNotFoundException(id));
////
//
////	}
//	// end::get-single-item[]
//
//	@PutMapping("/employees/{id}")
//	RouletteEntity replaceEmployee( @RequestBody RouletteEntity newRoulette, @PathVariable Long id) {
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
