package com.plannify.application;

import com.plannify.domain.Plan;
import com.plannify.domain.PlanId;
import com.plannify.domain.PlanRepository;

public final class PlanCreator {

	private final PlanRepository repository;

	public PlanCreator(final PlanRepository repository) {
		this.repository = repository;
	}

	public void perform(final PlanId planId) {
		final Plan plan = new Plan(planId);
		this.repository.save(plan);
	}
}
