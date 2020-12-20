public class Complex {

    private double x, y;

    /*
    Desc:   Implicit constructor. Calls the parameter constructor to initialize the number z
            with default Rez=0 and Imz=0
    Input:  None
    Output: None
     */
    public Complex() {
        this(0, 0);
    }

    /*
    Desc:   Constructs the complex number z = u + i*v
    Input:  param u Real part
            param v Imaginary part
    Output: None
     */
    public Complex(double u, double v) {
        this.x = u;
        this.y = v;
    }

    /*
    Desc:   Real part of this Complex number
            (the x-coordinate in rectangular coordinates).
    Input:  None
    Output: return Re[z] where z is this Complex number.
     */
    public double real() {
        return this.x;
    }

    /*
    Desc:   Imaginary part of this Complex number
            (the y-coordinate in rectangular coordinates).
    Input:  None
    Output: return Im[z] where z is this Complex number.
     */
    public double imag() {
        return this.y;
    }

    /*
    Desc:   Modulus of this Complex number
            (the distance from the origin in polar coordinates).
    Input:  None
    Output: return |z| where z is this Complex number.
     */
    public double mod() {
        if (x != 0 || y != 0) {
            return Math.sqrt(x * x + y * y);
        } else {
            return 0d;
        }
    }

    /*
    Desc:   Argument of this Complex number
            (the angle in radians with the x-axis in polar coordinates).
    Input:  None
    Output: return arg(z) where z is this Complex number.
     */
    public double arg() {
        return Math.atan2(y, x);
    }

    /*
    Desc:   Complex conjugate of this Complex number
            (the conjugate of x+i*y is x-i*y).
    Input:  None
    Output: return z-bar where z is this Complex number.
     */
    public Complex conj() {
        return new Complex(x, -y);
    }

    /*
    Desc:   Addition of Complex numbers (doesn't change this Complex number).
            <br>(x+i*y) + (s+i*t) = (x+s)+i*(y+t).
    Input:  param w is the number to add.
    Output: return z+w where z is this Complex number.
     */
    public Complex plus(Complex w) {
        return new Complex(this.x + w.real(),this.y + w.imag());
    }

    /*
    Desc:   Subtraction of Complex numbers (doesn't change this Complex number).
            <br>(x+i*y) - (s+i*t) = (x-s)+i*(y-t).
    Input:  param w is the number to subtract.
    Output: return z-w where z is this Complex number.
     */
    public Complex minus(Complex w) {
        return new Complex(this.x - w.real(),this.y - w.imag());
    }

    /*
    Desc:   Complex multiplication (doesn't change this Complex number).
    Input:  param w is the number to multiply by.
    Output: return z*w where z is this Complex number.
     */
    public Complex times(Complex w) {
        return new Complex(this.x * w.real() - this.y * w.imag(),this.x * w.imag() + this.y * w.real());
    }

    /*
    Desc:   Division of Complex numbers (doesn't change this Complex number).
            <br>(x+i*y)/(s+i*t) = ((x*s+y*t) + i*(y*s-y*t)) / (s^2+t^2)
    Input:  param w is the number to divide by
    Output: return new Complex number z/w where z is this Complex number
     */
    public Complex div(Complex w) {
        double den = Math.pow(w.mod(),2);
        return new Complex((this.x * w.real() + this.y * w.imag()) / den,
                            (this.y * w.real()-this.x * w.imag()) / den);
    }

    /*
    Desc:   Complex exponential (doesn't change this Complex number).
    Input:  None
    Output: return exp(z) where z is this Complex number.
     */
    public Complex exp() {
        return new Complex(Math.exp(this.x) * Math.cos(this.y),Math.exp(this.x) * Math.sin(this.y));
    }

    /*
    Desc:   Principal branch of the Complex logarithm of this Complex number.
            (doesn't change this Complex number).
            The principal branch is the branch with -pi < arg <= pi.
    Input:  None
    Output: return log(z) where z is this Complex number.
     */
    public Complex log() {
        return new Complex(Math.log(this.mod()), this.arg());
    }

    /*
    Desc:   Complex square root (doesn't change this complex number).
            Computes the principal branch of the square root, which
            is the value with 0 <= arg < pi.
    Input:  None
    Output: return sqrt(z) where z is this Complex number.
     */
    public Complex sqrt() {
        double r = Math.sqrt(this.mod());
        double theta = this.arg() / 2;
        return new Complex(r * Math.cos(theta),r * Math.sin(theta));
    }

    // Real cosh function (used to compute complex trig functions)
    private double cosh(double theta) {
        return (Math.exp(theta) + Math.exp(-theta)) / 2;
    }

    // Real sinh function (used to compute complex trig functions)
    private double sinh(double theta) {
        return (Math.exp(theta) - Math.exp(-theta)) / 2;
    }

    /*
    Desc:   Sine of this Complex number (doesn't change this Complex number).
            <br>sin(z) = (exp(i*z)-exp(-i*z))/(2*i).
    Input:  None
    Output: return sin(z) where z is this Complex number.
     */
    public Complex sin() {
        return new Complex(cosh(this.y) * Math.sin(this.x),sinh(this.y) * Math.cos(this.x));
    }

    /*
    Desc:   Cosine of this Complex number (doesn't change this Complex number).
            <br>cos(z) = (exp(i*z)+exp(-i*z))/ 2.
    Input:  None
    Output: return cos(z) where z is this Complex number.
     */
    public Complex cos() {
        return new Complex(cosh(this.y) * Math.cos(this.x),-sinh(this.y) * Math.sin(this.x));
    }

    /*
    Desc:   Hyperbolic sine of this Complex number
            (doesn't change this Complex number).
            <br>sinh(z) = (exp(z)-exp(-z))/2.
    Input:  None
    Output: return sinh(z) where z is this Complex number.
     */
    public Complex sinh() {
        return new Complex(sinh(this.x) * Math.cos(this.y),cosh(this.x) * Math.sin(this.y));
    }

    /*
    Desc:   Hyperbolic cosine of this Complex number
            (doesn't change this Complex number).
            <br>cosh(z) = (exp(z) + exp(-z)) / 2.
    Input:  None
    Output: return cosh(z) where z is this Complex number.
     */
    public Complex cosh() {
        return new Complex(cosh(this.x) * Math.cos(this.y),sinh(this.x) * Math.sin(this.y));
    }

    /*
    Desc:   Tangent of this Complex number (doesn't change this Complex number).
            <br>tan(z) = sin(z)/cos(z).
    Input:  None
    Output: return tan(z) where z is this Complex number.
     */
    public Complex tan() {
        return (this.sin()).div(this.cos());
    }

    /*
    Desc:   Negative of this complex number (chs stands for change sign).
            This produces a new Complex number and doesn't change
            this Complex number.
            <br>-(x+i*y) = -x-i*y.
    Input:  None
    Output: return -z where z is this Complex number.
     */
    public Complex chs() {
        return new Complex(-this.x, -this.y);
    }

    /*
    Desc:   String representation of this Complex number.
    Input:  None
    Output: return x+i*y, x-i*y, x, or i*y as appropriate.
     */
    public String toString() {
        if (this.x == 0 && this.y == 0) {
            return String.valueOf(0.0);
        }
        if (this.x != 0 && this.y > 0) {
            return this.x + " + " + this.y + "i";
        }
        if (this.x != 0 && this.y < 0) {
            return this.x + " - " + (-this.y) + "i";
        }
        if (this.y == 0) {
            return String.valueOf(this.x);
        }
        if (this.x == 0) {
            return this.y + "i";
        }
        // shouldn't get here (unless Inf or NaN)
        return this.x + " + i*" + this.y;

    }
}