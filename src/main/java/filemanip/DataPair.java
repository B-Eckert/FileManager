package filemanip;

/**
 * DataPair does some cool shit, basically, as simple as it is, it allows me to associate two values with each other kinda like indices or hashes but different. Ex: "Health" --> "Number".
 * This is used for variable storage as what are variables but stored pairs of data? (Name -> field.)
 * @param <T> The retriever of the data
 * @param <I> The value of the data
 */

public class DataPair<T, I> {
    T o1;
    I o2;

    /**
     * Sets up a data pair
     * @param one one is the first data point
     * @param two two is the second data point
     */
    public DataPair(T one, I two){
        o1 = one;
        o2 = two;
    }

    /**
     * Retrieves the first thing
     * @return
     */
    public T getOne(){
        return o1;
    }

    /**
     * Retrieves the second thing
     * @return
     */
    public I getTwo(){
        return o2;
    }

    /**
     * Sets the first thing (useful for changing variable names)
     * @param nOne
     */
    public void setOne(T nOne){
        o1 = nOne;
    }

    /**
     * Sets the second thing
     * @param nTwo
     */
    public void setTwo(I nTwo){
        o2 = nTwo;
    }
}
