package geekb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.module.InvalidModuleDescriptorException;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.spec.RSAOtherPrimeInfo;

public class WebServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)){
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("new connected");

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(), StandardCharsets.UTF_8));

                PrintWriter output = new PrintWriter(socket.getOutputStream());

                while (!input.ready()) ;

                while (input.ready()) {
                    System.out.println(input.readLine());
                }

                output.println("HTTP/1.1 200 Super");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<h1>VSE Ok!!</h1>");
                output.flush();

                input.close();
                output.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
