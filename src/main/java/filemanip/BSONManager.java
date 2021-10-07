package filemanip;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Magnum opus. Fucks around with BSON objects and files so you don't have to!
 */

//yea thats right, fuck you json
public class BSONManager {
    ArrayList<BSONFile> files = new ArrayList<>();
    TextFileManager n = new TextFileManager("BSONTesting\\Testing.bson");
    public BSONManager() throws IOException{ }
    public BSONManager(String name) throws IOException {
        n = new TextFileManager("BSONTesting\\" + name);
    }
    public BSONManager(String dir, String name) throws IOException{
        n = new TextFileManager(dir, name);
    }
    /**
     * This is pretty essential. Basically, it reads the file itself and then
     * makes all of the little subfiles and objects on it.
     * @throws IOException
     */
    public void readBSON() throws IOException {
        List<String> q = n.tryReadingShort();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        int currentIndex = 0;
        data.add(new ArrayList<String>());
        for(String g : q){
            if(!g.equals("")) {
                if (g.charAt(0) == '{') {
                    names.add(g.substring(1));
                } else if (g.charAt(0) == '\t') {
                    data.get(currentIndex).add(g.substring(1));
                } else if (g.charAt(0) == '}') {
                    currentIndex++;
                    data.add(new ArrayList<>());
                }
            }
        }
        for(int i = 0; i < names.size(); i++){
            files.add(new BSONFile(names.get(i),
                    data.get(i)));
        }
    }

    /**
     * Takes a bsonfile and actually writes it
     * onto the file itself.
     * @param file
     * @throws IOException
     */
    public void writeToFile(BSONFile file) throws IOException {
        if(exists(file)) return;
        String[][] content = file.getFields();
        String name = file.getName();
        if(files.indexOf(file) == -1){
            n.log("{" + name);
            for(String[] gq : content){
                String add = "\t";
                int tba = 0;
                for(String q : gq){
                    tba++;
                    add += q;
                    if(tba != 3)
                        add+=":";
                }
                n.log(add);
            }
            n.log("}");
        }
    }

    /**
     * Deletes a file of a certain name from the .bson.
     * @param fileName
     * @throws IOException
     */
    public void deleteFromFile(String fileName) throws IOException {
        List<String> lines = n.tryReadingShort();
        int start = -1; int end = -1;
        int i = 0;
        boolean found = false;
        while(i < lines.size() && !found){
            if(lines.get(i).equals("{"+ fileName)){
                start = i;
            }
            if(start != -1){
                if(lines.get(i).equals("}")){
                    end = i;
                    found = true;
                }
            }
            i++;
        }
        if(end < start){
            System.out.println("wtf");
            return;
        }
        if(found)
            n.deleteLines(start, end);
    }

    /**
     * Replaces a bsonfile of a certain name with another.
     * Then, it re-reads everything just to make sure its correct!
     * @param n
     * @throws IOException
     */
    public void replaceToFile(BSONFile n) throws IOException {
        deleteFromFile(n.getName());
        if(files.indexOf(n) != -1)
            files.remove(files.indexOf(n));
        writeToFile(n);
        readBSON();
    }

    public ArrayList<BSONFile> getFiles(){
        return files;
    }

    /**
     * Finds a specific bsonfile in the mix and
     * returns its index.
     * @param h
     * That file.
     * @return
     * Index of that file.
     */
    public int find(BSONFile h){
        for(int i = 0; i < files.size(); i++){
            if(files.get(i).facEquals(h))
                return i;
        }
        return -1;
    }

    /**
     * Determines if a BSONFile already
     * exists in the BSONManager.
     * @param h
     * @return
     */
    public boolean exists(BSONFile h){
        for(BSONFile q : files){
            if(q.facEquals(h))
                return true;
        }
        return false;
    }
}
