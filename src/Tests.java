import java.util.ArrayList;

public class Tests {

    private void testComplex() {
        Complex c = new Complex(1,1);
        System.out.println(c.mod());
        assert c.real() == 1;
        assert c.imag() == 1;
        assert c.conj().imag() == -1;
        Complex t = new Complex(1,2);
        Complex sum = c.plus(t);
        Complex diff = c.minus(t);
        Complex prod = c.times(t);
        Complex div = c.div(t);
        System.out.println("Sum = " + sum.toString());
        System.out.println("Diff = " + diff.toString());
        System.out.println("Prod = " + prod.toString());
        System.out.println("Div = " + div.toString());
        System.out.println("Exp = " + c.exp().toString());
        System.out.println("Arg = " + c.arg());
        System.out.println("Log = " + sum.log().toString());
        System.out.println("Sqrt = " + c.sqrt().toString());
        System.out.println("Cos = " + c.cos().toString());
        System.out.println("Sin = " + c.sin().toString());
        System.out.println("Cosh = " + c.cosh().toString());
        System.out.println("Sinh = " + c.sinh().toString());
        System.out.println("Tan = " + c.tan().toString());
        System.out.println("Chs = " + c.chs().toString());
        System.out.println(c.toString());
    }

    private void testController () {
        Controller ctrl = new Controller();
        ArrayList<Complex> cnt = ctrl.totalList("123.4, 3242, 34545, 234", "2, 123, 345.54, 1, 1");
        for (int i = 0; i < cnt.size(); ++i) {
            System.out.println(cnt.get(i).toString());
        }

        Complex res = ctrl.execCmd("ADD", "10, 10", "10, 40");
        System.out.println(res.toString());

        String s = ctrl.makeValidInput("12, 23, ,,,,");
        System.out.println(s);
    }

    public void runTests() {
        testComplex();
        testController();
    }

}
