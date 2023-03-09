package org.example.ServerV1;

import org.example.Dao.ILoginInfo;
import org.example.Dao.LoginDao;
import org.example.Dao.StudentMarkDao;
import org.example.Dao.StudentMarks;
import org.example.Model.Course;
import org.example.Model.LoginInfo;
import org.example.Service.CourseService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServerOp {

    static DataInputStream inputFromServer;
    static DataOutputStream outputToServer;
    static ObjectOutputStream objectOutputStream;
    static Scanner scanner;
    static StudentMarks studentMarks;
    static ILoginInfo loginDao;
    private static ServerSocket serverSocket;
    private static int port = 8000;
    private static CourseService courseService;
    private static LoginInfo loginInfo;

    public static void main(String[] args) throws SQLException, IOException {
        startServer();
        boolean flag = false;
        boolean boardFlag = true;
        do {
            flag = serverLogin();
            outputToServer.writeBoolean(flag);
        } while (!flag);

        do {
            dashBoard();
            boardFlag = inputFromServer.readBoolean();
        } while (boardFlag);
    }

    public static void startServer() throws IOException, SQLException {
        serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        inputFromServer = new DataInputStream(socket.getInputStream());
        outputToServer = new DataOutputStream(socket.getOutputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        loginDao = new LoginDao();
        studentMarks = new StudentMarkDao();
        courseService = new CourseService();
    }

    public static boolean serverLogin() throws IOException, SQLException {
        scanner = new Scanner(System.in);
        outputToServer.writeUTF("Enter a username:");
        String username = inputFromServer.readUTF();
        outputToServer.writeUTF("Enter a password:");
        String password = inputFromServer.readUTF();
        loginInfo = loginDao.checkCredentials(username, password);
        if (loginInfo != null)
            return true;
        return false;
    }

    public static void dashBoard() throws IOException, SQLException {
        outputToServer.writeUTF("Dashboard\n" + "Student courses :");
        List<Course> courses = studentMarks.getAllCoursesByStudentId(loginInfo.getUserId());
        objectOutputStream.writeObject(courses);
        while (true) {
            try {
                outputToServer.writeUTF("Enter an course id :");
                int option = inputFromServer.readInt();
                outputToServer.writeUTF("your mark : " + studentMarks.getMarkByStudentId(loginInfo.getUserId(),option).getCourseMark() + "" +
                        "\n average :" + courseService.average(option).getAsDouble() + "" +
                        "\n highest :" + courseService.highest(option).getAsDouble() + "" +
                        "\n lowest  :" + courseService.lowest(option).getAsDouble() + "" +
                        "\n median: :" + courseService.median(option));
                outputToServer.writeBoolean(true);
                break;
            } catch (Exception exception) {
                outputToServer.writeUTF("the course id does not exist");
                outputToServer.writeBoolean(false);

            }
        }

    }
}
