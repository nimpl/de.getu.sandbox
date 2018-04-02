package de.getu.apps.kdsj.common.types;

import de.getu.apps.kdsj.common.fault.EFaultCategory;
import de.getu.apps.kdsj.common.fault.Fault;
import de.getu.apps.kdsj.common.validation.ValidationContext;

public class TDatumBuilder extends TypeBuilder<TDatum> {
	protected Long targetValue;
	
	public TDatumBuilder(ValidationContext validationContext) {
		super(validationContext);
	}
	
	@Override
	public TypeBuilder<TDatum> setValue(Object value) {
		if (value instanceof Long) {
			this.targetValue = (Long) value;
		}
		return this;
	}

	@Override
	public TypeBuilder<TDatum> validate() {
		if (this.targetValue == null) {
			this.validationContext.add(
					new Fault.Builder().setCategory(EFaultCategory.SCHNITTSTELLE)
					.setCode("007").setMessage("lall").build());
		}
		return null;
	}

	@Override
	public TDatum build() {
		this.validate();
		return new TDatum(targetValue);
	}
}
