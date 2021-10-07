package filemanip;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  OK, I'm pretty damn proud of this one.
 *  Basically, it takes that DataPair object and uses it
 *  to store actual variables.
 */
public class BSONObject {
    private String name = "";
    private ArrayList<DataPair<String, String>> stringVars = new ArrayList<>();
    private ArrayList<DataPair<String, Integer>> intVars = new ArrayList<>();
    private ArrayList<DataPair<String, Double>> doubleVars = new ArrayList<>();
    private ArrayList<DataPair<String, Boolean>> boolVars = new ArrayList<>();
    private ArrayList<DataPair<String, Character>> charVars = new ArrayList<>();
    private ArrayList<DataPair<String, String>> miscVars = new ArrayList<>();
    private BSONFile source = null;

    /**
     * Forms an object given a file.
     * @param n
     */
    public BSONObject(BSONFile n){
        source = n;
        formulate(n);
    }

    /**
     * Returns the name of the object.
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Allows the alteration of the name of the object.
     * @param nName
     */
    public void setName(String nName){
        name = nName;
    }

    public void addString(String name, String value){
        stringVars.add(new DataPair<>(name, value));
    }

    public void addInt(String name, int value){
        intVars.add(new DataPair<>(name, value));
    }

    public void addDouble(String name, double value){
        doubleVars.add(new DataPair<>(name, value));
    }

    public void addBool(String name, boolean value){
        boolVars.add(new DataPair<>(name, value));
    }

    public void addChar(String name, char value){
        charVars.add(new DataPair<>(name, value));
    }

    public void addMisc(String name, String value){
        miscVars.add(new DataPair<>(name, value));
    }

    /**
     * Fetches a string variable.
     * @param name
     * Name of the variable to be returned.
     * @return
     * Returns the string associated with the name of that variable
     */
    public String fetchStr(String name){
        for(DataPair<String, String> i : stringVars){
            if(i.getOne().equals(name))
                return i.getTwo();
        }
        return "NOT_FOUND";
    }
    /**
     * Fetches an integer variable.
     * @param name
     * Name of the variable to be returned.
     * @return
     * Returns the integer associated with the name of that variable
     */
    public int fetchInt(String name){
        for(DataPair<String, Integer> i : intVars){
            if(i.getOne().equals(name))
                return i.getTwo();
        }
        return -1;
    }

    /**
     * Fetches a double variable.
     * @param name
     * Name of the variable to be returned.
     * @return
     * Returns the double associated with the name of that variable
     */
    public double fetchDouble(String name){
        for(DataPair<String, Double> i : doubleVars){
            if(i.getOne().equals(name))
                return i.getTwo();
        }
        return -1;
    }

    /**
     * Fetches a boolean variable.
     * @param name
     * Name of the variable to be returned.
     * @return
     * Returns the boolean associated with the name of that variable
     */
    public boolean fetchBool(String name){
        for(DataPair<String, Boolean> i : boolVars){
            if(i.getOne().equals(name))
                return i.getTwo();
        }
        return false;
    }

    /**
     * Fetches a character variable.
     * @param name
     * Name of the variable to be returned.
     * @return
     * Returns the character associated with the name of that variable
     */
    public char fetchChar(String name){
        for(DataPair<String, Character> i : charVars){
            if(i.getOne().equals(name))
                return i.getTwo();
        }
        return '%';
    }

    /**
     * Fetches a "misc" variable, which is stored as a string.
     * @param name
     * Name of the variable to be returned.
     * @return
     * Returns the miscellaneous object associated with the name of that variable
     */
    public String fetchMisc(String name){
        for(DataPair<String, String> i : miscVars){
            if(i.getOne().equals(name))
                return i.getTwo();
        }
        return "NOT_FOUND";
    }

    /**
     * Searches for and replaces a variable's data with something else.
     * @param name
     * Name of the variable to be altered.
     * @param strData
     * Replacement for the old data.
     */
    public void searchAndReplace(String name, String strData){
        for(DataPair<String, String> i : stringVars){
            if(i.getOne().equals(name))
                i.setTwo(strData);
        }
    }
    /**
     * Searches for and replaces a variable's data with something else.
     * @param name
     * Name of the variable to be altered.
     * @param strData
     * Replacement for the old data.
     */
    public void searchAndReplace(String name, int strData){
        for(DataPair<String, Integer> i : intVars){
            if(i.getOne().equals(name))
                i.setTwo(strData);
        }
    }
    /**
     * Searches for and replaces a variable's data with something else.
     * @param name
     * Name of the variable to be altered.
     * @param strData
     * Replacement for the old data.
     */
    public void searchAndReplace(String name, double strData){
        for(DataPair<String, Double> i : doubleVars){
            if(i.getOne().equals(name))
                i.setTwo(strData);
        }
    }

    /**
     * Searches for and replaces a variable's data with something else.
     * @param name
     * Name of the variable to be altered.
     * @param strData
     * Replacement for the old data.
     */
    public void searchAndReplace(String name, boolean strData){
        for(DataPair<String, Boolean> i : boolVars){
            if(i.getOne().equals(name))
                i.setTwo(strData);
        }
    }
    /**
     * Searches for and replaces a variable's data with something else.
     * @param name
     * Name of the variable to be altered.
     * @param strData
     * Replacement for the old data.
     */
    public void searchAndReplace(String name, char strData){
        for(DataPair<String, Character> i : charVars){
            if(i.getOne().equals(name))
                i.setTwo(strData);
        }
    }
    /**
     * Searches for and replaces a variable's data with something else.
     * @param name
     * Name of the variable to be altered.
     * @param strData
     * Replacement for the old data.
     */
    public void searchAndReplaceMisc(String name, String strData){
        for(DataPair<String, String> i : miscVars){
            if(i.getOne().equals(name))
                i.setTwo(strData);
        }
    }

    /**
     * Jesus fucking christ...
     * Basically, this is what takes the raw fields of a file and converts it
     * into actionable data, forming it into an actual BSON object. This won't
     * need to be called by the user.
     * @param n
     */
    public void formulate(BSONFile n){
        name = n.getName();
        String[][] fields = n.getFields();
        for(String[] i : fields){
            String ref = i[0].toLowerCase();
            if(ref.equals("string")){
                stringVars.add(new DataPair<>(i[1], i[2]));
            }
            else if(ref.equals("int") || ref.equals("integer") || ref.equals("short")){
                intVars.add(new DataPair<>(i[1], Integer.parseInt(i[2])));
            }
            else if(ref.equals("double") || ref.equals("dbl") || ref.equals("dubl") || ref.equals("long")){
                doubleVars.add(new DataPair<>(i[1], Double.parseDouble(i[2])));
            }
            else if(ref.equals("bool") || ref.equals("boolean") || ref.equals("boo!")){
                boolVars.add(new DataPair<>(i[1], Boolean.parseBoolean(i[2])));
            }
            else if(ref.equals("char") || ref.equals("character")){
                charVars.add(new DataPair<>(i[1], i[2].charAt(0)));
            }
            else{
                miscVars.add(new DataPair<>(i[1], i[2]));
            }
        }
    }

    /**
     * This fundamentally takes the object as it is
     * and converts it back into the raw fields of
     * a BSON file, which can then be turned back
     * into an actual file on the computer
     * @return
     * This BSONObject as a BSONFile.
     */
    public BSONFile convertToFile(){
        ArrayList<String> end = new ArrayList<>();
        for(DataPair<String, String> i : stringVars){
            end.add("string:"+ i.getOne() + ":" + i.getTwo());
        }
        for(DataPair<String, Integer> i : intVars){
            end.add("int:"+ i.getOne() + ":" + i.getTwo());
        }
        for(DataPair<String, Double> i : doubleVars){
            end.add("double:"+ i.getOne() + ":" + i.getTwo());
        }
        for(DataPair<String, Boolean> i : boolVars){
            end.add("bool:"+ i.getOne() + ":" + i.getTwo());
        }
        for(DataPair<String, Character> i : charVars){
            end.add("char:"+ i.getOne() + ":" + i.getTwo());
        }
        for(DataPair<String, String> i : miscVars){
            end.add("misc:"+ i.getOne() + ":" + i.getTwo());
        }
        BSONFile n = new BSONFile(name, end);
        return n;
    }

    /**
     * Does what toString does best, baby.
     * @return
     */
    public String toString(){
        String end = "";
        end += name + "\n{\n";
        for(DataPair<String, String> i : stringVars){
            end += "\tString "+ i.getOne() + " = \"" + i.getTwo() + "\";\n";
        }
        for(DataPair<String, Integer> i : intVars){
            end += "\tint " + i.getOne() + " = " + i.getTwo() + ";\n";
        }
        for(DataPair<String, Double> i : doubleVars){
            end += "\tdouble " + i.getOne() + " = " + i.getTwo() + ";\n";
        }
        for(DataPair<String, Boolean> i : boolVars){
            end += "\tboolean " + i.getOne() + " = " + i.getTwo() + ";\n";
        }
        for(DataPair<String, Character> i : charVars){
            end += "\tchar " + i.getOne() + " = \'" + i.getTwo() + "\';\n";
        }
        for(DataPair<String, String> i : miscVars){
            end +="\tTYPE_NOT_FOUND " + i.getOne() + " = " + i.getTwo() + ";\n";
        }
        end += "}\n";
        return end;
    }
}
