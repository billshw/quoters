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

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DatabaseLoader {

	@Bean
	CommandLineRunner init(QuoteRepository repository) {

		return args -> {
			repository.save(new Quote("Soul - Spark"));
			repository.save(new Quote("YouTuber - achievement "));
			repository.save(new Quote("Help other ppl"));
			repository.save(new Quote("Relationships"));
			repository.save(new Quote("1% improvement"));
			repository.save(new Quote("be do hv"));
			repository.save(new Quote("Excuses are the obstructions that become the reasons for you abandoning your dream."));
			repository.save(new Quote("What you resist persists."));
			repository.save(new Quote("Life shrinks or expands in proportion with one’s courage."));
			repository.save(new Quote("If you lose, don't lose the lesson."));
			repository.save(new Quote("The struggle you're in today is developing the strength you need for tomorrow."));
			repository.save(new Quote("If you don't prioritize your life, someone else will."));
			repository.save(new Quote("Fear does not prevent death. It prevents life."));
			repository.save(new Quote("Eat the frog."));
			repository.save(new Quote("Don't let perfection become procrastination. Do it now."));
			repository.save(new Quote("If you are always trying to be normal you will never know how amazing you can be."));
			repository.save(new Quote("Do the thing you think you cannot do."));
		};
	}
}
