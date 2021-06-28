package com.plannify.application;

import com.plannify.domain.PlanId;
import com.plannify.domain.PlanInvalid;
import com.plannify.domain.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Create Plan should")
class PlanCreatorShould {

	private PlanRepository repository;

	@BeforeEach
	void setUp() {
		this.repository = Mockito.mock(PlanRepository.class);
	}

	@Test
	@DisplayName("create a new one with a valid request")
	void createAPlanWhenValidRequestIsGiven() {
		final PlanId planId = PlanId.newOne();

		new PlanCreator(this.repository).perform(planId);
	}

	@Test
	@DisplayName("throw error with a null plan identifier")
	void throwNotValidWhenPlanIdIsNull() {
		assertThrows(PlanInvalid.class, () ->
				new PlanCreator(this.repository).perform(null)
		);
	}
}