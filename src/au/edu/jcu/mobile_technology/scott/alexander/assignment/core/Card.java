package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * General representation of the Cards to be displayed on a device
 */
public class Card implements Parcelable {
    private ArrayList<Integer> values;

    /**
     * Default constructor
     */
    public Card() {

    }

    public Card(Parcel parcel) {
        values = (ArrayList<Integer>) parcel.readSerializable();
    }

    /**
     * Creates a new Card with an initial starting value
     *
     * @param initialValue
     */
    public Card(int initialValue) {
        getValues().add(initialValue);
    }

    /**
     * Gets a list of values within the card
     *
     * @return Card values
     */
    public List<Integer> getValues() {
        if (values == null) {
            values = new ArrayList<Integer>();
        }
        return values;
    }

    /**
     * Adds a value to the card
     *
     * @param value Value to add
     */
    public void add(int value) {
        if (!contains(value)) {
            values.add(value);
        }
    }

    /**
     * Checks if the card contains a specified value
     *
     * @param value Value to find
     * @return True if found
     */
    public boolean contains(int value) {
        return getValues().contains(value);
    }


    /**
     * PARCELABLE METHODS USED TO PASS CARDS BETWEEN ACTIVITIES WITHIN A BUNDLE
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(values);
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel parcel) {
            return new Card(parcel);
        }

        @Override
        public Card[] newArray(int i) {
            return new Card[i];
        }
    };
}
