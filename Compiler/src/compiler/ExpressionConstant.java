/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author leijurv
 */
public class ExpressionConstant extends Expression{
    private final Object value;
    public ExpressionConstant(Object val){
        value=val;
    }
    @Override
    public Object evaluate(Context c){
        return value;
    }
    @Override
    public String toString(){
        return "~constant "+value+"~";
    }
}