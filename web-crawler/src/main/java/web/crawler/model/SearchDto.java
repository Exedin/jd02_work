package web.crawler.model;

import java.util.List;

public class SearchDto {
    private String seed;
    private List <String>terms;

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public String getSeed() {
        return seed;
    }

    public List<String> getTerms() {
        return terms;
    }
}
