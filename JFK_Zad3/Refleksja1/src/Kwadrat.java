import jfk.ICallable;
import jfk.Description;

@Description(description = "Metoda podnosi do kwadratu")
public class Kwadrat implements ICallable {

    @Override
    public String Call(int a) {
        return String.valueOf(a*a);
    }

}
