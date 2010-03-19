package org.advin.library.interfaces;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IAdvinDSModule extends IAdvinModule
{
    boolean open(String aSource);
    void commit();
    void close();
    ArrayList<DSStructElement> getStructure();
    void setStructure(ArrayList<DSStructElement> aStructure);
    Hashtable getElementAttributes(String aElementID);
    Object getElementData();
};
