public enum eMainMenu {
    LOAD_FILE
            {
                @Override
                public String ToString()
                {
                    return "Load file";
                }
            },
    PRINT_FILE_DATA
            {
                @Override
                public String ToString()
                {
                    return "Print file data";
                }
            },
    ACTIVATE_ALGO
            {
                @Override
                public String ToString()
                {
                    return "Activate evolutinary algorithm";
                }
            },
    PRINT_BEST_SOLUTION
            {
                @Override
                public String ToString()
                {
                    return "Show best time table solution details";
                }
            },
    PRINT_ALGO_PROCESS
            {
                @Override
                public String ToString()
                {
                    return "Show algorithm process";
                }
            },
    EXIT
            {
                @Override
                public String ToString()
                {
                    return "QUIT";
                }
            };

    public abstract String ToString();
    public static void PrintMenu()
    {
        eMainMenu[] values = eMainMenu.values();
        for (int i=1; i<=values.length;i++) {
            System.out.println(i + ". " + values[i - 1].ToString());
        }
    }
}
