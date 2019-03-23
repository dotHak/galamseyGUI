// Package name
package monitoring.gui.model;

// Dependecies
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *  Definition of the Hashmap class
 * @author Hubert, Lisa, Bobie, Jones
 * @version 1.0.0
 */
public class HashMap<T> {

    // Instant variables.
    private static int TABLE_SIZE = 100;
    private int size;

    LinkedHashEntry<T>[] table;


    // Hash map constructor
    public HashMap() {

        table = new LinkedHashEntry[TABLE_SIZE];

        for (int i = 0; i < TABLE_SIZE; i++)

            table[i] = null;

    }

    public int getSize() {
        return size;
    }

    /**
     * Get method to retrieve object from the hash table
     *
     * @param key
     * @return
     */
    public T get(int key) {
        T temp = null;
        int hash = (key % TABLE_SIZE);

        if (table[hash] == null)

            System.out.println("No object exist for the key");

        else {

            LinkedHashEntry <T> entry = table[hash];

            while (entry != null && entry.getKey() != key)

                entry = entry.getNext();

            if (entry == null)

                System.out.println("No object exist for key");

            else

                temp = entry.getValue();

        }
        return temp;
    }

    /**
     *  Method for retrieving all objects from the hash table
     * @return
     */
    public ObservableList<T> getAllItems(){

        ObservableList<T> allItems = FXCollections.observableArrayList();
        if(size == 0 ){
            System.out.println("No items");
        }
        else{
            for(int i =0; i <= size; i++) {
                LinkedHashEntry <T> entry = table[i];
                while (entry != null){
                    allItems.add(entry.getValue());
                    entry = entry.getNext();
                }
            }
        }

        return  allItems;
    }

    /**
     * Add method for appending patient to hash table.
     *
     * @param key
     * @param value
     */
    public void add(int key, T value) {

        int hash = (key % TABLE_SIZE);

        if (table[hash] == null)

            table[hash] = new LinkedHashEntry(key, value);

        else {

            LinkedHashEntry entry = table[hash];

            while (entry.getNext() != null && entry.getKey() != key )

                entry = entry.getNext();

            if (entry.getKey() == key)

                entry.setValue(value);

            else

                entry.setNext(new LinkedHashEntry(key, value));

        }
        size++;

    }


    /**
     * Remove function for delete patient from hash table
     *
     * @param key
     */
    public void remove(int key) {

        int hash = (key % TABLE_SIZE);

        if (table[hash] != null) {

            LinkedHashEntry prevEntry = null;

            LinkedHashEntry entry = table[hash];

            while (entry.getNext() != null && entry.getKey() != key) {

                prevEntry = entry;

                entry = entry.getNext();

            }

            if (entry.getKey() == key) {

                if (prevEntry == null)

                    table[hash] = entry.getNext();

                else

                    prevEntry.setNext(entry.getNext());

            }

        }
        size--;
    }
}


