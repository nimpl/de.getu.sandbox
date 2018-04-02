package de.getu.apps.kdsj.common.fault;

import java.util.Enumeration;

import de.getu.apps.kdsj.common.types.EnumType;

public enum EFaultField implements EnumType{
	MESSAGE(true, 0),
	CLASS_NAME(true, 1),
	METHOD_NAME(true, 2),
	FIELD_NAME(true, 3),
	VALUE(true, 4),;
	private final boolean active;
	private final int code;
	
	private EFaultField(boolean active, int code) {
		this.active = active;
		this.code = code;
	}
	
	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public boolean isActive() {
		return active;
	}		
}
