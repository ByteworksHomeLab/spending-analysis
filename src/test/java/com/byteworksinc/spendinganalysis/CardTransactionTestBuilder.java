package com.byteworksinc.spendinganalysis;

import com.byteworksinc.spendinganalysis.entities.CreditCardTransaction;
import com.byteworksinc.spendinganalysis.models.ChargeCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CardTransactionTestBuilder {

    private static final Logger log = LoggerFactory.getLogger(CardTransactionTestBuilder.class);

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static CreditCardTransaction build(Date date, ChargeCard chargeCard, String description, BigDecimal amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        UUID uuid = UUID.randomUUID();
        return new CreditCardTransaction(uuid.toString(), date, year, month, chargeCard, description, amount, null, null);
    }

    public static CreditCardTransaction build() {
        CreditCardTransaction listing = null;
        try {
            listing = build(formatter.parse("2024-01-13"), ChargeCard.APPLE, "Price Chopper", BigDecimal.valueOf(48.73).setScale(2, RoundingMode.HALF_UP));
        } catch (ParseException e) {
            log.warn("Could not parse transaction date", e);
        }
        return listing;
    }
}
