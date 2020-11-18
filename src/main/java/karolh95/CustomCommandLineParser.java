package karolh95;

import karolh95.options.CustomOptions;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CustomCommandLineParser {

    public CommandLine parse(String[] args) throws ParseException {

        Options options = new CustomOptions();
        CommandLineParser parser = new BasicParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("ShamirsSecretSharing-1.0", options);
            throw new ParseException(e.getMessage());
        }

        return cmd;
    }
}
