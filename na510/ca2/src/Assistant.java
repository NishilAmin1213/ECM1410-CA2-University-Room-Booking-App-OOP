package com.company;
import java.util.Arrays;
import java.util.regex.*;

public class Assistant {

    // Declare Attributes
    private String name;
    private String email;
    private static Assistant[] createdAssistants = new Assistant[]{};

    /**
     * Constructor for the Assistant class - To create a new Assistant
     * @param name - Name of the assistant we are creating
     * @param email - Email of the assistant we are creating
     */
    public Assistant(String name, String email){
        // Constructor for an Assistant

        // Check the name is non-blank
        if (Pattern.matches(" *",name)){
            // The name passed in is either ""(Empty string) OR 1 or more " "(spaces)
            System.out.println("Invalid Name - Blank Name Given");
            throw new IllegalArgumentException();
        }

        // Check the email is in a correct format
        if (!(Pattern.matches(".+@uok.ac.uk",email))){
            // The email passed in follows the format [*]@uok.ac.uk (where [*] is 1 or more chars (i.e. not blank))
            System.out.println("Invalid Email - String format incorrect");
            throw new IllegalArgumentException();
        }

        // Check the email is unique
        for (Assistant a:Assistant.createdAssistants){
            // Loop through all created assistants
            if (a.email == email){
                // If an identical email is found, this is not a valid object to create.
                System.out.println("Invalid Email - Identical Email Found");
                throw new IllegalArgumentException();
            }
        }

        // Finish instantiation as the parameters are valid
        this.name = name;
        this.email = email;
        Assistant.addAssistant(this);
    }

    /**
     * @return The email belonging to this assistant
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Adds an assistant to the static array of assistants
     * @param a - Assistant we are adding
     */
    public static void addAssistant(Assistant a){
        // Create new array of length one greater than the current array and make it a copy of the original array
        Assistant.createdAssistants = Arrays.copyOf(Assistant.createdAssistants, Assistant.createdAssistants.length + 1);
        // Add the new element to the last index of the new array (which should be empty)
        Assistant.createdAssistants[Assistant.createdAssistants.length - 1] = a;
    }

    /**
     * Overrides the toString() method to allow easy printing of this object
     * @return The string representing the data belonging to this object in the correct format
     */
    @Override
    public String toString() {
        return "| " + this.name + " | " + this.email + " |";
    }
}
