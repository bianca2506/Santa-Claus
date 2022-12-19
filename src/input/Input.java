package input;

import java.util.List;

public final class Input {
    private Integer numberOfYears;
    private Integer santaBudget;
    private InitialDataInput initialData;
    private List<AnnualChangesInput> annualChanges;
    private static Input instance = null;

    private Input() { }

    /**
     *
     * @return
     */
    public static Input getInput() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Integer getSantaBudget() {
        return santaBudget;
    }

    public InitialDataInput getInitialData() {
        return initialData;
    }

    public List<AnnualChangesInput> getAnnualChanges() {
        return annualChanges;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(final Integer santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setInitialData(final InitialDataInput initialData) {
        this.initialData = initialData;
    }

    public void setAnnualChanges(final List<AnnualChangesInput> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
