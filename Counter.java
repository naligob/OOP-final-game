/*
 * Classname : Rectangle
 * @author Ilan Bogomolnik
 */

/**
 * this class represent an counter , we can add or subtract the saved number and show the value.
 */
public class Counter {
    private int number;

    /**
     * builder.
     * @param number the starting number
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * @param numberUp add number to current count.
     */
    void increase(int numberUp) {
        this.number += numberUp;
    }

    /**
     *
     * @param numberDown subtract number from current count.
     */
    void decrease(int numberDown) {
        this.number -= numberDown;
    }

    /**
     * @return the current count.
     */
    int getValue() {
        return number;
    }
}
