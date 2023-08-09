package infrastructure.helpers;


import core.dtos.base.BaseDto;
import core.exceptions.MovieException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {

    private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config/server");

    public BaseDto sendMessage(String partOfMovieName) {
        try (Socket socket = new Socket(RESOURCE_BUNDLE.getString("host.ip"), Integer.parseInt(RESOURCE_BUNDLE.getString("host.port")))){
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print(partOfMovieName);

            Scanner in = new Scanner(socket.getInputStream());
            String message = in.next();
            LOGGER.log(Level.INFO, message);
            return null;
        } catch (UnknownHostException ex) {
            LOGGER.log(Level.SEVERE, "Host is unknown.", ex);
            throw new MovieException("Host is unknown.");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to send message.", ex);
            throw new MovieException("Unable to send message.");
        }
    }
}
