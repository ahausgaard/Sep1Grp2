package kløverly.domain;

import java.io.Serializable;
import kløverly.util.IdGenerator;

public class Resident implements Serializable
{

  private String name;
  private int personalPointAmount;
  private int activityLevel;
  private String id;
  private int communityPointAmount = 0; // tilføjet

  public Resident(String name)
  {
    this.name = name;
    personalPointAmount = 50;
    activityLevel = 0;
    this.id = IdGenerator.generate("R");
  }

  // tilføjet
  public void setCommunityPointAmount(int communityPointAmount) {
        this.communityPointAmount = communityPointAmount;
    }
    // tilføjet
  public int getCommunityPointAmount() {
        return communityPointAmount;
    }

  public Resident()
  {

  }

  public void setPersonalPointAmount(int personalPointAmount)
  {
    this.personalPointAmount = personalPointAmount;
  }

  public void setActivityLevel(int activityLevel)
  {
    this.activityLevel = activityLevel;
  }

  public String getName()
  {
    return name;
  }

  public int getPersonalPointAmount()
  {
    return personalPointAmount;
  }

  public int getActivityLevel()
  {
    return activityLevel;
  }

  public String getId()
  {
    return id;
  }

  @Override public String toString()
  {
    return "Resident{" + "name='" + name + '\'' + ", personalPointAmount="
        + personalPointAmount + ", activityLevel=" + activityLevel + ", id='"
        + id + '\'' + '}';
  }

}
