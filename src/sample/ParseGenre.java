package sample;

public class ParseGenre {

    private static final String id28 = "action";
    private static final String id12 = "adventure";
    private static final String id16 = "animated";
    private static final String id35 = "comedy";
    private static final String id80 = "crime";
    private static final String id99 = "documentary";
    private static final String id18 = "drama";
    private static final String id10751 = "family";
    private static final String id14 = "fantasy";
    private static final String id36 = "history";
    private static final String id27 = "horror";
    private static final String id10402 = "musical";
    private static final String id9648 = "mystery";
    private static final String id10749 = "romance";
    private static final String id878 = "sci-fi";
    private static final String id53 = "thriller";
    private static final String id10752 = "war";
    private static final String id37 = "western";

    public static String genreName(String genreID) {

        StringBuilder included = new StringBuilder();
        StringBuilder excluded = new StringBuilder();
        StringBuilder includedExcluded = new StringBuilder();
        String includedPolished;

        excluded.append("\" data-excluded=\"");

        if(genreID.contains("28") || genreID.contains("10759")) {
            included.append(id28 + ", ");
        } else {
            excluded.append(id28 + " , ");
        }
        if (genreID.contains("12") || genreID.contains("10759")) {
            included.append(id12 + ", ");
        } else {
            excluded.append(id12 + " , ");
        }
        if (genreID.contains("16")) {
            included.append(id16 + ", ");
        } else {
            excluded.append(id16 + " , ");
        }
        if (genreID.contains("35")) {
            included.append(id35 + ", ");
        } else {
            excluded.append(id35 + " , ");
        }
        if (genreID.contains("80")) {
            included.append(id80 + ", ");
        } else {
            excluded.append(id80 + " , ");
        }
        if (genreID.contains("99")) {
            included.append(id99 + ", ");
        } else {
            excluded.append(id99 + " , ");
        }
        if (genreID.contains("18")) {
            included.append(id18 + ", ");
        } else {
            excluded.append(id18 + " , ");
        }
        if (genreID.contains("10751")) {
            included.append(id10751 + ", ");
        } else {
            excluded.append(id10751 + " , ");
        }
        if (genreID.contains("14") || genreID.contains("10765")) {
            included.append(id14 + ", ");
        } else {
            excluded.append(id14 + " , ");
        }
        if (genreID.contains("36")) {
            included.append(id36 + ", ");
        } else {
            excluded.append(id36 + " , ");
        }
        if (genreID.contains("27")) {
            included.append(id27 + ", ");
        } else {
            excluded.append(id27 + " , ");
        }
        if (genreID.contains("10402")) {
            included.append(id10402 + ", ");
        } else {
            excluded.append(id10402 + " , ");
        }
        if (genreID.contains("9648")) {
            included.append(id9648 + ", ");
        } else {
            excluded.append(id9648 + " , ");
        }
        if (genreID.contains("10749")) {
            included.append(id10749 + ", ");
        } else {
            excluded.append(id10749 + " , ");
        }
        if (genreID.contains("878") || genreID.contains("10765")) {
            included.append(id878 + ", ");
        } else {
            excluded.append(id878 + " , ");
        }
        if (genreID.contains("53")) {
            included.append(id53 + ", ");
        } else {
            excluded.append(id53 + " , ");
        }
        if (genreID.contains("10752") || genreID.contains("10768")) {
            included.append(id10752 + ", ");
        } else {
            excluded.append(id10752 + " , ");
        }
        if (genreID.contains("37")) {
            included.append(id37 + ", ");
        } else {
            excluded.append(id37 + " , ");
        }

        try {
            includedPolished = included.toString().substring(0,included.length() - 2);
        } catch (StringIndexOutOfBoundsException e) {
            includedPolished = null;
        }

        includedExcluded.append(includedPolished).append(excluded.toString().substring(0,excluded.length() - 2));

        return includedExcluded.toString();
    }

}
