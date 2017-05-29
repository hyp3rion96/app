package app.exercise.algebra;

/**
 * Rational class that can be used to do arithmetic operations on rational
 * numbers. The denominator of Rational objects is always positive.
 * @author Alexander Wähling
 * @version 1.1
 */
public class Rational implements Arithmetic {
    /**
     * stores the numerator of the rational number.
     */
    private int num;
    /**
     * stores the denominator of the rational number. Is guaranteed
     * to be always positive.
     */
    private int denom;


    /**
     *
     * Initializes a new Rational object with the passed values for the numerator and denominator. Cancels out
     * numerator and denominator.
     * @see #num
     * @see #denom
     * @param num numerator of the new Rational object
     * @param denom denominator of the new Rational object
     */
    public Rational(int num, int denom) {
        this.num = num;
        this.denom = denom;
        this.cancel();
    }

    /**
     * Clone constructor. Returns a new Rational object that is identical to the passed one.
     * @param r Rational object to clone.
     */
    public Rational(Rational r) {
        this.num = r.getNum();
        this.denom = r.getDenom();
        this.cancel();
    }

    /**
     * Cancels out the numerator and denominator of this object by dividing
     * with their {@link #gcd(int, int)}. Also ensures that the denominator
     * is always positive.
     */
    private void cancel() {
        int gcd = gcd(num, denom);
        num /= gcd;
        denom /= gcd;
        if(denom < 0) {
            denom *= -1;
            num *= -1;
        }
    }
    /**
     * Use to get the numerator of this object.
     * @return numerator value of this object.
     */
    public int getNum() {
        return num;
    }

    /**
     * Use to set numerator of this object. Also invokes {@link #cancel()}
     * @param num numerator value to set
     */
    public void setNum(int num) {
        this.num = num;
        this.cancel();
    }

    /**
     * Use to get the denominator of the object.
     * @return denominator value of this object.
     */
    public int getDenom() {
        return denom;
    }

    /**
     * Use to set denominator of this object. Also invokes {@link #cancel()}
     * @param denom denominator value to set
     */
    public void setDenom(int denom) {
        this.denom = denom;
        this.cancel();
    }

    /**
     * Adds an operand of type {@link Arithmetic} to this object.
     * Also invokes {@link #cancel()}
     * @param operand operand that is added.
     * @throws IllegalArgumentException if operand is not of type {@link Rational}
     */
    @Override
    public void add(Arithmetic operand) {
        if(operand instanceof Rational) {
            Rational r = (Rational) operand;
            this.num = this.num * r.getDenom() + this.denom * r.getNum();
            this.denom = this.denom * r.getDenom();
            this.cancel();
        } else {
            throw new IllegalArgumentException("Only rationals can be added to Rationals");
        }
    }

    /**
     * Subtracts operand of type {@link Arithmetic} from this object.
     * Also invokes {@link #cancel()}
     * @param operand operand that is subtracted.
     * @throws IllegalArgumentException if operand is not of type {@link Rational}
     */
    @Override
    public void sub(Arithmetic operand) {
        if(operand instanceof Rational) {
            Rational r = (Rational) operand;
            this.num = this.num * r.getDenom() - this.denom * r.getNum();
            this.denom = this.denom * r.getDenom();
            this.cancel();
        } else {
            throw new IllegalArgumentException("Only rationals can be subtracted from Rationals");
        }
    }

    /**
     * Multiplies operand of type {@link Arithmetic} with this object.
     * Also invokes {@link #cancel()}
     * @param operand operand that is multiplied with.
     * @throws IllegalArgumentException if operand is not of type {@link Rational}
     */
    @Override
    public void mul(Arithmetic operand) {
        if(operand instanceof Rational) {
            Rational r = (Rational) operand;
            this.num *= r.getNum();
            this.denom *= r.getDenom();
            this.cancel();
        } else {
            throw new IllegalArgumentException("Only rationals can be multiplied with Rationals");
        }
    }

    /**
     * Divides this object by operand of type {@link Arithmetic}.
     * Also invokes {@link #cancel()}
     * @param operand operand that is divided by.
     * @throws IllegalArgumentException if operand is not of type {@link Rational}
     */
    @Override
    public void div(Arithmetic operand) {
        if(operand instanceof Rational) {
            Rational r = (Rational) operand;
            this.num *= r.getDenom();
            this.denom *= r.getNum();
            this.cancel();
        } else {
            throw new IllegalArgumentException("Rationals can only be divided by Rationals");
        }
    }

    /**
     * Clones the current object by creating a new Rational opbject
     * with the same numerator and denominator values as this object.
     * @return a copy of the current object.
     */
    @Override
    public Rational clone() {
        return new Rational(num, denom);
    }

    /**
     * Returns a String representation of the current object.
     * E.g. "5 / 3" or "-6 / 9"
     * @return String representation of current object.
     */
    @Override
    public String toString() {
        return num + " / " + denom;
    }

    /**
     * Checks if the passed Object is equal to the current Rational
     * object. Returns true if the passed Object is of type
     * Rational and the numerator and denominator from
     * both Rational objects are equal. Otherwise returns false.
     * Implementations guarantees that the equals() method is reflexive,
     * symmetric, transitive and consistent.
     * @param obj Object to compare to.
     * @return boolean - true if the Objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rational) {
            Rational r = (Rational) obj;
            if(this.num != r.getNum() || this.denom != r.getDenom())
                return false;
            else
                return true;

        } else {
            return false;
        }
    }

    /**
     * Uses very sophisticated methods to compute a hash value for the
     * current Rational object. Curiously always returns 42.
     * @return Answer to the Ultimate Question of Life, the Universe, and Everything.
     */
    @Override
    public int hashCode() {
        return 42;
    }

    /**
     * Function to calculate the greatest common divisor of two integers.
     * @param a
     * @param b
     * @return int - gcd of a and b
     */
    private static int gcd(int a, int b) {
        if(b != 0)
            return gcd(b, Math.floorMod(a, b));
        return a;
    }
}
