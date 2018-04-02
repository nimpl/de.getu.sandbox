package de.getu.apps.kdsj.common.fault;

import de.getu.apps.kdsj.common.types.EnumType;
import de.getu.apps.kdsj.common.validation.ValidationContext;

public class EFaultFieldFactory {
	public EFaultField create(ValidationContext validationContext, int code) {
		for (EFaultField each: EFaultField.values()) {
			if (each.getCode() == code) {
				return each;
			}
		}
		
		validationContext.add(new Fault.Builder().setCategory(EFaultCategory.VALIDATION)
				.setCode("UEB_007")
				.setMessage("Es existiert kein Objekt vom Typ  %s  mit dem code %d.")
				.addMessageParameter(EFaultField.class)
				.addMessageParameter(code)
				.build());
		return null;
	}
}