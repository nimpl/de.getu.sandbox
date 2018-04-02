package de.getu.apps.kdsj.common.fault;

import de.getu.apps.kdsj.common.types.EnumType;
import de.getu.apps.kdsj.common.validation.ValidationContext;

public class EFaultCategoryFactory {

	public static <T extends EnumType<?>>  T create(ValidationContext validationContext, Class<T> cls, int code) {
		for (T each: cls.getEnumConstants()) {
			if (each.getCode()==code) {
				return each;
			}
		}
		validationContext.add(new Fault.Builder().setCategory(EFaultCategory.VALIDATION)
				.setCode("UEB_007")
				.setMessage("Es existiert kein Objekt vom Typ  %s  mit dem code %d.")
				.addMessageParameter(cls.getSimpleName())
				.addMessageParameter(code)
				.build());
		return null;
	}
	public EFaultCategory create(ValidationContext validationContext, int code) {
		for (EFaultCategory each: EFaultCategory.values()) {
			if (each.getCode() == code) {
				return each;
			}
		}
		
		validationContext.add(new Fault.Builder().setCategory(EFaultCategory.VALIDATION)
				.setCode("UEB_007")
				.setMessage("Es existiert kein Objekt vom Typ  %s  mit dem code %d.")
				.addMessageParameter(EFaultCategory.class)
				.addMessageParameter(code)
				.build());
		return null;
	}
}