package com.plannify.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class WannaDos {

	private List<WannaDo> value;

	public void add(final WannaDo wannaDo) {
		if (this.value == null) {
			this.value = new ArrayList<>();
		}
		this.value.add(wannaDo);
	}

	public List<WannaDo> value() {
		return Collections.unmodifiableList(this.value);
	}
}
