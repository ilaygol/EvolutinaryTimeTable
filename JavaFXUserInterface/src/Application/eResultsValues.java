package Application;

public enum eResultsValues {
    FILEDATA
            {
                @Override
                public String toString() {
                    return "Show file data";
                }
            },
    BESTSOLUTIONBYROW
            {
                @Override
                public String toString() {
                    return "Best solution- By raw";
                }
            },
    BESTSOLUTIONBYTEACHER
            {
                @Override
                public String toString() {
                    return "Best solution- By teacher";
                }
            },
    BESTSOLUTIONBYCLASS
            {
                @Override
                public String toString() {
                    return "Best solution- By class";
                }
            },
    EVOLUTION
            {
                @Override
                public String toString() {
                    return "Algorithm evolution";
                }
            };
    public abstract String toString();
}
