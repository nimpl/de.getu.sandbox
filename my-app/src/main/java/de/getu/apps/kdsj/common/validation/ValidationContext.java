package de.getu.apps.kdsj.common.validation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.getu.apps.kdsj.common.fault.Fault;
import de.getu.apps.kdsj.common.fault.KDSJException;

public class ValidationContext {
	protected List<Fault> faults;
	protected Stack<ValidationScope> scopeStack = new Stack<>();
	
	public ValidationContext() {
	}
	
	public ValidationContext enterScope(Class<?> cls, Method method) {
		return enterScope(cls, method ==null?null:method.getName());
	}
	public ValidationContext enterScope(Class<?> cls, String methodName) {
		scopeStack.push(new ValidationScope(cls, methodName));
		return this;
	}
	
	public ValidationContext exitScope() {
		scopeStack.pop();
		return this;
	}
	
	public void fire() {
		if (this.faults == null || faults.size() == 0) {
			return;
		}
		KDSJException e = new KDSJException.Builder().addFaults(faults).build();
		this.faults.clear();
		throw e;
	}
	
	public List<Fault> getFaults() {
		return this.faults;
	}

	public ValidationContext add(ValidationResult fault) {
		if (fault == null || fault.valid()) {
			return this;
		}
		if (faults == null) {
			this.faults = new ArrayList<>();
		}
		this.faults.addAll(fault.faults());
		return this;
	}
	
	private static class ValidationScope{
		protected Class<?> cls;
		protected String methodName;
		
		ValidationScope(Class<?> cls, String methodName) {
			this.cls = cls;
			this.methodName = methodName;
		}
	}
}