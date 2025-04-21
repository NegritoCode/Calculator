package logica;

/**
 * Estado de escritura actual de un Monomio
 */
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