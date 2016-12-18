package pl.edu.agh.config.conversion;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.joda.money.Money;
import org.joda.money.format.*;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;

/**
 * Created by hector on 04.12.16.
 */
public class String2MoneyConverter implements Converter<String, Money> {

    final static String DOLLAR_SYMBOL = "$";

    private final MoneyFormatter format = new MoneyFormatterBuilder()
            .append(DollarPrinter.INSTANCE, DollarParser.INSTANCE)
            .appendAmountLocalized()
            .toFormatter();

    /**
     * Parses String to org.joda.money.Money
     * It cannot parse other values than USD
     *
     * @param s - Money in String of the following format: $208
     * @return Money
     */
    @Override
    public Money convert(String s) {
        return format.parseMoney(s);
    }

    private enum DollarParser implements MoneyParser {
        INSTANCE;

        @Override
        public void parse(MoneyParseContext context) {

            final int endPos = context.getIndex() + 1;
            if (endPos > context.getTextLength()) {
                context.setError();
            } else {
                final String code = context.getTextSubstring(context.getIndex(), endPos);
                if (code.equals(DOLLAR_SYMBOL)) {
                    context.setCurrency(CurrencyUnit.USD);
                    context.setIndex(endPos);
                }
            }
        }

    }

    private enum DollarPrinter implements MoneyPrinter {
        INSTANCE;

        @Override
        public void print(MoneyPrintContext moneyPrintContext, Appendable appendable, BigMoney money) throws IOException {

            if (money.getCurrencyUnit().equals(CurrencyUnit.USD)) {
                appendable.append(DOLLAR_SYMBOL);
            } else {
                throw new IllegalCurrencyException("Cannot parse symbol other than of USD currency");
            }
        }

    }

}
