package com.plannify.domain;

import java.util.Objects;

public final class Plan {

	private final PlanId planId;

	public Plan(final PlanId planId) {
		if (planId == null) {
			throw new PlanInvalid();
		}
		this.planId = planId;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !this.getClass().equals(o.getClass())) {
			return false;
		}
		final Plan plan = (Plan) o;
		return this.planId.equals(plan.planId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.planId);
	}
}
