package kløverly.domain;

import java.io.Serializable;
import java.time.LocalDate;


public class GreenGoal implements Serializable
{
  private final String title;
  private final String prize;
  private int currentPoints;
  private int targetPoints;
  private final LocalDate creationDate;
  private LocalDate deadlineDate;

  public GreenGoal(String title, int targetPoints, LocalDate deadlineDate, String prize)
  {
    this.title = title;
    this.targetPoints = targetPoints;
    this.currentPoints = 0;
    this.creationDate = LocalDate.now();
    this.deadlineDate = deadlineDate;
    this.prize = prize;
  }

  public void addPoints(int points)
  {
    this.currentPoints += points;
  }

  @Override public String toString()
  {
    return "\nGreenGoal{" + "title='" + title + '\'' + ", currentPoints="
        + currentPoints + ", targetPoints=" + targetPoints + ", creationDate="
        + creationDate + ", deadlineDate=" + deadlineDate + '}';
  }

    public String getPrize() {
        return prize;
    }

    public String getTitle()
  {
    return title;
  }

  public int getCurrentPoints()
  {
    return currentPoints;
  }

  public void setCurrentPoints(int currentPoints)
  {
    this.currentPoints = currentPoints;
  }

  public int getTargetPoints()
  {
    return targetPoints;
  }

  public LocalDate getCreationDate()
  {
    return creationDate;
  }


  public LocalDate getDeadlineDate()
  {
    return deadlineDate;
  }

}
