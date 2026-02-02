package com.God.sAlgo.God.sAlgo.solver;

import java.io.*;
import java.util.Map;

public class GodsTableSerializer {

    private static final String FILE_PATH = "gods_table.bin";

//    -----------SAVE-------------
    public static void save(Map<Long, Integer> table) {
        try (ObjectOutputStream out =
                new ObjectOutputStream(new BufferedOutputStream(
                        new FileOutputStream(FILE_PATH)))) {
            out.writeObject(table);
            System.out.println("God's table saved to disk");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save gods table", e);
        }
    }

//    ----------LOAD----------------
    @SuppressWarnings("unchecked")
    public static Map<Long, Integer> load() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(
                             new FileInputStream(FILE_PATH)))) {

            System.out.println("Gods table loaded from disk.");
            return (Map<Long, Integer>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load gods table", e);
        }
    }

    public static boolean exists() {
        return new File(FILE_PATH).exists();
    }
}
