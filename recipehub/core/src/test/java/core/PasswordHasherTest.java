package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the PasswordHasher class.
 */
public class PasswordHasherTest {
    @Test
    @DisplayName("Test if password is hashed correctly")
    public void testHashPassword() {
        String hashedPassword = PasswordHasher.hashPassword("Password123");
        Assertions.assertNotEquals("Password123", hashedPassword, "The password should be hashed.");
        String[] hashedPasswordArray = hashedPassword.split(":");
        Assertions.assertEquals(2, hashedPasswordArray.length,
                "The hashed password should be split into two parts.");
    }

    @Test
    @DisplayName("Test if password is verified correctly")
    public void testVerifyPassword() {
        String hashedPassword = PasswordHasher.hashPassword("Password123");
        String wrongHashedPassword = hashedPassword + ":wrong";
        Assertions.assertFalse(PasswordHasher.verifyPassword("Password123", wrongHashedPassword),
                "The password should not be verified.");
        Assertions.assertTrue(PasswordHasher.verifyPassword("Password123", hashedPassword),
                "The password should be verified.");
    }
}
