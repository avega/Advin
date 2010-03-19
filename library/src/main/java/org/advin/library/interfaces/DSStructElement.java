package org.advin.library.interfaces;

import java.util.ArrayList;

public class DSStructElement implements IDSStructElement
{
    String elementID = "";
    String elementCaption = "";
    ArrayList<DSStructElement> Childs = null;

    @Override
    public String getID() { return elementID; };

    @Override
    public void setID(String aID) { elementID = aID; };
    
    @Override
    public String getCaption() { return elementCaption; };

    @Override
    public void setCaption(String aCaption) { elementCaption = aCaption; };

    @Override
    public boolean hasChilds()
    {
        boolean vResult = true;
        if (Childs == null || Childs.isEmpty()) vResult = false;
        return vResult;
    };

    @Override
    public ArrayList<DSStructElement> getChilds() { return Childs; };

    @Override
    public void setChilds(ArrayList<DSStructElement> aChilds) { Childs = aChilds; };
};
