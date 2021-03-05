package com.passnail.generator.tools.prop;


import com.passnail.common.ConfAttributes;

/**
 * A class holding the constants containing the information needed for the property handlers.
 */
public final class PropertiesConstants {

    private static ConfAttributes confAttributes = ConfAttributes.INSTANCE;

    private PropertiesConstants() {
    }

    /**
     * A path to default properties of the {@link com.passnail.generator.tools.pass.DefaultPasswordGenerator}.
     */
    public static final String DEFAULT_GENERATOR_PROPERTIES_PATH = confAttributes.getInstallationPath() + "/conf/generator.properties";

    /**
     * A key of property defining a length of passwords being generated.
     */
    public static final String PASSWORD_LENGTH_PROPERTY_KEY = "gen.passw.length.nr";

    /**
     * A key of property defining a number of lower case characters in passwords being generated.
     */
    public static final String NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY = "gen.lower.case.nr";

    /**
     * A key of property defining a number of upper case characters in passwords being generated.
     */
    public static final String NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY = "gen.upper.case.nr";

    /**
     * A key of property defining a number of digits in passwords being generated.
     */
    public static final String NUMBER_OF_DIGITS_PROPERTY_KEY = "gen.digits.nr";

    /**
     * A key of property defining a number of special characters in passwords being generated.
     */
    public static final String NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY = "gen.special.chars.nr";


    /**
     * A default value for default password length property.
     */
    public static final Integer PASSWORD_LENGTH_DEFAULT_VALUE = 9;

    /**
     * A default value for number of lower case characters property.
     */
    public static final Integer NUMBER_OF_LOWER_CASE_CHARACTERS_DEFAULT_VALUE = 5;

    /**
     * A default value for number of upper case characters property.
     */
    public static final Integer NUMBER_OF_UPPER_CASE_CHARACTERS_DEFAULT_VALUE = 1;

    /**
     * A default value for number of digits property.
     */
    public static final Integer NUMBER_OF_DIGITS_DEFAULT_VALUE = 2;

    /**
     * A default value for number of special characters property.
     */
    public static final Integer NUMBER_OF_SPECIAL_CHARACTERS_DEFAULT_VALUE = 1;


}
