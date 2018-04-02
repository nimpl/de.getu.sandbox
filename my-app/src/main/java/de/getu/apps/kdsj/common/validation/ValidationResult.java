package de.getu.apps.kdsj.common.validation;

import java.util.Collections;
import java.util.List;

import de.getu.apps.kdsj.common.fault.Fault;

public interface ValidationResult {
	public static final ValidationResult OK = new ValidationResult() {
		@Override
		public boolean valid() {
			return true;
		}
		@Override
		public List<? extends Fault> faults() {
			return Collections.emptyList();
		}
	};
	public boolean valid();
	public List<? extends Fault> faults();
}
