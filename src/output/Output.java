package output;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private List<AnnualChildren> annualChildren;

    public Output() {
        this.annualChildren = new ArrayList<>();
    }

    public final List<AnnualChildren> getAnnualChildren() {
        return annualChildren;
    }

    public final void setAnnualChildren(final List<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
