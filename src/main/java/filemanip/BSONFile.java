package filemanip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This isn't actually a file, but is a raw representation of a BSONFile and essentially allows the further isolation of objects within a file,
 * counting a file as everything between two sets of brackets.
 */

public class BSONFile {
    private String name = "";
    private HashMap fields = new HashMap();
    private HashMap types = new HashMap();
    private ArrayList<String> fieldKeys = new ArrayList<>();
    //TODO: Arrays as fieldtype:fieldname:{fieldvalue, fieldvalue, fieldvalue};

    /**
     * Makes an empty BSON File
     * @param name
     */
    public BSONFile(String name) {
        this.name = name;
    }

    /**
     * Makes a BSON file with starting fields.
     * @param name
     * @param data
     * An arraylist of the fields in the data itself. Represented as strings
     * due to the format of a data field complete: (type:name:value)
     */
    public BSONFile(String name, ArrayList<String> data){
        this.name = name;
        addFields(data);
    }

    /**
     * Adds new fields to the file.
     * @param data
     * Fields to be added to the file, arrays following the format
     * type:name:value.
     */
    public void addFields(ArrayList<String> data){
        //follows format fieldtype:fieldname:fieldvalue
        for(String g : data){
            String[] results = g.split(":");
            if(results.length >= 3) {
                fields.put(results[1], results[2]);
                types.put(results[1], results[0]);
                fieldKeys.add(results[1]);
            }
            else
                System.out.println("Improper formatting: Error.");
        }
    }

    public void addArrayFields(String name, String type, ArrayList<String> data) {
        ArrayList<String> inputData = new ArrayList<>();
        int counter = 1;
        for(String n : data){
            inputData.add(type + ":" + name + "_" + counter + ":" + n);
            counter++;
        }
        addFields(inputData);
    }

    /**
     * Gets the full registry of fields in the format
     * n[1] => {type, name, value}
     * n[2] => {type2, name2, value}
     * etc
     * @return
     * Returns the 3xn array of fields.
     */
    public String[][] getFields(){
        String[][] n = new String[fieldKeys.size()][3];
        for(int i = 0; i < fieldKeys.size(); i++){
            n[i] = new String[] {(String) types.get(fieldKeys.get(i)),
                    fieldKeys.get(i),
                    (String)fields.get(fieldKeys.get(i))};
        }
        return n;
    }

    /**
     * Gets the name of the file.
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Formats the fil as a string in the formats of:
     * BRO
     * int:brovalue:2
     * string:bro:bruh
     * etc
     * @return
     */
    public String toString(){
        String end = "";
        end += name + "\n";
        for(String g : fieldKeys){
            end += types.get(g) + ":" + g + ":" + fields.get(g) + "\n";
        }
        return end;
    }

    /**
     * Determines the equality of two objects based on the name and field keys.
     * @param q
     * @return
     */
    public boolean equals(Object q){
        if(q instanceof BSONFile) {
            BSONFile newQ = (BSONFile) q;
            if(!newQ.name.equals(this.name) || !this.fieldKeys.equals(newQ.fieldKeys))
                return false;
            return true;
        }
        else
            return false;
    }

    /**
     * Shortcut used to see if an object facetiously equals another, using the toString as evidence.
     * @param q
     * @return
     */
    public boolean facEquals(Object q){
        if(this.toString().equals(q.toString()))
            return true;
        return false;
    }
}
