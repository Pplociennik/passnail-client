package com.passnail.server.core.tools.prop;

import com.passnail.server.core.app.config.ConfAttributes;

/**
 * A class holding the constants containing the information needed for the property handlers.
 */
public final class PropertiesConstants {

    private static ConfAttributes confAttributes = ConfAttributes.INSTANCE;

    private PropertiesConstants() {
    }

    public static final String DEFAULT_GENERATOR_PROPERTIES_PATH = confAttributes.getInstallationPath() + "/conf/generator.properties";


    public static final String PASSWORD_LENGTH_PROPERTY_KEY = "gen.passw.length.nr";
    public static final String NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY = "gen.lower.case.nr";
    public static final String NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY = "gen.upper.case.nr";
    public static final String NUMBER_OF_DIGITS_PROPERTY_KEY = "gen.digits.nr";
    public static final String NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY = "gen.special.chars.nr";

    public static final Integer PASSWORD_LENGTH_DEFAULT_VALUE = 9;
    public static final Integer NUMBER_OF_LOWER_CASE_CHARACTERS_DEFAULT_VALUE = 5;
    public static final Integer NUMBER_OF_UPPER_CASE_CHARACTERS_DEFAULT_VALUE = 1;
    public static final Integer NUMBER_OF_DIGITS_DEFAULT_VALUE = 2;
    public static final Integer NUMBER_OF_SPECIAL_CHARACTERS_DEFAULT_VALUE = 1;


}
