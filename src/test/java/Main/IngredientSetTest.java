package Main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientSetTest {

    @Test
    public void generalConstructorTests() {}

    @Test
    public void invalidConstructorTests() {}
}

/*
    should hold multiple IIDContainers in a "locked" state
        shouldn't allow incomplete IIDContainers, nor allow contained IIDContainers to be modified
    should be able to provide a list of contained IIDContainers
    should have a description, provided by user in constructor to define what the relation is between IIDContainers
    should have a name defining the relationship (e.g. gluten-free grains)
    should provide add method to add an IIDContainer
    should provide remove method to remove an IIDContainer
        these methods should not allow duplicate additions, or invalid removals
        can target IDDContainer for removal by specifying name + descriptor tag (or providing an IIDContainer that matches)
    should compare & list differences between two sets (see legacy code for example)
 */