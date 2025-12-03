package Kløverly.presentation.core;

import Kløverly.persistence.DataManager;

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
