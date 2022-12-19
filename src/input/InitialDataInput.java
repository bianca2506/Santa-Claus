package input;

import java.util.List;

public class InitialDataInput {
    private List<ChildrenInput> children;
    private List<SantaGiftsListInput> santaGiftsList;

    public final List<ChildrenInput> getChildren() {
        return children;
    }

    public final void setChildren(final List<ChildrenInput> children) {
        this.children = children;
    }

    public final List<SantaGiftsListInput> getSantaGiftsList() {
        return santaGiftsList;
    }

    public final void setSantaGiftsList(final List<SantaGiftsListInput> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
