/**
 * Created by oborovsky on 26.09.15.
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Polynom implements IPolynom{
    private ArrayList<Double> mA;
    private void resize(ArrayList<Double> a1, ArrayList<Double> a2) {
        int l0 = a1.size();
        int l1 = a2.size();
        int lmax = (l1 > l0) ? l1 : l0;
        int lmin = (l1 > l0) ? l0 : l1;
        ArrayList<Double> t;

        if ( l1 > l0) {
            t = a1;
        } else {
            t = a2;
        }
        for (int i = lmin; i < lmax; i++) {
            t.add(0.0);
        }
    }
    private void resize(ArrayList<Double> a, int size) {
        for (int i = a.size(); i < size; i++) {
            a.add(0.0);
        }
    }
    public Polynom() {
        mA = new ArrayList<>();
    }
    public Polynom(ArrayList<Double> a) {
        mA = new ArrayList<>(a);
    }
    public void setCof(ArrayList<Double> a) {
        mA = a;
    }
    public ArrayList<Double> getCof() {
        return mA;
    }
    public Polynom add(Polynom p) {
        ArrayList<Double> src = new ArrayList<>(p.mA);
        ArrayList<Double> dest = new ArrayList<>(mA);
        resize(dest, src);
        int l = dest.size();
        for (int i = 0; i < l; i++) {
            dest.set(i, dest.get(i) + src.get(i));
        }
        return new Polynom(dest);
    }
    public Polynom mult(double k) {
        ArrayList<Double> dest = new ArrayList<>(mA);
        for(int i = 0; i < dest.size(); i++) {
            dest.set(i, dest.get(i) * k);
        }
        return new Polynom(dest);
    }
    public Polynom mult(Polynom p) {
        ArrayList<Double> a1 = new ArrayList<>(mA);
        ArrayList<Double> a2 = new ArrayList<>(p.mA);
        int n = a1.size();
        int m = a2.size();
        resize(a1, a2);
        int l = n * m - m - n + 3;

        ArrayList<Double> na = new ArrayList<>(l);
        resize(a1, l);
        resize(a2, l);

        for( int i = 0; i <= l - 1; i++) {
            double ci = 0;
            for( int j = 0; j <= i; j++) {
                ci += a1.get(j) * a2.get(i - j);
            }
            na.add(i, ci);
        }
        return new Polynom(na);
    }
    public double square(double left, double right) {
        Polynom tmp = integral(0.0);
        return tmp.result(right) - tmp.result(left);
    }
    public Polynom diff() {
        ArrayList<Double> a = new ArrayList<>(mA);
        for (int i = 1; i < a.size(); i++) {
            a.set(i-1, i * a.get(i));
        }
        a.remove(a.size() - 1);
        a.trimToSize();
        return new Polynom(a);
    }
    public Polynom integral(double c) {
        ArrayList<Double> a = new ArrayList<>(mA);
        if( a.size() == 0 ) {
            a.add(c);
            return new Polynom(a);
        }
        a.add(0.0);
        double t1 = 0.0;
        double t2 = a.get(0);
        for(int i = 0; i < a.size() - 1; i++) {
            t1 = t2;
            t2 = a.get(i+1);
            a.set(i + 1, t1 / (i + 1));
        }
        a.set(0, c);
        return new Polynom(a);
    }
    public double result( double x) {
        if (mA.size() == 0) return 0.0;
        double r = mA.get(0);
        for(int i = 1; i < mA.size(); i++) {
            r += Math.pow(x, i) * mA.get(i);
        }
        return r;
    }
    public void out() {
       for(int i = 0; i < mA.size(); i++) {
           System.out.print(mA.get(i));
           System.out.print(",");
       }
        System.out.println();
    }
    public static void main(String[] args) {
        Polynom p = new Polynom();
        Polynom p1 = new Polynom();
        ArrayList<Double> a = new ArrayList<>();
        a.add(2.0);
        a.add(1.0);
        p.setCof(a);
        ArrayList<Double> a1 = new ArrayList<>(a);
        a1.add(1.0);
        p1.setCof(a1);
        System.out.print("p: ");
        p.out();
        System.out.print("p1: ");
        p1.out();
        p = p.mult(2.0);
        System.out.print("p*2: ");
        p.out();
        p = p.mult(p1);
        System.out.print("p*p1: ");
        p.out();
        Polynom p2 =p.add(p1);
        System.out.print("p2=p+p1: ");
        p2.out();
        System.out.print("diff p: ");
        p = p.diff();
        p.out();
        System.out.print("integral p: ");
       p = p.integral(1.0);
        p.out();
        System.out.println("result on 0: " + p.result(0));
        System.out.println("square p 0 - 2: " + p.square(0, 2));
        ArrayList<Double> a2 = new ArrayList<>();
        a2.add(0.0);
        a2.add(0.0);
        a2.add(1.0);
        Polynom p3 = new Polynom(a2);
        System.out.println("square p3 0 - 1: " + p3.square(0, 1));
    }
}
