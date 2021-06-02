package com.plannify.domain;

public interface PlanRepository {

	void save(Plan plan);

	Plan findPlan(PlanId planId);
}
