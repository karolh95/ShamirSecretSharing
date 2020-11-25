package karolh95;

import karolh95.options.OptionsFactory;
import karolh95.strategy.BehaviorFactory;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args) {

        Options options = OptionsFactory.getOptions();
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        BehaviorFactory.getBehavior(cmd).run();
    }
}
