package kløverly.util;
import java.util.UUID;

public class IdGenerator
{
  public static String generate(String prefix)
  {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    return prefix.toUpperCase() + uuid.substring(0,6).toLowerCase();
  }
}
