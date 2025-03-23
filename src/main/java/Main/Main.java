package Main;

import Interfaces.IIDTag;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

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

//        System.out.println(myContainer.getTag(CutState.class));
//        System.out.println(myContainer.isValidIID());
//        myContainer.addTag(IngredientType.BAKING);
//        System.out.println(myContainer.isValidIID());
//        myContainer.addTag(IngredientUnit.MILLILITER);
//        System.out.println(myContainer.isValidIID());
//        myContainer.addTag(CutState.GROUND);
//        System.out.println(myContainer.isValidIID());
//        System.out.println(myContainer);
    }


}
