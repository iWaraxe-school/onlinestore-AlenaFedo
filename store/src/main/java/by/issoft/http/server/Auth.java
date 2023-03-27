package by.issoft.http.server;

import com.sun.net.httpserver.BasicAuthenticator;

public class Auth extends BasicAuthenticator {

    public Auth(String get) {
        super(get);
    }

    public boolean checkCredentials(String user, String password) {
        return user.equals("admin") && password.equals("admin");
    }
}
