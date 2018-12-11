import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Wyszukiwarka {
    public static void main(String[] args) {
        Connection[] connections = {Jsoup.connect("http://www.onet.pl/"), Jsoup.connect("http://www.wp.pl/"), Jsoup.connect("http://www.interia.pl/")};
        try {
            Path path = Paths.get("./popular_words.txt");
            if (!Files.exists(path)) Files.createFile(path);
            else {
                Files.delete(path);
                Files.createFile(path);
            }

            for (Connection connect : connections) {
                Document document = connect.get();
                Elements links = document.select("span.title");
                Files.write(path, links.eachText(), StandardOpenOption.APPEND);
            }

            StringTokenizer st;
            ArrayList<String> inList = new ArrayList<>();

            for (String linefromFile : Files.readAllLines(path)) {
                st = new StringTokenizer(linefromFile, " ");
                while (st.hasMoreTokens()) {
                    inList.add(st.nextToken());
                }
            }

            //Clear input
            File file = new File(path.toString());
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            //Removing words shorter than 3 chars
            for (int i = (inList.size() - 1); i > (-1); i--) if (inList.get(i).length() < 4) inList.remove(i);
            Files.write(path, inList, StandardOpenOption.APPEND);

            //Making forbidden words table
            String[] excludedWords = {"prosta", "były", "kazał", "swoje", "tylko", "będzie", "czekają", "dzieci"};

            //Making output file
            Path path2 = Paths.get("./filtered_popular_words.txt");
            if (!Files.exists(path2)) Files.createFile(path2);
            else {
                Files.delete(path2);
                Files.createFile(path2);
            }

            //Making outlist without forbidden words
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> outList = new ArrayList<>();

            while ((line = buffReader.readLine()) != null) {
                if (!StringUtils.containsAny(line.toLowerCase(), excludedWords)) {
                    outList.add(line);
                }
            }
            Collections.sort(outList);

            //Removing repetitions in output file
            boolean isDoubled = true;
            boolean wasChanged;
            if (outList.size() > 1) {
                while (isDoubled) {
                    wasChanged = false;
                    for (int i = 0; i < (outList.size() - 1); i++) {
                        if (outList.get(i).equals(outList.get(i + 1))) {
                            outList.remove(i + 1);
                            wasChanged = true;
                            break;
                        }
                    }
                    if (!wasChanged) isDoubled = false;
                }
            }

            //Making output file
            Files.write(path2, outList, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Błąd zapisu pliku");
        }
    }
}
