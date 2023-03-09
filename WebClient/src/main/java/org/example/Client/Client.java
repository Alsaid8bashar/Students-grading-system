package org.example.Client;

import java.io.IOException;

public interface   Client {

     void startClient() throws IOException;

    void login() throws IOException;

     void dashboard()throws IOException, ClassNotFoundException;
}
