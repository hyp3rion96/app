package app.exercise.algebra;

/**
 * Extension of the {@link Rational} class that can be used to do arithmetic operations on rational
 * numbers and compare them to each other. The denominator of {@link Rational} objects is always positive.
 * @author Alexander Wähling
 * @version 1.0
 */
public class CompRational extends Rational implements Comparable<CompRational> {
    /**
     * Constructor for CompRational Numbers. Calls {@link Rational#Rational(int, int)} constructor with passed parameters.
     * @param num numerator to initialize CompRational object with
     * @param denom denominator to initialize CompRational object with
     */
    public CompRational(int num, int denom) {
        super(num, denom);
    }

    /**
     * Compares this object with the specified object for order.
     * Returns -1 , 0, 1 as this object is less than, equal to, or greater than the specified object. This implementation of compareTo() is
     * consistent with equals, so (x.compareTo(y)==0) == (x.equals(y)).
     * @param obj the object to be compared.
     * @return -1 , 0, 1 as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(CompRational obj) {
        // compute the double representations of the Rational objects, since there is a natural order on them, that is
        // consistent with equals()
        double realThis = (double) this.getNum() / (double) this.getDenom();
        double realObj = (double) obj.getNum() / (double) obj.getDenom();
        if(realThis < realObj)
            return -1;
        else if(realThis > realObj)
            return 1;
        else
            return 0;
    }
}
