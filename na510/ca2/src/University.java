package com.company;

import java.util.Arrays;

public class University {

    // Declare Attributes
    private Assistant[] assistants = new Assistant[]{};
    private Room[] rooms = new Room[]{};

    /**
     * Constructor for University
     */
    public University(){
    }


    /**
     * Print out all the universities Assistants (With ID values)
     */
    public void showAssistants(){
        int counter = 10;
        for (Assistant a:this.assistants){
            System.out.println("    " + (counter++) + ". " + a);
        }
    }

    /**
     * Print out all the universities Rooms (With ID values)
     */
    public void showRooms(){
        int counter = 10;
        for (Room r:this.rooms){
            System.out.println("    " + (counter++) + ". " + r);
        }
    }

    /**
     * @return - Array of Assistants belonging to the university
     */
    public Assistant[] getAssistants() {
        return assistants;
    }

    /**
     * @return - Array of Rooms belonging to the university
     */
    public Room[] getRooms() {
        return rooms;
    }

    /**
     * Add an assistant to the universities array of assistants
     * @param a - Assistant we are adding to the university
     */
    public void addAssistant(Assistant a){
        // Create new array of length one greater than the current array and make it a copy of the original array
        this.assistants = Arrays.copyOf(this.assistants, this.assistants.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        this.assistants[this.assistants.length - 1] = a;
    }

    /**
     * Add a room to the universities array of rooms
     * @param r - Room we are adding to the university
     */
    public void addRoom(Room r){
        // Create new array of length one greater than the current array and make it a copy of the original array
        this.rooms = Arrays.copyOf(this.rooms, this.rooms.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        this.rooms[this.rooms.length - 1] = r;
    }
}
