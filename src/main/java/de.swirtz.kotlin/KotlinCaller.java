import de.swirtz.kotlin.LambdaFunKt;
import de.swirtz.kotlin.generics.ClassWithTypeParam;

/**
 * Created by simonw on 05.04.17.
 */
public class KotlinCaller {

    public static void main(String[] args) {
        ClassWithTypeParam<Byte> kotlinInstance = new ClassWithTypeParam<Byte>((byte) 10);
        kotlinInstance.getType();

        //        LambdaFunKt.applyToList()
        

    }
}
