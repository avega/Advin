package org.advin.library.interfaces;

public interface IAdvinModule
{
    void initModule(IAppInterface appIFace);
    void startModule();
    void doneModule();
    boolean isActive();
};
