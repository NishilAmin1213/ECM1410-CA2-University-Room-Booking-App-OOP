package com.company;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Room {

    // Declare Attributes
    private String code;
    final int CAPACITY; // Final as the capacity of a room cannot change
    private static Room[] createdRooms = new Room[]{};

    /**
     * Constructor for the Room class - To create a new Room
     * @param code - Room code for the room
     * @param capacity - Capacity of the room we are creating (Cannot be changed)
     */
    public Room(String code, int capacity){
        // Constructor for an Assistant

        // Check the name is non-blank
        if (Pattern.matches(" *", code)){
            // The code passed in is either ""(Empty string) OR 1 or more " "(spaces)
            System.out.println("Invalid Code - Blank Code Given");
            throw new IllegalArgumentException();
        }

        // Check the code is unique
        for (Room r:Room.createdRooms){
            // Loop through all created assistants
            if (r.code == code){
                // If an identical code is found, this is not a valid object to create.
                System.out.println("Invalid Code - Identical Code Found");
                throw new IllegalArgumentException();
            }
        }

        // Ensure the capacity is greater than 0
        if (!(capacity > 0)){
            // If the capacity isn't greater than 0
            System.out.println("Invalid Capacity - Capacity not greater than 0 is Invalid");
            throw new IllegalArgumentException();
        }

        this.code = code;
        this.CAPACITY = capacity;
        Room.addRoom(this);
    }

    /**
     * Adds a room to the static array of rooms
     * @param r Room we are adding
     */
    private static void addRoom(Room r){
        // Create new array of length one greater than the current array and make it a copy of the original array
        Room.createdRooms = Arrays.copyOf(Room.createdRooms, Room.createdRooms.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        Room.createdRooms[Room.createdRooms.length - 1] = r;
    }

    /**
     * @return - The capacity of the room
     */
    public int getCapacity() {
        return this.CAPACITY;
    }

    /**
     * @return  - The code of the room
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Overrides the toString() method to allow easy printing of this object
     * @return The string representing the data belonging to this object in the correct format
     */
    @Override
    public String toString() {
        return "| " + this.code + " | capacity: " + this.CAPACITY + " |";
    }

}
