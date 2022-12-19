package input;

import enums.CityStrategyEnum;

import java.util.List;

public class AnnualChangesInput {
    private Integer newSantaBudget;
    private List<SantaGiftsListInput> newGifts;
    private List<ChildrenInput> newChildren;
    private List<ChildUpdateInput> childrenUpdates;
    private CityStrategyEnum strategy;

    public final CityStrategyEnum getStrategy() {
        return strategy;
    }

    public final void setStrategy(final CityStrategyEnum strategy) {
        this.strategy = strategy;
    }

    public final Integer getNewSantaBudget() {
        return newSantaBudget;
    }

    public final void setNewSantaBudget(final Integer newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public final List<SantaGiftsListInput> getNewGifts() {
        return newGifts;
    }

    public final void setNewGifts(final List<SantaGiftsListInput> newGifts) {
        this.newGifts = newGifts;
    }

    public final List<ChildrenInput> getNewChildren() {
        return newChildren;
    }

    public final void setNewChildren(final List<ChildrenInput> newChildren) {
        this.newChildren = newChildren;
    }

    public final List<ChildUpdateInput> getChildrenUpdates() {
        return childrenUpdates;
    }

    public final void setChildrenUpdates(final List<ChildUpdateInput> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
