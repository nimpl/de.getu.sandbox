package de.getu.apps.kdsj.common.fault;

import java.util.Enumeration;

import de.getu.apps.kdsj.common.types.EnumType;

public enum EFaultCategory implements EnumType{
	VALIDATION(true, 0),
	SICHERHEIT(true, 1),
	VERARBEITUNG(true, 2),
	SCHNITTSTELLE(true, 3),
	TECHNISCH(true, 4),
	EXTERN(true, 5);
	private final boolean active;
	private final int code;
	
	private EFaultCategory(boolean active, int code) {
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
