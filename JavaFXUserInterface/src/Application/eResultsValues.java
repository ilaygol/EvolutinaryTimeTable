package Application;

public enum eResultsValues {
    FILEDATA
            {
                @Override
                public String toString() {
                    return "Show File Data";
                }
            },
    BESTSOLUTIONBYROW
            {
                @Override
                public String toString() {
                    return "Best Solution-By Raw";
                }
            },
    BESTSOLUTIONBYTEACHER
            {
                @Override
                public String toString() {
                    return "Best Solution-By Teacher";
                }
            },
    BESTSOLUTIONBYCLASS
            {
                @Override
                public String toString() {
                    return "Best Solution-By Class";
                }
            },
    EVOLUTION
            {
                @Override
                public String toString() {
                    return "Algorithm Evolution";
                }
            };
    public abstract String toString();
}
