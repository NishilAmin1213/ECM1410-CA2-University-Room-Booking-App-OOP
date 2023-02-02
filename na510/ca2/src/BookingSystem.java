package com.company;
/*
3. There is a time-slot concept that will guide the booking system. For instance, rooms will be available, and
assistants will work at a specific time-slot, i.e., date, time and duration. Hence, tests should be booked at
available slots.

4. Every time-slot has a fixed duration – a positive number representing the duration of a test, in minutes. This
quantity includes the time spent doing the test and the time to sanitize the room. The current policy establishes
this duration to be 60 minutes.
*/

import java.util.Arrays;

public class BookingSystem {

    // Declare Attributes
    BookableRoom[] bookableRooms = new BookableRoom[]{};
    AssistantOnShift[] assistantsOnShift = new AssistantOnShift[]{};
    Booking[] bookings = new Booking[]{};


    /**
     * Constructor for the booking system
     */
    public BookingSystem(){
    }




    // ADD, REMOVE AND SHOW FOR BOOKABLE ROOMS

    /**
     * @return - Array of bookable rooms belonging to the BookingSystem
     */
    public BookableRoom[] getBookableRooms() {
        return bookableRooms;
    }

    /**
     * Add a bookable room to the array of bookable rooms in the booking system
     * @param r - Bookable room we are adding
     */
    public void addBookableRoom(BookableRoom r){
        // Create new array of length one greater than the current array and make it a copy of the original array
        this.bookableRooms = Arrays.copyOf(this.bookableRooms, this.bookableRooms.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        this.bookableRooms[this.bookableRooms.length - 1] = r;
    }


    /**
     * Remove a bookable room from the array of bookable rooms in the system
     * @param r - Bookable room we are removing
     */
    public void removeBookableRoom(BookableRoom r){
        // Copy all items to a new array of length one less than the original array skipping the BookableRoom specified
        BookableRoom[] temp = new BookableRoom[this.bookableRooms.length-1];
        for(int i=0, j=0;i<this.bookableRooms.length;i++){
            if (!(this.bookableRooms[i] == r)){
                // If the room is not the one specified, add it to the updated array
                temp[j++] = this.bookableRooms[i];
            }
        }
        BookableRoom.removeBookableRoom(r);
        this.bookableRooms = temp;
    }

    /**
     * Print out all the Booking Systems Bookable Rooms (With ID values)
     */
    public void showBookableRooms(){
        int counter = 10;
        for (BookableRoom r:this.bookableRooms){
            System.out.println("    " + (counter++) + ". " + r);
        }
    }

    // ADD, REMOVE AND SHOW FOR ASSISTANTS ON SHIFT

    /**
     * @return - Array of Assistant On Shifts belonging to the BookingSystem
     */
    public AssistantOnShift[] getAssistantsOnShift() {
        return assistantsOnShift;
    }

    /**
     * Add an Assistant On Shift to the array of assistants on shift in the booking system
     * @param a - Assistant On Shift we are adding
     */
    public void addAssistantOnShift(AssistantOnShift a){
        // Create new array of length one greater than the current array and make it a copy of the original array
        this.assistantsOnShift = Arrays.copyOf(this.assistantsOnShift, this.assistantsOnShift.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        this.assistantsOnShift[this.assistantsOnShift.length - 1] = a;
    }

    /**
     * Remove an Assistant On Shift from the array of assistant on shifts in the system
     * @param a - Bookable room we are removing
     */
    public void removeAssistantOnShift(AssistantOnShift a){
        // Copy all items to a new array of length one less than the original array skipping the BookableRoom specified
        AssistantOnShift[] temp = new AssistantOnShift[this.assistantsOnShift.length-1];
        for(int i=0;i<this.assistantsOnShift.length-1;i++){
            if (!(this.assistantsOnShift[i] == a)){
                // If the room is not the one specified, add it to the updated array
                temp[i] = this.assistantsOnShift[i];
            }
        }
        AssistantOnShift.removeAssistantOnShift(a);
        this.assistantsOnShift = temp;
    }

    /**
     * Print out all the Booking Systems Assistants On Shift (With ID values)
     */
    public void showAssistantsOnShift(){
        int counter = 10;
        for (AssistantOnShift a:this.assistantsOnShift){
            System.out.println("    " + (counter++) + ". " + a);
        }
    }

    // ADD, REMOVE AND SHOW FOR BOOKINGS

    /**
     * @return - Array of bookings belonging to the BookingSystem
     */
    public Booking[] getBookings() {
        return bookings;
    }

    /**
     * Add a Booking to the array of bookings in the booking system
     * @param b - Booking we are adding
     */
    public void addBooking(Booking b){
        // Create new array of length one greater than the current array and make it a copy of the original array
        this.bookings = Arrays.copyOf(this.bookings, this.bookings.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        this.bookings[this.bookings.length - 1] = b;
    }

    /**
     * Remove a booking from the array of bookings in the system
     * @param b - Bookable room we are removing
     */
    public void removeBooking(Booking b){
        // Copy all items to a new array of length one less than the original array skipping the BookableRoom specified
        Booking[] temp = new Booking[this.bookings.length-1];
        for(int i=0, j=0;i<this.bookings.length;i++){
            if (!(this.bookings[i] == b)){
                // If the room is not the one specified, add it to the updated array
                temp[j++] = this.bookings[i];
            }else{
            }
        }
        Booking.removeBooking(b);
        this.bookings = temp;
    }

    /**
     * Print out all the Booking Systems Bookings (With ID values)
     */
    public void showBookings(){
        int counter = 10;
        for (Booking b:this.bookings){
            System.out.println("    " + (counter++) + ". " + b);
        }
    }

}
