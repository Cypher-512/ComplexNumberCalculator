import java.util.ArrayList;

public class Controller {

    /*
        Desc:   Eliminate illegal characters from a string of numbers,
                separated by ", " (black box testing approach)
        Input:  User input
        Output: Validated string
     */
    public String makeValidInput(String input) {
        StringBuilder sb = new StringBuilder(input);
        String res;
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String illegalChars = "+=-_!@#:$%^&*()<>?/;'\"{}[]\\|~`" + abc + ABC;

        // check if it contains digits and it's worth parsing it
        boolean containsDigits = false;
        for (int i = 0; i < sb.length() && !containsDigits; ++i) {
            if ('0' <= sb.charAt(i) && sb.charAt(i) <= '9') containsDigits = true;
        }

        if (!containsDigits) return ""; // no digits, no parsing, bye

        // delete illegal characters
        for (int i = 0; i < sb.length(); ++i) {
            if (illegalChars.contains(String.valueOf(sb.charAt(i)))) {
                sb.deleteCharAt(i);
                --i;
            }
        }

        // parse problematic characters, "." and ","
        // delete "." if there are multiple of them
        // no problem if . is at position 0, the number will be seen as 0.r.., r = 1,9
        for (int i = 0; i < sb.length(); ++i) {
            if (i < sb.length() - 1) {
                if (sb.charAt(i) == '.') {
                    while (!('0' <= sb.charAt(i + 1) && sb.charAt(i + 1) <= '9'))
                        sb.deleteCharAt(i + 1);
                }
            }
        }

        // delete consecutive ","
        if (sb.charAt(0) == ',') {
            while (sb.charAt(0) == ',')
                sb.deleteCharAt(0);
        }
        for (int i = 1; i < sb.length() - 1; ++i) {
            if (sb.charAt(i) == ',') {
                while (sb.charAt(i + 1) == ',')
                    sb.deleteCharAt(i + 1);
            }
        }
        while (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }

        res = sb.toString();
        return res;
    }

    /*
        Desc:   Parse numbers from a given string
        Input:  User input
        Output: ArrayList that contains the real parts from the given complex numbers
     */
    private ArrayList<Complex> stringParseReal(String real) {
        ArrayList<Complex> content = new ArrayList<>();
        real = makeValidInput(real);

        if (real.equals("")){
            content.add(new Complex(0,0));
            return content;
        }

        String delimit = "[, ]+";
        String[] rezTokens = real.split(delimit);
        // set real part
        for (String itr : rezTokens) {
            double tmp = Double.parseDouble(itr);
            Complex c = new Complex(tmp, 0);
            content.add(c);
        }

        return content;
    }

    /*
        Desc:   Parse numbers from a given string
        Input:  User input
        Output: ArrayList that contains the imaginary parts from the given complex numbers
     */
    private ArrayList<Complex> stringParseImg(String img) {
        ArrayList<Complex> content = new ArrayList<>();
        img = makeValidInput(img);

        if (img.equals("")){
            content.add(new Complex(0,0));
            return content;
        }

        String delimit = "[, ]+";
        String[] imgTokens = img.split(delimit);
        // set img part
        for (String itr : imgTokens) {
            double tmp = Double.parseDouble(itr);
            Complex c = new Complex(0, tmp);
            content.add(c);
        }

        return content;
    }

    /*
        Desc:   Combine real and imaginary parts to form the list of complex numbers
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: List of complex numbers
     */
    ArrayList<Complex> totalList(String real, String img) {
        ArrayList<Complex> rez = stringParseReal(real);
        ArrayList<Complex> imz = stringParseImg(img);

        if (rez.size() == 0 && imz.size() == 0) {
            ArrayList<Complex> exceptArr = new ArrayList<>();
            exceptArr.add(new Complex(0,0));
            exceptArr.add(new Complex(0,0));
            return exceptArr;
        }

        ArrayList<Complex> list = new ArrayList<>();
        for (int i = 0; i < Math.min(rez.size(), imz.size()); ++i)
            list.add(rez.get(i).plus(imz.get(i)));
        if (rez.size() > imz.size()) {
            for (int i = imz.size(); i < rez.size(); ++i)
                list.add(rez.get(i));
        }
        if (imz.size() > rez.size()) {
            for (int i = rez.size(); i < imz.size(); ++i)
                list.add(imz.get(i));
        }
        return list;
    }

    /*
        Desc:   Add all complex numbers from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Complex sum of all numbers
     */
    private Complex addNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        Complex sum = new Complex(num.get(0).real(), num.get(0).imag());
        for (int i = 1; i < num.size(); ++i) {
            sum = sum.plus(num.get(i));
        }
        return sum;
    }

    /*
        Desc:   Subtract all complex numbers from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Complex difference of all numbers
     */
    private Complex subNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        Complex diff = new Complex(num.get(0).real(), num.get(0).imag());
        for (int i = 1; i < num.size(); ++i)
            diff = diff.minus(num.get(i));
        return diff;
    }

    /*
        Desc:   Multiply all complex numbers from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Complex product of all numbers
     */
    private Complex mulNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        Complex prod = new Complex(num.get(0).real(), num.get(0).imag());
        for (int i = 1; i < num.size(); ++i)
            prod = prod.times(num.get(i));
        return prod;
    }

    /*
        Desc:   Division all complex numbers from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Complex division of all numbers
     */
    private Complex divNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        Complex division = new Complex(num.get(0).real(), num.get(0).imag());
        for (int i = 1; i < num.size(); ++i)
            division = division.div(num.get(i));
        return division;
    }

    /*
        Desc:   Calculate exponential of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Exponential of the first complex number
     */
    private Complex expNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).exp();
    }

    /*
        Desc:   Calculate argument of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Double value argument of the first complex number
     */
    private double argNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).arg();
    }

    /*
        Desc:   Calculate the sin of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Sin of the first complex number
     */
    private Complex sinNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).sin();
    }

    /*
        Desc:   Calculate cos of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Cos of the first complex number
     */
    private Complex cosNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).cos();
    }

    /*
        Desc:   Calculate tan of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Tan of the first complex number
     */
    private Complex tanNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).tan();
    }

    /*
        Desc:   Calculate the modulus of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Double value modulus of the first complex number
     */
    private double modNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).mod();
    }

    /*
        Desc:   Calculate hyperbolic sin of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Hyperbolic sin of the first complex number
     */
    private Complex sinhNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).sinh();
    }

    /*
        Desc:   Calculate hyperbolic cos of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Hyperbolic cos of the first complex number
     */
    private Complex coshNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).cosh();
    }

    /*
        Desc:   Calculate sqrt of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Sqrt of the first complex number
     */
    private Complex sqrtNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).sqrt();
    }

    /*
        Desc:   Calculate log of the first complex number from a list
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: Log of the first complex number
     */
    private Complex logNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).log();
    }

    /*
        Desc:   Change sign of the first complex number
        Input:  Real and imaginary parts given by the user, seen as strings
        Output: First sign changed complex number
     */
    private Complex chsNum(String real, String img) {
        ArrayList<Complex> num = totalList(real, img);
        return new Complex(num.get(0).real(), num.get(0).imag()).chs();
    }

    /*
        Desc:   Calculate the result, based on the operation chosen by the user
        Input:  String command cmd, real and imaginary parts of the complex numbers
        Output: Result based on the operation chosen
     */
    public Complex execCmd(String cmd, String real, String img) {
        Complex res;
        switch (cmd) {
            case "ADD":
                res = addNum(real, img);
                break;
            case "SUB":
                res = subNum(real, img);
                break;
            case "MUL":
                res = mulNum(real, img);
                break;
            case "DIV":
                res = divNum(real, img);
                break;
            case "EXP":
                res = expNum(real, img);
                break;
            case "ARG":
                double tmpArg = argNum(real, img);
                res = new Complex(tmpArg, 0);
                break;
            case "SIN":
                res = sinNum(real, img);
                break;
            case "COS":
                res = cosNum(real, img);
                break;
            case "TAN":
                res = tanNum(real, img);
                break;
            case "MOD":
                double tmpMod = modNum(real, img);
                res = new Complex(tmpMod, 0);
                break;
            case "SINH":
                res = sinhNum(real, img);
                break;
            case "COSH":
                res = coshNum(real, img);
                break;
            case "LOG":
                res = logNum(real, img);
                break;
            case "SQRT":
                res = sqrtNum(real, img);
                break;
            default:
                res = chsNum(real, img);
                break;
        }
        return res;
    }
}
