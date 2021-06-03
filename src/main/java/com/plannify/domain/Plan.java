package com.plannify.domain;

import java.util.List;
import java.util.Objects;

public final class Plan extends AggregateRoot {

	private final PlanId planId;
	private final WannaDos wannaDos;

	public Plan(final PlanId planId) {
		if (planId == null) {
			throw new PlanInvalid();
		}
		this.planId = planId;
		this.wannaDos = new WannaDos();
	}

	public void addWannaDo(final WannaDo wannaDo) {
		this.wannaDos.add(wannaDo);
		this.register(new WannaDoForPlanAdded(wannaDo));
	}

	public List<WannaDo> getWannaDos() {
		return this.wannaDos.value();
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
