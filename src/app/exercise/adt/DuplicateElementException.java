package app.exercise.adt;

/**
 * Exception to throw when an element to be inserted is aleady present in the data structure.
 */
public class DuplicateElementException extends IllegalArgumentException {

    public DuplicateElementException() { super();}

    public DuplicateElementException(String s) { super(s);}
}
