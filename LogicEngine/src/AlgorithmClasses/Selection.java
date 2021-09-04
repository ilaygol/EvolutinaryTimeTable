
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataTransferClasses.SelectionData;
import ParsedClasses.ETTSelection;

import java.util.*;

public class Selection {

    private String m_Type;
    private String m_Configuration;
    private Integer m_Percent;
    private Double m_PTE;
    private Integer m_Elitism;
    private eSelection m_eType;
    private Random m_Roller;

    public Selection(String i_Type, String i_Percent, String i_PTE, String i_Elitism) {
        this.setType(i_Type);
        this.setElitism(i_Elitism);
        switch(i_Type.toUpperCase()) {
            case "TRUNCATION":
                this.setPercent(i_Percent);
                this.m_PTE=(double)0;
                break;
            case "ROULETTEWHEEL":
                this.m_Percent=0;
                this.m_PTE=(double)0;
                break;
            case "TOURNAMENT":
                this.m_Percent=0;
                this.setPTE(i_PTE);
                break;
        }
        m_eType=eSelection.valueOf(i_Type.toUpperCase());
        m_Roller=new Random();
    }

    public Selection(ETTSelection i_ETTSelection) {
        m_Type = i_ETTSelection.getType();
        m_Configuration = i_ETTSelection.getConfiguration();
        extractConfiguration();
        if (i_ETTSelection.getETTElitism() != null) {
            m_Elitism = i_ETTSelection.getETTElitism();
        } else {
            m_Elitism = 0;
        }

        m_eType = eSelection.valueOf(m_Type.toUpperCase());
        m_Roller = new Random();
    }

    public Selection(SelectionData i_SelectionData) {
        m_Type = i_SelectionData.getType();
        m_Configuration = "";
        m_Percent = i_SelectionData.getPercent();
        m_PTE = i_SelectionData.getPTE();
        m_Elitism = i_SelectionData.getElitism();
        m_eType = eSelection.valueOf(m_Type.toUpperCase());
        m_Roller = new Random();
    }


    public String getType() {
        return m_Type;
    }

    public String getConfiguration() {
        return m_Configuration;
    }

    public SelectionData getSelectionData() {
        return new SelectionData(this);
    }

    public Integer getPercent() {
        return m_Percent;
    }

    public Double getPTE() {
        return m_PTE;
    }

    public Integer getElitism() {
        return m_Elitism;
    }

    public void setElitism(Integer i_Elitism) {
        m_Elitism = i_Elitism;
    }

    public void setType(String i_Type) {
        if (i_Type.toUpperCase().equals("TRUNCATION") || i_Type.toUpperCase().equals("ROULETTEWHEEL") || i_Type.toUpperCase().equals("TOURNAMENT"))
            this.m_Type = i_Type;
        else
            throw new RuntimeException("Error: Invalid Selection type!");
    }

    public void setElitism(String i_Elitism) {
        try {
            Integer elitism=Integer.parseInt(i_Elitism);
            if(elitism>=0)
                m_Elitism=elitism;
            else
                throw new RuntimeException("Error: Elitism Must be Positive");
        } catch(Exception e)
        {
            throw new RuntimeException("Error: Elitism must be a number.");
        }
    }

    public void setPercent(String i_Percent) {
        try {
            Integer percent =Integer.parseInt(i_Percent);
            if(percent >=1)
                m_Percent=percent;
            else
                throw new RuntimeException("Error: Percent Must be Positive.");
        } catch(Exception e)
        {
            throw new RuntimeException("Error: Percent must be a number.");
        }

    }

    public void setPTE(String i_PTE) {
        try {
            Double PTE=Double.parseDouble(i_PTE);
            if(PTE>=0 && PTE<=1)
                m_PTE=PTE;
            else
                throw new RuntimeException("Error: PTE must be between 0-1.");
        } catch(Exception e)
        {
            throw new RuntimeException("Error: PTE must be a number between 0-1");
        }

    }

    public Generation createNextGeneration(Generation i_PrevGeneration, Crossover i_Crossover, Integer i_InitialPopulation, AmountOfObjectsCalc i_AmountOfObj) {
        Generation nextGeneration = new Generation();
        for (int i = 0; i < m_Elitism; i++)
            nextGeneration.addParentToGeneration(i_PrevGeneration.getParentByIndex(i));
        m_eType.activate(m_Percent, m_PTE, m_Roller, i_PrevGeneration, nextGeneration, i_InitialPopulation, i_AmountOfObj, i_Crossover);
        return nextGeneration;
    }


    public void extractConfiguration() {
        if (m_Configuration != null) {
            Scanner in = new Scanner(m_Configuration.toString()).useDelimiter("[^0-9]+");
            if (m_Type.toUpperCase().equals("TOURNAMENT")) {
                m_PTE = in.nextDouble();
                ;
                m_Percent = 0;
            } else if (m_Type.toUpperCase().equals("TRUNCATION")) {
                m_Percent = in.nextInt();
                m_PTE = (double) 0;
            }
            in.close();
        } else {
            m_PTE = (double) 0;
            m_Percent = 0;
        }
    }
}
