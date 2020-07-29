package serialization;

import cache.CacheHandler;

import java.io.*;

public class SerializerCache {

    String FILENAME = "CACHEHANDLER_serialization";

    /**
     * Сериализует объект класса
     *
     */
    public void serialize(CacheHandler o) throws IOException {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)
        ) {
            System.out.println(o.getClass().getSimpleName() + " serialization start");
            out.writeObject(o);
            System.out.println("Serialization finish");
        }
    }

    /**
     * Десериализует объект класса из файла
     */
    @SuppressWarnings("unchecked")
    public CacheHandler deserialize() throws IOException, ClassNotFoundException {
        try(
                FileInputStream fileInputStream = new  FileInputStream(FILENAME);
                ObjectInputStream in = new ObjectInputStream(fileInputStream)
        ) {
            System.out.println("Deserialization start");
            CacheHandler deserializedObject = (CacheHandler) in.readObject();
            System.out.println(deserializedObject.getClass().getSimpleName() + " deserialization finish");
            return deserializedObject;
        }
    }
}