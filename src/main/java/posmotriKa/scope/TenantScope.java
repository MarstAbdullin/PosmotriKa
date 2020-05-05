package posmotriKa.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TenantScope implements Scope {
    private Map<String, Object> greetingScope = Collections.synchronizedMap(new HashMap<>());
    private Map<String, Runnable> destructionCallbacks = Collections.synchronizedMap(new HashMap<String, Runnable>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        Object scopedObject = greetingScope.get(name);

        if (scopedObject == null) {
            scopedObject = objectFactory.getObject();
            greetingScope.put(name, scopedObject);
        }
        return scopedObject;

    }

    @Override
    public Object remove(String name) {

        Object scopedObject = greetingScope.get(name);
        if (scopedObject != null) {
            greetingScope.remove(name);
            return scopedObject;
        }
        else {
            return null;
        }
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        destructionCallbacks.put(name, runnable);
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return "greeting";
    }
}

