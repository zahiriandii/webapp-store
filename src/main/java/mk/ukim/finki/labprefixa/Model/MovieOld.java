package mk.ukim.finki.labprefixa.Model;

import lombok.Data;

@Data
public class MovieOld
{
    String title;
    String summary;
    private Production production;
    double rating;
    public MovieOld() {
    }

    public MovieOld(String title, String summary, double rating, Production production) {
        this.production = production;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }

}
