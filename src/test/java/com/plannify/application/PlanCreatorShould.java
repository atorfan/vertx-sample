package com.plannify.application;

import com.plannify.domain.Plan;
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
		final Plan plan = new Plan(planId);

		new PlanCreator(this.repository).perform(plan);
	}

	@Test
	@DisplayName("throw error with a null plan")
	void throwNotValidWhenPlanIsNull() {
		assertThrows(PlanInvalid.class, () ->
				new PlanCreator(this.repository).perform(null)
		);
	}

	@Test
	@DisplayName("throw error with a null plan identifier")
	void throwNotValidWhenPlanIdIsNull() {
		assertThrows(PlanInvalid.class, () ->
				new PlanCreator(this.repository).perform(new Plan(null))
		);
	}
}