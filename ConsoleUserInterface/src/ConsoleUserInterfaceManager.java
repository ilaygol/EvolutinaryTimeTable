
import DataTransferClasses.ProgressData;
import DataTransferClasses.StudyData;
import DataTransferClasses.DataPrinter;
import DataTransferClasses.SubjectData;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class ConsoleUserInterfaceManager {
    private LogicEngineManager m_LogicEngineManager;

    public ConsoleUserInterfaceManager()
    {
        m_LogicEngineManager=new LogicEngineManager();
    }

    public void StartProgram()
    {
        MainMenuHandler();
    }

    private void MainMenuHandler() {
        eMainMenu userChoice;
        do{
            userChoice= GetMainMenuUserInput();
            ActivateUserChoice(userChoice);
        }
        while (userChoice!=eMainMenu.EXIT);
    }

    private void ActivateUserChoice(eMainMenu i_UserChoice) {
        switch (i_UserChoice)
        {
            case LOAD_FILE:
                LoadFile();
                break;
            case PRINT_FILE_DATA:
                PrintFileData();
                break;
            case ACTIVATE_ALGO:
                ActivateAlgorithm();
                break;
            case PRINT_BEST_SOLUTION:
                PrintBestSolution();
                break;
            case PRINT_ALGO_PROCESS:
                PrintAlgorithmProcess();
                break;
            case EXIT:
                System.out.println("Bye Bye!");
                break;
        }
    }

    private void PrintAlgorithmProcess() {
        try {
                boolean isFirst=true;
                Integer previousFitness=0;
                Map<Integer, Integer> fitnessesMap = m_LogicEngineManager.PrintAlgorithmProcess();
                System.out.println("Generations Fitnesses");
                for(Map.Entry<Integer,Integer> entry:fitnessesMap.entrySet()) {
                    System.out.print("Generation number: " + entry.getKey() + " | Best fitness: " + entry.getValue());
                    if (isFirst) {
                        System.out.println(" | Difference from previous fitness: --");
                        isFirst = false;
                    } else {
                        Integer diff = entry.getValue() - previousFitness;
                        System.out.println(" | Difference from previous fitness: " + diff);
                    }
                    previousFitness = entry.getValue();
                }
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void PrintBestSolution() {
        try {
                if(m_LogicEngineManager.getIsFileLoaded())
                {
                    if(m_LogicEngineManager.getIsAlgoActivated())
                    {
                        ePrintOptions userInput=getPrintOptionUserInput();
                        userInput.printSolution(m_LogicEngineManager.getBestSolutionData(),m_LogicEngineManager.getAmountOfData());
                    }
                    else
                    {
                        System.out.println("ERROR: Please activate the algorithm first");
                    }
                }
                else
                {
                    System.out.println("ERROR: Please load a file, then activate the algorithm before choosing this option");
                }
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
    }

    private void ActivateAlgorithm() {
        if(m_LogicEngineManager.getIsFileLoaded()) {
            boolean isContinue=!m_LogicEngineManager.getIsAlgoActivated();
                    if(isContinue||getUserContinueAlgoInput()) {
                        Scanner algorithmInputScanner = new Scanner(System.in);
                        Integer generationsNum = 0, printEveryAmountOfGeneration = 0, reqFitness = 0;
                        boolean isCorrect = false;
                        System.out.print("Please enter the amount of generations you would like to create: ");
                        while (!isCorrect) {
                            try {
                                generationsNum = algorithmInputScanner.nextInt();
                                if (generationsNum < 100)
                                    System.out.println("Amount of generations can't be less than 100."
                                            + System.lineSeparator() + "Please enter a number above or equals to 100");
                                else
                                    isCorrect = true;
                            } catch (InputMismatchException e) {
                                System.out.println("ERROR: Amount of generations should be a number!" + System.lineSeparator()
                                        + "Please enter a Number:");
                                algorithmInputScanner.nextLine();
                            }
                        }
                        isCorrect = false;
                        System.out.println("Every how many generations would you like to print the fitness of the best solution?");
                        while (!isCorrect) {
                            try {
                                printEveryAmountOfGeneration = algorithmInputScanner.nextInt();
                                if (printEveryAmountOfGeneration > generationsNum || printEveryAmountOfGeneration<1)
                                    System.out.println("Error: Please enter a number between 1 and " + generationsNum);
                                else
                                    isCorrect = true;
                            } catch (InputMismatchException e) {
                                System.out.println("ERROR: Amount of generations should be a number!" + System.lineSeparator()
                                        + "Please enter a number:");
                                algorithmInputScanner.nextLine();
                            }
                        }
                        isCorrect = false;
                        System.out.print("Please enter the Desired fitness (range: 1-100): ");
                        while (!isCorrect) {
                            try {
                                reqFitness = algorithmInputScanner.nextInt();
                                if (reqFitness > 100 || reqFitness < 1)
                                    System.out.println("wrong Fitness,Please enter the Desired fitness (range: 1-100): ");
                                else
                                    isCorrect = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Fitness must be an Number between 1 and 100, Please enter The Desired Fitness");
                                algorithmInputScanner.nextLine();
                            }
                        }
                        try {
                            m_LogicEngineManager.ActivateAlgorithm(generationsNum, printEveryAmountOfGeneration, this::printProgress, reqFitness);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
        }
        else {
            System.out.println("ERROR: No file has been loaded, Please load a file before choosing this option");
        }
    }

    private void PrintFileData() {
        try {
            printDataInFormat(m_LogicEngineManager.PrintFileData());
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printDataInFormat(DataPrinter i_DataPrinter)
    {
        System.out.println("File Information"+System.lineSeparator()+"The subjects are:");
        i_DataPrinter.getSubjectsSet().forEach(Subj -> System.out.println("ID: "+ Subj.getSubjectID() + " | Name: " + Subj.getSubjectName()));
        System.out.println(System.lineSeparator()+"The teachers are:");

        for(Integer teacherID: i_DataPrinter.getTeacherID2SubjectsMap().keySet())
        {
            Set<SubjectData> teacherSubjects = i_DataPrinter.getTeacherID2SubjectsMap().get(teacherID);
            System.out.println("Teacher ID: "+teacherID+", The subjects he teaches are:");
            teacherSubjects.forEach(subj-> System.out.println("ID: "+subj.getSubjectID() + " | Name: " + subj.getSubjectName()));
        }

        System.out.println(System.lineSeparator()+"The classes are:");
        for(Integer classID: i_DataPrinter.getClasseID2SubjectsMap().keySet())
        {
            Set<StudyData> classSubjects = i_DataPrinter.getClasseID2SubjectsMap().get(classID);
            System.out.println("Class ID: "+classID+", The subjects taught in this class are:");
            classSubjects.forEach(subj-> System.out.println("Subject ID: "+subj.getSubjectID()+" | Subject name: "+subj.getSubjectName()
                    +" | Hours: "+ subj.getReqHours()));
        }
        System.out.println(System.lineSeparator()+"The rules are:");
        i_DataPrinter.getRulesNames2TypeMap().forEach((ruleName, ruleType)-> System.out.println("Name: "+ruleName+" | Type: "+ruleType.toString()));



        System.out.println(System.lineSeparator()+"Initial population: "+i_DataPrinter.getInitialPopulation());
        System.out.println("Selection type: "+i_DataPrinter.getSelectionData().getType()+" | Configuration: "+i_DataPrinter.getSelectionData().getConfiguration());
        System.out.println("Crossover name: "+i_DataPrinter.getCrossoverData().getName()+" | Cutting points: "+i_DataPrinter.getCrossoverData().getCuttingPoints());
        System.out.println("The mutations are:");
        i_DataPrinter.getMutationsDataList().forEach(mutation->
                System.out.println("Name: "+mutation.getName()+" | Probability: "+mutation.getProbability()+" | Configuration: "+mutation.getConfiguration()));

    }

    private void LoadFile() {
        String fileName;
        Scanner scanner=new Scanner(System.in);
        boolean isCorrect=false;
        while(!isCorrect) {
            System.out.println("Please enter the file's full path:");
            fileName = scanner.nextLine();
            try {
                m_LogicEngineManager.LoadFile(fileName);
                isCorrect =true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("The file has been loaded successfully!");

    }

    private eMainMenu GetMainMenuUserInput()
    {
        boolean isCorrect=false;
        eMainMenu userChoice=null;
        Scanner mainMenuInput=new Scanner(System.in);
        System.out.println(System.lineSeparator()+"Main Menu");
        eMainMenu.PrintMenu();
        while (!isCorrect)
        {
            try {
                System.out.println("Please enter your choice number:");
                userChoice=eMainMenu.values()[mainMenuInput.nextInt()-1];
                isCorrect=true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("ERROR! You should enter a number");
                mainMenuInput.nextLine();
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("ERROR! The number is out of range");
            }
        }
        return userChoice;
    }

    private ePrintOptions getPrintOptionUserInput()
    {
        boolean isCorrect=false;
        ePrintOptions userChoice=null;
        Scanner mainMenuInput=new Scanner(System.in);
        System.out.println(System.lineSeparator()+"Print Options");
        ePrintOptions.PrintMenu();
        while (!isCorrect)
        {
            try {
                System.out.println("Please enter your choice number:");
                userChoice=ePrintOptions.values()[mainMenuInput.nextInt()-1];
                isCorrect=true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("ERROR! You should enter a number");
                mainMenuInput.nextLine();
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("ERROR! The number is out of range");
            }
        }
        return userChoice;
    }

    private void printProgress(ProgressData i_ProgressData)
    {
        System.out.println("The best solution after "+i_ProgressData.getGeneration()+" generations is "+i_ProgressData.getFitness());
    }

    private boolean getUserContinueAlgoInput()
    {
        boolean isContinue=false;
        boolean isCorrect=false;
        String userChoice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("WARNING: The algorithm has been already activated,"+System.lineSeparator()+
                "The old data will be lost. Would you like to continue? Please enter Y/N");
        while (!isCorrect) {
            try {
                userChoice = scanner.nextLine();
                if(userChoice.toUpperCase().equals("Y"))
                {
                    isCorrect=true;
                    isContinue=true;
                }
                else if(userChoice.toUpperCase().equals("N"))
                {
                    isCorrect=true;
                }
                else
                {
                    System.out.println("ERROR: Wrong input! please enter Y/N");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Unknown exception");
            }
        }
        return isContinue;
    }
}
