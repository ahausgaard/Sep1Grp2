package kløverly.domain;

import java.io.Serializable;
import java.time.LocalDate;


public class CommunityGoal implements Serializable
{
  private String title;
  private String prize;
  private int currentPoints;
  private int targetPoints;
  private LocalDate creationDate;
  private LocalDate deadlineDate;

  public CommunityGoal(String title, int targetPoints, LocalDate deadlineDate, String prize)
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

  public boolean isGoalReached()
  {
    return currentPoints >= targetPoints;
  }

  public double getProgress()
  {
    if (targetPoints == 0) return 0;
    return (double) currentPoints / targetPoints;
  }

  @Override public String toString()
  {
    return "\nCommunityGoal{" + "title='" + title + '\'' + ", currentPoints="
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

  public void setTitle(String title)
  {
    this.title = title;
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

  public void setTargetPoints(int targetPoints)
  {
    this.targetPoints = targetPoints;
  }

  public LocalDate getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate)
  {
    this.creationDate = creationDate;
  }

  public LocalDate getDeadlineDate()
  {
    return deadlineDate;
  }

  public void setDeadlineDate(LocalDate deadlineDate)
  {
    this.deadlineDate = deadlineDate;
  }
}
