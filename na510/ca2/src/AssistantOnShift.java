package com.company;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

public class AssistantOnShift {

    // Declare Attributes
    private Assistant assistant;
    private LocalDateTime timeSlot;
    private String status = "FREE";
    private static AssistantOnShift[] createdAssistantsOnShift = new AssistantOnShift[]{};


    /**
     * Constructor for the AssistantOnShift class - To create a new AssistantOnShift
     * @param assistant - Assistant we are creating the shift for
     * @param timeSlot - Timeslot the shift is for
     */
    public AssistantOnShift(Assistant assistant, LocalDateTime timeSlot){
        // Check validity of the timeSlot (ONE OF 3 SLOTS)
        if (!(timeSlot.isEqual(LocalDateTime.of(timeSlot.getYear(), timeSlot.getMonth(), timeSlot.getDayOfMonth(), 7, 00)) ||
                timeSlot.isEqual(LocalDateTime.of(timeSlot.getYear(), timeSlot.getMonth(), timeSlot.getDayOfMonth(), 8, 00)) ||
                timeSlot.isEqual(LocalDateTime.of(timeSlot.getYear(), timeSlot.getMonth(), timeSlot.getDayOfMonth(), 9, 00)))){
            // Time is not in the allowed range for rooms (not between 7AM and 9AM)
            System.out.println("Invalid time slot - Not in range 7AM - 9AM");
            throw new IllegalArgumentException();
        }

        // Check if this shift time hasn't already been created
        for(AssistantOnShift a:AssistantOnShift.createdAssistantsOnShift){
            if ((a.getAssistant() == assistant)&&(a.getTimeSlot().isEqual(timeSlot))){
                // Assistant has another identical shift
                System.out.println("Invalid assistant - they already have this shift");
                throw new IllegalArgumentException();
            }
        }

        this.assistant = assistant;
        this.timeSlot = timeSlot;
        AssistantOnShift.addAssistantOnShift(this);

    }


    /**
     * Adds an AssistantOnshift to the static array of assistantsOnShift
     * @param a - AssistantOnShift we are adding
     */
    private static void addAssistantOnShift(AssistantOnShift a){
        // Create new array of length one greater than the current array and make it a copy of the original array
        AssistantOnShift.createdAssistantsOnShift = Arrays.copyOf(AssistantOnShift.createdAssistantsOnShift, AssistantOnShift.createdAssistantsOnShift.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        AssistantOnShift.createdAssistantsOnShift[AssistantOnShift.createdAssistantsOnShift.length - 1] = a;
    }

    /**
     * Removes an AssistantOnshift from the static array of assistantsOnShift
     * @param a - AssistantOnShift we are removing
     */
    public static void removeAssistantOnShift(AssistantOnShift a){
        if(a.status == "FREE"){
            // REMOVE ASSISTANT
            // Copy all items to a new array of length one less than the original array skipping the BookableRoom specified
            AssistantOnShift[] temp = new AssistantOnShift[AssistantOnShift.createdAssistantsOnShift.length-1];
            for(int i=0;i<AssistantOnShift.createdAssistantsOnShift.length-1;i++){
                if (!(AssistantOnShift.createdAssistantsOnShift[i] == a)){
                    // If the room is not the one specified, add it to the updated array
                    temp[i] = AssistantOnShift.createdAssistantsOnShift[i];
                }
            }
            AssistantOnShift.createdAssistantsOnShift = temp;
        }else{
            System.out.println("Unable to remove bookable room - Room not EMPTY");
        }
    }

    /**
     * Makes the status of the AssistantOnShift BUSY
     */
    public void makeBusy(){
        this.status = "BUSY";
    }

    /**
     * Makes the status of the AssistantOnShift FREE
     */
    public void makeFree(){
        this.status = "FREE";
    }

    /**
     * @return - TimeSlot for the AssistantOnShift
     */
    public LocalDateTime getTimeSlot() {
        return this.timeSlot;
    }

    /**
     * @return - Status for the AssistantOnShift
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @return - Assistant the AssistantOnShift represents
     */
    public Assistant getAssistant() {
        return this.assistant;
    }

    /**
     * Overrides the toString() method to allow easy printing of this object
     * @return The string representing the data belonging to this object in the correct format
     */
    @Override
    public String toString() {
        return "| " + this.timeSlot + " | " + this.status + " | " + this.assistant.getEmail() + " |";
    }
}
