import java.util.Random;

public class UserProfile {
    // Member Variables For The UserProfile
    private String userID;
    private String username;
    private String userEmail;
    private String userPassword;
    private int familySize;

    // Parametised Constructor For The UserProfile
    public UserProfile(String userID, String username, String userEmail, String userPassword, int familySize) {
        this.userID = userID;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.familySize = familySize;
    }
    
    // Method To Generate A Unique UserID For Each User
    // Uses First Two Letters Of The Username, A Random 4 Digit Number, And The Family Size
    private String generateUserID() {
        Random number = new Random();
        int code = 1000 + number.nextInt(9000);
        String firstTwoLetters;
        if (username.length() >= 2){
            firstTwoLetters = username.substring(0,2);
        } else {
            firstTwoLetters = username;
        }
        return firstTwoLetters.toUpperCase() + code + '-' + familySize;
    }

    // Getters For The UserProfile
    public String getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public int getFamilySize() {
        return familySize;
    }

    //Method to Display the Non-sensitive UserInfo When Requested
    public String toString(){
        return "UserID: " + getUserID() + "\n" + 
        "Username: " + getUsername() + "\n" +
        "Email: " + getUserEmail();
    }
}
