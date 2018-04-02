package de.getu.apps.kdsj.common.fault;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KDSJException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	Fault[] faults;
	
	KDSJException(String message, Throwable cause, Fault[] faultArr) {
		super(message, cause);
		this.faults = faultArr;
	}

	public static class Builder {
		String message;
		Throwable cause;
		List<Fault> faultL;
		
		public Builder addFault(Fault fault) {
			if (faultL == null) {
				faultL = new LinkedList<>();
			}
			this.faultL.add(fault);
			return this; 
		}
		
		public Builder addFaults(List<Fault> faultL) {
			if (this.faultL == null) {
				this.faultL = new LinkedList<>();
			}
			this.faultL.addAll(faultL);
			return this; 
		}
		public Builder setCause(Throwable cause) {
			this.cause = cause;
			return this;
		}
		
		public KDSJException build() {
			if (faultL == null || faultL.size() == 0) {
				throw new IllegalStateException("Kein Fault-Element vorhanden.");
			}
			long n = faultL.stream().map(f->f.category()).distinct().count();
			//Message bauen
			return new KDSJException(message, cause, faultL.toArray(new Fault[faultL.size()]));
		}
	}	
}