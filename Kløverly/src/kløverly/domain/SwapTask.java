package kløverly.domain;

import java.io.Serial;

public class SwapTask extends Task
{
  private Resident stakeholder;
  @Serial
  private static final long serialVersionUID = 1L;

    // Opdateret constructor der tager imod en Resident

    public SwapTask(String title, int value, String description, Resident stakeholder) {
        // Vi beholder "ST" prefixet som du har i din nuværende kode
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

  public void setStakeholder(Resident stakeholder)
  {
    this.stakeholder = stakeholder;
  }
}
