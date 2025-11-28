package kløverly.presentation.core;

import kløverly.persistence.DataManager;

public class ControllerConfigurator
{
  private static DataManager dataManager;

  public static DataManager getDataManager()
  {
    return dataManager;
  }

  public static void setDataManager(DataManager dm)
  {
    dataManager = dm;
  }
}
