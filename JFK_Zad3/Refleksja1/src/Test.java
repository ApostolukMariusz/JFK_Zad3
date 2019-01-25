import jfk.ICallable;
import jfk.Description;

@Description(description = "Metoda Testowa")
public class Test implements ICallable {

    @Override
    public String Call(int a) {
        return String.valueOf(a*3);
    }
}
