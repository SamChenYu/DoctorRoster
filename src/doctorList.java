import java.util.Scanner;
import java.util.ArrayList;

public class doctorList
{
    private int doctorConNum;
    private int doctorTNum;
    ArrayList<Doctor> doctorCon = new ArrayList<>();
    ArrayList<Doctor> doctorT = new ArrayList<>();
    
    
    public doctorList()
    {
        doctorConNum = 0;
        doctorTNum = 0;
    }

    public void addDoctor() {
        Doctor newClass = new Doctor();
        Scanner in = new Scanner(System.in);
        
        System.out.println("---------------");
        System.out.println("Input Name: ");
        String inputName = in.nextLine();
        newClass.setName(inputName);
        
        System.out.println("Input Phone: ");
        int inputPhone = in.nextInt();
        newClass.setPhone(inputPhone);
        
        System.out.println("Input Initial: ");
        String inputInitial = in.next();
        newClass.setInitial(inputInitial);
        
        System.out.println("Input Role (T/C): ");
        String inputRole = in.next();
        newClass.setRole(inputRole);
        
        //doctorNumber++;
        //Split into either consultant or trainee array
        switch(inputRole) {
            case ("T"):
                System.out.println("Input R Year: ");
                int inputR = in.nextInt();
                newClass.setR(inputR);
                
                doctorT.add(newClass);
                doctorTNum++;
                break;
            
            case ("C"):
                doctorCon.add(newClass);
                doctorConNum++;
                break;
        } 
        //doctors.add(newClass);
        
        newClass.printDoctorInfo();        
        
   
    }
    
    
    public void removeDoctor(String role, int location) {
        if(role.equals("T")) {
            System.out.println(doctorT.get(location).getName() + " has been removed");
            doctorT.remove(location);
        } else {
            System.out.println(doctorCon.get(location).getName() + " has been removed");
            doctorCon.remove(location);
        }
    }
    
    
    public void getDoctorArray(String role) {
        switch(role) {
            case ("C"):
                for(int i = 0; i < doctorConNum; i++) {
                    Doctor output = doctorCon.get(i);
                    output.printDoctorInfo();
                }
                break;
            
            case("T"):
                for(int i = 0; i < doctorTNum; i++) {
                    Doctor output = doctorT.get(i);
                    output.printDoctorInfo();
                }
        }
    }
    
    

    //Getters and Setters for individual instances in the arrayList
    // GETTERS AND SETTERS FOR THE CONSULTANTS
    public String getName(String role, int location) {
        String output = "";
        
        switch(role) {
            case ("C"):
                output = (doctorCon.get(location)).getName();
                break;
            
            case("T"):
                output = (doctorT.get(location)).getName();
                break;
        }
        return output;
    }
    
    public int getPhone(String role, int location) {
        int output = 0;
        switch(role) {
            case ("C"):
                output = (doctorCon.get(location)).getPhone();
                break;
            
            case("T"):
                output = (doctorT.get(location)).getPhone();
                break;
        }
        return output;
    }    
    
    public String getRole(String role, int location) {
        String output = "";
        switch(role) {
            case ("C"):
                output = (doctorCon.get(location)).getRole();
                break;
            
            case("T"):
                output = (doctorT.get(location)).getRole();
                break;
        }

        return output;
    }    
    
    public String getInitial(String role, int location) {
        String output = "";
        switch(role) {
            case ("C"):
                output = (doctorCon.get(location)).getInitial();
                break;
            
            case("T"):
                output = (doctorT.get(location)).getInitial();
                break;
        } 
        return output;
    }    
    
    public void setName(String role, int location, String name) {
        
        switch(role) {
            case ("C"):
                (doctorCon.get(location)).setName(name);
                break;
            
            case("T"):
                (doctorT.get(location)).setName(name);
                break;
        }
    }
    
    public void setPhone(String role, int location, int phone) {

        switch(role) {
            case ("C"):
                (doctorCon.get(location)).setPhone(phone);
                break;
            
            case("T"):
                (doctorT.get(location)).setPhone(phone);
                break;
        }
    }
    
    public void setRole(int location) {

        //removing T
        (doctorT.get(location)).setR(0);
        (doctorT.get(location)).setRole("C");
        doctorCon.add(doctorT.get(location));
        doctorT.remove(location);
        doctorConNum++;
        doctorTNum--;
   
    }
    
    public void setRole(int location, int R) {
        (doctorCon.get(location)).setR(R);
        (doctorCon.get(location)).setRole("T");
        doctorT.add(doctorCon.get(location));
        doctorCon.remove(location);
        doctorConNum++;
        doctorTNum--;    
    }
    
    public void setInitial(String role, int location, String initial) {
        
        switch(role) {
            case ("C"):
                (doctorCon.get(location)).setInitial(initial);
                break;
            
            case("T"):
                (doctorT.get(location)).setInitial(initial);
                break;
        }
    }
    
    public void addUnavailabletoDoctor(String role, int location, int date, String info, String remarks) {
        switch(role) {
            case ("C"):
                (doctorCon.get(location)).AddUnavailable(date, info, remarks); // references under the doctor class
                break;
            
            case("T"):
                (doctorT.get(location)).AddUnavailable(date, info, remarks);
                break;
        }
    }
    
    public int getDoctorConNum() {
        return doctorConNum;
    }
    
    public int getDoctorTNum() {
        return doctorTNum;
    }
    
    
    public ArrayList<Doctor> getDoctorConArray() {
        return doctorCon;
    }
    
    public ArrayList<Doctor> getDoctorTArray() {
        return doctorT;
    }
    
    //Starting the actual algorithm to manage the roster
    //get consultant and trainee array
    // iterate through their unavailable arrays - use as a paleltte to decide if they are available
    
    
    
    public void sampleDoctors() {
        Doctor Sam = new Doctor("Sam", 123, "SCY");
        doctorCon.add(Sam);
        doctorConNum++;
        Sam.AddUnavailable(1,"AM","ADMIN");
        Sam.AddUnavailable(2,"PM","LECTURE");
        
        Doctor Dylan = new Doctor("Dylan", 234, "DK");
        doctorCon.add(Dylan);
        doctorConNum++;
        Dylan.AddUnavailable(2,"OFF","No");
        
        Doctor EanHan = new Doctor("Ean Han", 345, "EH");
        doctorCon.add(EanHan);
        doctorConNum++;
        EanHan.AddUnavailable(10,"PM", "Lecture");
        
        Doctor Darrel = new Doctor("Darrel", 9477, "DT");
        doctorCon.add(Darrel);
        doctorConNum++;
        
        Doctor Max = new Doctor("Max", 6969, "MR");
        doctorCon.add(Max);
        doctorConNum++;
        
        Doctor Henri = new Doctor("Henri", 44, "HF");
        doctorCon.add(Henri);
        doctorConNum++;
        
        Doctor Rayner = new Doctor("Rayner", 369, "RL");
        doctorCon.add(Rayner);
        doctorConNum++;
        
        Doctor John = new Doctor("John", 666, "JW");
        doctorCon.add(John);
        doctorConNum++;
        
        Doctor Ethan = new Doctor("Ethan", 15195, "EL");
        doctorCon.add(Ethan);
        doctorConNum++;
        
        Doctor Luca = new Doctor("Luca", 50, "LG");
        doctorCon.add(Luca);
        doctorConNum++;
        
        Sam.printDoctorInfo();        
        Dylan.printDoctorInfo();
        EanHan.printDoctorInfo();
        Darrel.printDoctorInfo();
        Max.printDoctorInfo();
        Henri.printDoctorInfo();
        Rayner.printDoctorInfo();
        John.printDoctorInfo();
        Ethan.printDoctorInfo();
        
        //Palette 1-AM should return Dylan and Ean Han
        //Palette 1-PM should return Sam, Dylan and Ean Han
        //Palette 2 should return Sam, Ean Han
        
        Doctor Sophie = new Doctor("Sophie", 65, "SD", 1);
        doctorT.add(Sophie);
        doctorTNum++;
        Sophie.AddUnavailable(1,"AM","Sick leave");
        Sophie.AddUnavailable(2,"PM","Lmao");
        
        Doctor Scarlet = new Doctor("Scarlet", 32, "SC", 2);
        doctorT.add(Scarlet);
        doctorTNum++;
        Scarlet.AddUnavailable(2,"OFF","Ya");
        
        Doctor Caitlin = new Doctor("Caitlin", 9477, "CW", 3);
        doctorT.add(Caitlin);
        doctorTNum++;
        Caitlin.AddUnavailable(5,"OFF", "LEAVE");
        
        Doctor JunRay = new Doctor("Jun Ray", 99, "JR", 3);
        doctorT.add(JunRay);
        doctorTNum++;
        
        Doctor Audrey = new Doctor("Audrey", 1975, "T", 2);
        doctorT.add(Audrey);
        doctorTNum++;
        
        Doctor Dyann = new Doctor("Dyann", 1, "DG", 1);
        doctorT.add(Dyann);
        doctorTNum++;
        
        Doctor Lauren = new Doctor("Lauren", 39, "L", 3);
        doctorT.add(Lauren);
        doctorTNum++;
        
        Doctor Joelle = new Doctor("Joelle", 65, "JG", 2);
        doctorT.add(Joelle);
        doctorTNum++;
        
        Sophie.printDoctorInfo();        
        Scarlet.printDoctorInfo();
        Caitlin.printDoctorInfo();
        JunRay.printDoctorInfo();
        Audrey.printDoctorInfo();
        Dyann.printDoctorInfo();
        Lauren.printDoctorInfo();
        Joelle.printDoctorInfo();
        
        //Palette 1-AM should return Dylan and Ean Han
        //Palette 1-PM should return Sam, Dylan and Ean Han
        //Palette 2 should return Sam, Ean Han
        
    }
    
    public ArrayList<Doctor> getConPalette(int day, String session) {
        //Step 1 - create the palette full of the doctors
        ArrayList<Doctor> conPalette = new ArrayList<>();
        conPalette.clear(); //Just in case that there are remaining elements
        
        
        for(int i = 0; i < doctorCon.size(); i++) {
            conPalette.add(doctorCon.get(i)); // read in the doctors from the doctorCon array
        }
        
        
        
        
        //Step 2 - access each doctor
        for(int i = 0; i < conPalette.size(); i++) {

            //Step 3 - access individiual doctor's unavailable dates - linear search through to match the day and session
            for(int j = 0; j < (conPalette.get(i)).getUnavailableNum() -1; j++) {
                //accesing the unavailable[] length
                
                if ( (conPalette.get(i)).getUnavailableDate(j) == day && ((conPalette.get(i)).getUnavailableInfo(j)).equals(session))  {
                    //Conditional statement: if a Doctor is unavailable on this day and session: remove them from the palette
                    conPalette.remove(i);
                }
                
                if ( (conPalette.get(i)).getUnavailableDate(j) == day && ((conPalette.get(i)).getUnavailableInfo(j)).equals("OFF") ) {
                    // Next conditional statement: if they are OFF on that day, then they are also removed
                    conPalette.remove(i);
                }
                
            }
            
        }

        
        
        return conPalette;
        
    }
    
    
    public ArrayList<Doctor> getTPalette(int day, String session) {
        
        //Step 1 - create the palette full of doctors
        ArrayList<Doctor> TPalette = new ArrayList<>();
        TPalette.clear();
        for(int i = 0; i < doctorT.size(); i++) {
            TPalette.add(doctorT.get(i));
        }
        
        
        //Step 2 - access each doctor
        for(int i = 0; i < TPalette.size(); i++) {
            //System.out.println("TPalette size: ------------------");
            //(TPalette.get(i)).printDoctorInfo();                          //printing testing
            //System.out.println("unavailable dates: --------------");
            //(TPalette.get(i)).printUnavailable();
            
            //Step 3 - access individual doctors unavailable dates - linear search through to match the day and session
            for(int j = 0; j < (TPalette.get(i)).getUnavailableNum() - 1; j++) {
                
                if( (TPalette.get(i)).getUnavailableDate(j) == day && ((TPalette.get(i)).getUnavailableInfo(j)).equals(session)) {
                    TPalette.remove(i);
                }
                
                if ( (TPalette.get(i)).getUnavailableDate(j) == day && ((TPalette.get(i)).getUnavailableInfo(j)).equals("OFF")) {
                    TPalette.remove(i);
                }
                
                
            }
        }
        /* This code is for testing only to view the palettes themselves
        System.out.println("TPalette: ---------");
        
        for(int i = 0; i < TPalette.size(); i++) {
            System.out.println((TPalette).get(i).getName());
        }
        
        System.out.println("doctor T ---------");
        for(int i = 0; i < doctorT.size(); i++) {
            System.out.println( (doctorT.get(i)).getName());
        }
        */
        return TPalette;
    }
    
    
    
    public ArrayList<Doctor> getOFFTPalette(int day, String remark, String session) {
        
        ArrayList<Doctor> TOFF = new ArrayList<>();
        TOFF.clear();
        
        //Step 1 access doctors
        for(int i = 0; i < doctorT.size(); i++) {
            
            //Step 2 access doctor's unavailable dates
            for(int j = 0; j < (doctorT.get(i)).getUnavailableNum(); j++) {
                
                //Step 3 - check if the unavailable date is the int day and that the remark is the same
                if( (doctorT.get(i)).getUnavailableDate(j) == day && ((doctorT.get(i)).getUnavailableRemarks(j)).equals(remark) && ((doctorT.get(i)).getUnavailableInfo(j)).equals(session)) {
                    TOFF.add(doctorT.get(i));
                }
                
            }
            
        }
        
        return TOFF;
    }
    
    public ArrayList<Doctor> getOFFTPalette(int day, String session) {
        
        ArrayList<Doctor> TOFF = new ArrayList<>();
        TOFF.clear();
        
        //Step 1 access doctors
        for(int i = 0; i < doctorT.size(); i++) {
            
            //Step 2 access doctor's unavailable dates
            for(int j = 0; j < (doctorT.get(i)).getUnavailableNum(); j++) {
                
                //Step 3 - check if the unavailable date is the int day and that the remark is the same
                if( (doctorT.get(i)).getUnavailableDate(j) == day && ((doctorT.get(i)).getUnavailableInfo(j)).equals(session)) {
                    TOFF.add(doctorT.get(i));
                }
                
            }
            
        }
        
        return TOFF;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Doctor> getOFFCONPalette(int day, String remark, String session) {
        
        ArrayList<Doctor> CONOFF = new ArrayList<>();
        CONOFF.clear();
        
        //Step 1 access doctors
        for(int i = 0; i < doctorCon.size(); i++) {
            
            //Step 2 access doctor's unavailable dates
            for(int j = 0; j < (doctorCon.get(i)).getUnavailableNum(); j++) {
                
                //Step 3 - check if the unavailable date is the int day and that the remark is the same
                if( (doctorCon.get(i)).getUnavailableDate(j) == day && ((doctorCon.get(i)).getUnavailableRemarks(j)).equals(remark) && ((doctorCon.get(i)).getUnavailableInfo(j)).equals(session)) {
                    CONOFF.add(doctorCon.get(i));
                }
                
            }
            
        }
        
        return CONOFF;
    }
    
    public ArrayList<Doctor> getOFFCONPalette(int day, String session) {
        
        ArrayList<Doctor> CONOFF = new ArrayList<>();
        CONOFF.clear();
        
        //Step 1 access doctors
        for(int i = 0; i < doctorCon.size(); i++) {
            
            //Step 2 access doctor's unavailable dates
            for(int j = 0; j < (doctorCon.get(i)).getUnavailableNum(); j++) {
                
                
                if( (doctorCon.get(i)).getUnavailableDate(j) == day && ((doctorCon.get(i)).getUnavailableInfo(j)).equals(session)) {
                    
                    
                    String remark = (doctorCon.get(i)).getUnavailableRemarks(j);
                    if( !remark.equals("LEAVE") && !remark.equals("ADMIN") && !remark.equals("LECTURE")) {
                        
                        CONOFF.add(doctorCon.get(i));
                    }
                }
                
            }
            
        }
        
        return CONOFF;
    }
    
    
    
    
}
