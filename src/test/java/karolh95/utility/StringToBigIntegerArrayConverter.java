package karolh95.utility;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.math.BigInteger;
import java.util.Arrays;

public class StringToBigIntegerArrayConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        String s = (String) source;
        return Arrays.stream(s.split("\\s*,\\s*")).map(BigInteger::new).toArray(BigInteger[]::new);
    }
}
