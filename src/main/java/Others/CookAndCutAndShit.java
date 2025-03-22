package Others;

import Exceptions.InvalidRequestException;
import Interfaces.CookInterface;
import Interfaces.CutInterface;
import Tags.CookState;
import Tags.CutState;
import Interfaces.IIDTag;

public class CookAndCutAndShit implements CookInterface, CutInterface {
    private String[] IIDTags;
    static final String IID_ORDER = "name-ingtype-cook-cut-unit";
    private final int COOK_INDEX = 4;
    private final int CUT_INDEX = 5;
    private String newIID;

    /* *
     * Cook Methods
     * TODO Provide guard statements and exception handling (e.g. if IID is not in a valid format)
     *      For valid IID Format, look at IID_ORDER
     */

    public String bake(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:" + CookState.BAKED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String fry(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:" + CookState.FRIED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String sautee(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:" + CookState.SAUTEED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String sear(String IID) {
        Guards.checkIID(IID);

        IIDTags = IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:" + CookState.SEARED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String grill(String IID) {
        Guards.checkIID(IID);

        IIDTags = IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.GRILLED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String roast(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.ROASTED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String smoke(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.SMOKED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String stew(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.STEWED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String steam(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.STEAMED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String toast(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.TOASTED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String boil(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "COOKSTATE:"+ CookState.BOILED.getCookID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

//    public String cook(String IID, IIDTag cookState) {
//        switch(cookState) {
//            case CookState.RAW:
//                return "THIS SHOULDN'T BE RAW.";
//            case CookState.BAKED:
//                return bake(IID);
//            case CookState.FRIED:
//                return fry(IID);
//            case CookState.SAUTEED:
//                return sautee(IID);
//            case CookState.GRILLED:
//                return grill(IID);
//            case CookState.ROASTED:
//                return roast(IID);
//            case CookState.SMOKED:
//                return smoke(IID);
//            case CookState.STEWED:
//                return stew(IID);
//            case CookState.STEAMED:
//                return steam(IID);
//            case CookState.TOASTED:
//                return toast(IID);
//            case CookState.BOILED:
//                return boil(IID);
//            case CookState.NONE:
//                return "THIS SHOULDN'T BE NONE.";
//            default:
//                throw new InvalidRequestException();
//        }
//    }

    public String cook(String IID, IIDTag cookState) {
        switch(cookState) {
            case CookState.RAW:
                return "THIS SHOULDN'T BE RAW.";
            case CookState.BAKED:
                return bake(IID);
            case CookState.FRIED:
                return fry(IID);
            case CookState.SAUTEED:
                return sautee(IID);
            case CookState.GRILLED:
                return grill(IID);
            case CookState.ROASTED:
                return roast(IID);
            case CookState.SMOKED:
                return smoke(IID);
            case CookState.STEWED:
                return stew(IID);
            case CookState.STEAMED:
                return steam(IID);
            case CookState.TOASTED:
                return toast(IID);
            case CookState.BOILED:
                return boil(IID);
            case CookState.NONE:
                return "THIS SHOULDN'T BE NONE.";
            default:
                throw new InvalidRequestException();
        }
    }

    /* ----------------------------------------------------------------------------------------------------------------- */
    /* *
     * Cut Methods
     * TODO Provide guard statements and exception handling (e.g if IID is not in a valid format)
     *      For valid IID Format, look at IID_ORDER
     */

    public String slice(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[CUT_INDEX] = "CUTSTATE:" + CutState.SLICED.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String chop(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[CUT_INDEX] = "CUTSTATE:" + CutState.CHOPPED.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String dice(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[COOK_INDEX] = "CUTSTATE:" + CutState.DICED.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String mince(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[CUT_INDEX] = "CUTSTATE:" + CutState.MINCED.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String shred(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[CUT_INDEX] = "CUTSTATE:" + CutState.SHREDDED.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String ground(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[CUT_INDEX] = "CUTSTATE:" + CutState.GROUND.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String julienne(String IID) {
        Guards.checkIID(IID);

        IIDTags =  IIDParser.parseIID(IID);
        IIDTags[CUT_INDEX] = "CUTSTATE:" + CutState.JULIENNED.getCutID();
        newIID = IIDParser.getIID(IIDTags);
        return newIID;
    }

    public String cut(String IID, IIDTag cutState) {
        switch (cutState) {
            case CutState.WHOLE:
                return "THIS SHOULDN'T BE WHOLE.";
            case CutState.SLICED:
                return slice(IID);
            case CutState.CHOPPED:
                return chop(IID);
            case CutState.DICED:
                return dice(IID);
            case CutState.MINCED:
                return mince(IID);
            case CutState.SHREDDED:
                return shred(IID);
            case CutState.GROUND:
                return ground(IID);
            case CutState.JULIENNED:
                return julienne(IID);
            case CutState.NONE:
                return "THIS SHOULDN'T BE NONE.";
            default:
                return "CUT ENUM NOT FOUND";
        }
    }
}
