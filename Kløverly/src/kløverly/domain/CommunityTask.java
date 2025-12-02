package kløverly.domain;

import java.io.Serial;

public class CommunityTask extends Task
{
  @Serial
  private static final long serialVersionUID = 1L;

  public CommunityTask(String title, String type, int value, String description)
  {
    super(title, type, value, description);
  }

  @Override public String toString()
  {
    return "CommunityTask{} " + super.toString();
  }
}
