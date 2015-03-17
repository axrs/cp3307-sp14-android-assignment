package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

/**
 * Basic High Scores Entry Class
 */
public class HighScore extends DatabaseModel {
    private static String lastUsedName;
    private String name = "Undefined";
    private long score = 0;

    /**
     * Constructs an empty HighScore
     */
    public HighScore() {
    }

    /**
     * Constructs a HighScore with provided name and score
     *
     * @param name  Record holders name
     * @param score Record Score
     */
    public HighScore(String name, long score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets the holders name
     *
     * @return Holders name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the holders name
     *
     * @param name Holders name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the score
     *
     * @return Score
     */
    public long getScore() {
        return score;
    }

    /**
     * Sets the score
     *
     * @param score Score
     */
    public void setScore(long score) {
        this.score = score;
    }

    /**
     * Gets the last manually entered name for a record
     *
     * @return Last user entered name
     */
    public static String getLastUsedName() {
        return lastUsedName;
    }

    /**
     * Sets the last manually entered name
     *
     * @param lastUsedName User provided name
     */
    public static void setLastUsedName(String lastUsedName) {
        HighScore.lastUsedName = lastUsedName;
    }
}
