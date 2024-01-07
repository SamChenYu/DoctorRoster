import java.util.Scanner;
import java.io.*;

public class Main
{
    
    Roster r;
    
    
    
    
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Doctor Roster-----------");
        System.out.println("-------Add Roster Information-------");

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Month (1-12)");
        int monthInput = in.nextInt();
        
        System.out.println("Enter Year");
        int yearInput = in.nextInt(); 
        
        System.out.println("Is this year a leap year? (true/false)");
        boolean leapyearInput = in.nextBoolean();
        
        
        Roster r = new Roster(leapyearInput, monthInput, yearInput );
        
        int instruction = 0;
        
        while(instruction != 6) {
            System.out.println("-----------MENU-----------");
            System.out.println("(1) Display Doctors");
            System.out.println("(2) Add Doctor");
            System.out.println("(3) Use Sample Doctors");
            System.out.println("(4) Remove Doctor");
            System.out.println("(5) Add Unavailable Date");
            System.out.println("(6) Generate Roster");

            instruction = in.nextInt();
            switch(instruction) {

                case(1):
                    System.out.println("(1) Display Doctors");
                    System.out.println("-----------Consultants----------- ");
                    r.DoctorList.getDoctorArray("C");
                    System.out.println("-----------Trainees----------- ");
                    r.DoctorList.getDoctorArray("T");
                    break;

                case(2):
                    System.out.println("(2) Add Doctor");
                    r.DoctorList.addDoctor();
                    break;

                case(3):
                    System.out.println("(3) Use Sample Doctors");
                    r.DoctorList.sampleDoctors();
                    break;
                    
                case(4):
                    System.out.println("(4) Remove Doctor");
                    in.nextLine();
                    System.out.println("Trainee or Consultant? (T/C)");
                    String role = in.nextLine();
                    in.nextLine();
                    System.out.println("Enter Location");
                    int location = in.nextInt();
                    location--; //The user doesn't know arrays start from 0
                    r.DoctorList.removeDoctor(role, location);
                    
                    
                    
                case(5):
                    System.out.println("(5) Add Unavailable Date");
                    in.nextLine();
                    System.out.println("Trainee or Consultant? (T/C)");
                    role = in.nextLine();
                    in.nextLine();
                    System.out.println("Enter Location");
                    location = in.nextInt();
                    location--; //The user doesn't know arrays start from 0
                    
                    System.out.println("Enter Unavailable Date");
                    int date = in.nextInt();
                    System.out.println("Enter Session Unavailable  (AM/PM/OFF)");
                    String info = in.nextLine();
                    System.out.println("Enter Remarks (LECTURE/LEAVE/ADMIN) ");
                    String remarks = in.nextLine();
                    
                    if( role.equals("T")) {
                        r.DoctorList.doctorT.get(location).AddUnavailable(date,info,remarks);
                    } else {
                        r.DoctorList.doctorCon.get(location).AddUnavailable(date,info,remarks);
                    }
                    
                case(6):
                    r.populate();
                    r.printWithTabs();
                    r.OutputTextFile();
                    System.out.println("Done! .txt file has been created");
                    System.out.println("You may exit the program");
            }
            System.out.println("-----");
            System.out.println("Enter any key to continue");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            in.nextLine();
            String dummy = in.nextLine();
            
        }
        
        
        
    }
    
    
    
}
