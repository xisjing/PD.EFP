package upm.jbb.io;

/**
 * Representa los datos básicos de una clase. Realiza procesamiento de
 * pertenencia a grupos
 * 
 */
public class InputType {
	// El nombre de la clase: Integer, String, mipaquete.MiClase...
	private String type;
	private String msg;
	private Object value;
	private String error;
	private Object[] values;

	private InputTypeListener listener = null;

	private static final String[] PRIMITIVE_VALUES = { "boolean", "char",
			"byte", "short", "int", "long", "float", "double" };
	private static final String[] PRIMITIVE_ARRAY_VALUES = { "Z", "C", "B",
			"S", "I", "J", "F", "D" };
	private static final String[] WRAPPER_VALUES = { "Boolean", "Character",
			"Byte", "Short", "Integer", "Long", "Float", "Double" };
	private static final String[] COLLECTION_VALUES = { "java.util.List",
			"java.util.Set", "java.util.SortedSet", "java.util.Queue",
			"java.util.Deque" };
	private static final String[] INTEGER_VALUES = { "byte", "short", "int",
			"long", "Byte", "Short", "Integer", "Long" };
	private static final String[] DECIMAL_VALUES = { "float", "double",
			"Float", "Double" };
	private static final Object[] BOOLEAN_VALUES = { false, true };
	private static final String SELECT_VALUE = "Select";
	private static final String[] COLOR_VALUES = { "java.awt.Color" };
	private static final String[] FILE_VALUES = { "java.io.File" };

	// Se normaliza el nombre del tipo
	private String normalize(String type) {
		// De "java.lang.Integer" a "Integer"
		type = type.replaceAll("java.lang.", "");

		// De "class paquete.MiClase" a "paquete.Clase"
		// ".+" significa cualquier caracter, una o más veces
		if (type.matches("class .+"))
			type = type.substring(6);

		// De "[Z" a "Boolean[]"
		// "\\[" es para interpretar "[" como caracter
		for (int i = 0; i < PRIMITIVE_ARRAY_VALUES.length; i++)
			if (type.matches("\\[" + PRIMITIVE_ARRAY_VALUES[i]))
				type = PRIMITIVE_VALUES[i] + "[]";

		// De "[Lpaquete.MiClase;" a "paquete.MiClase[]"
		if (type.matches("\\[L.+"))
			type = type.substring(2, type.length() - 1) + "[]";
		return type;
	}

	public InputType(String type, String msg) {
		this.type = this.normalize(type);
		this.values = null;
		if (!isArray() && !isCollection()) {
			try {
				Class<?> clase = Class.forName(this.type);
				if (clase.isEnum()) {
					this.values = clase.getEnumConstants();
				}
			} catch (ClassNotFoundException e) {
			} catch (NoClassDefFoundError e) {
			}

		}
		if (this.isBoolean()) {
			this.setValues(BOOLEAN_VALUES);
		}
		this.msg = msg;
		this.setError("");
	}

	public InputType(String type) {
		this(type, "");
		this.msg = this.getType();
	}

	// Dada un array de objetos, el usuario elige uno
	public InputType(String msg, Object[] values) {
		this.type = SELECT_VALUE;
		this.msg = msg;
		this.values = values;
		this.error = "";
	}

	public void removeListener() {
		this.listener = null;
	}

	public void addListener(InputTypeListener listener) {
		this.listener = listener;
	}

	public void notifyObserver() {
		if (this.listener != null)
			this.listener.update();
	}

	public String getType() {
		return type;
	}

	public String getMsg() {
		return msg;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
		this.notifyObserver();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
		this.notifyObserver();
	}

	public boolean isError() {
		return !this.error.equals("");
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
		this.notifyObserver();
	}

	public boolean isPrimitive() {
		for (String s : PRIMITIVE_VALUES)
			if (this.type.equals(s))
				return true;
		return false;
	}

	public boolean isPrimitiveArray() {
		for (String s : PRIMITIVE_VALUES)
			if (this.arrayType().equals(s))
				return true;
		return false;
	}

	public boolean isSingle() {
		if (this.isPrimitive())
			return true;
		for (String s : WRAPPER_VALUES)
			if (this.type.equals(s))
				return true;
		if (this.isString())
			return true;
		if (this.isColor())
			return true;
		if (this.isFile())
			return true;
		return isEnum();
	}

	public boolean isSingleArray() {
		if (this.isPrimitiveArray())
			return true;
		for (String s : WRAPPER_VALUES)
			if (this.arrayType().equals(s))
				return true;
		if (this.isStringArray())
			return true;
		return isEnum();
	}

	public boolean isBoolean() {
		return this.type.equals("Boolean") || this.type.equals("boolean");
	}

	public boolean isChar() {
		return this.type.equals("Character") || this.type.equals("char");
	}

	public boolean isByte() {
		return this.type.equals("Byte") || this.type.equals("byte");
	}

	public boolean isShort() {
		return this.type.equals("Short") || this.type.equals("short");
	}

	public boolean isInt() {
		return this.type.equals("Integer") || this.type.equals("int");
	}

	public boolean isLong() {
		return this.type.equals("Long") || this.type.equals("long");
	}

	public boolean isFloat() {
		return this.type.equals("Float") || this.type.equals("float");
	}

	public boolean isDouble() {
		return this.type.equals("Double") || this.type.equals("double");
	}

	public boolean isString() {
		return this.type.equals("String");
	}

	public boolean isStringArray() {
		return this.isArray() && this.arrayType().equals("String");
	}

	public boolean isObjectArray() {
		return this.isArray() && this.arrayType().equals("Object");
	}

	public boolean isInteger() {
		for (String s : INTEGER_VALUES)
			if (type.equals(s))
				return true;
		return false;
	}

	public boolean isColor() {
		for (String s : COLOR_VALUES)
			if (type.equals(s))
				return true;
		return false;
	}

	public boolean isFile() {
		for (String s : FILE_VALUES)
			if (type.equals(s))
				return true;
		return false;
	}

	public boolean isObject() {
		return this.type.equals("Object");
	}

	public boolean isDecimal() {
		for (String s : DECIMAL_VALUES)
			if (type.equals(s))
				return true;
		return false;
	}

	public boolean isCollection() {
		for (String s : COLLECTION_VALUES)
			if (genericName().equals(s))
				return true;
		return false;
	}

	public String genericName() {
		if (type.indexOf("<") == -1)
			return type;
		else
			return type.substring(0, type.indexOf("<"));
	}

	public String genericType() {
		if (type.indexOf("<") == -1)
			return "Object";
		return type.substring(type.indexOf("<") + 1, type.lastIndexOf(">"));
	}

	public boolean isArray() {
		return type.indexOf("[") != -1;

	}

	public String arrayType() {
		if (type.indexOf("[") == -1)
			return type;
		else
			return type.substring(0, type.indexOf("["));
	}

	public boolean isEnum() {
		if (!isCollection() && !isArray()) {
			try {
				Class<?> clase = Class.forName(type);
				return clase.isEnum();
			} catch (ClassNotFoundException e) {
			} catch (NoClassDefFoundError e) {
			}
		}
		return false;
	}

	public boolean isSelect() {
		return this.type.equals(SELECT_VALUE);
	}

	@Override
	public String toString() {
		return "Type(" + type + "," + msg + "," + value + "," + error + ","
				+ values[0] + ")";
	}

}
