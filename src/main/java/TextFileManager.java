
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulates files.
 * Shits simple.
 */

public class TextFileManager {
    private String fileLoc = "";
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();
    private static final String DEFAULT_DIR = System.getProperty("user.home") + "\\Desktop\\";

    /**
     * Uses a pre-existing file on ya desktop
     * @param fileName
     * @throws IOException
     */
    public TextFileManager(String fileName) throws IOException {
        fileLoc = DEFAULT_DIR + fileName;
        ensureFile();
    }

    /**
     * Manipulates a text file but in some wackadoodle location.
     * @param dir
     * Directory
     * @param filename
     * Filename
     * @throws IOException
     */
    public TextFileManager(String dir, String filename) throws IOException {
        fileLoc = dir + filename;
        ensureFile();
    }


    /**
     * Shits essential, makes sure the file is actually there.
     * @throws IOException
     */
    public void ensureFile() throws IOException {
        Path p = Paths.get(fileLoc);
        boolean b = Files.isReadable(p);
        if(!b){
            File n = new File(fileLoc);
            n.createNewFile();
        }
    }

    /**
     * This is supposed to be used for "Short" text files but it works fine on long ones.
     * Basically it converts a text file into a list of strings by line.
     * @return
     * @throws IOException
     */
    public List<String> tryReadingShort() throws IOException {
        ensureFile();
        Path p = Paths.get(fileLoc);
        return Files.readAllLines(p);
    }

    /**
     * Yknow how we took a file and read it.
     * Does the same thing backwards, just makes one.
     * @param lines
     * @throws IOException
     */
    public void tryWritingShort(List<String> lines) throws IOException {
        ensureFile();
        Path p = Paths.get(fileLoc);
        List<String> n = tryReadingShort();
        List<String> artifact = new ArrayList<>();
        while(!n.isEmpty()){
            artifact.add(n.remove(0));
        }
        while(!lines.isEmpty()) {
            artifact.add(lines.remove(0));
        }
        Files.write(p, artifact);
    }

    /**
     * Log is just shorthand for writing something down. Does the heavy lifting of turning a string into an array into writing.
     * @param log
     * whatever you're writing
     * @throws IOException
     */
    public void log(String log) throws IOException {
        List<String> n = new ArrayList<>();
        n.add(log);
        tryWritingShort(n);
    }

    /**
     * Deletes lines between two values, including those lines.
     * @param start
     * First line to  be deleted
     * @param finish
     * Last line to be deleted.
     * @throws IOException
     */
    //Personal Note: Works by saving everything on the file BUT those lines and then rewriting the whole thing again.
    public void deleteLines(int start, int finish) throws IOException {
        if(finish < start) return;
        ensureFile();
        Path p = Paths.get(fileLoc);
        List<String> n = tryReadingShort();
        List<String> artifact = new ArrayList<>();
        for(int i = 0; i < n.size(); i++){
            if(i < start || i > finish)
                artifact.add(n.get(i));
        }
        Files.write(p, artifact);
    }

    /**
     * Fancily logs something down, used for other shit like logs of things.
     * @param inLog
     * @throws IOException
     */
    public void logWithTime(String inLog) throws IOException{
        log("-------------------------------------------");
        log("Entry made at " + dtf.format(now));
        log("-------------------------------------------");
        log(inLog);
    }

    /**
     * Shows everything on the text file.
     * @throws IOException
     */
    public void print() throws IOException{
        List<String> n = tryReadingShort();
        while(!n.isEmpty()){
            System.out.println(n.remove(0));
        }
    }
}
