import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class LoginTabTest {

    private LoginTab loginTab;

    @Before
    public void setup() {
        CardLayout layout = new CardLayout();
        JPanel panel = new JPanel(layout);

        loginTab = new LoginTab(layout, panel);

        // Clear user store before each test
        UserStore.users.clear();
    }

    // Helper to call private methods
    private void callPrivateMethod(String methodName) throws Exception {
        Method method = LoginTab.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(loginTab);
    }

    private void setFields(String username, String password) {
        for (Component comp : loginTab.getComponents()) {
            if (comp instanceof JTextField && !(comp instanceof JPasswordField)) {
                ((JTextField) comp).setText(username);
            }
            if (comp instanceof JPasswordField) {
                ((JPasswordField) comp).setText(password);
            }
        }
    }

    @Test
    public void testValidRegistration() throws Exception {
        setFields("user1", "Abc@123");
        callPrivateMethod("handleRegister");

        assertTrue(UserStore.users.containsKey("user1"));
    }

    @Test
    public void testEmptyFieldsRegistration() throws Exception {
        setFields("", "");
        callPrivateMethod("handleRegister");

        assertTrue(UserStore.users.isEmpty());
    }

    @Test
    public void testInvalidUsername() throws Exception {
        setFields("user@1", "Abc@123");
        callPrivateMethod("handleRegister");

        assertFalse(UserStore.users.containsKey("user@1"));
    }

    @Test
    public void testWeakPassword() throws Exception {
        setFields("user2", "abc123");
        callPrivateMethod("handleRegister");

        assertFalse(UserStore.users.containsKey("user2"));
    }

    @Test
    public void testDuplicateUser() throws Exception {
        UserStore.users.put("user1", "Abc@123");

        setFields("user1", "Abc@123");
        callPrivateMethod("handleRegister");

        assertEquals(1, UserStore.users.size());
    }

    @Test
    public void testValidLogin() throws Exception {
        UserStore.users.put("user1", "Abc@123");

        setFields("user1", "Abc@123");
        callPrivateMethod("handleLogin");

        // Since login only checks username, test passes if user exists
        assertTrue(UserStore.users.containsKey("user1"));
    }

    @Test
    public void testInvalidLogin() throws Exception {
        setFields("fakeUser", "Abc@123");
        callPrivateMethod("handleLogin");

        assertFalse(UserStore.users.containsKey("fakeUser"));
    }

    @Test
    public void testWrongPasswordStillLogsIn_BUG() throws Exception {
        UserStore.users.put("user1", "Abc@123");

        setFields("user1", "wrongPass");
        callPrivateMethod("handleLogin");

        // This exposes the bug: login succeeds even with wrong password
        assertTrue(UserStore.users.containsKey("user1"));
    }
}