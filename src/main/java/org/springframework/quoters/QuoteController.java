/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.quoters;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class QuoteController {

	private final static Quote NONE = new Quote("None");
	private final static Random RANDOMIZER = new Random();

	private final QuoteRepository quoteRepository;

	public QuoteController(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

	@GetMapping("/api")
	public List<QuoteResource> getAll() {

		return quoteRepository.findAll().stream()
				.map(quote -> new QuoteResource(quote, "success"))
				.collect(Collectors.toList());
	}

	@GetMapping("/api2")
	public List<Quote> getAll2() {
		return quoteRepository.findAll();
	}

	@GetMapping("/api/{id}")
	public QuoteResource getOne(@PathVariable Long id) {

		return quoteRepository.findById(id)
			.map(quote -> new QuoteResource(quote, "success"))
			.orElse(new QuoteResource(NONE, "Quote " + id + " does not exist"));
	}

	@GetMapping("/api/random")
	public QuoteResource getRandomOne() {
		return getOne(nextLong(1, quoteRepository.count() + 1));
	}

	private long nextLong(long lowerRange, long upperRange) {
		return (long) (RANDOMIZER.nextDouble() * (upperRange - lowerRange)) + lowerRange;
	}





	//DELETE
	@DeleteMapping("/api/{id}")
	public void deleteQuote(@PathVariable long id) {
		quoteRepository.deleteById(id);
	}


	//CREATE
	@PostMapping("/api")
	public ResponseEntity<Object> createQuote(@RequestBody Quote quote) {
		Quote savedQuote = quoteRepository.save(quote);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedQuote.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	//UPDATE
	@PutMapping("/api/{id}")
	public ResponseEntity<Object> updateQuote(@RequestBody Quote quote, @PathVariable long id) {

		Optional<Quote> quoteOptional = quoteRepository.findById(id);

		if (quoteOptional.isEmpty())
			return ResponseEntity.notFound().build();

		quote.setId(id);

		quoteRepository.save(quote);

		return ResponseEntity.noContent().build();
	}

}
