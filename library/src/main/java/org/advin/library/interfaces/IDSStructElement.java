package org.advin.library.interfaces;

import java.util.ArrayList;

public interface IDSStructElement
{
    String getID();
    void setID(String aID);
    String getCaption();
    void setCaption(String aCaption);
    boolean hasChilds();
    ArrayList<DSStructElement> getChilds();
    void setChilds(ArrayList<DSStructElement> aChilds);
};
