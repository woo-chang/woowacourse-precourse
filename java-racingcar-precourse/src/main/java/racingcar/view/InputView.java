package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT = "시도할 회수는 몇회인가요?";

    public String readCarNames() {
        System.out.println(CAR_NAMES);
        return Console.readLine();
    }

    public String readAttempt() {
        System.out.println(ATTEMPT);
        return Console.readLine();
    }

}
