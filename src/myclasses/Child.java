package myclasses;

import enums.Category;
import enums.Cities;
import enums.ElvesType;
import output.SantaGiftsListOutput;

import java.util.ArrayList;
import java.util.List;

public class Child extends Children {
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<SantaGiftsListOutput> receivedGifts;

    public Child(final Integer id, final String lastName, final String firstName,
                 final Integer age, final Cities city, final Double niceScore,
                 final List<Category> giftsPreferences, final Integer niceScoreBonus,
                 final ElvesType elf) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences,
                niceScoreBonus, elf);
        this.averageScore = 0.0;
        this.receivedGifts = new ArrayList<>();
        this.niceScoreHistory = new ArrayList<>();
    }

    public final Double getAverageScore() {
        return averageScore;
    }

    public final void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public final List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public final Double getAssignedBudget() {
        return assignedBudget;
    }

    public final void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public final List<SantaGiftsListOutput> getReceivedGifts() {
        return receivedGifts;
    }

    public final void setReceivedGifts(final List<SantaGiftsListOutput> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
