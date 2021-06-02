package com.plannify.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AggregateRoot {

	private final List<DomainEvent> domainEvents = new ArrayList<>();

	protected void register(final DomainEvent domainEvent) {
		this.domainEvents.add(domainEvent);
	}

	public List<DomainEvent> pullDomainEvents() {
		return Collections.unmodifiableList(this.domainEvents);
	}
}
