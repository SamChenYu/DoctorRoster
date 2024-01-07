import java.time.Month;
import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

import java.io.*;


public class Roster
{
    private int year;
    Month month;
    private boolean isLeapYear;
    Date firstDay;
    
    String[][] rosterArray; // the actual roster itself -2D array
    String[] stringDays = new String[] {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
    doctorList DoctorList = new doctorList();

    
    public Roster(String test) { // intiailizer that sets default value
        isLeapYear = true;
        month = month.JANUARY;
        year = 2020;
        firstDay = new Date(0, month.getValue() - 1, year);
        initializeRosterArray();
        DoctorList.sampleDoctors(); // This line is for testing and adds the testing Trainees into the DoctorList
    }
    
        public Roster() { // intiailizer that sets default value
        isLeapYear = true;
        month = month.JANUARY;
        year = 2020;
        firstDay = new Date(0, month.getValue() - 1, year);
        initializeRosterArray();
    }
    
    public Roster(boolean leapInput, int monthInput, int yearInput) { // initializer that asks for int values of month
        isLeapYear = leapInput;
        month = Month.of(monthInput); // converts the int to a month
        year = yearInput;
        firstDay = new Date(0, month.getValue() - 1, year);
        initializeRosterArray();

    }
    
    
    public int monthLength() {
        return month.length(isLeapYear);
    }
    
    public void monthLengthString() {
        System.out.println(month.name());
    }
    
    public int getWeekDay() {
        return firstDay.getDay();
    }
    
    public void getWeekDayString() {
        System.out.println(stringDays[firstDay.getDay()]);
    }
    
    
    
    
    
    
    
    
    public void initializeRosterArray() {
        rosterArray = new String[31][monthLength() + 1];
        //28 main columns
        //+2 for date headers (date numbers and string day)
        //monthLength + 1 because of column header
        for(int i = 0; i < 31; i++) {
            
            for(int j = 0; j < monthLength() + 1; j++) {
                rosterArray[i][j] = ("");
            }
        //Initializes the entire array and sets everything to blank
        }
        
        //Setting the actual date columns
        int counter = 1;
        for(int i = 1; i < monthLength() + 1; i++) {
            //sets the number column
            rosterArray[0][i] = Integer.toString(counter);
            counter++;
        }
        
        //getWeekDay() gives value of weekday (4)
        //getWeekDayString gives actual text of weekday
        //rosterArray[1][i] - set 
        int weekCounter = getWeekDay();
        for(int i = 1 ; i < monthLength() + 1; i++) {
            rosterArray[1][i] = stringDays[weekCounter];
            weekCounter++;
            if(weekCounter > 6) {
                weekCounter = 0;
            }
        }
        
        //Setting the individual 28 columns
        rosterArray[0][0] = "Date"; 
        rosterArray[1][0] = "Day";
        rosterArray[2][0] = "CT_AM_CON";
        rosterArray[3][0] = "CT_AM_T";
        rosterArray[4][0] = "CT_PM_CON";
        rosterArray[5][0] = "CT_PM_T";
        rosterArray[6][0] = "CT_ONCALL_T";
        
        rosterArray[7][0] = "MR_AM_CON1";
        rosterArray[8][0] = "MR_AM_CON2";
        rosterArray[9][0] = "MR_AM_T";
        rosterArray[10][0] = "MR_PM_CON3";
        rosterArray[11][0] = "MR_PM_T";
        rosterArray[12][0] = "MR_E_T";
        
        rosterArray[13][0] = "ANG_AM";
        rosterArray[14][0] = "ANG_PM"; // all 3 angios are the same person
        rosterArray[15][0] = "ANG_INRCON";
        
        rosterArray[16][0] = "RETURN_CT"; // random con both are AM sessions
        rosterArray[17][0] = "RETURN_MRI"; // random con
        
        rosterArray[18][0] = "DUTY_AM"; // usually the MR CON 2
        rosterArray[19][0] = "DUTY_PM"; // usually the CON 3
        
        rosterArray[20][0] = "POSTCALL"; // same as the person on call from the day before
        rosterArray[21][0] = "TT"; // the person who is on call today and on evening shift (two people)
        
        rosterArray[22][0] = "OFF_AM"; // off columns are usually the trainees from weekends
        rosterArray[23][0] = "OFF_PM";
        rosterArray[24][0] = "PHOFF"; // consultants - carried from weekends
        rosterArray[25][0] = "LEAVE_CON"; // usually for annual leave
        rosterArray[26][0] = "LEAVE_T";
        rosterArray[27][0] = "REMARKS";
        rosterArray[28][0] = "ADMIN_AM";
        rosterArray[29][0] = "ADMIN_PM";
        rosterArray[30][0] = "LECTURE";
        
        
        
        
    }
    
    
    public void printHorizontal() {
        for(int i = 0; i < 31; i++){
            
            System.out.println(Arrays.toString(rosterArray[i]));
            
        }
    }
    
    public void printVertical() {
        String [] specificDay = new String[31];
        for(int i = 0; i < monthLength() + 1; i++) {
            
            for(int j = 0; j < 31; j++) {
                specificDay[j] = (rosterArray[j][i]);
            }
            System.out.println(Arrays.toString(specificDay));
        }
        
    }
    
    
    
    
    
    public ArrayList<Doctor> conditionalGetTPalette(int day, String session) {
        /*The doctorList class has no access to any time
        R2 - Wednesday PM
        R3 - Thursday PM
        R4 - Alternate Tuesday PM
        Also check if they weren't on the Today's on-call
        */
        
        ArrayList<Doctor> TPalette = DoctorList.getTPalette(day, session); 
        for(int i = 0 ; i < TPalette.size() ; i++) {
            
            boolean R2bool = false;
            boolean R3bool = false;
            boolean R4bool = false;
            boolean callbool = false;
            //R2s are not free on Wednesday PM
            if((TPalette.get(i)).getR() == 2 && rosterArray[1][day].equals("Wed") && session.equals("PM")) {
                R2bool = true;
            }
            
            if((TPalette.get(i)).getR() == 3 && rosterArray[1][day].equals("Thur") && session.equals("PM")) {
                R3bool = true;
            }
            
            if((TPalette.get(i)).getR() == 4 && rosterArray[1][day].equals("Tue") && session.equals("PM")) {
                R4bool = true;
            }
            
            if( i > 0 ) { //checking if they were the last person on call - if they are on today's post call that means they are on leave
                
                if( (TPalette.get(i)).getInitial().equals(rosterArray[20][day]) ) {
                    callbool = true;
                }
            }
            
            if( i > 0 ) { //checking if they were the last person on call - if they are on today's post call that means they are on leave
                
                if( (TPalette.get(i)).getInitial().equals(rosterArray[6][day - 1]) ) {
                    System.out.println("REMOVED A DOUBLE");
                    callbool = true;
                }
            }
            
            if(R2bool == true || R3bool == true || R4bool == true || callbool == true) {
                TPalette.remove(i);
            }

        }
        System.out.println("TPalette-------");
        for(int j = 0; j < TPalette.size(); j++) {
            System.out.println(((TPalette).get(j)).getName());
        } 
        return TPalette;
    }
    
    
    
    public void populate() {
        
        Random randomGen = new Random();
        ArrayList<Doctor> Palette = new ArrayList<>();
        int randomSelector;
        //Read in the TList and ConList from the doctorList
        
        //THE FOLLOWING DOES FOR THE ENTIRE MONTH - WEEKENDS HAVE DIFFERENT PALETTES, SO THIS WILL BE OVERWRITTEN LATER
        
        for (int i = 1; i < monthLength() +1; i++) {
            
            //For every single day in the month
            
            //
            //
            //CONPALETTE AM SESSIONS
            //
            //
            
            
            Palette = DoctorList.getConPalette(i, "AM");
            randomSelector = randomGen.nextInt(Palette.size()); // selects randomly between 0 and 49
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[2][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[7][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[8][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
                
                rosterArray[18][i] = rosterArray[8][i];
                //The duty_am is the Con2
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[13][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
                rosterArray[14][i] = rosterArray[13][i];
                rosterArray[15][i] = rosterArray[13][i]; // the Angio person is always the same
                
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[16][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[17][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            //
            //
            //conPalette PM sessions
            //
            //
            
            Palette.clear();
            Palette = DoctorList.getConPalette(i, "PM");
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[4][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[10][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
                
                rosterArray[19][i] = rosterArray[10][i];
            }
            
            
            
            //
            //
            // TraineePalette AM sessions
            //
            //
            Palette.clear();
            Palette = conditionalGetTPalette(i, "AM");
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[3][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[9][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            
            
            
            //
            //
            // TraineePalette PM Sessions
            //
            //
            
            Palette.clear();
            Palette = conditionalGetTPalette(i, "PM");
            
            
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[5][i] = (Palette.get(randomSelector)).getInitial();
                Palette.remove(randomSelector);
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[6][i] = (Palette.get(randomSelector)).getInitial();
                
                
                Palette.remove(randomSelector);
                
                if (i < monthLength() ) {
                    rosterArray[20][i + 1] = rosterArray[6][i]; //on call is on postcall tomororw
                    
                }
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[11][i] = (Palette.get(randomSelector)).getInitial(); // Trainee pm session
                Palette.remove(randomSelector);
            }
            
            if( Palette.size() > 0) {
                randomSelector = randomGen.nextInt(Palette.size());
                rosterArray[12][i] = (Palette.get(randomSelector)).getInitial(); // Trainee Evening session
                Palette.remove(randomSelector);
            }
            
            
            rosterArray[21][i] = rosterArray[6][i] + "+" + rosterArray[12][i]; // training time is the on call + evening session
            
            
            //LEAVE for T and CON
            ArrayList<Doctor> leavePalette = DoctorList.getOFFCONPalette(i, "LEAVE", "OFF");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[25][i] = rosterArray[25][i] + " + " + (leavePalette.get(g)).getInitial(); // Annual leave for CON
            }
            
            
            //ADMIN for CON
            leavePalette.clear();
            leavePalette = DoctorList.getOFFCONPalette(i, "ADMIN", "AM");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[28][i] = rosterArray[28][i] + " + " + (leavePalette.get(g)).getInitial(); // ADMIN AM
            }
            
            leavePalette.clear();
            leavePalette = DoctorList.getOFFCONPalette(i, "ADMIN", "PM");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[29][i] = rosterArray[29][i] + " + " + (leavePalette.get(g)).getInitial(); // ADMIN PM
            }
            
            leavePalette.clear();
            leavePalette = DoctorList.getOFFCONPalette(i, "LECTURE", "PM");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[30][i] = rosterArray[30][i] + " + " + (leavePalette.get(g)).getInitial(); // LECTURE
            }
            
            leavePalette.clear();
            leavePalette = DoctorList.getOFFTPalette(i, "OFF");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[26][i] = rosterArray[26][i] + " + " + (leavePalette.get(g)).getInitial(); // TRAINEES OFF (LEAVE COLUMN)
                rosterArray[27][i] = rosterArray[27][i] + " + " + (leavePalette.get(g)).getUnavailableRemarks(g);
            }
            
            leavePalette.clear();
            leavePalette = DoctorList.getOFFCONPalette(i, "OFF");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[24][i] = rosterArray[24][i] + " + " + (leavePalette.get(g)).getInitial(); // CONSULTANTS OFF
                rosterArray[27][i] = rosterArray[27][i] + " + " + (leavePalette.get(g)).getUnavailableRemarks(g);
            }
            
            leavePalette.clear();
            leavePalette = DoctorList.getOFFTPalette(i, "AM");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[22][i] = rosterArray[22][i] + " + " + (leavePalette.get(g)).getInitial(); // TRAINEES OFF (LEAVE COLUMN)
            }
            
            leavePalette.clear();
            leavePalette = DoctorList.getOFFTPalette(i, "PM");
            for(int g = 0; g < leavePalette.size(); g++) {
                rosterArray[23][i] = rosterArray[23][i] + " + " + (leavePalette.get(g)).getInitial(); // TRAINEES OFF (LEAVE COLUMN)
            }

        }
        
        //Time to blank out the roster for the weekends
        
        for(int a = 1; a < monthLength() + 1; a++) {
            
            if( rosterArray[1][a].equals("Sat")) {
                rosterArray[4][a] = "-";
                rosterArray[5][a] = "-";
                rosterArray[10][a] = "-";
                rosterArray[12][a] = "-";
                rosterArray[14][a] = "-";
                rosterArray[15][a] = "-";
                rosterArray[19][a] = "-";
                rosterArray[21][a] = "-";
                
            }
            
            if( rosterArray[1][a].equals("Sun")) {
                rosterArray[2][a] = "-";
                rosterArray[3][a] = "-";
                rosterArray[4][a] = "-";
                rosterArray[5][a] = "-";
                rosterArray[7][a] = "-";
                rosterArray[8][a] = "-";
                rosterArray[9][a] = "-";
                rosterArray[10][a] = "-";
                rosterArray[11][a] = "-";
                rosterArray[12][a] = "-";
                rosterArray[13][a] = "-";
                rosterArray[14][a] = "-";
                rosterArray[15][a] = "-";
                rosterArray[18][a] = "-";
                rosterArray[19][a] = "-";
                rosterArray[21][a] = "-";

            }
            
            
            
            
        }
        
        
        
        
        
        
        
        
    }
    

    
    
    public void printWithTabs() {
        String fullArray = ""; 
        for(int i = 0; i < monthLength() + 1; i++) {
            fullArray = "";
            for(int j = 0; j < 31; j++) {
                fullArray = fullArray + rosterArray[j][i] + '\t';
            }
                System.out.println(fullArray);
        }
    }
    
    
    public void OutputTextFile() throws IOException {
        
        File file = new File("Roster Output.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        
        
        
        String fullArray = ""; 
        for(int i = 0; i < monthLength() + 1; i++) {
            fullArray = "";
            for(int j = 0; j < 31; j++) {
                fullArray = fullArray + rosterArray[j][i] + '\t';
            }
                pw.println(fullArray);
        }
        
        pw.close();
    }
    
    
    
    
}
