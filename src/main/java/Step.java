import Exceptions.InvalidIIDException;
import Exceptions.InvalidRequestException;
import Interfaces.IIDTag;
import Tags.CookState;
import Tags.CutState;

import java.util.ArrayList;
import java.util.Arrays;

public class Step extends CookAndCutAndShit {


    private String description, name;
    private ArrayList<String> tagsAsString;
    private IIDTag IIDTagOut;
    private IIDTag[] tags;

    public Step(String description, String name) {
        this(description, name, null);
    }

    public Step(String description, String name, IIDTag IIDTagOut) {
        this(description, name, null, IIDTagOut);
    }

    public Step(String description, String name, IIDTag[] tags, IIDTag IIDTagOut) {
        this.description = description;
        this.name = name;
        this.tags = Arrays.copyOf(tags, tags.length);
        this.IIDTagOut = IIDTagOut;
        tagsAsString = new ArrayList<>();
        for (IIDTag tag : tags) {
            tagsAsString.add(tag.toString());
        }
    }

    /*
     * IIDComponentsIn cannot be empty
     * The component that is going in must be an existing component in the ingredient set
     */

    /*
     *  Pass in IID String
     *  must return IID
     *  The name of the ingredient coming in must be in ingredientSet

     *  If it is in ingredientSet, must change the cook and/or cut enum(s)
     *  in the IID of the ingredient coming in and return a String IID with changed enums
     *  that match the requested changes (i.e. IIDComponentsOut)
     *
     Use checkIIDComponentsIn() method to check the length of IIDCo
     *  Use the cook() and cut() methods as needed
     */
    public String doThis(String IID) {
        checkTagsIn(tags);
        hasAppropriateTags(IID);
        if (Arrays.asList(CookState.values()).contains(IIDTagOut)) {
            return cook(IID, IIDTagOut);
        } else if (Arrays.asList(CutState.values()).contains(IIDTagOut)) {
            return cut(IID, IIDTagOut);
        } else {
            throw new InvalidIIDException();
        }
    }

    // confirm we have the correct compponents of IID
    // if the IID contains all of criticalTags, method return true
    // if the IID DOES NOT contain all of criticalTags, method throws new exception
    // some items need to be cooked twice (e.g. pan-searing a steak and then baking it in an oven)
    private void hasAppropriateTags(String IID) {
        Guards.checkIID(IID);
        ArrayList<IIDTag> IIDTags = new ArrayList<>();
        IIDTags.add(IIDParser.getCookStateEnum(IID));
        IIDTags.add(IIDParser.getCutStateEnum(IID));
        if (!IIDTags.containsAll(Arrays.asList(tags))) {
            throw new InvalidRequestException();
        }

        // if IIDTagOut is raw and the IID is not raw, throw exception
        if (IIDParser.getCookStateEnum(IID) != CookState.RAW && IIDTagOut == CookState.RAW) {
            throw new InvalidRequestException("WHY ARE YOU TRYING TO TURN A COOKED INGREDIENT BACK TO BEING RAW");
        }

        // if IIDTagOut != raw and IID cook state != raw, throw exception
        // trying to cook a cooked item
        // if ((IIDParser.getCookStateEnum(IID) != CookState.RAW) && (IIDTagOut != CookState.RAW)) {
        //     throw new InvalidRequestException("ITEM IS ALREADY COOKED");
        // }

        // if ((IIDParser.getCutStateEnum(IID)) != CutState.WHOLE) {
        //     throw new InvalidRequestException("ITEM IS ALREADY CUT");
        // }
    }

    // Check to see if the IIDTagsIn:
    //  - are actual enums
    //  - is this necessary?
    private void checkTagsIn(IIDTag[] IIDTagsIn) {
        for (IIDTag tagIn : IIDTagsIn) {
            if (!Arrays.asList(CookState.values()).contains(tagIn) && !Arrays.asList(CutState.values()).contains(tagIn)) {
                throw new InvalidRequestException();
            }
        }
    }
}
