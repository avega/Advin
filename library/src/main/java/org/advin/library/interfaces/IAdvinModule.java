package org.advin.library.interfaces;

public interface IAdvinModule
{
    void am_init(IAppInterface appIFace);
    void am_activate();
    void am_done();
    boolean am_isActive();
};
