package de.getu.apps.kdsj.common.types;

import static org.junit.Assert.*;

import org.junit.Test;

import de.getu.apps.kdsj.common.fault.EFaultCategory;
import de.getu.apps.kdsj.common.fault.EFaultCategoryFactory;
import de.getu.apps.kdsj.common.validation.ValidationContext;

public class EFaultCategoryFactoryTest {

	@Test
	public void testCreateValidationContextClassOfTInt() {
		ValidationContext validationContext;
		EFaultCategory t = EFaultCategoryFactory.create(new ValidationContext(),
				EFaultCategory.class, 0);
		assertEquals(EFaultCategory.VALIDATION, t);

		validationContext = new ValidationContext();
		t = EFaultCategoryFactory.create(validationContext,
				EFaultCategory.class, -1);
		try {
			validationContext.fire();
		} catch (RuntimeException e) {
			assertTrue(e.getMessage().contains("kein"));			
		}

	}

}
