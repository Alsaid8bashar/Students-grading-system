package org.example.ServerV1;

import java.io.IOException;
import java.sql.SQLException;

public interface Server {
    void startServer() throws IOException, SQLException;
    boolean serverLogin() throws IOException, SQLException;
    void dashBoard() throws IOException, SQLException;
}
