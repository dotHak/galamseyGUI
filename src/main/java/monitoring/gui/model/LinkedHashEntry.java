package monitoring.gui.model;

/**
 * Definitions of a linked list class for hash map.
 * @author Hubert, Lisa, Jones, Bobie
 * @version 1.0.0
 */
public class LinkedHashEntry<T> {

    // Instantiating the  variable for the for class
    private int key;

    private T value;

    private LinkedHashEntry next;


    /**
     *  Overloaded constructor for the linked list.
     * @param key
     * @param value
     */
    LinkedHashEntry(int key, T value) {

        this.key = key;

        this.value = value;

        this.next = null;

    }


    /**
     *  Accessor method  for the value
     * @return the value
     */
    public T getValue() {

        return value;

    }


    /**
     *  Mutator method for the value
     * @param value the new value
     */
    public void setValue(T value) {
        if (value != null){
            this.value = value;
        }

    }


    /**
     * Accessor method for the key
     * @return the key
     */
    public int getKey() {

        return key;

    }


    /**
     * Accessor method for the next object
     * @return the next object
     */
    public LinkedHashEntry getNext() {

        return next;

    }


    /**
     * Mutator method for next object
     * @param next the object
     */
    public void setNext(LinkedHashEntry next) {

        this.next = next;

    }

}