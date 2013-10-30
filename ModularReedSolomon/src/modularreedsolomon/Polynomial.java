/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modularreedsolomon;

import java.math.BigInteger;

/**
 *
 * @author leif
 */
public class Polynomial {
    int mod;
    int[] c;
    public Polynomial(int[] cs, int Mod){
        mod=Mod;
        int zeros=0;
        for (int i=cs.length-1; i>=0; i--){
            if (cs[i]==0){
                zeros++;
            }else{
                break;
            }
        }
        int[] r=new int[cs.length-zeros];
        for (int i=0; i<r.length; i++){
            r[i]=cs[i]%mod;
        }
        c=r;
    }
    public Polynomial add(Polynomial p){
        if (p.c.length>c.length){
            return p.add(this);
        }
        int[] r=new int[c.length];
        System.arraycopy(c, 0, r, 0, r.length);
        for (int i=0; i<p.c.length; i++){
            r[i]+=p.c[i];
        }
        return new Polynomial(r,mod);
    }
    public Polynomial subtract(Polynomial p){
        int[] r=new int[p.c.length];
        for (int i=0; i<r.length; i++){
            r[i]=mod-p.c[i];
        }
        return add(new Polynomial(r,mod));
    }
    public Polynomial multiply(Polynomial p){
        int[] r=new int[p.c.length+c.length-1];
        for (int i=0; i<c.length; i++){
            for (int n=0; n<p.c.length; n++){
                r[i+n]+=c[i]*p.c[n];
            }
        }
        return new Polynomial(r,mod);
    }
    public Polynomial divide(Polynomial p){
        System.out.println(this+","+p);
        /*
        if (p.c.length==1){
            int C=MAYTRIX.invert(p.c[0], mod);
            int[] result=new int[c.length];
            for (int i=0; i<c.length; i++){
                result[i]=(c[i]*C)%mod;
            }
            return new Polynomial(result,mod);
        }*/
        if (c.length==0){
            return new Polynomial(new int[] {0},mod);
        }
        Polynomial R=new Polynomial(c,mod);
            int a=R.c[R.c.length-1];
            int b=p.c[p.c.length-1];
            System.out.println(a+":"+b);
            System.out.println(c[2]);
            System.out.println(c.length);
            int C=MAYTRIX.invert(b,mod);
            int d=(C*a)%mod;
            System.out.println(d);
            int[] Q=new int[c.length];
          int zeros=0;
        for (int i=c.length-1; i>=0; i--){
            if (c[i]==0){
                zeros++;
            }else{
                break;
            }
        }
            for (int i=Q.length-1; i>=Q.length-p.c.length; i--){
                Q[i+zeros]=(p.c[p.c.length-(Q.length-i-1)-1])*d;
            }
            System.out.println(p.c.length);
            System.out.println(this);
            System.out.println(c[c.length-1]);
            System.out.println(new Polynomial(Q,mod));
            System.out.println("Cat"+subtract(new Polynomial(Q,mod)));
            Polynomial X=(subtract(new Polynomial(Q,mod))).divide(p);
            int[] S=new int[X.c.length+1];
            for (int i=0; i<X.c.length; i++){
                S[i]=X.c[i];
            }
            S[S.length-1]=d;
            return new Polynomial(S,mod);
    }
    public String toString(){
        String s="";
        for (int i=c.length-1; i>=0; i--){
            //s=s+c[i]+"x^"+i+" ";
            s=s+c[i]+" ";
        }
        return s;
    }
    public int eval(int x){
        int r=0;
        for (int i=0; i<c.length; i++){
            BigInteger N=new BigInteger(x+"");
            N=N.modPow(new BigInteger(""+i),new BigInteger(""+mod));
            N=N.multiply(new BigInteger(""+c[i]));
            N=N.mod(new BigInteger(""+mod));
            //r+=c[i]*(((int)Math.pow(x,i))%mod);
            //r=r%mod;
            r+=N.intValue();
            r=r%mod;
        }
        return r%mod;
    }
    public boolean equals(Object o){
        if (o instanceof Polynomial){
            Polynomial p=(Polynomial)o;
            if (c.length!=p.c.length){
                return false;
            }
            for (int i=0; i<c.length; i++){
                if (c[i]!=p.c[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
