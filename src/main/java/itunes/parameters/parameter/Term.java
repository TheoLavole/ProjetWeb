package itunes.parameters.parameter;

import java.util.List;

import itunes.utils.UrlUtils;

/**
 * @author szagriichuk
 */
public class Term implements Parameter {
    private final static String predicate = "term=";
    private List<String> queryValues;

    public Term(List<String> queryValues) {
        this.queryValues = queryValues;
    }

    private String makeQueryString(List<String> queryValues) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (String queryValue : queryValues) {
            stringBuilder.append(queryValue).append(" ");
        }
        return stringBuilder.toString();
    }

    public String createSearchParameter() {
        String query = makeQueryString(queryValues);
        query = UrlUtils.encodeUrl(query);
        return predicate + query;
    }
}
