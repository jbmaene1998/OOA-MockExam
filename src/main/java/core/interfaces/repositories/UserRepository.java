package core.interfaces.repositories;

public interface UserRepository {
    boolean addUser(String username, String password);
    boolean loginUser(String username, String password);
}