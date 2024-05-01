/**
 *
 *
 * @Adapted from Frank M. Carrano
 * @version 2.0
 */
package adt;

import java.io.Serializable;

public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    private void doubleArray() { //
        T[] oldArray = array;
        array = (T[]) new Object[2 * array.length];
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = oldArray[i];
        }
    }

    @Override
    public boolean add(T newEntry) { //
        if (isFull()) {
            doubleArray();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            if (isFull()) {
                doubleArray();
            }
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) { ///
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
        }

        return result;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) { //
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) { ///
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int getNumberOfEntries() { //
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public String toString() { ///
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= numberOfEntries + 1; numberOfEntries is array's
     * numberOfEntries before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * numberOfEntries; numberOfEntries is array's numberOfEntries before
     * removal.
     */
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    @Override
    public void sort() {
        for (int i = 0; i < numberOfEntries - 1; i++) {
            for (int j = 0; j < numberOfEntries - i - 1; j++) {
                if (((Comparable<T>) array[j]).compareTo(array[j + 1]) > 0) {
                    // Swap array[j] and array[j + 1]
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public ListInterface<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= numberOfEntries || toIndex <= fromIndex || toIndex > numberOfEntries) {
            throw new IndexOutOfBoundsException("Invalid subList range");
        }

        ListInterface<T> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(array[i]);
        }

        return subList;
    }

    @Override
    public ListInterface<T> retainAll(ListInterface<T> listToRetain) {
        ListInterface<T> retainedList = new ArrayList<>();

        for (int i = 1; i <= listToRetain.getNumberOfEntries(); i++) {
            T currentItem = array[i];
            if (listToRetain.contains(currentItem)) {
                retainedList.add(currentItem);
            }
        }

        return retainedList;
    }

}
