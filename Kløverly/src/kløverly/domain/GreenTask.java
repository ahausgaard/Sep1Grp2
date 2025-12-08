package kløverly.domain;


public class GreenTask extends Task
{
  public GreenTask(String title, int value, String description)
  {
    super("GT", title, value, description);
  }

  @Override public String toString()
  {
    return "GreenTask{} " + super.toString();
  }
}
