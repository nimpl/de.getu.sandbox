package de.getu.apps.kdsj.common.types;

import de.getu.apps.kdsj.common.validation.ValidationContext;

public abstract class TypeBuilder<T extends AbstractType<?>> {
	protected ValidationContext validationContext;
	protected Object value;

	public TypeBuilder(ValidationContext validationContext) {
		this.validationContext = validationContext;
	}
	public abstract TypeBuilder<T> setValue(Object value);
	public abstract TypeBuilder<T> validate();
	public abstract T build();
}
