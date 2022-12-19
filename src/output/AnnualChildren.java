package output;

import java.util.ArrayList;
import java.util.List;

public class AnnualChildren {
    private List<ChildrenOutput> children;

    public AnnualChildren() {
        this.children = new ArrayList<>();
    }

    public final List<ChildrenOutput> getChildren() {
        return children;
    }

    public final void setChildren(final List<ChildrenOutput> children) {
        this.children = children;
    }
}
