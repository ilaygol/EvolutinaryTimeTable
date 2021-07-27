
import DataTransferClasses.DataPrinter;

import java.util.InputMismatchException;
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
       DataPrinter data= m_LogicEngineManager.PrintFileData();
       data.Print();


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
