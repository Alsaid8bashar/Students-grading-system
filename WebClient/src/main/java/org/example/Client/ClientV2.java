package org.example.Client;

import org.example.Model.Course;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClientV2 {

    private static DataOutputStream toServer = null;
    private static DataInputStream fromServer = null;
    private static ObjectInputStream in;
    private static Scanner scanner;
    private static Socket socket;

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        startClient();
        boolean loginFlag;
        boolean boardFlag = true;

        do {
            login();
            loginFlag = fromServer.readBoolean();
        } while (!loginFlag);

        do {
            dashboard();
            System.out.println("enter any number to logout or char to continue ");
            if (scanner.hasNextInt()) {
                boardFlag = false;
            } else {
                scanner.next();
            }
            toServer.writeBoolean(boardFlag);
        } while (boardFlag);

    }

    public static void startClient() throws IOException {
        socket = new Socket("localhost", 8000);
        fromServer = new DataInputStream(socket.getInputStream());
        toServer = new DataOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        scanner = new Scanner(System.in);
    }

    public static void login() throws IOException {
        System.out.println(fromServer.readUTF());
        toServer.writeUTF(scanner.nextLine());

        System.out.println(fromServer.readUTF());
        toServer.writeUTF(scanner.nextLine());
    }


    public static void dashboard() throws IOException, ClassNotFoundException {
        System.out.println(fromServer.readUTF());
        List<Course> courses = (List<Course>) in.readObject();
        for (Course course : courses) {
            System.out.println(course);
        }

        while (true) {
            try {
                System.out.println(fromServer.readUTF());
                toServer.writeInt(scanner.nextInt());
                System.out.println(fromServer.readUTF());
               if (fromServer.readBoolean()){
                   break;
               }
            } catch (Exception exception) {
                System.out.println("Invalid course ID ");
            }
        }
    }


}
