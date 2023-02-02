package com.company;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BookableRoom {

    // Declare Attributes
    private Room room;
    private LocalDateTime timeSlot; // 7AM TO 10AM, AND ONE SLOT IS 1HR
    private int occupancy = 0;
    private String status;
    private static BookableRoom[] createdBookableRooms = new BookableRoom[]{};


    /**
     * Constructor for the BookableRoom class
     * @param room - Room we are making bookable
     * @param timeSlot - Timeslot for which is will be bookable
     */
    public BookableRoom(Room room, LocalDateTime timeSlot){
        // Constructor for an BookableRoom

        // Check validity of the timeSlot
        if (!(timeSlot.isEqual(LocalDateTime.of(timeSlot.getYear(), timeSlot.getMonth(), timeSlot.getDayOfMonth(), 7, 00)) ||
                timeSlot.isEqual(LocalDateTime.of(timeSlot.getYear(), timeSlot.getMonth(), timeSlot.getDayOfMonth(), 8, 00)) ||
                timeSlot.isEqual(LocalDateTime.of(timeSlot.getYear(), timeSlot.getMonth(), timeSlot.getDayOfMonth(), 9, 00)))){
            // Time is not in the allowed range for rooms (not between 7AM and 9AM)
            System.out.println("Error!\nInvalid time slot - Not in range 7AM - 9AM");
            throw new IllegalArgumentException();
        }

        // Make sure room hasn't already been booked
        for (BookableRoom r:BookableRoom.createdBookableRooms){
            if ((r.getRoom() == room)&&(r.getTimeSlot().isEqual(timeSlot))){
                // Room already booked for this time
                System.out.println("Error!\nThis room is already booked for this time");
                throw new IllegalArgumentException();
            }
        }

        this.room = room;
        this.timeSlot = timeSlot;
        BookableRoom.addBookableRoom(this);
        this.updateStatus();
    }


    /**
     * Adds a room to the static array of bookable rooms
     * @param r  - BookableRoom we are adding
     */
    private static void addBookableRoom(BookableRoom r){
        // Create new array of length one greater than the current array and make it a copy of the original array
        BookableRoom.createdBookableRooms = Arrays.copyOf(BookableRoom.createdBookableRooms, BookableRoom.createdBookableRooms.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        BookableRoom.createdBookableRooms[BookableRoom.createdBookableRooms.length - 1] = r;
    }

    /**
     * Increment the value of the Bookable Rooms occupancy, then update the Bookable Rooms status
     * @return - Whether the occupant has been successfully added (Boolean)
     */
    public boolean addOccupant(){
        if (this.status == "FULL"){
            return false;
        }else{
            // Increment Occupancy
            this.occupancy++;
            // Update Status
            this.updateStatus();
            return true;
        }
    }

    /**
     * Decrement the value of the Bookable Rooms occupancy, then update the Bookable Rooms status
     * @return - Whether the occupant has been successfully removed (Boolean)
     */
    public boolean removeOccupant(){
        if (this.status == "EMPTY"){
            return false;
        }else{
            // Decrement Occupancy
            this.occupancy--;
            // Update Status
            this.updateStatus();
            return true;
        }
    }

    /**
     * @return - The status of the Bookable Room
     */
    public String getStatus() {
        return this.status;
    }


    /**
     * Recalculate the Status of the Bookable Room - Based on occupancy and the Rooms capacity
     */
    public void updateStatus(){
        if (occupancy == 0){
            // Room is empty, set status to EMPTY
            this.status = "EMPTY";
        }else if(occupancy < this.room.getCapacity()){
            // Occupancy is less than capacity, set status to AVAILABLE
            this.status = "AVAILABLE";
        }else if(occupancy == this.room.getCapacity()){
            // Occupancy equal to capacity, set status to FULL
            this.status = "FULL";
        }
    }

    /**
     * Remove a Bookable Room from the static array of BookableRooms
     * @param r - The Bookable Room we are removing
     */
    public static void removeBookableRoom(BookableRoom r){
        if(r.status == "EMPTY"){
            // REMOVE ROOM
            // Copy all items to a new array of length one less than the original array skipping the BookableRoom specified
            BookableRoom[] temp = new BookableRoom[BookableRoom.createdBookableRooms.length-1];
            for(int i=0, j=0;i<BookableRoom.createdBookableRooms.length;i++){
                if (!(BookableRoom.createdBookableRooms[i] == r)){
                    // If the room is not the one specified, add it to the updated array
                    temp[j++] = BookableRoom.createdBookableRooms[i];
                }
            }
            BookableRoom.createdBookableRooms = temp;
        }else{
            System.out.println("Unable to remove bookable room - Room not EMPTY");
        }
    }

    /**
     * @return - TimeSlot of the BookableRoom
     */
    public LocalDateTime getTimeSlot() {
        return this.timeSlot;
    }

    /**
     * @return - Room the BookableRoom represents
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Overrides the toString() method to allow easy printing of this object
     * @return The string representing the data belonging to this object in the correct format
     */
    @Override
    public String toString() {
        return "| " + this.timeSlot + " | " + this.status + " | " + this.room.getCode() + " | occupancy: " + this.occupancy + " |";
    }
}
