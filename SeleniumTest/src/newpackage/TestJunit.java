package newpackage;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

public class TestJunit {
   @Test
   public void testSetup() {
      String str= "I am done with Junit setup";
      assertEquals("I am done with Junit setup",str);
   }
}
