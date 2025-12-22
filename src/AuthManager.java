import java.io.*;
import java.util.HashMap;

public class AuthManager {

    private HashMap<String, String> userData;
    private final String filePath = "users.txt";

    public AuthManager() {
        userData = new HashMap<>();
        loadUsers();
    }

    /**
     * Loads all username/password entries from the text file.
     */
    private void loadUsers() {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("User file not found. Creating new one...");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (!line.contains(",")) continue;  // ignore invalid lines

                String[] parts = line.split(",", 2);
                String username = parts[0].trim();
                String password = parts[1].trim();

                userData.put(username, password);
            }

        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    /**
     * Checks whether a username/password pair is valid.
     */
    public boolean validateLogin(String username, String password) {
        if (!userData.containsKey(username)) return false;

        return userData.get(username).equals(password);
    }

}
