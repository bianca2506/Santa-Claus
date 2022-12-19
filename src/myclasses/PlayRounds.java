package myclasses;

import common.Constants;
import enums.Category;
import enums.Cities;
import enums.CityStrategyEnum;
import enums.ElvesType;
import input.ChildUpdateInput;
import input.ChildrenInput;
import input.Input;
import input.SantaGiftsListInput;
import output.ChildrenList;
import output.AnnualChildren;
import output.SantaGiftsListOutput;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;


public final class PlayRounds {
    private PlayRounds() {
    }

    /**
     * Functie pentru prima runda
     * @param childrenList
     * @param input
     * @return
     */
    public static AnnualChildren playFirstRound(final List<Child> childrenList, final Input input) {
        Double sum = 0.0, budgetUnit;
        AnnualChildren annualChildren;

        // daca copilul are peste 18 ani il scot din lista
        removeAdult(childrenList);

        for (Child children : childrenList) {
            // daca copilul este baby
            if (children.getAge() < Constants.AGE_5) {
                children.setAverageScore(Constants.BABY_AVERAGE_SCORE);

                // adaug in lista nice score-ul din input
                children.getNiceScoreHistory().add(children.getNiceScore());

                // calculez o suma in care adaug averageScore-ul
                sum += children.getAverageScore();

                // daca copilul este kid sau teen
            } else if (children.getAge() >= Constants.AGE_5 && children.getAge()
                    <= Constants.AGE_18) {
                // calculez averageScore-ul copiilor
                double bonus = children.getNiceScoreBonus();
                double niceScore = children.getNiceScore();
                niceScore += niceScore * bonus / Constants.NUMBER_100;
                if (niceScore > Constants.NUMBER_10) {
                    children.setAverageScore(Constants.NUMBER_10);
                } else {
                    children.setAverageScore(niceScore);
                }

                // adaug in lista nice score-ul din input
                children.getNiceScoreHistory().add(children.getNiceScore());

                // calculez o suma in care adaug averageScore-ul
                sum += children.getAverageScore();
            }
        }
        // calculez budgetUnit
        budgetUnit = input.getSantaBudget() / sum;

        for (Child child : childrenList) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
        }

        elfPinkBlack(input, childrenList);
        giveGifts(input, childrenList);
        elfYellow(input, childrenList);

        // sortez lista in functie de id
        childrenList.sort(Comparator.comparing(Child::getId));
        annualChildren = ChildrenList.createList(childrenList);
        return annualChildren;
    }

    /**
     * Functie pentru rundele urmatoare
     * @param input
     * @param childrenList
//     * @param childrenList2
     * @return
     */
    public static AnnualChildren playRounds(final Input input, final List<Child> childrenList,
                                             final int i) {
        AnnualChildren annualChildren;

        double sum = 0.0, budgetUnit;
        // incrementez varsta copiilor
        growChildren(childrenList);
        // daca au peste 18 ani ii scot din lista
        removeAdult(childrenList);
        update(input, i, childrenList);

        for (Child children : childrenList) {
            // daca copilul este baby
            if (children.getAge() < Constants.AGE_5) {
                // calculez averageScore-ul dupa formula
                children.setAverageScore(Constants.BABY_AVERAGE_SCORE);

                // calculez o suma in care adaug averageScore-ul
                sum += children.getAverageScore();

            // daca copilul este kid
            } else if (children.getAge() >= Constants.AGE_5 && children.getAge()
                    < Constants.AGE_12) {
                double sumKid = 0.0;
                double bonus = children.getNiceScoreBonus();
                double averageScore;

                // daca lista niceScoreHistory are doar un element
                if (children.getNiceScoreHistory().size() == 1) {
                    averageScore = children.getNiceScore();
                } else {
                    // daca are mai multe elemente, parcurg lista si calculez suma lor
                    for (int k = 0; k < children.getNiceScoreHistory().size(); k++) {
                        sumKid += children.getNiceScoreHistory().get(k);
                    }
                    averageScore = sumKid / children.getNiceScoreHistory().size();
                }

                averageScore += averageScore * bonus / Constants.NUMBER_100;
                if (averageScore > Constants.NUMBER_10) {
                    children.setAverageScore(Constants.NUMBER_10);
                } else {
                    children.setAverageScore(averageScore);
                }
                // calculez o suma in care adaug averageScore-ul
                sum += children.getAverageScore();

            // daca copilul este teen
            } else if (children.getAge() >= Constants.AGE_12 && children.getAge()
                    <= Constants.AGE_18) {
                double sumTeen = 0.0, sumI = 0.0;
                double bonus = children.getNiceScoreBonus();
                double averageScore;

                // daca lista niceScoreHistory are doar un element
                if (children.getNiceScoreHistory().size() == 1) {
                    averageScore = children.getNiceScore();
                } else {
                    // daca are mai multe elemente, parcurg lista si calculez suma lor
                    for (int k = 0; k < children.getNiceScoreHistory().size(); k++) {
                        sumTeen += children.getNiceScoreHistory().get(k) * (k + 1);
                        sumI += (k + 1);
                    }
                    averageScore = sumTeen / sumI;
                }
                averageScore += averageScore * bonus / Constants.NUMBER_100;

                if (averageScore > Constants.NUMBER_10) {
                    children.setAverageScore(Constants.NUMBER_10);
                } else {
                    children.setAverageScore(averageScore);
                }

                // calculez o suma in care adaug averageScore-ul
                sum += children.getAverageScore();
            }
        }
        // calculez budgetUnit
        budgetUnit = input.getSantaBudget() / sum;

        for (Child child : childrenList) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
        }

        elfPinkBlack(input, childrenList);
        strategy(input, childrenList, i);
        giveGifts(input, childrenList);
        elfYellow(input, childrenList);

        // sortez lista in functie de id
        childrenList.sort(Comparator.comparingInt(Children::getId));
        annualChildren = ChildrenList.createList(childrenList);

        return annualChildren;
    }

    /**
     * Functie care sorteaza lista in functie de strategie
     * @param input
     * @param childList
     * @param i
     */
    public static void strategy(final Input input, final List<Child> childList, final int i) {
        if (input.getAnnualChanges().get(i - 1).getStrategy().equals(CityStrategyEnum.ID)) {
            childList.sort(Comparator.comparing(Child::getId));
        } else if (input.getAnnualChanges().get(i - 1).getStrategy().equals(
                CityStrategyEnum.NICE_SCORE)) {
            childList.sort(Comparator.comparing(Child::getAverageScore).reversed());
        } else if (input.getAnnualChanges().get(i - 1).getStrategy().equals(
                CityStrategyEnum.NICE_SCORE_CITY)) {
            niceScoreCityStrategy(childList);
        }
    }

    /**
     * Functie care sorteaza lista in functie de niceScoreCity
     * @param childList
     */
    private static void niceScoreCityStrategy(final List<Child> childList) {
        Map<Cities, Double> cities = new HashMap<>();
        for (Child child : childList) {
            // daca map-ul cities nu contine orasul copilului atunci are
            // scorul de cumintenie 0.0
            if (!cities.containsKey(child.getCity())) {
                cities.put(child.getCity(), 0.0);
            }
        }
        for (Cities city : cities.keySet()) {
            double sum = 0.0;
            int nr = 0;
            for (Child child : childList) {
                if (child.getCity().equals(city)) {
                    sum += child.getAverageScore();
                    nr++;
                }
            }
            double value = sum / nr;
            // adaug in map-ul cities orasul si valoarea acestuia
            cities.put(city, value);
        }
        // creez o lista pentru orase si una pentru scorurile acestora
        List<Cities> cityList = new ArrayList<>();
        List<Double> cityScores = new ArrayList<>();

        for (Cities city : cities.keySet()) {
            cityList.add(city);
            // adaug in lista de cityScores valoarea de la orasul city
            cityScores.add(cities.get(city));
        }
        // sortez orasele dupa scoruri
        for (int j = 0; j < cityList.size() - 1; j++) {
            for (int k = j + 1; k < cityList.size(); k++) {
                if (cityScores.get(k) > cityScores.get(j)) {
                    Collections.swap(cityList, j, k);
                    Collections.swap(cityScores, j, k);
                }
            }
        }
        // creez o lista pentru copiii sortati
        List<Child> sortedChildren = new ArrayList<>();
        // sortez copiii in functie de id
        childList.sort(Comparator.comparingInt(Child::getId));

        // adaug in lista sortedChildren copiii sortati
        for (Cities value : cityList) {
            for (Child child : childList) {
                if (value == child.getCity()) {
                    sortedChildren.add(child);
                }
            }
        }
        childList.clear();
        childList.addAll(sortedChildren);
    }

    /**
     * Functie care calculeaza bugetul pentru elfii pink si black
     * @param input
     * @param childList
     */
    private static void elfPinkBlack(final Input input, final List<Child> childList) {
        for (Child child : childList) {
            for (int i = 0; i < input.getInitialData().getChildren().size(); i++) {
                if (input.getInitialData().getChildren().get(i).getElf().
                        equals(ElvesType.BLACK) && Objects.equals(child.getId(),
                        input.getInitialData().getChildren().get(i).getId())) {
                    double budget = child.getAssignedBudget();
                    budget -= budget * Constants.NUMBER_30 / Constants.NUMBER_100;
                    child.setAssignedBudget(budget);
                    break;
                } else if (input.getInitialData().getChildren().get(i).getElf().
                        equals(ElvesType.PINK) && Objects.equals(child.getId(),
                        input.getInitialData().getChildren().get(i).getId())) {
                    double budget = child.getAssignedBudget();
                    budget += budget * Constants.NUMBER_30 / Constants.NUMBER_100;
                    child.setAssignedBudget(budget);
                    break;
                }
            }
        }
    }

    /**
     * Functie pentru elful yellow
     * @param input
     * @param childList
     */
    private static void elfYellow(final Input input, final List<Child> childList) {
        for (Child child : childList) {
            for (int i = 0; i < input.getInitialData().getChildren().size(); i++) {
                if (input.getInitialData().getChildren().get(i).getElf().
                        equals(ElvesType.YELLOW) && Objects.equals(child.getId(),
                        input.getInitialData().getChildren().get(i).getId())) {
                    // daca lista de cadouri primite este goala
                    if (child.getReceivedGifts().isEmpty()) {
                        // luam prima categorie preferata
                        Category category = child.getGiftsPreferences().get(0);
                        List<SantaGiftsListInput> santaGiftsListInput = input.getInitialData().
                                getSantaGiftsList();
                        // sortam lista in functie de pret
                        santaGiftsListInput.sort(Comparator.comparingDouble(
                                SantaGiftsListInput::getPrice));

                        for (SantaGiftsListInput gift : santaGiftsListInput) {
                            int quantity = gift.getQuantity();
                            if (gift.getCategory().equals(category) && gift.getQuantity() > 0) {
                                // creez variabila pentru cadouri pentru output
                                SantaGiftsListOutput santaGiftsListOutput =
                                        new SantaGiftsListOutput(gift.getProductName(),
                                                gift.getPrice(), gift.getCategory());
                                // adaug in lista de ReceivedGifts cadoul
                                child.getReceivedGifts().add(santaGiftsListOutput);
                                // scad cantitatea
                                quantity--;
                                gift.setQuantity(quantity);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Functie care adauga in lista copiilor cadourile
     * @param input
     * @param childrenList
     */
    public static void giveGifts(final Input input, final List<Child> childrenList) {
        for (Child children : childrenList) {

            // sortez lista de cadouri din input in functie de pret
            input.getInitialData().getSantaGiftsList().sort(
                    Comparator.comparing(SantaGiftsListInput::getPrice));
            List<SantaGiftsListInput> santaGiftsListInput = input.getInitialData().
                    getSantaGiftsList();

            double budget = children.getAssignedBudget();
            // parcurg lista de preferinte
            for (int k = 0; k < children.getGiftsPreferences().size(); k++) {
                // parcurg lista de cadouri din input
                for (SantaGiftsListInput gift : santaGiftsListInput) {
                    int quantity = gift.getQuantity();

                    // adaug field-urile din input intr-o variabila de output
                    SantaGiftsListOutput santaGiftsListOutput = new SantaGiftsListOutput(
                            gift.getProductName(), gift.getPrice(), gift.getCategory());

                    // daca categoria cadoului este egala cu categoria preferinte a copilului,
                    // se incadreaza in buget si nu exista in lista de categorii
                    if (children.getGiftsPreferences().get(k).equals(santaGiftsListOutput.
                            getCategory()) && santaGiftsListOutput.getPrice() <= budget
                            && quantity > 0) {
                        // adaug in lista de ReceivedGifts cadoul
                        children.getReceivedGifts().add(santaGiftsListOutput);
                        // scad cantitatea
                        quantity--;
                        gift.setQuantity(quantity);
                        budget -= santaGiftsListOutput.getPrice();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Functie pentru schimbarile anuale
     * @param input
     * @param i
     * @param childrenList
     */
    public static void update(final Input input, final int i, final List<Child> childrenList) {
        // setez bugetul cu noul buget
        input.setSantaBudget(input.getAnnualChanges().get(i - 1).getNewSantaBudget());
        // adaug toti copii noi
        input.getInitialData().getChildren().addAll(input.getAnnualChanges().get(i - 1).
                getNewChildren());
        // parcurg lista de copii noi
        for (ChildrenInput child : input.getAnnualChanges().get(i - 1).getNewChildren()) {
            // formez copilul in functie de cel din input
            Child child1 = new Child(child.getId(), child.getLastName(), child.getFirstName(),
                    child.getAge(), child.getCity(), child.getNiceScore(),
                    child.getGiftsPreferences(), child.getNiceScoreBonus(), child.getElf());
            child1.getNiceScoreHistory().add(child1.getNiceScore());
            childrenList.add(child1);
        }
        for (ChildrenInput children : input.getInitialData().getChildren()) {
            for (ChildUpdateInput childUpdateInput : input.getAnnualChanges().get(i - 1).
                    getChildrenUpdates()) {
                if (childUpdateInput.getId().equals(children.getId())) {
                    if (childUpdateInput.getNiceScore() != null) {
                        // setez noul nice score al copilului
                        children.setNiceScore(childUpdateInput.getNiceScore());
                    }

                    // daca noua lista de referinte nu este goala
                    if (!childUpdateInput.getGiftsPreferences().isEmpty()) {
                        // creez o lista pentru categorii
                        List<Category> updateCategory = new ArrayList<>();
                        // adaug toate preferintele copiilor
                        updateCategory.addAll(childUpdateInput.getGiftsPreferences());
                        updateCategory.addAll(children.getGiftsPreferences());

                        for (int k = 0; k < updateCategory.size() - 1; k++) {
                            for (int j = k + 1; j < updateCategory.size(); j++) {
                                // daca exista deja categoria o sterg
                                if (updateCategory.get(k).equals(updateCategory.get(j))) {
                                    updateCategory.remove(j);
                                    j--;
                                }
                            }
                        }
                        children.setGiftsPreferences(updateCategory);
                    }
                    children.setElf(childUpdateInput.getElf());
                    break;
                }
            }
        }
        // adaug noile cadouri
        input.getInitialData().getSantaGiftsList().addAll(input.getAnnualChanges().
                get(i - 1).getNewGifts());
        PlayRounds.removeAdult(childrenList);

        for (Child children : childrenList) {
            for (ChildUpdateInput childUpdateInput : input.getAnnualChanges().get(i - 1).
                    getChildrenUpdates()) {
                if (childUpdateInput.getId().equals(children.getId())) {
                    if (childUpdateInput.getNiceScore() != null) {
                        // adaug in niceScoreHistory niceScore-ul de la runda respectiva
                        children.getNiceScoreHistory().add(childUpdateInput.getNiceScore());
                    }

                    // daca noua lista de referinte nu este goala
                    if (!childUpdateInput.getGiftsPreferences().isEmpty()) {
                        // creez o lista pentru categorii
                        List<Category> updateCategory = new ArrayList<>();
                        // adaug toate preferintele copiilor
                        updateCategory.addAll(childUpdateInput.getGiftsPreferences());
                        updateCategory.addAll(children.getGiftsPreferences());

                        for (int k = 0; k < updateCategory.size() - 1; k++) {
                            for (int j = k + 1; j < updateCategory.size(); j++) {
                                // daca exista deja categoria o sterg
                                if (updateCategory.get(k).equals(updateCategory.get(j))) {
                                    updateCategory.remove(j);
                                    j--;
                                }
                            }
                        }
                        children.setGiftsPreferences(updateCategory);
                    }
                    children.setElf(childUpdateInput.getElf());
                    break;
                }
            }
        }
    }

    /**
     * Functie care scoate adultul din lista de copii
     * @param childrenList
     */
    public static void removeAdult(final List<Child> childrenList) {
        // daca copilul are peste 18 ani il scot din lista
        for (int i = 0; i < childrenList.size(); i++) {
            if (childrenList.get(i).getAge() > Constants.AGE_18) {
                childrenList.remove(childrenList.get(i));
            }
        }
    }

    /**
     * Functie care creste varsta copiilor cu un an
     * @param childrenList
     */
    public static void growChildren(final List<Child> childrenList) {
        for (Child children : childrenList) {
            int age = children.getAge();
            age++;
            children.setAge(age);
        }
    }
}
