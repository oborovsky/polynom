/**
 * Created by ulihtenshtein on 26.09.15.
 */
public interface IPolynom {
    void add(Polynom p);
    void mult(double k);
    void mult(Polynom p);
    double square(double left, double right);
    void diff();
    void integral(double c);
    double result( double x);
}
