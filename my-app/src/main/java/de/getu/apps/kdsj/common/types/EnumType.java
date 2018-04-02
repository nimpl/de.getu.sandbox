package de.getu.apps.kdsj.common.types;

public interface EnumType<T extends Enum<T>> {
	public boolean isActive();
	public int getCode();
}