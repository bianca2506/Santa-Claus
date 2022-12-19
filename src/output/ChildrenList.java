package output;

import myclasses.Child;

import java.util.List;

public final class ChildrenList {
    private static AnnualChildren childrenList;

    private ChildrenList() {
    }

    /**
     *
     * @param childList
     * @return
     */
    public static AnnualChildren createList(final List<Child> childList) {
        childrenList = new AnnualChildren();
        for (Child child : childList) {
            ChildrenOutput childrenOutput = new ChildrenOutput(child.getId(),
                    child.getLastName(), child.getFirstName(),
                    child.getCity(), child.getAge(), child.getGiftsPreferences(),
                    child.getAverageScore(), child.getNiceScoreHistory(),
                    child.getAssignedBudget(), child.getReceivedGifts());
            childrenList.getChildren().add(childrenOutput);
        }
        return childrenList;
    }
}
