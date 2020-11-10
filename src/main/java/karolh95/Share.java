package karolh95;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor
@Getter
public class Share {

    private final BigInteger key;
    private final BigInteger shadow;
}
