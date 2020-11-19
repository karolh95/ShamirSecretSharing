package karolh95.options;

import karolh95.CustomCommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

class CustomCommandLineParserTest {

    private static List<Arguments> parseTest() {

        return List.of(
                toArguments(new ReconstructionOption()),
                toArguments(new InputFileOption()),
                toArguments(new OutputFileOption()),
                toArguments(new PrimeOption()),
                toArguments(new SecretOption()),
                toArguments(new ShadowsOption()),
                toArguments(new ThresholdOption())
        );
    }

    private static String[] toParamsWithoutArgs(Option option) {

        return toArray("--" + option.getLongOpt(), "--" + OutputFileOption.longOpt, "output_file");
    }

    private static Arguments toArguments(Option option) {

        return Arguments.of(toArray("--" + option.getLongOpt(), "value", "--" + OutputFileOption.longOpt, "output_file"), toArray(option.getLongOpt()));
    }

    private static String[] toArray(String... string) {
        return string;
    }

    private static List<Arguments> invalidArgumentsParseTest() {

        return List.of(
                Arguments.of((Object) toParamsWithoutArgs(new InputFileOption())),
                Arguments.of((Object) toParamsWithoutArgs(new PrimeOption())),
                Arguments.of((Object) toParamsWithoutArgs(new SecretOption())),
                Arguments.of((Object) toParamsWithoutArgs(new ShadowsOption())),
                Arguments.of((Object) toParamsWithoutArgs(new ThresholdOption()))
        );
    }

    @ParameterizedTest
    @MethodSource("parseTest")
    public void parseTest(String[] args, String[] optionsNames) throws ParseException {

        CustomCommandLineParser parser = new CustomCommandLineParser();
        CommandLine cmd = parser.parse(args);

        Assertions.assertNotNull(cmd, "CustomCommandLineParser::parse should not return null");

        for (String option : optionsNames) {
            boolean hasOption = cmd.hasOption(option);
            Assertions.assertTrue(hasOption, "Option " + option + " not found in " + Arrays.toString(args));
        }
    }

    @ParameterizedTest
    @MethodSource("invalidArgumentsParseTest")
    public void invalidArgumentsParseTest(String[] args) {

        CustomCommandLineParser parser = new CustomCommandLineParser();

        CommandLine cmd = parser.parse(args);

        Assertions.assertNull(cmd, "CustomCommandLineParser::parse should return null");
    }
}