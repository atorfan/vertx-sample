package com.plannify.application;

import com.plannify.domain.Plan;
import com.plannify.domain.PlanId;
import com.plannify.domain.PlanInvalid;
import com.plannify.domain.PlanRepository;
import com.plannify.domain.PlanRepositoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CreatePlanShould {

	private PlanRepository repository;

	@BeforeEach
	void setUp() {
		this.repository = new PlanRepositoryMock();
	}

	@Test
	void createAPlanWhenValidRequestIsGiven() {
		final PlanId planId = PlanId.newOne();
		final Plan plan = new Plan(planId);

		new CreatePlan(this.repository).perform(plan);
	}

	@Test
	void throwNotValidWhenPlanIsNull() {
		assertThrows(PlanInvalid.class, () ->
				new CreatePlan(this.repository).perform(null)
		);
	}

	@Test
	void throwNotValidWhenPlanIdIsNull() {
		assertThrows(PlanInvalid.class, () ->
				new CreatePlan(this.repository).perform(new Plan(null))
		);
	}
}