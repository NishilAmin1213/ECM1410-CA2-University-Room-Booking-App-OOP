package com.company;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookingApp {

    /**
     * Main System for the BookingApp
     * @param args - ARGS FOR MAIN
     */
    public static void main(String[] args){

        // Create a University and BookingSystem
        University university = new University();
        BookingSystem bookingSystem = new BookingSystem();

        // Run function to hard code all necessary objects.
        hardCodeObjects(university, bookingSystem);

        // Create Scanner for use later
        Scanner s = new Scanner(System.in);

        // RUN THIS CODE CONTINUOUSLY
        while(true){

            //CLEAR CONSOLE
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Print the Main Menu
            printMenu();

            // Get choice input from the user in the form of an integer
            int choice = getInput(s);

            if (choice == 1){
                // LIST BOOKABLE ROOMS
                System.out.println("University of Knowledge - COVID test\n");
                bookingSystem.showBookableRooms();
                String msg = "0. Back to main menu.\n-1. Quit application.\n";
                do{
                    //GET INPUT FROM USER FOR NEXT DECISION
                    System.out.println(msg);
                    choice = getInput(s);
                    if(choice !=0){
                        msg = "Input NOT valid:\n0. Back to main menu.\n-1. Quit application.\n";
                    }
                }while(choice != 0);

            }else if(choice == 2) {
                // ADD BOOKABLE ROOMS
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n\nAdding bookable room\n");
                // Show Rooms
                university.showRooms();
                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), separated by a white space.");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    // Get string input from the user
                    String myStr = getString(s);
                    // Check if the input means leave (back to menu)
                    if (myStr.equals("0")) {
                        exit = true;
                    // Check if the string is in the correct format
                    }else if(!(myStr.matches("\\d\\d\\s\\d\\d/\\d\\d/\\d\\d\\d\\d\\s\\d\\d:\\d\\d"))){
                        msg = "Error!\nFormat not matched (xx xx/xx/xx xx:xx)";
                    }else{
                        // Split the input string into its components
                        String[] result = myStr.split(" ");
                        try{
                            // Find the room we are making a Bookable Room for
                            Room r = university.getRooms()[Integer.valueOf(result[0])-10];
                            // Create a Local Date Time instance based on the users imput
                            LocalDateTime dateTime = LocalDateTime.of(Integer.valueOf(result[1].split("/")[2]), Integer.valueOf(result[1].split("/")[1]), Integer.valueOf(result[1].split("/")[0]), Integer.valueOf(result[2].split(":")[0]), Integer.valueOf(result[2].split(":")[1]));
                            try{
                                // Create the bookable room using the users inputs
                                BookableRoom b = new BookableRoom(r, dateTime);
                                // Add this room to the booking system
                                bookingSystem.addBookableRoom(b);
                                // Display Message
                                System.out.println("Bookable Room added successfully:");
                                System.out.println(b);
                            // Catch possible errors and give a message for these
                            }catch(IllegalArgumentException a){
                                continue;
                            }catch(DateTimeException d){
                                msg = "Error!\nInvalid Date or Time Entered";
                                continue;
                            }
                        }catch(ArrayIndexOutOfBoundsException e){
                            msg = "Error!\nInvalid ID Entered";
                            continue;
                        }
                    }
                }

            }else if(choice == 3){
                // REMOVE BOOKABLE ROOMS
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n");

                // Display all bookable rooms that are able to be deleted
                int counter = 10;
                ArrayList<BookableRoom> emptyRooms = new ArrayList<BookableRoom>();
                for (BookableRoom b:bookingSystem.getBookableRooms()){
                    if (b.getStatus().equals("EMPTY")){
                        emptyRooms.add(b);
                        System.out.println(counter++ + ". " + b);
                    }
                }

                System.out.println("Removing bookable room");
                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID to select the bookable room to be removed");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    // Get integer value for users choice of room to delete
                    int myInt = getInput(s);
                    // Check if the input means leave (back to menu)
                    if (myInt == 0) {
                        exit = true;
                    }else{
                        try {
                            // Find room we are deleting
                            BookableRoom b = emptyRooms.get(myInt - 10);
                            // Display Complete Message
                            bookingSystem.removeBookableRoom(b);
                            System.out.println("Bookable Room removed successfully:\n"+b);
                        // Catch possible errors and give a message for these
                        }catch(IndexOutOfBoundsException i){
                            msg = "Error!\nID is invalid";
                        }
                    }
                }

            }else if(choice == 4){
                // LIST ASSISTANTS ON SHIFT
                System.out.println("University of Knowledge - COVID test\n");
                // Show assistants on shift
                bookingSystem.showAssistantsOnShift();
                String msg = "0. Back to main menu.\n-1. Quit application.\n";
                do{
                    //GET INPUT FROM USER FOR NEXT DECISION
                    System.out.println(msg);
                    choice = getInput(s);
                    if(choice !=0){
                        msg = "Input NOT valid:\n0. Back to main menu.\n-1. Quit application.\n";
                    }
                }while(choice != 0);

            }else if(choice == 5){
                // ADD ASSISTANTS ON SHIFT
                int[] times = new int[]{7,8,9};
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n\nAdding assistant on shift\n");
                university.showAssistants();
                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID listed to a room, a date (dd/mm/yyyy), separated by a white space.");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    // Get input from the user (format of string)
                    String myStr = getString(s);
                    // Check if the input means leave (back to menu)
                    if (myStr.equals("0")) {
                        exit = true;
                    // Check if the string is in the correct format
                    }else if(!(myStr.matches("\\d\\d\\s\\d\\d/\\d\\d/\\d\\d\\d\\d"))){
                        msg = "Error!\nFormat not matched (xx xx/xx/xx)";
                    }else{

                        String[] result = myStr.split(" ");
                        for (int i:times){
                            try{
                                Assistant a = university.getAssistants()[Integer.valueOf(result[0])-10];
                                LocalDateTime dateTime = LocalDateTime.of(Integer.valueOf(result[1].split("/")[2]), Integer.valueOf(result[1].split("/")[1]), Integer.valueOf(result[1].split("/")[0]), i, 00);
                                try{
                                    AssistantOnShift shift = new AssistantOnShift(a, dateTime);
                                    bookingSystem.addAssistantOnShift(shift);
                                    System.out.println("Bookable Room added successfully:");
                                    System.out.println(shift);
                                }catch(IllegalArgumentException x){
                                    continue;
                                }catch(DateTimeException d){
                                    msg = "Error!\nInvalid Date or Time Entered";
                                    continue;
                                }
                            }catch(ArrayIndexOutOfBoundsException e){
                                msg = "Error!\nInvalid ID Entered";
                                continue;
                            }
                        }
                    }
                }

            }else if(choice == 6){
                // REMOVE ASSISTANT ON SHIFT
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n");

                // Display all Assistant on Shifts that are able to be deleted
                int counter = 10;
                ArrayList<AssistantOnShift> freeShifts = new ArrayList<AssistantOnShift>();
                for (AssistantOnShift a:bookingSystem.getAssistantsOnShift()){
                    if (a.getStatus().equals("FREE")){
                        freeShifts.add(a);
                        System.out.println(counter++ + ". " + a);
                    }
                }

                System.out.println("Removing assistant on shift");
                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID to select the bookable room to be removed");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    // Get integer value for users choice of room to delete
                    int myInt = getInput(s);
                    // Check if the input means leave (back to menu)
                    if (myInt == 0) {
                        exit = true;
                    }else{
                        try {
                            AssistantOnShift a = freeShifts.get(myInt - 10);
                            bookingSystem.removeAssistantOnShift(a);
                            System.out.println("Bookable Room removed successfully:\n"+a);
                        // Catch possible errors and give a message for these
                        }catch(IndexOutOfBoundsException i){
                            msg = "Error!\nID is invalid";
                        }
                    }
                }

            }else if(choice == 7){
                // LIST BOOKINGS
                System.out.println("University of Knowledge - COVID test\n");
                System.out.println("Select which booking to list:\n1. All\n2.  Only bookings status:SCHEDULED\n3.  Only bookings status:COMPLETED");
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                // Get Input on what type of bookings to be displayed
                choice = getInput(s);
                // Display SCHEDULED bookings
                if(choice == 2){
                    int counter = 10;
                    for(Booking b:bookingSystem.getBookings()){
                        if (b.getStatus() == "SCHEDULED"){
                            System.out.println("    " + (counter++) + ". " + b);
                        }
                    }
                // Display COMPLETED bookings
                }else if(choice == 3){
                    int counter = 10;
                    for(Booking b:bookingSystem.getBookings()){
                        if (b.getStatus() == "COMPLETED"){
                            System.out.println("    " + (counter++) + ". " + b);
                        }
                    }
                // Display ALL bookings
                }else if(choice == 0){
                    continue;
                }else{
                    bookingSystem.showBookings();
                }
                String msg = "0. Back to main menu.\n-1. Quit application.\n";
                do{
                    //GET INPUT FROM USER FOR NEXT DECISION
                    System.out.println(msg);
                    choice = getInput(s);
                    if(choice !=0){
                        msg = "Input NOT valid:\n0. Back to main menu.\n-1. Quit application.\n";
                    }
                }while(choice != 0);

            }else if(choice == 8){
                // ADD BOOKINGS
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n\nAdding booking (appointment for a COVID test) to the system\n");

                int counter = 10;
                ArrayList<BookableRoom> bookableRooms = new ArrayList<BookableRoom>();
                ArrayList<AssistantOnShift> assistantsOnShift = new ArrayList<AssistantOnShift>();
                // PRINT OPTIONS
                for(BookableRoom b:bookingSystem.getBookableRooms()){
                    if(b.getStatus() != "FULL"){
                        for(AssistantOnShift a:bookingSystem.getAssistantsOnShift()){
                            if(a.getStatus() == "FREE" && a.getTimeSlot().isEqual(b.getTimeSlot())){
                                //THIS IS A VIABLE OPTION
                                bookableRooms.add(b);
                                assistantsOnShift.add(a);
                                System.out.println("    " + counter++ + ". " + a.getTimeSlot());
                            }
                        }
                    }
                }

                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID of an available time-slot and the student email, separated by a white space.");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    String myStr = getString(s);
                    if (myStr.equals("0")) {
                        exit = true;
                    }else if(!(myStr.matches("\\d+\\s.+@uok.ac.uk"))){
                        msg = "Error!\nFormat not matched (xx x@uok.ac.uk)";
                    }else{
                        try {
                            String[] result = myStr.split(" ");
                            bookingSystem.addBooking(new Booking(bookableRooms.get(Integer.valueOf(result[0]) - 10), assistantsOnShift.get(Integer.valueOf(result[0]) - 10), result[1]));
                        }catch(IndexOutOfBoundsException i){
                            msg = "ID entered is not valid";
                        }
                    }
                }

            }else if(choice == 9){
                // REMOVE BOOKINGS
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n");

                // Display all bookings that are able to be deleted
                int counter = 10;
                ArrayList<Booking> scheduledBookings = new ArrayList<Booking>();
                for(Booking b:bookingSystem.getBookings()) {
                    if (b.getStatus() == "SCHEDULED") {
                        System.out.println("    " + (counter++) + ". " + b);
                        scheduledBookings.add(b);
                    }
                }
                System.out.println("Removing booking from system");
                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID to select the bookable room to be removed");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    // Get integer value for users choice of room to delete
                    int myInt = getInput(s);
                    if (myInt == 0) {
                        exit = true;
                    }else{
                        try {
                            // Find booking we are deleting
                            Booking b = scheduledBookings.get(myInt - 10);
                            bookingSystem.removeBooking(b);
                            // Display Complete Message
                            System.out.println("Bookable Room removed successfully:\n"+b);
                        // Catch possible errors and give a message for these
                        } catch(IndexOutOfBoundsException i){
                            msg = "Error!\nID is invalid";
                        }
                    }
                }
            }else if(choice == 10){
                // CONCLUDE BOOKINGS
                String msg = "";
                System.out.println("University of Knowledge - COVID test\n");

                // Display all bookings that can be concluded
                int counter = 10;
                ArrayList<Booking> scheduledBookings = new ArrayList<Booking>();
                for(Booking b:bookingSystem.getBookings()) {
                    if (b.getStatus() == "SCHEDULED") {
                        System.out.println("    " + (counter++) + ". " + b);
                        scheduledBookings.add(b);
                    }
                }

                System.out.println("Conclude booking");
                boolean exit = false;
                while (!exit){
                    System.out.println(msg);
                    msg = "";
                    System.out.println("Please, enter one of the following:\n\nThe sequential ID to select the booking to be completed");
                    System.out.println("0. Back to main menu.\n-1. Quit application.\n");
                    // Get user input for which booking to complete
                    int myInt = getInput(s);
                    if (myInt == 0) {
                        exit = true;
                    }else{
                        try {
                            // find booking and complete it
                            Booking b = scheduledBookings.get(myInt - 10);
                            b.completeBooking();
                            // Display completed message
                            System.out.println("Booking completed successfully:\n"+b);
                        // Catch possible errors and give a message for these
                        } catch(IndexOutOfBoundsException i){
                            msg = "Error!\nID is invalid";
                        }
                    }
                }
            }
        }
    }


    /**
     * Get a String from the user
     * @param s - Scanner instance
     * @return - String entered by the user
     */
    private static String getString(Scanner s){
        String res = s.nextLine();
        if (res.equals("-1")){
            System.exit(0);
        }
        return res;
    }


    /**
     * Get and Integer from the user
     * @param s - Scanner instance
     * @return - Integer entered by the user
     */
    private static int getInput(Scanner s){
        int res = s.nextInt();
        s.nextLine();
        // If the result is -1, then exit the system
        if (res == -1){
            System.exit(0);
        }
        return res;
    }

    /**
     * Print the Main Menu for the System
     */
    private static void printMenu(){
        System.out.println("University of Knowledge - COVID test \n\nManage Bookings\n\nPlease, enter the number to select your option:\n\nTo manage Bookable Rooms:\n" +
                "    1. List\n    2. Add\n    3. Remove\nTo manage Assistants on Shift:\n    4. List\n    5. Add\n    6. Remove\nTo manage Bookings:\n    7. List\n" +
                "    8. Add\n    9. Remove\n    10. Conclude");
    }

    /**
     * HARD CODE to create all the required instances for the rest of the code
     * @param university - University Instance we are creating rooms and assistants for
     * @param bookingSystem - Booking System we are creating AssistantOnShifts, Bookings and BookableRooms for
     */
    private static void hardCodeObjects(University university, BookingSystem bookingSystem){
        //CREATE 3 ASSISTANTS
        university.addAssistant(new Assistant("John Doe", "jd123@uok.ac.uk"));
        university.addAssistant(new Assistant("James Smith", "js954@uok.ac.uk"));
        university.addAssistant(new Assistant("David Clark", "dc357@uok.ac.uk"));
        //CREATE 3 ROOMS
        university.addRoom(new Room("A1", 6));
        university.addRoom(new Room("B14", 3));
        university.addRoom(new Room("C9", 10));

        //CREATE 9 BOOKABLE ROOMS
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[0], LocalDateTime.of(2021, 1, 1, 7, 00)));
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[0], LocalDateTime.of(2021, 1, 1, 8, 00)));
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[0], LocalDateTime.of(2021, 1, 1, 9, 00)));

        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[1], LocalDateTime.of(2021, 1, 1, 7, 00)));
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[1], LocalDateTime.of(2021, 1, 1, 8, 00)));
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[1], LocalDateTime.of(2021, 1, 1, 9, 00)));

        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[2], LocalDateTime.of(2021, 1, 1, 7, 00)));
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[2], LocalDateTime.of(2021, 1, 1, 8, 00)));
        bookingSystem.addBookableRoom(new BookableRoom(university.getRooms()[2], LocalDateTime.of(2021, 1, 1, 9, 00)));

        //CREATE 6 ASSISTANTS ON SHIFT
        bookingSystem.addAssistantOnShift(new AssistantOnShift(university.getAssistants()[0], LocalDateTime.of(2021, 1, 1, 7,00)));
        bookingSystem.addAssistantOnShift(new AssistantOnShift(university.getAssistants()[0], LocalDateTime.of(2021, 1, 1, 8,00)));

        bookingSystem.addAssistantOnShift(new AssistantOnShift(university.getAssistants()[1], LocalDateTime.of(2021, 1, 1, 7,00)));
        bookingSystem.addAssistantOnShift(new AssistantOnShift(university.getAssistants()[1], LocalDateTime.of(2021, 1, 1, 8,00)));

        bookingSystem.addAssistantOnShift(new AssistantOnShift(university.getAssistants()[2], LocalDateTime.of(2021, 1, 1, 7,00)));
        bookingSystem.addAssistantOnShift(new AssistantOnShift(university.getAssistants()[2], LocalDateTime.of(2021, 1, 1, 8,00)));

        //CREATE SPECIFIC BOOKINGS

        //To make one bookable room full - use room b14 i.e. second room in array(Index 1) at 7:00 - index 3 for bookableRooms, fill with one of each assistant on shift at 7:00
        bookingSystem.addBooking(new Booking(bookingSystem.getBookableRooms()[3], bookingSystem.getAssistantsOnShift()[0], "email1@uok.ac.uk"));
        bookingSystem.addBooking(new Booking(bookingSystem.getBookableRooms()[3], bookingSystem.getAssistantsOnShift()[2], "email1@uok.ac.uk"));
        bookingSystem.addBooking(new Booking(bookingSystem.getBookableRooms()[3], bookingSystem.getAssistantsOnShift()[4], "email1@uok.ac.uk"));
        //To make one assistant on shift busy, this is done with the above step
        //To make one assistant on shift free - no booking will be made for the 3rd(Index 2) assistant in the array at 9:00
        //To make one bookable room empty - no booking will be made for the 3rd(Index 2) room in the array at 9:00
        //To make one bookable room available - only 2 out of 6 bookings will be made for room A1 i.e first room in array(Index 0(Index 1 for bookablerooms)) at 8:00 (1st assistant on shift Index 1)
        bookingSystem.addBooking(new Booking(bookingSystem.getBookableRooms()[1], bookingSystem.getAssistantsOnShift()[1], "email1@uok.ac.uk"));
        //To make one booking complete will make a booking for (2nd assistant on shift at 8:00) in room A1 at 8:00 and this will be marked as complete (Has to be marked complete / Concluded)
        bookingSystem.addBooking(new Booking(bookingSystem.getBookableRooms()[4], bookingSystem.getAssistantsOnShift()[3], "email1@uok.ac.uk"));
        bookingSystem.bookings[4].completeBooking();
    }

}
