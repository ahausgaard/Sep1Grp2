package kløverly.domain;

import java.io.Serial;

public class SwapTask extends Task
{
  private Resident stakeholder;
  @Serial
  private static final long serialVersionUID = 1L;

  public SwapTask(String title, String type, int value, String description)
  {
    super("ST",title, type, value, description);
  }

  @Override public String toString()
  {
    return "SwapTask{} " + super.toString();
  }

  public Resident getStakeholder()
  {
    return stakeholder;
  }

  public void setStakeholder(Resident stakeholder)
  {
    this.stakeholder = stakeholder;
  }
}
