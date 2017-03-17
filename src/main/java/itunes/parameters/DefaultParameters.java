package itunes.parameters;

import itunes.parameters.parameter.Country;
import itunes.parameters.parameter.Lang;
import itunes.parameters.parameter.Limit;
import itunes.parameters.parameter.Media;

/**
 * @author Sergii.Zagriichuk
 */
public interface DefaultParameters {
    public final static Country DEFAULT_COUNTRY = new Country("US");
    public final static Media DEFAULT_MEDIA = Media.ALL;
    public final static Lang DEFAULT_LANG = Lang.ENGLISH;
    public final static Limit DEFAULT_LIMIT = new Limit(50);
}
