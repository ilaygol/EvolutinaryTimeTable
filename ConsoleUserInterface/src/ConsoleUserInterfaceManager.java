
import DataTransferClasses.StudyData;
import DataTransferClasses.DataPrinter;
import DataTransferClasses.SubjectData;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

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
        System.out.println(m_LogicEngineManager.PrintAlgorithmProcess());
    }

    private void PrintBestSolution() {
        System.out.println(m_LogicEngineManager.PrintBestSolution());
    }

    private void ActivateAlgorithm() {
        m_LogicEngineManager.ActivateAlgorithm();
    }

    private void PrintFileData() {
       printDataInFormat(m_LogicEngineManager.PrintFileData());
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
        boolean isCorrect=false;
        Scanner scanner=new Scanner(System.in);
        while(!isCorrect) {
            System.out.println("Please enter the file's full path:");
            fileName = scanner.nextLine();
            try {
                m_LogicEngineManager.LoadFile(fileName);
                isCorrect=true;
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
}
