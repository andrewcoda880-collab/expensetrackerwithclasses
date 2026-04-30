import java.io.*;
import java.util.HashMap;

public class UserStore {

    public static HashMap<String, User> users = new HashMap<>();
    private static final String FILE_NAME = "users.txt";

    static {
        loadUsers();
    }

    // ================= LOAD =================
    public static void loadUsers() {
        users.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    String username = parts[0];
                    String password = parts[1];
                    String question = parts[2];
                    String answer = parts[3];

                    users.put(username, new User(password, question, answer));
                }
            }

        } catch (IOException e) {
            System.out.println("No user file found, starting fresh.");
        }
    }

    // ================= SAVE =================
    public static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String username : users.keySet()) {
                User user = users.get(username);

                writer.write(username + "|" +
                             user.password + "|" +
                             user.securityQuestion + "|" +
                             user.securityAnswer);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}