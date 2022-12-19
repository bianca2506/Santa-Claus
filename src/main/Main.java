package main;

import checker.Checker;
import common.Constants;
import input.Input;
import input.InputLoader;
import myclasses.Assign;
import myclasses.Child;
import myclasses.Children;
import myclasses.PlayRounds;
import output.AnnualChildren;
import output.Output;
import output.Writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        int i = 1;
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        while (i <= Constants.TESTS_NUMBER) {
            String inputFile = Constants.TESTS_PATH + "test" + i + ".json";
            String outputFile = Constants.OUTPUT_PATH + i + ".json";
            InputLoader inputLoader = new InputLoader(inputFile);
            Input input = inputLoader.readData();

            Output output = new Output();
            Writer writer = new Writer(outputFile);

            // creez lista de copii
            List<Children> childrenListInput = Assign.assignChildren(input);

            // creez lista de copii pentru output
            List<Child> childrenList = Assign.assignChildrenOutput(childrenListInput);

            AnnualChildren annualChildren1 = PlayRounds.playFirstRound(childrenList, input);
            AnnualChildren copy = Children.deepCopyChildList(annualChildren1);
            output.getAnnualChildren().add(copy);

            for (Child child : childrenList) {
                child.getReceivedGifts().clear();
            }

            for (int j = 1; j <= input.getNumberOfYears(); j++) {
                AnnualChildren annualChildren2 = PlayRounds.playRounds(input,
                        childrenList, j);
                AnnualChildren copy2 = Children.deepCopyChildList(annualChildren2);
                output.getAnnualChildren().add(copy2);
                for (Child child : childrenList) {
                    child.getReceivedGifts().clear();
                }
            }

            writer.writeData(output);
            i++;
        }
        Checker.calculateScore();
    }
}
