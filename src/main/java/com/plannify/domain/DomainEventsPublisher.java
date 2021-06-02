package com.plannify.domain;

import java.util.List;

public interface DomainEventsPublisher {

	void publish(List<DomainEvent> domainEvents);
}
