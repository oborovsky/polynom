/**
 * Created by ulihtenshtein on 26.09.15.
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Polynom implements IPolynom{
    private ArrayList<Double> mA;
    private void resize(ArrayList<Double> a) {
        int l0 = mA.size();
        int l1 = a.size();
        ArrayList<Double> t;

        if ( l1 > l0) {
            t = mA;
        } else {
            t = a;
        }
        for (int i = l0; i < l1; i++) {
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
    public void add(Polynom p) {
        resize(p.mA);
        int l = mA.size();
        for (int i = 0; i < l; i++) {
            mA.set(i, mA.get(i) + p.mA.get(i));
        }
    }
    public void mult(double k) {
        for(int i = 0; i < mA.size(); i++) {
            mA.set(i, mA.get(i) * k);
        }
    }
    public void mult(Polynom p) {
        int n = mA.size();
        int m = p.mA.size();
        resize(p.mA);
        int l = n * m - m - n + 3;

        ArrayList<Double> na = new ArrayList<>(l);
        resize(p.mA, l);
        resize(p.mA);

        for( int i = 0; i <= l - 1; i++) {
            double ci = 0;
            for( int j = 0; j <= i; j++) {
                ci += mA.get(j) * p.mA.get(i - j);
            }
            na.add(i, ci);
        }
        mA = na;
    }
    public double square(double left, double right) {
        Polynom tmp = new Polynom(mA);
        tmp.integral(0.0);

        return tmp.result(right) - tmp.result(left);
    }
    public void diff() {
        for (int i = 1; i < mA.size(); i++) {
            mA.set(i-1, i*mA.get(i));
        }
        mA.remove(mA.size() - 1);
        mA.trimToSize();
    }
    public void integral(double c) {
        if( mA.size() == 0 ) {
            mA.add(c);
            return;
        }
        mA.add(0.0);
        double t1 = 0.0;
        double t2 = mA.get(0);
        for(int i = 0; i < mA.size() - 1; i++) {
            t1 = t2;
            t2 = mA.get(i+1);
            mA.set(i+1, t1/(i+1));
        }
        mA.set(0, c);
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
        p.mult(2.0);
        System.out.print("p*2: ");
        p.out();
        p.mult(p1);
        System.out.print("p*p1: ");
        p.out();
        p.add(p1);
        System.out.print("p+p1: ");
        p.out();
        System.out.print("diff p: ");
        p.diff();
        p.out();
        System.out.print("integral p: ");
        p.integral(1.0);
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
