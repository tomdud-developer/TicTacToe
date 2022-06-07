package tictactoe.players;

public enum DifficultyLevel {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    UNKNOWN("");

    private final String name;

    private DifficultyLevel(String name) {
        this.name = name;
    }

    public static DifficultyLevel getByString(String str) {
        for(DifficultyLevel level: DifficultyLevel.values()) {
            if(level.name.equals(str)) return level;
        }

        return UNKNOWN;
    }
}
