package core.services;

import core.exceptions.MovieException;
import core.helpers.Crypto;
import core.interfaces.repositories.UserRepository;
import core.interfaces.services.UserService;
import core.helpers.LoginState;
import infrastructure.repositories.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
    private final UserRepository _userRepository;
    private String loggedInUser;

    public UserServiceImpl() {
        _userRepository = new UserRepositoryImpl();
    }
    @Override
    public boolean login(String username, String password) {
        try {
            if (_userRepository.loginUser(username, password)){
                LoginState.setUser(username);
            }
            return true;

        } catch (MovieException ex){
            return false;
        }
    }

    @Override
    public boolean register(String username, String password) {
        try {
            boolean validRegister = _userRepository.addUser(username, password);
            if (validRegister){
                LoginState.setUser(username);
            }
            return validRegister;
        } catch (MovieException ex){
            return false;
        }
    }
}
