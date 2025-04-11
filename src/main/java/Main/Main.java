package Main;

import Interfaces.TagDataInterface;
import Tags.*;

public class Main {
    public static void main(String[] args) {
        String[] myStrings = {"a", "b", "c"};

        RootName rn = new RootName("name");
        RootName rn2 = new RootName("name2");
        TagDataInterface rn3 = new RootName("name3");
        TagDataInterface rd2 = new RootDescriptor("descriptor2");
        RootDescriptor rd = new RootDescriptor("descriptor");

        System.out.println(rn.isSameTagType(rd));
        System.out.println(rn.isSameTagType(rn2));
        System.out.println(rn.isSameTagType(rn3));
        System.out.println(rn.isSameTagType(rd2));
        System.out.println(rn.isSameTagType(RootDescriptor.class));


//        System.out.println(CookState.RAW.isSameTagType(CutState.class));

//        Class<?> testClass = IIDTag.class;
//        Class<? extends IIDTag> testClass2 = CookState.class;
//        System.out.println(testClass);
//        System.out.println(testClass2);

//        System.out.println(CookState.RAW.isSameTagType(CutState.class));

//        IIDContainer myContainer = new IIDContainer(new IIDTag[]{CookState.BOILED,CutState.CHOPPED});
//        myContainer.addTag(IngredientType.MISC);
//        myContainer.addTag(IngredientUnit.UNIT);
//        myContainer.addNameTag("TestFood");
//        myContainer.addDescriptorTag("Some test food"); // should auto-lock here
//        myContainer.unlock();
//        myContainer.addNameTag("TestFood2");
//        myContainer.lock();
//        System.out.println(myContainer.getNameTag());

//        PantryNode myNode = new PantryNode(myContainer);
//        PantryStorage myStorage = new PantryStorage();
//        myStorage.addPantryNode(myNode);
//
//        try {
//            FileOutputStream fOut = new FileOutputStream("src/main/java/DataStorage/PantryStorage.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fOut);
//            out.writeObject(myStorage);
//            out.close();
//            fOut.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        PantryStorage myReadStorage;
//        try {
//            FileInputStream fIn = new FileInputStream("src/main/java/DataStorage/PantryStorage.ser");
//            ObjectInputStream oIn = new ObjectInputStream(fIn);
//            myReadStorage = (PantryStorage) oIn.readObject();
//            oIn.close();
//            fIn.close();
//            System.out.println(myReadStorage);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


}
