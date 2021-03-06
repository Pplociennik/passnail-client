package com.passnail.data.security.crypto;


import static com.passnail.data.security.util.DataSecurityConstants.CRYPTO_KEY_GRANULATION_NUMBER;
import static com.passnail.data.security.util.DataSecurityConstants.CRYPTO_SALT_MULTIPLIER;

/**
 * Created by: Pszemko at piątek, 05.03.2021 23:32
 * Project: passnail-client
 */
public class CryptoUtility {

    public static String prepareKey(String aKey) {
        StringBuilder builder = new StringBuilder(aKey);
        String reversedKey = builder.reverse().toString();

        builder = new StringBuilder(reversedKey);

        for (int i = 0; i < CRYPTO_KEY_GRANULATION_NUMBER; i++) {
            builder.append(
                    i % 2 == 0 ?
                            aKey :
                            reversedKey
            );
        }

        return builder.toString();
    }

    public static String prepareSalt(String aSalt) {
        StringBuilder builder = new StringBuilder(aSalt);
        String reversedSalt = builder.reverse().toString();

        for (int i = 0; i < aSalt.length() * CRYPTO_SALT_MULTIPLIER; i++) {
            builder.append(reversedSalt);
        }

        return builder.toString();
    }
}
