package exp.jsapar.logic;

import java.util.HashMap;
import java.util.Map;

// alpha code: watch your steps
public class FilterStuff {
	final Map<Class, FilterLogic> map = new HashMap<Class, FilterLogic>();

    void register(Class clazz, FilterLogic logic){
            map.put(clazz, logic);
    }

    void doSomething(Object value) throws Exception{
            FilterLogic logic = map.get(value.getClass());
            if(logic==null)throw new Exception();
            logic.doSomething();
    }
}
