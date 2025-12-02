package kløverly.domain;

import java.io.Serial;

public class GreenTask extends Task
{
  @Serial
  private static final long serialVersionUID = 1L;

  public GreenTask(String title, String type, int value, String description)
  {
    super(title, type, value, description);
  }
}
