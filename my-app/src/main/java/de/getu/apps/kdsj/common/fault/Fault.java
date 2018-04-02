package de.getu.apps.kdsj.common.fault;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import org.ietf.jgss.MessageProp;

import de.getu.apps.kdsj.common.validation.ValidationResult;

public class Fault implements ValidationResult {
	protected EnumMap<EFaultField, String> fieldMap = new EnumMap<>(EFaultField.class);
	protected EFaultCategory category;

	protected String code;
	
	protected Fault() {
	}
	
	public EFaultCategory category() {
		return this.category;
	}
	public String code() {
		return this.code;
	}
	public String message() {
		return this.fieldMap.get(EFaultField.MESSAGE);
	}
	
	@Override
	public boolean valid() {
		return false;
	}
	@Override
	public String toString() {
		StringBuilder sb = append(null, "category", this.category);
		sb = append(sb, "code", this.code);
		for (EFaultField each: EFaultField.values()) {
			if (getFieldValue(each)!=null) {
				sb = append(sb, each.name(), getFieldValue(each));				
			}
		}
		return sb.toString();
	}
	
	protected static StringBuilder append(StringBuilder sb, String name, Object value) {
		if (sb == null) {
			sb = new StringBuilder();
		}
		if (sb.length() > 0) {
			sb.append(' ');
		}
		if (name == null) {
			if (value != null) {
				sb.append(value);				
			}
		} else if (value == null) {
			sb.append(name).append("=null");
		} else {
			sb.append(name).append("=").append(value);
		}
		return sb;
	}
	
	protected void setField(EFaultField field, String value) {
		this.fieldMap.put(field, value);
	}
	protected String getFieldValue(EFaultField field) {
		return this.fieldMap.get(field);
	}
	
	@Override
	public List<Fault> faults() {
		return Collections.singletonList(this);
	}
	
	public static class Builder{
		Fault fault;
		List<Object> mesageParamL;
		public Builder setCategory(EFaultCategory category) {
			if (fault == null) {
				fault = new Fault();

			}
			fault.category = category;
			return this;
		}
		public Builder setCode(String code) {
			if (fault == null) {
				fault = new Fault();

			}
			fault.code = code;
			return this;
		}
		protected Builder setFaultField(EFaultField field, String value) {
			if (fault == null) {
				fault = new Fault();
			}
			fault.setField(field, value);
			return this;
		}
		public Builder setClassName(String clsName) {
			return setFaultField(EFaultField.CLASS_NAME, clsName);
		}
		public Builder setMethodName(String methodName) {
			return setFaultField(EFaultField.METHOD_NAME, methodName);
		}
		public Builder setValue(Object value) {
			return setFaultField(EFaultField.VALUE, value==null?null:value.toString());
		}
		public Builder setMessage(String message) {
			return setFaultField(EFaultField.MESSAGE, message);
		}
		public Builder addMessageParameter(Object messageParam) {
			if (this.mesageParamL == null) {
				this.mesageParamL = new LinkedList<>();
			}
			this.mesageParamL.add(messageParam);
			return this;
		}
		public Fault build() {
			if (fault == null || fault.code == null || fault.category == null) {
				throw new IllegalStateException("Fault requires: category and code and message");
			}
			if (this.mesageParamL != null && this.mesageParamL.size() > 0) {
				fault.setField(EFaultField.MESSAGE, 
						String.format(fault.message(), this.mesageParamL.toArray()));				
			}
			return fault;
		}
	}
}