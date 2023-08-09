package api.cli;

import org.jasypt.util.text.StrongTextEncryptor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {

    private static final Logger LOGGER = Logger.getLogger(Program.class.getName());

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        StrongTextEncryptor encryptor = new StrongTextEncryptor();

        encryptor.setPassword("SuperSecretPassword");

        LOGGER.log(Level.INFO,encryptor.encrypt("moviebase-user"));
        LOGGER.log(Level.INFO, encryptor.encrypt("moviebase-pwd"));
    }
}
