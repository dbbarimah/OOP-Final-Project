import java.util.Random;

public class UserProfile extends Person{
    // Member Variables For The UserProfile
    private String userID;
    private String email;
    private String password;
    private int familySize;

    // Parametised Constructor For The UserProfile
    public UserProfile(String name, String email, String password, int familySize) {
        super(name);
        this.familySize = familySize;
        this.userID = generateUserID();
        this.email = email;
        this.password = password;
    }
    
    // Method To Generate A Unique UserID For Each User
    // Uses First Two Letters Of The Username, A Random 4 Digit Number, And The Family Size
    private String generateUserID() {
        Random number = new Random();
        int code = 1000 + number.nextInt(9000);
        String name = getName();
        String firstTwoLetters;

        if (name.length() >= 2){
            firstTwoLetters = name.substring(0,2);
        } else {
            firstTwoLetters = name;
        }
        return firstTwoLetters.toUpperCase() + code + '-' + familySize;
    }

    // Getters For The UserProfile
    public String getUserID() {
        return userID;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public int getFamilySize() {
        return familySize;
    }

    //Method to Display the Non-sensitive UserInfo When Requested
    @Override
    public String toString(){
        return "UserID: " + getUserID() + "\n" + 
        "Username: " + getName() + "\n" +
        "Email: " + getEmail();
    }
}
