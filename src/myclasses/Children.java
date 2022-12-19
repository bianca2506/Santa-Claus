package myclasses;

import enums.Category;
import enums.Cities;
import enums.ElvesType;
import output.AnnualChildren;
import output.ChildrenOutput;
import output.SantaGiftsListOutput;

import java.util.ArrayList;
import java.util.List;


public class Children {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private Cities city;
    private Double niceScore;
    private List<Category> giftsPreferences;
    private Integer niceScoreBonus;
    private ElvesType elf;

    public Children(final Integer id, final String lastName, final String firstName,
                    final Integer age, final Cities city, final Double niceScore,
                    final List<Category> giftsPreferences, final Integer niceScoreBonus,
                    final ElvesType elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    public final Integer getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public final void setNiceScoreBonus(final Integer niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public final ElvesType getElf() {
        return elf;
    }

    public final void setElf(final ElvesType elf) {
        this.elf = elf;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(final Integer age) {
        this.age = age;
    }

    public final Cities getCity() {
        return city;
    }

    public final void setCity(final Cities city) {
        this.city = city;
    }

    public final Double getNiceScore() {
        return niceScore;
    }

    public final void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public final List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * Functie pentru crearea unei copii pentru AnnualChildren
     * @param annualChildren
     * @return
     */
    public static AnnualChildren deepCopyChildList(final AnnualChildren annualChildren) {
        AnnualChildren copy = new AnnualChildren();
        for (ChildrenOutput child : annualChildren.getChildren()) {
            List<Double> copyNiceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
            List<SantaGiftsListOutput> copyGiftsList = deepCopyGiftsListOutput(child.
                    getReceivedGifts());
            ChildrenOutput child1 = new ChildrenOutput(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    copyNiceScoreHistory, child.getAssignedBudget(), copyGiftsList);
            copy.getChildren().add(child1);
        }
        return copy;
    }

    /**
     * Functie pentru crearea unei copii pentru lista de cadouri de output
     * @param listToCopy
     * @return
     */
    public static List<SantaGiftsListOutput> deepCopyGiftsListOutput(
            final List<SantaGiftsListOutput> listToCopy) {
        List<SantaGiftsListOutput> copy = new ArrayList<>();
        for (SantaGiftsListOutput santaGiftsListOutput : listToCopy) {
            SantaGiftsListOutput santaGiftsListOutput1 = new SantaGiftsListOutput(
                    santaGiftsListOutput.getProductName(),
                    santaGiftsListOutput.getPrice(), santaGiftsListOutput.getCategory());
            copy.add(santaGiftsListOutput1);
        }
        return copy;
    }
}
