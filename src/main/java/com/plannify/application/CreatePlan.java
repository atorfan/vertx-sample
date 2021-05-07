package com.plannify.application;

import com.plannify.domain.Plan;
import com.plannify.domain.PlanInvalid;
import com.plannify.domain.PlanRepository;

public final class CreatePlan {

	private final PlanRepository repository;

	public CreatePlan(final PlanRepository repository) {
		this.repository = repository;
	}

	public void perform(final Plan plan) {
		this.ensurePlanNonNull(plan);

		this.repository.save(plan);
	}

	private void ensurePlanNonNull(final Plan plan) {
		if (plan == null) {
			throw new PlanInvalid();
		}
	}
}
