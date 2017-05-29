package app.exercise.algebra;

/**
 * Interface specifying basic operations addition, subtraction, multiplication and division on Arithmetic objects.
 */
public interface Arithmetic {
    /**
     * add operand to object
     * @param operand operand that is added
     */
    void add(Arithmetic operand);

    /**
     * subtract operand from object
     * @param operand operand that is subtracted
     */
    void sub(Arithmetic operand);

    /**
     * multiplt object by operand
     * @param operand operand that is multiplied with
     */
    void mul(Arithmetic operand);

    /**
     * divide opbject by operand
     * @param operand operand that is divided by
     */
    void div(Arithmetic operand);

}
