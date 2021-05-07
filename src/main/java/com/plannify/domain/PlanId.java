package com.plannify.domain;

import java.util.Objects;
import java.util.UUID;

public final class PlanId {

	private final UUID uuid;

	private PlanId(final UUID uuid) {
		this.uuid = uuid;
	}

	public static PlanId newOne() {
		return new PlanId(UUID.randomUUID());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !this.getClass().equals(o.getClass())) {
			return false;
		}
		final PlanId planId = (PlanId) o;
		return this.uuid.equals(planId.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.uuid);
	}
}
