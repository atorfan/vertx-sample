package com.plannify.domain;

import java.util.Objects;

public final class WannaDo {

	private final String value;

	public WannaDo(final String value) {
		this.value = value;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !this.getClass().equals(o.getClass())) {
			return false;
		}
		final WannaDo wannaDo = (WannaDo) o;
		return this.value.equals(wannaDo.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.value);
	}
}
