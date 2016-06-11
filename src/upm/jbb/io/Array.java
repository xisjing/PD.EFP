package upm.jbb.io;

public class Array {

	private String nameType;
	private Object array;

	public Array(InputType type) {
		this.nameType = type.arrayType();
		this.array = createArray(0);
	}

	public Array(Object array) {
		if (array.getClass().isArray()) {
			this.array = array;
			InputType inputType = new InputType(array.getClass().getSimpleName());
			this.nameType = inputType.arrayType();
		} else System.out.println("DEBUG (Array): el parámetro no es una array");
	}

	public Object getArray() {
		return this.array;
	}

	private Object createArray(int length) {
		if (this.nameType.equals("byte")) return new byte[length];
		else if (this.nameType.equals("short")) return new short[length];
		else if (this.nameType.equals("int")) return new int[length];
		else if (this.nameType.equals("long")) return new long[length];
		else if (this.nameType.equals("float")) return new float[length];
		else if (this.nameType.equals("double")) return new double[length];
		else if (this.nameType.equals("boolean")) return new boolean[length];
		else if (this.nameType.equals("char")) return new char[length];
		else if (this.nameType.equals("Byte")) return new Byte[length];
		else if (this.nameType.equals("Short")) return new Short[length];
		else if (this.nameType.equals("Integer")) return new Integer[length];
		else if (this.nameType.equals("Long")) return new Long[length];
		else if (this.nameType.equals("Float")) return new Float[length];
		else if (this.nameType.equals("Double")) return new Double[length];
		else if (this.nameType.equals("Characte")) return new Character[length];
		else if (this.nameType.equals("String")) return new String[length];
		else if (this.nameType.equals("Object")) return new Object[length];
		else {
			try {
				Class<?> clase = Class.forName(this.nameType);
				return java.lang.reflect.Array.newInstance(clase, length);
			} catch (ClassNotFoundException e) {
				System.out
						.println("WARNING (Array): error al crear el array, clase no encontrada: "
								+ this.nameType);
			}
		}
		return null;
	}

	public void addElement(Object element) {
		int length = java.lang.reflect.Array.getLength(this.array);
		Object array2 = this.createArray(length + 1);
		for (int i = 0; i < length; i++)
			java.lang.reflect.Array.set(array2, i, java.lang.reflect.Array.get(array, i));
		java.lang.reflect.Array.set(array2, length, element);
		this.array = array2;
	}

	public String toString() {
		String res = "", separador = this.nameType + " [";
		for (int i = 0; i < java.lang.reflect.Array.getLength(this.array); i++) {
			res += separador + java.lang.reflect.Array.get(array, i);
			separador = ",";
		}
		if (res.length() == 0) return this.nameType + " [ ]";
		return res + "]";
	}

}