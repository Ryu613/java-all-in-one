package example.p42.functionalInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Processor {
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    /**
     * 用lambda表达式，减少BufferedReader的模板代码
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String oneLine = processFile((br) -> br.readLine());
        String twoLines = processFile((br) -> br.readLine() + br.readLine());
        System.out.println(oneLine + " | " + twoLines);
    }
}
