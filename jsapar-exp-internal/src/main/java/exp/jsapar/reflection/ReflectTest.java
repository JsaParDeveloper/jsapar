package exp.jsapar.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
	public static void main(String[] args)
    {
        try {
            Class dummyClass = Class.forName("exp.jsapar.reflection.DummyTo");
           
            //parameter types for methods
            Class[] partypes = new Class[]{String.class};
           
            //Create method object . methodname and parameter types
            Method meth = dummyClass.getMethod("toString", partypes);
           
            //parameter types for constructor
            Class[] constrpartypes = new Class[]{String.class, String.class};
           
            //Create constructor object . parameter types
            Constructor constr = dummyClass.getConstructor(constrpartypes);
           
            //create instance
            Object dummyto = constr.newInstance(new String[]{"Java Programmer", "The Netherlands"});
       
            //Arguments to be passed into method
            Object[] arglist = new Object[]{"I am a"};
       
            //invoke method!!
            String output = (String) meth.invoke(dummyto, arglist);
            System.out.println(output);
       
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
