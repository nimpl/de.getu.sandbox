package de.getu.apps.kdsj.common.types;

import static org.junit.Assert.*;

import org.junit.Test;

import de.getu.apps.kdsj.common.fault.KDSJException;
import de.getu.apps.kdsj.common.validation.ValidationContext;

public class TDatumFactoryTest {

	@Test
	public void test() {
		ValidationContext validationContext = new ValidationContext();
		
		TDatum res = new TDatumFactory().create(validationContext, -1L);
		assertNull(res);
		assertEquals(1, validationContext.getFaults().size());
		try {
			validationContext.fire();
			fail("Exception expected");
		} catch (KDSJException e) {
			System.out.println(e.toString());
			assertEquals(0, validationContext.getFaults().size());			
		}
	}

}
