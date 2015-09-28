/**
 * Created by oborovsky on 26.09.15.
 */
public interface IPolynom {
    Polynom add(Polynom p);
    Polynom mult(double k);
    Polynom mult(Polynom p);
    double square(double left, double right);
    Polynom diff();
    Polynom integral(double c);
    double result( double x);
}
