package org.crysil.errorhandling;

public abstract class CrySILException extends Exception {

	private static final long serialVersionUID = -1129344166754694958L;

	public abstract int getErrorCode();

	public static CrySILException fromErrorCode(final int code) {
		// FIXME return appropriate exception
		return new CrySILException() {

			private static final long serialVersionUID = 7248400306801724819L;

			@Override
			public int getErrorCode() {
				return code;
			}
		};
	}

}
