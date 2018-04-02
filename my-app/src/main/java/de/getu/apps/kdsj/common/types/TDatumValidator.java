package de.getu.apps.kdsj.common.types;

import de.getu.apps.kdsj.common.fault.EFaultCategory;
import de.getu.apps.kdsj.common.fault.Fault;
import de.getu.apps.kdsj.common.validation.ValidationResult;

public class TDatumValidator {
	public ValidationResult validate(Long value) {
		if ( 19000101 <= value && value <= 99991231 ) {
			return ValidationResult.OK;
		}
		return new Fault.Builder()
			.setCategory(EFaultCategory.VALIDATION)
			.setCode("UEB_001")
			.setValue(value)
			.setMessage("Wert %s ist kein valider Wert des Typs.")
			.addMessageParameter(value)
			.addMessageParameter(TDatum.class)
			.build();
	}
}