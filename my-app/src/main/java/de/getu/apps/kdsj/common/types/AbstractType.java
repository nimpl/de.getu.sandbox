package de.getu.apps.kdsj.common.types;

public class AbstractType<T extends Object> {
	T value;

	protected AbstractType(T value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value.toString();
	}
}
