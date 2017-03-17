package itunes.parameters.parameter;

/**
 * @author szagriichuk
 */
public class Country implements Parameter {
    private final String country;

    public Country(String country) {
        this.country = country;
    }

    public String createSearchParameter() {
        return "country=" + country;
    }
}
