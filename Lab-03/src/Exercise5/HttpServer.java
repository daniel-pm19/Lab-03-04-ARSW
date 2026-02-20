package Exercise5;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class HttpServer {

    private static final int PORT = 35000;
    private static final Path WWW_ROOT = Paths.get("www").toAbsolutePath().normalize();
    public static void main(String[] args) throws IOException{
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server running on port: " + PORT);
            while(true){
                try (Socket clientSocket = serverSocket.accept();) {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream rawOut = clientSocket.getOutputStream();

                String inputLine = in.readLine();
                if (inputLine == null || inputLine.isEmpty()) {
                    continue;
                }

                System.out.println("Received: " + inputLine);

                String header;
                while ((header = in.readLine()) != null && !header.isEmpty()) {
                    System.out.println(header);
                }

                String[] parts = inputLine.split("\\s+");
                if (parts.length < 2) {
                    sendError(rawOut, "400 Bad Request", "Bad Request");
                    continue;
                }

                String method = parts[0];
                String rawPath = parts[1];

                if (!method.equals("GET")) {
                    sendError(rawOut, "405 Method Not Allowed", "Only GET supported");
                    continue;
                }

                String decodedPath = URLDecoder.decode(rawPath, "UTF-8");
                if (decodedPath.contains("..")) {
                    sendError(rawOut, "403 Forbidden", "Forbidden");
                    continue;
                }

                if (decodedPath.equals("/")) {
                    decodedPath = "/index.html";
                }

                Path requested = WWW_ROOT.resolve(decodedPath.substring(1)).normalize();
                if (!requested.startsWith(WWW_ROOT)) {
                    sendError(rawOut, "403 Forbidden", "Forbidden");
                    continue;
                }

                if (Files.exists(requested) && Files.isRegularFile(requested)) {
                    byte[] data = Files.readAllBytes(requested);
                    String contentType = guessContentType(requested);
                    sendResponse(rawOut, "200 OK", contentType, data);
                } else {
                    String body = "<html><body><h1>404 Not Found</h1></body></html>";
                    sendResponse(rawOut, "404 Not Found", "text/html; charset=utf-8", body.getBytes("UTF-8"));
                }

                rawOut.flush();

                System.out.println(" Ready to receipt ...");

                } catch (IOException e){
                    System.err.println("Accept failed: " + e.getMessage());
                }
            }
        } catch (IOException e){
            System.err.println("Could not listen on port: " + PORT + " -> " + e.getMessage());
            System.exit(1);
        }

    }


    private static void sendResponse(OutputStream out, String status, String contentType, byte[] body) throws IOException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), false);
        pw.print("HTTP/1.1 " + status + "\r\n");
        pw.print("Content-Type: " + contentType + "\r\n");
        pw.print("Content-Length: " + body.length + "\r\n");
        pw.print("Connection: close\r\n");
        pw.print("\r\n");
        pw.flush();
        out.write(body);
    }

    private static void sendError(OutputStream out, String status, String message) throws IOException {
        String body = "<html><body><h1>" + status + "</h1><p>" + message + "</p></body></html>";
        sendResponse(out, status, "text/html; charset=utf-8", body.getBytes("UTF-8"));
    }

    private static String guessContentType(Path p) {
        String name = p.getFileName().toString().toLowerCase();
        if (name.endsWith(".html") || name.endsWith(".htm")) return "text/html; charset=utf-8";
        if (name.endsWith(".css")) return "text/css";
        if (name.endsWith(".js")) return "application/javascript";
        if (name.endsWith(".png")) return "image/png";
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) return "image/jpeg";
        if (name.endsWith(".gif")) return "image/gif";
        if (name.endsWith(".svg")) return "image/svg+xml";
        if (name.endsWith(".ico")) return "image/x-icon";
        if (name.endsWith(".txt")) return "text/plain; charset=utf-8";
        return "application/octet-stream";
    }
    
}
