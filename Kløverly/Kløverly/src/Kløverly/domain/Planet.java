package Kløverly.domain;

public class Planet
{
  private String name;
  private int id;
  private String climateDescription;
  private double distanceFromStarAU;
  private boolean hasLife;
  private boolean hasAtmosphere;

  @Override public String toString()
  {
    return "Planet{" + "name='" + name + '\'' + ", id=" + id
        + ", climateDescription='" + climateDescription + '\''
        + ", distanceFromStarAU=" + distanceFromStarAU + ", hasLife=" + hasLife
        + ", hasAtmosphere=" + hasAtmosphere + '}';
  }

  public boolean isHasAtmosphere()
  {
    return hasAtmosphere;
  }

  public void setHasAtmosphere(boolean hasAtmosphere)
  {
    this.hasAtmosphere = hasAtmosphere;
  }

  public Planet(String name, String climateDescription,
      double distanceFromStarAU, boolean hasLife, boolean hasAtmosphere)
  {
    this.name = name;
    this.climateDescription = climateDescription;
    this.distanceFromStarAU = distanceFromStarAU;
    this.hasLife = hasLife;
    this.hasAtmosphere = hasAtmosphere;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getClimateDescription()
  {
    return climateDescription;
  }

  public void setClimateDescription(String climateDescription)
  {
    this.climateDescription = climateDescription;
  }

  public double getDistanceFromStarAU()
  {
    return distanceFromStarAU;
  }

  public void setDistanceFromStarAU(double distanceFromStarAU)
  {
    this.distanceFromStarAU = distanceFromStarAU;
  }

  public boolean isHasLife()
  {
    return hasLife;
  }

  public void setHasLife(boolean hasLife)
  {
    this.hasLife = hasLife;
  }


}
