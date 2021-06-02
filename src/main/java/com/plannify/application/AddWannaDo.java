package com.plannify.application;

import com.plannify.domain.DomainEventsPublisher;
import com.plannify.domain.Plan;
import com.plannify.domain.PlanId;
import com.plannify.domain.PlanInvalid;
import com.plannify.domain.PlanRepository;
import com.plannify.domain.WannaDo;
import com.plannify.domain.WannaDoInvalid;

public final class AddWannaDo {

	private final PlanRepository repository;
	private final DomainEventsPublisher domainEventsPublisher;

	public AddWannaDo(final PlanRepository repository, final DomainEventsPublisher domainEventsPublisher) {
		this.repository = repository;
		this.domainEventsPublisher = domainEventsPublisher;
	}

	public void perform(final PlanId planId, final WannaDo wannaDo) {
		this.ensurePlanIdNonNull(planId);
		this.ensureWannaDoNonNull(wannaDo);

		final Plan plan = this.repository.findPlan(planId);
		plan.addWannaDo(wannaDo);

		this.domainEventsPublisher.publish(plan.pullDomainEvents());

		this.repository.save(plan);
	}

	private void ensurePlanIdNonNull(final PlanId planId) {
		if (planId == null) {
			throw new PlanInvalid();
		}
	}

	private void ensureWannaDoNonNull(final WannaDo wannaDo) {
		if (wannaDo == null) {
			throw new WannaDoInvalid();
		}
	}
}
