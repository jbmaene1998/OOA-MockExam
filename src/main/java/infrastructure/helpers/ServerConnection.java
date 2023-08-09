package infrastructure.helpers;


import core.dtos.base.BaseDto;
import core.exceptions.MovieException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {

    private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config/server");

    private static String HOST_IP = RESOURCE_BUNDLE.getString("host.ip");
    private static int HOST_PORT = Integer.parseInt(RESOURCE_BUNDLE.getString("host.port"));


    public BaseDto sendMessage(String partOfMovieName) {
        try (Socket socket = new Socket(HOST_IP, HOST_PORT)){
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

    public static boolean TestConnection() {
        try {
            InetAddress inetAddress = InetAddress.getByName(HOST_IP);
            return inetAddress.isReachable(1000); // Timeout in milliseconds
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to connect to server.", e);
            return false;
        }
    }
}
