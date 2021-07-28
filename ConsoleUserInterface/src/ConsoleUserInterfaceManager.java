
import DataTransferClasses.DataPrinter;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

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
        System.out.println(m_LogicEngineManager.ActivateAlgorithm());
    }

    private void PrintFileData() {
       printDataInFormat(m_LogicEngineManager.PrintFileData());
    }

    private void printDataInFormat(DataPrinter i_DataPrinter)
    {
        System.out.println("File Information"+System.lineSeparator()+"The subjects are:");
        i_DataPrinter.getID2SubjectMap().forEach((subjCode, subjName) -> System.out.println("ID: "+subjCode + " Name: " + subjName));
        System.out.println(System.lineSeparator()+"The teachers are:");
        for(Integer teacherID: i_DataPrinter.getTeachersID2SubjMap().keySet())
        {
            Map<Integer, String> teacherSubjects = i_DataPrinter.getTeachersID2SubjMap().get(teacherID);
            System.out.println("Teacher ID: "+teacherID+" The subjects he teaches are:");
            teacherSubjects.forEach((subjCode,subjName)-> System.out.println("ID: "+subjCode + " Name: " + subjName));
        }
        System.out.println(System.lineSeparator()+"The classes are:");
        for(Integer classID: i_DataPrinter.getClassesID2SubjMap().keySet())
        {
            Map<Integer, String> classSubjects = i_DataPrinter.getClassesID2SubjMap().get(classID);
            Map<Integer, Integer> classRequirements = i_DataPrinter.getClassID2ReqHoursMap().get(classID);
            System.out.println("Class ID: "+classID+" The subjects taught in this class are:");
            for(Integer subjID:classSubjects.keySet())
            {
                System.out.println("Subject ID: "+subjID+" Subject name: "+classSubjects.get(subjID)
                        +" Hours: "+ classRequirements.get(subjID));
            }
        }
        System.out.println("The rules are:");
        i_DataPrinter.getRulesNames2TypeMap().forEach((ruleName, ruleType)-> System.out.println("Name: "+ruleName+" Type: "+ruleType.toString()));
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
        System.out.println("Main Menu");
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
