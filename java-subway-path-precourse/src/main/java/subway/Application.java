package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.util.SubwayConfig;

public class Application {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        SubwayConfig.configure();
        SubwayController subwayController = new SubwayController(scanner);
        subwayController.process();
    }
}
