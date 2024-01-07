import java.util.ArrayList;

public class Doctor
{

    private String name;
    private int phone;
    private String initial;
    private String role;
    private int R; // year level - only applicable for trainees
    ArrayList<Integer> unavailable = new ArrayList<>();
    ArrayList<String> unavailableInfo = new ArrayList<>(); // either AM, PM or OFF
    ArrayList<String> unavailableRemarks = new ArrayList<>();
    private int unavailableNum;
    
    public Doctor()
    {
        name = "";
        phone = 0;
        initial = "";
        role = "";
        R = 0;
        unavailableNum = 0;
    }
    
    public Doctor(String name, int phone, String initial) { // testing constructor for adding Con easily
        this.name = name;
        this.phone = phone;
        this.initial = initial;
        role = "C";
        R = 0;
        unavailableNum = 0;
    }
    
    public Doctor(String name, int phone, String initial, int R) {
        this.name = name;
        this.phone = phone;
        this.initial = initial;
        this.R = R;
        role = "T";
        unavailableNum = 0;
    }
    
    //Getters and Setters
    public String getName() { return name; }
    public int getPhone() { return phone; }
    public String getRole() { return role; }
    public String getInitial() {return initial; }
    public int getR() { return R; }
    public int getUnavailableDate(int location) { return unavailable.get(location); }
    public String getUnavailableInfo(int location) { return unavailableInfo.get(location); }
    public int getUnavailableNum() { return unavailableNum; }
    public String getUnavailableRemarks(int location) { return unavailableRemarks.get(location); }
    
    public void setName(String name) { this.name = name; }
    public void setPhone(int phone) { this.phone = phone; }
    public void setRole(String role) {this.role = role; }
    public void setInitial(String initial) {this.initial = initial; }
    public void setR(int R) {this.R = R; }
    
    
    
    
    
    
    
    public void printDoctorInfo() {
        System.out.println("-------------");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Initial: " + initial);
        System.out.println("Role: " + role);
        if (R != 0) {
            System.out.println("R" + Integer.toString(R));
        }
        printUnavailable();
    }
    
    public void AddUnavailable(int date, String info, String remarks) {
        unavailable.add(date);
        unavailableInfo.add(info);
        unavailableRemarks.add(remarks);
        unavailableNum++;
        
    }
    
    public void printUnavailable() {
        System.out.println("Unavailable Dates: "+ unavailable);
        System.out.println("Session Info: " + unavailableInfo);
        System.out.println("Remarks :" + unavailableRemarks);
    }
    
    
    
    public ArrayList<String> getUnavailableInfo() {
        return unavailableInfo;
    }
    
    
    
    
}
