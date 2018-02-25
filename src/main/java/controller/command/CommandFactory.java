package controller.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandFactory {

    private static Map<String, Class> commands;
    private static CommandFactory instance = null;

    private CommandFactory() {
        commands = new ConcurrentHashMap<>();
    }

    public static synchronized CommandFactory getCommandFactory() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public void registerCommand(String url, Class command) {
        commands.put(url, command);
    }

    /**
     *
     * @param url
     * @returns the command to which the specified key
     * @url is mapped, or a NotFound object if this map contains no mapping for
     * the key.
     */
    public Command createCommand(String url, HttpServletRequest request, HttpServletResponse response) {
        Command command = null;        
        Class commandClass = commands.containsKey(url) ? (Class) commands.get(url) : NotFound.class;
        try {
            Constructor commandConstructor = commandClass.getDeclaredConstructor(new Class[]{HttpServletRequest.class, HttpServletResponse.class});
            command = (Command) commandConstructor.newInstance(request, response);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(CommandFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return command;
    }
   
}
