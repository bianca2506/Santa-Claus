package myclasses;

import input.Input;

import java.util.ArrayList;
import java.util.List;

public final class Assign {
    private Assign() {
    }

    /**
     * Functie care creeaza lista de copii
     * @param input
     * @return
     */
    public static List<Children> assignChildren(final Input input) {
        List<Children> childrenList = new ArrayList<>();
                input.getInitialData().getChildren().forEach(childrenInput -> {
                    Children children = new Children(childrenInput.getId(),
                            childrenInput.getLastName(), childrenInput.getFirstName(),
                            childrenInput.getAge(), childrenInput.getCity(),
                            childrenInput.getNiceScore(), childrenInput.getGiftsPreferences(),
                            childrenInput.getNiceScoreBonus(), childrenInput.getElf());
                    childrenList.add(children);
                });
        return childrenList;
    }

    /**
     * Functie care creeaza lista de copii pentru output
     * @param childrenList
     * @return
     */
    public static List<Child> assignChildrenOutput(final List<Children> childrenList) {
        List<Child> childList = new ArrayList<>();
        for (Children children : childrenList) {
            Child child = new Child(children.getId(),
                    children.getLastName(), children.getFirstName(), children.getAge(),
                    children.getCity(), children.getNiceScore(),
                    children.getGiftsPreferences(), children.getNiceScoreBonus(),
                    children.getElf());
            childList.add(child);
        }
        return childList;
    }
}
