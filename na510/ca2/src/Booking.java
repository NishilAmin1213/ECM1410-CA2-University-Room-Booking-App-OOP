package com.company;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Booking {

    // Declare Attributes
    private BookableRoom bookableRoom;
    private AssistantOnShift assistantOnShift;
    private LocalDateTime timeSlot;
    private int idCode;
    private String studentEmail;
    private String status = "SCHEDULED";
    private static Booking[] bookings = new Booking[]{};


    /**
     * Constructor for the Booking class - To create a new Booking
     * @param bookableRoom - BookableRoom the booking takes place
     * @param assistantOnShift - AssistantOnShift that will be present
     * @param studentEmail - Email of the student the booking is for
     */
    public Booking(BookableRoom bookableRoom, AssistantOnShift assistantOnShift, String studentEmail){

        // Check that the room is not FULL
        if (bookableRoom.getStatus() == "FULL"){
            // The bookable room is FULL, hence we cannot create this booking
            System.out.println("Bookable Room is FULL - Not able to overflow this room :(");
            throw new IllegalArgumentException();
        }

        // Check that the assistant is FREE
        if(assistantOnShift.getStatus() == "BUSY"){
            // The assistant on shift is BUSY, hence we cannot create this booking
            System.out.println("AssistantOnShift is BUSY - Not able to overflow this room :(");
            throw new IllegalArgumentException();
        }

        // Check the email is in a correct format
        if (!(Pattern.matches(".+@uok.ac.uk", studentEmail))){
            // The email passed in follows the format [*]@uok.ac.uk (where [*] is 1 or more chars (i.e. not blank))
            System.out.println("Invalid Email - String format incorrect");
            throw new IllegalArgumentException();
        }

        // Make sure BookableRoom is same time as assistantOnShift
        if(!(assistantOnShift.getTimeSlot().isEqual(bookableRoom.getTimeSlot()))){
            // The room booking an assistant on shift times are not equal
            System.out.println("INVALID TIME SLOT -  for either room booking or assistant");
            throw new IllegalArgumentException();
        }


        // MAKE ASSISTANT BUSY
        assistantOnShift.makeBusy();
        // INCREMENT BOOKABLE ROOM OCCUPANCY
        bookableRoom.addOccupant();

        this.bookableRoom = bookableRoom;
        this.assistantOnShift = assistantOnShift;
        this.timeSlot = bookableRoom.getTimeSlot();
        this.idCode = Booking.bookings.length;
        this.studentEmail = studentEmail;
        Booking.addBooking(this);
    }

    /**
     * Delete a booking - Remove and free up associated BookableRoom and Assistant
     * @param b - Booking we are deleting data for
     */
    public static void deleteBooking(Booking b){
        if (b.status == "COMPLETED"){
            System.out.println("CANNNOT DELETE BOOKING - HAS ALREADY BEEN COMPLETED");
        }else {
            //NEED TO FREE THE ASSISTANT
            b.assistantOnShift.makeFree();
            //NEED TO DECREMENT THE ROOM OCCUPANCY
            b.bookableRoom.removeOccupant();
        }
    }

    /**
     * Remove a booking from the static array of bookings
     * @param b - Booking we are removing
     */
    public static void removeBooking(Booking b){
        if(b.status == "SCHEDULED"){
            // REMOVE BOOKING
            // Copy all items to a new array of length one less than the original array skipping the Booking specified
            Booking[] temp = new Booking[Booking.bookings.length-1];
            for(int i=0, j=0;i<Booking.bookings.length-1;i++){
                if (!(Booking.bookings[i] == b)){
                    // If the room is not the one specified, add it to the updated array
                    temp[j++] = Booking.bookings[i];
                }
            }
            Booking.bookings = temp;
            Booking.deleteBooking(b);
        }else{
            System.out.println("Unable to remove booking - Booking has been completed");
        }
    }

    /**
     * Add booking to the static array of bookings
     * @param b - Booking we are adding
     */
    private static void addBooking(Booking b){
        // Create new array of length one greater than the current array and make it a copy of the original array
        Booking.bookings = Arrays.copyOf(Booking.bookings, Booking.bookings.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        Booking.bookings[Booking.bookings.length - 1] = b;
    }

    /**
     * @return - Status of the Booking
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the bookings status to COMPLETED
     */
    public void completeBooking(){
        this.status = "COMPLETED";
    }

    /**
     * Overrides the toString() method to allow easy printing of this object
     * @return The string representing the data belonging to this object in the correct format
     */
    @Override
    public String toString() {
        return "| " + this.timeSlot + " | " + this.status + " | " + this.assistantOnShift.getAssistant().getEmail() + " | " +
                this.bookableRoom.getRoom().getCode() + " | " + this.studentEmail + " |";
    }
}
