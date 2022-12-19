package output;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public final class Writer {

    private final String outputPath;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Writer(final String outputPath) {
        this.outputPath = outputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    /**
     * Functie care afiseaza datele
     * @param output
     * @throws IOException
     */
    public void writeData(final Output output) throws IOException {
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            objectWriter.writeValue(new File(outputPath), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
