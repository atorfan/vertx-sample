package com.plannify.application;

import com.plannify.domain.DomainEventsPublisher;
import com.plannify.domain.Plan;
import com.plannify.domain.PlanId;
import com.plannify.domain.PlanInvalid;
import com.plannify.domain.PlanNotFound;
import com.plannify.domain.PlanRepository;
import com.plannify.domain.WannaDo;
import com.plannify.domain.WannaDoInvalid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@DisplayName("Wanna Do Appender should")
class WannaDoAppenderShould {

	private PlanRepository repository;
	private DomainEventsPublisher domainEventsPublisher;

	@BeforeEach
	void setUp() {
		this.repository = Mockito.mock(PlanRepository.class);
		this.domainEventsPublisher = Mockito.mock(DomainEventsPublisher.class);
	}

	@Test
	@DisplayName("add new wanna do to the given plan")
	void addToThePlan() {
		final PlanId planId = PlanId.newOne();
		final Plan plan = new Plan(planId);
		final WannaDo wannaDo = new WannaDo("Travel to Paris");

		given(this.repository.findPlan(planId))
				.willReturn(plan);

		final WannaDoAppender addWannaDo = new WannaDoAppender(this.repository, this.domainEventsPublisher);
		addWannaDo.perform(planId, wannaDo);

		assertTrue(plan.getWannaDos().contains(wannaDo));
		then(this.domainEventsPublisher).should(times(1)).publish(plan.pullDomainEvents());
	}

	@Test
	@DisplayName("throw plan not found because not exists")
	void throwPlanNotFound() {
		final PlanId planId = PlanId.newOne();
		final WannaDo wannaDo = new WannaDo("Travel to Paris");

		given(this.repository.findPlan(planId))
				.willReturn(null);

		assertThrows(PlanNotFound.class, () ->
				new WannaDoAppender(this.repository, this.domainEventsPublisher).perform(planId, wannaDo)
		);
	}

	@Test
	@DisplayName("throw plan invalid because null plan identifier")
	void throwPlanInvalidForNullPlanId() {
		final WannaDo wannaDo = new WannaDo("Travel to Paris");

		assertThrows(PlanInvalid.class, () ->
				new WannaDoAppender(this.repository, this.domainEventsPublisher).perform(null, wannaDo)
		);
	}

	@Test
	@DisplayName("throw wanna do invalid because null")
	void throwWannaDoInvalidForNullInput() {
		final PlanId planId = PlanId.newOne();

		assertThrows(WannaDoInvalid.class, () ->
				new WannaDoAppender(this.repository, this.domainEventsPublisher).perform(planId, null)
		);
	}
}