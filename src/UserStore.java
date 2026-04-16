import java.io.*;
import java.util.HashMap;

public class UserStore {
    public static HashMap<String, String> users = new HashMap<>();
    private static final String FILE_NAME = "users.txt";

    // Load users when app starts
    static {
        loadUsers();
    }

    // ================= LOAD =================
    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
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
                writer.write(username + "," + users.get(username));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}