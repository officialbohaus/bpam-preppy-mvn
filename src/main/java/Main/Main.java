package Main;

import Interfaces.IIDTag;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String[] myStrings = {"a", "b", "c"};

        System.out.println(CookState.RAW.isSameTagType(CutState.class));

        IIDContainer myContainer = new IIDContainer(new IIDTag[]{CookState.BOILED,CutState.CHOPPED});
        myContainer.addTag(IngredientType.MISC);
        myContainer.addTag(IngredientUnit.UNIT);
        myContainer.addNameTag("TestFood");
        myContainer.addDescriptorTag("Some test food"); // should auto-lock here
        myContainer.unlock();
        myContainer.addNameTag("TestFood2");
        myContainer.lock();
        System.out.println(myContainer.getNameTag());

        PantryNode myNode = new PantryNode(myContainer);
        PantryStorage myStorage = new PantryStorage();
        myStorage.addPantryNode(myNode);

        try {
            FileOutputStream fOut = new FileOutputStream("src/main/java/DataStorage/PantryStorage.ser");
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(myStorage);
            out.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PantryStorage myReadStorage;
        try {
            FileInputStream fIn = new FileInputStream("src/main/java/DataStorage/PantryStorage.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            myReadStorage = (PantryStorage) oIn.readObject();
            oIn.close();
            fIn.close();
            System.out.println(myReadStorage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
