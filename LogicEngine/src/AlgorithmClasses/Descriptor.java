
package AlgorithmClasses;

import DataClasses.TimeTable;
import ParsedClasses.ETTDescriptor;

public class Descriptor {

    private TimeTable m_TimeTable;
    private EvolutionEngine m_EvolutionEngine;

    public Descriptor(ETTDescriptor i_ETTDescriptor)
    {
        m_TimeTable=new TimeTable(i_ETTDescriptor.getETTTimeTable());
        m_EvolutionEngine=new EvolutionEngine(i_ETTDescriptor.getETTEvolutionEngine());
    }

    public TimeTable getTimeTable() {
        return m_TimeTable;
    }
}
