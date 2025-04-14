package logica;

/**
 * Esta clase representa un unico termino de una expresion matemática
 */
public class Monomio {

    public enum Estado {
        /** estado inicial, esperando primer dígito */
        INICIO,
        /** recibiendo dígitos enteros */
        ENTERO,
        /** recibio punto, esperando dígitos decimales */
        PUNTO,
        /** recibiendo digitos decimales después del punto */
        DECIMAL
    }

    private Estado estado;
    protected String expresion;

    /**
     * Operador que precede a este monomio en la expresión.
     * Por defecto es '+'.
     */
    private char operador;

    /**
     *
     */
    public Monomio(String expresion) {
        setExpresion(expresion);
    }

    /**
     * Reinicia el monomio a vacio
     */
    public void clear() {
        expresion = "";
        operador = '+';
        estado = Estado.INICIO;
    }

    /**
     * Imprime el monomio mostrando primero su operador previo.
     */
    public void printSelf(String spaces) {
        System.out.print(getOperador() + getExpresion());
    }

    /**
     * Evaluar si es una operación
     */
    public static boolean isOperacion(char entrada) {
        return entrada == '+' || entrada == '-' || entrada == '*' || entrada == '/';
    }

    /**
     * Obtener string del monomio
     */
    public String getExpresion() {
        return expresion.length() > 0 ? expresion : "0";
    }

    /**
     * Setear nueva expresión
     */
    public final void setExpresion(String expresion) {
        clear();
        for (int i = 0; i < expresion.length(); i++) {
            insertarEntrada(expresion.charAt(i));
        }
    }
    public void set(String expresion) {
        setExpresion(expresion);
    }

    /**
     * Retorna su estado actual
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Obtiene el operador que precede a este monomio.
     * 
     * @return El operador previo (+, -, *, /).
     */
    public final char getOperador() {
        return operador;
    }

    /**
     * Establece el operador que precede a este monomio.
     */
    public final void setOperador(char operador) {
        if (isOperacion(operador)) {
            this.operador = operador;
        } else {
            throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }

    /**
     * Calcula el valor numérico del monomio
     */
    public double calcular() {
        return Double.parseDouble(getExpresion());
    }

    /**
     * Procesa un nuevo carácter para construir la expresión
     * Valida el caracter segun el estado de construccion actual
     */
    public void insertarEntrada(char entrada) {
        switch (estado) {
            case INICIO:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                    estado = Estado.ENTERO;
                } else {
                    throw new IllegalArgumentException("Se esperaba un dígito inicial");
                }
                break;

            case ENTERO:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                } else if (entrada == '.') {
                    expresion += entrada;
                    estado = Estado.PUNTO;
                } else {
                    throw new IllegalArgumentException("Se esperaba dígito o punto decimal");
                }
                break;

            case PUNTO:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                    estado = Estado.DECIMAL;
                } else {
                    throw new IllegalArgumentException("Se esperaba dígito después del punto");
                }
                break;

            case DECIMAL:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                } else {
                    throw new IllegalArgumentException("Se esperaba dígito decimal");
                }
                break;
        }
    }
}