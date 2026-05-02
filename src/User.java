public class User {
    String password;
    String securityQuestion;
    String securityAnswer;

    public User(String password, String securityQuestion, String securityAnswer) {
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
}