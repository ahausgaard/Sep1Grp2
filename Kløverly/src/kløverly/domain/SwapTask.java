package kløverly.domain;

import java.io.Serial;

public class SwapTask extends Task
{
  private final Resident stakeholder;

  public SwapTask(String title, int value, String description,
      Resident stakeholder)
  {
    super("ST", title, value, description);
    this.stakeholder = stakeholder;
  }

  @Override public String toString()
  {
    return "SwapTask{} " + super.toString();
  }

  public Resident getStakeholder()
  {
    return stakeholder;
  }

}
