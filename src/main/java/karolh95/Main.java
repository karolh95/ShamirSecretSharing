package karolh95;

import karolh95.strategy.BehaviorFactory;

public class Main {

    public static void main(String[] args) {

        BehaviorFactory.getBehavior(args).run();
    }
}
