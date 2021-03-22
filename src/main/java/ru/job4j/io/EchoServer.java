package ru.job4j.io;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String answer = null;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("msg=Bye")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            server.close();
                        } else if (str.contains("msg=Hello")) {
                            answer = "Hello";
                        } else if (str.contains("msg=Any")) {
                            answer = "What";
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }
}


//                    if (str.contains("msg=Exit")) {
//                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
//                        server.close();
//                    } else if (str.contains("msg=Any")) {
//                        out.write("What.".getBytes());
//                    } else if (str.contains("msg=Hello")) {
//                        out.write("Hello.".getBytes());
//                    }