package OCR;

import java.io.PrintWriter;

public class Reader {

    public static void main(String[] args) {
        String input="C:\\Users\\jiang\\Documents\\CS3733\\CS-3733-C19-Team-O\\src\\OCR\\testocr.png";
        String output="C:\\Users\\jiang\\Documents\\CS3733\\CS-3733-C19-Team-O\\src\\OCR\\output";
        String tesseract="C:\\Users\\jiang\\Documents\\CS3733\\CS-3733-C19-Team-O\\src\\OCR\\tesseract.exe";
        String[] command =
                {
                        "cmd",
                };
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println("\""+tesseract+"\" \""+input+"\" \""+output+"\" -l eng");
            stdin.close();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
