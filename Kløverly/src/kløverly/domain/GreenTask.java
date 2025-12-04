package kløverly.domain;

import java.io.Serial;

public class GreenTask extends Task
{
  @Serial
  private static final long serialVersionUID = 1L;

  public GreenTask(String title, String type, int value, String description)
  {
    super("GT", title, type, value, description);
  }

  @Override public String toString()
  {
    return "GreenTask{} " + super.toString();
  }
}
