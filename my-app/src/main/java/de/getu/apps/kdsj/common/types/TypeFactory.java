package de.getu.apps.kdsj.common.types;

import de.getu.apps.kdsj.common.validation.ValidationContext;

public abstract class TypeFactory<T extends AbstractType<?>> {
	public static <F extends AbstractType<?>>  TypeFactory<F> getFactory(Class<F> typeClass) {
		if (TDatum.class.equals(typeClass)) {
			return (TypeFactory<F>) new TDatumFactory();
		}
		throw new IllegalStateException();
	}
	
	public abstract T create(ValidationContext validationContext, Object value);
}
