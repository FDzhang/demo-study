package cn.example.tcp;

import java.io.*;
import java.net.Socket;

public class UploadTCPClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 10001);
            System.out.println("连接到服务器端，开始文件上传");

            File file = new File(
                    "D:\\devCode\\demo-study\\java-mvn\\src\\main\\java\\cn\\example\\bird.jpg");

            FileInputStream inputStream = new FileInputStream(file);

            OutputStream outputStream = socket.getOutputStream();

            byte[] bytes = new byte[1024];
            int byteRead;

            while ((byteRead = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, byteRead);
            }
            socket.shutdownOutput();
            inputStream.close();

            InputStream inputStream1 = socket.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream1));

            String response = read.readLine();
            System.out.println(response);

            outputStream.close();
            inputStream1.close();
            read.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
