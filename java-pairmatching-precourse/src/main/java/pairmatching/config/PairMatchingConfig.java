package pairmatching.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import pairmatching.domain.Course;
import pairmatching.repository.NameRepository;
import pairmatching.view.OutputView;

public class PairMatchingConfig {

    private static final String BACKEND_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_PATH = "src/main/resources/frontend-crew.md";

    private PairMatchingConfig() {
    }

    public static void configure() {
        try {
            NameRepository.clear();
            configureBackend();
            configureFrontend();
        } catch (IOException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void configureBackend() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(BACKEND_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            for (String name : line.split(" ")) {
                NameRepository.addNames(Course.BACKEND, name);
            }
        }
    }

    private static void configureFrontend() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FRONTEND_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            for (String name : line.split(" ")) {
                NameRepository.addNames(Course.FRONTEND, name);
            }
        }
    }
}
