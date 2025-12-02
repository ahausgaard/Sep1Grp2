package kløverly.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Resident implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  private String name;
  private int personalPointAmount;
  private int activityLevel;
  private int id;

  public Resident(String name)
  {
    this.name = name;
    personalPointAmount = 0;
    activityLevel = 0;
  }

  public Resident()
  {

  }

  @Override public String toString()
  {
    return "Resident{" + "name='" + name + '\'' + ", personalPointAmount="
        + personalPointAmount + ", activityLevel=" + activityLevel + ", id="
        + id + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (!(o instanceof Resident resident))
      return false;
    return personalPointAmount == resident.personalPointAmount
        && activityLevel == resident.activityLevel && id == resident.id
        && Objects.equals(name, resident.name);
  }

  @Override public int hashCode()
  {
    return Objects.hash(name, personalPointAmount, activityLevel, id);
  }
}
