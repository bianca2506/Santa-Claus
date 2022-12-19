package input;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class InputLoader {
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public final String getInputPath() {
        return inputPath;
    }

    /**
     * Functie care citeste input-ul
     * @return
     * @throws IOException
     */
    public final Input readData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(inputPath), Input.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
