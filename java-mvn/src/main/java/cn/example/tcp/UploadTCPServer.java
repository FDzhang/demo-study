package cn.example.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadTCPServer {

    public static void main(String[] args) {
        try (
                ServerSocket socket = new ServerSocket(10001);

        ) {
            while (true) {
                Socket accept = socket.accept();
                new Thread(new ClientHandler(accept)).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        File dir = new File("D:\\devCode\\demo-study\\java-mvn\\src\\main\\java\\cn\\example");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (InputStream inputStream = socket.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(
                     "D:\\devCode\\demo-study\\java-mvn\\src\\main\\java\\cn\\example\\b1.jpg");
        ) {

            byte[] bytes = new byte[1024];
            int idx;
            while ((idx = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, idx);
            }

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("上传成功".getBytes());
            outputStream.flush();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
