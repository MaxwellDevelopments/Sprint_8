import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Epic("Tests class Account")
public class AccountTests {

    private static String getQuestionMark(int quantity) {
        return "?".repeat(Math.max(0, quantity));
    }

    public static Stream<Arguments> positiveTestsData() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        return Stream.of(
                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(1),
                        getQuestionMark(1)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(1),
                        getQuestionMark(2)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(2),
                        getQuestionMark(1)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(1),
                        getQuestionMark(17)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(17),
                        getQuestionMark(1)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(9),
                        getQuestionMark(8)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(8),
                        getQuestionMark(9)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(5),
                        getQuestionMark(5))))
        );
    }

    public static Stream<Arguments> negativeTestsData() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        return Stream.of(
                arguments(""),

                arguments(" "),

                arguments(fakeValuesService.letterify(
                        getQuestionMark(1))),

                arguments(fakeValuesService.letterify(
                        getQuestionMark(5))),

                arguments(fakeValuesService.letterify(String.format(
                        " %s %s",
                        getQuestionMark(2),
                        getQuestionMark(2)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s ",
                        getQuestionMark(2),
                        getQuestionMark(2)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s  %s",
                        getQuestionMark(2),
                        getQuestionMark(2)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(1),
                        getQuestionMark(18)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(1),
                        getQuestionMark(19)))),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(2),
                        getQuestionMark(19)))),

                arguments("Орк Прек"),

                arguments("1234 567"),

                arguments(fakeValuesService.letterify(String.format(
                        "%s %s",
                        getQuestionMark(15),
                        getQuestionMark(19)))),

                arguments("@%&* ^*@(")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveTestsData")
    @DisplayName("Positive tests for class account. Check full name")
    void positiveTests(String fullName) {
        checkNameToEmboss(fullName, true);
    }

    @ParameterizedTest
    @MethodSource("negativeTestsData")
    @DisplayName("Negative tests for class account. Check full name")
    void negativeTests(String fullName) {
        checkNameToEmboss(fullName, false);
    }

    @Step("Check full name")
    private void checkNameToEmboss(String fullName, boolean expected) {
        Account account = new Account(fullName);
        MatcherAssert.assertThat(account.checkNameToEmboss(), is(expected));
    }
}
