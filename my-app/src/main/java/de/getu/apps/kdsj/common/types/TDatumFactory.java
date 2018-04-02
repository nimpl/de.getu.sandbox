package de.getu.apps.kdsj.common.types;

import de.getu.apps.kdsj.common.fault.EFaultCategory;
import de.getu.apps.kdsj.common.fault.Fault;
import de.getu.apps.kdsj.common.validation.ValidationContext;
import de.getu.apps.kdsj.common.validation.ValidationResult;

public class TDatumFactory extends TypeFactory<TDatum>{
	public TDatum create(ValidationContext validationContext, Long value) {
		ValidationResult validationResult = new TDatumValidator().validate(value);
		if (validationResult.valid()) {
			return new TDatum(value);
		} else {
			validationContext.enterScope(this.getClass(), "create");
			validationContext.add(validationResult);
			validationContext.exitScope();
			return null;
		}
	}

	@Override
	public TDatum create(ValidationContext validationContext, Object value) {
		if (value instanceof Long) {
			return create(validationContext, (Long)value);
		} else {
			validationContext.add(new Fault.Builder().setCategory(EFaultCategory.VALIDATION)
					.setCode("123")
					.setMessage("Trallala")
					.build());
		}
		return null;
	}		
}