package kløverly.domain;

import java.io.Serial;

public class SwapTask extends Task
{
  @Serial
  private static final long serialVersionUID = 1L;

  public SwapTask(String title, String type, int value, String description)
  {
    super(title, type, value, description);
  }
}
