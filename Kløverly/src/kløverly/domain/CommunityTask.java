package kløverly.domain;

public class CommunityTask extends Task
{
  public CommunityTask(String title, int value, String description)
  {
    super("CT", title, value, description);
  }

  @Override public String toString()
  {
    return "Community Task{} " + super.toString();
  }
}
