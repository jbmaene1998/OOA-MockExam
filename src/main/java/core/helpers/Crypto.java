package core.helpers;

import org.jasypt.util.text.StrongTextEncryptor;
import org.mindrot.jbcrypt.BCrypt;


public class Crypto {

    private static final Crypto INSTANCE = new Crypto();
    private static final String KEY = "SECRET";
    private final StrongTextEncryptor encryptor;

    public static Crypto getInstance() {
        return INSTANCE;
    }

    private Crypto() {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(KEY);
    }
    public String encrypt(String in) {
        return encryptor.encrypt(in);
    }

    public String decrypt(String in) {
        return encryptor.decrypt(in);
    }
    public String hash(String in) {
        return BCrypt.hashpw(in, BCrypt.gensalt());
    }

    public boolean comparePasswords(String in, String hashed) {
        return BCrypt.checkpw(in, hashed);
    }
}
