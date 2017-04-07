package Zaliczeniowe2.Utils;
import Zaliczeniowe2.Utils.StringUtils;
import Zaliczeniowe2.domain.User;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class DummyUserDB extends HttpServlet {
    private static User generateAdmin(){
        User a = new User("admin", StringUtils.makeSHA256("admin"),"s14774@pjwstk.edu.pl");
        a.setPermissionLevel(3);
        return a;
    }

    public static void addUser(User u, ServletContext application){
        List<User> userList = new ArrayList<>();

        if(application.getAttribute("userList") == null){
            userList.add(generateAdmin());
            userList.add(u);
            application.setAttribute("userList", userList);
        }else{
            userList = getUsers(application);
            userList.add(u);
            setDatabase(application,userList);
        }
    }

    public static void updateUser(User userNew, ServletContext application){
        if(userNew==null) return;

        List<User> userList = getUsers(application);
        for(User u : userList){
            if(u.getLogin().equals(userNew.getLogin())) {
                u.setPermissionLevel(userNew.getPermissionLevel());
                u.setEmail(userNew.getEmail());
                u.setPassword(userNew.getPassword());
            }
        }
    }

    public static List<User> getUsers(ServletContext application){
        if(application.getAttribute("userList") == null){
            return new ArrayList<>();
        }else{
            return (List<User>) application.getAttribute("userList");
        }
    }

    public static User getUserByLogin(ServletContext application,String login){
        for(User u : getUsers(application)){
            if(u.getLogin().equals(login))
                return u;
        }
        return null;
    }

    public static User getUserByEmail(ServletContext application,String email){
        for(User u : getUsers(application)){
            if(u.getEmail().equals(email))
                return u;
        }
        return null;
    }

    public static User tryLogin(ServletContext application,String login,String password){
        if(StringUtils.isAnyEmpty(login,password)){
            return null;
        }

        User u = getUserByLogin(application,login);
        if(u==null)
            return null;

        if(u.getPassword().equals(StringUtils.makeSHA256(password))){
            return u;
        }else{
            return null;
        }

    }

    private static void setDatabase(ServletContext application, List<User> userList){
        application.setAttribute("userList", userList);
    }
}
