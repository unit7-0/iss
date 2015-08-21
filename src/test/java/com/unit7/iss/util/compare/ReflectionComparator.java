package com.unit7.iss.util.compare;

import com.unit7.iss.exception.ApplicationException;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by breezzo on 16.08.15.
 */
public class ReflectionComparator implements Comparator<Object> {
    @Override
    public int compare(Object a, Object b) {
        if (a == b)
            return 0;

        if (a == null)
            return -1;

        if (b == null)
            return 1;

        final Class<?> aClass = a.getClass();
        final Class<?> bClass= b.getClass();

        if (isIterable(aClass) && isIterable(bClass))
            return compareIterables(a, b);

        if (aClass.isArray() && bClass.isArray())
            return compareArrays(a, b);

        if (aClass != bClass)
            return -1;

        if (canCompare(aClass))
            return ((Comparable) a).compareTo(b);

        try {
            return compareFields(a, b);
        } catch (InvocationTargetException | IllegalAccessException  e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    private boolean canCompare(Class c) {
        return Comparable.class.isAssignableFrom(c);
    }

    private boolean isIterable(Class c) {
        return Iterable.class.isAssignableFrom(c);
    }

    private int compareIterables(Object a, Object b) {
        final Iterator<Object> aIt = ((Iterable) a).iterator();
        final Iterator<Object> bIt = ((Iterable) b).iterator();

        while (aIt.hasNext() && bIt.hasNext()) {
            final int compareResult = compare(aIt.next(), bIt.next());
            if (compareResult != 0) {
                return compareResult;
            }
        }

        if (bIt.hasNext()) {
            return -1;
        }

        if (aIt.hasNext()) {
            return 1;
        }

        return 0;
    }

    private int compareArrays(Object a, Object b) {
        Object[] arrayA = asArray(a);
        Object[] arrayB = asArray(b);

        if (arrayA.length != arrayB.length)
            return arrayA.length - arrayB.length;

        for (int i = 0; i < arrayA.length; ++i) {
            Object o1 = arrayA[i];
            Object o2 = arrayB[i];

            final int compareResult = compare(o1, o2);

            if (compareResult != 0)
                return compareResult;
        }

        return 0;
    }

    private Object[] asArray(Object obj) {
        Class<?> c = obj.getClass().getComponentType();

        if (c == boolean.class)
            return ArrayUtils.toObject((boolean[]) obj);

        if (c == byte.class)
            return ArrayUtils.toObject((byte[]) obj);

        if (c == char.class)
            return ArrayUtils.toObject((char[]) obj);

        if (c == short.class)
            return ArrayUtils.toObject((short[]) obj);

        if (c == int.class)
            return ArrayUtils.toObject((int[]) obj);

        if (c == long.class)
            return ArrayUtils.toObject((long[]) obj);

        if (c == float.class)
            return ArrayUtils.toObject((float[]) obj);

        if (c == double.class)
            return ArrayUtils.toObject((double[]) obj);

        return (Object[]) obj;
    }

    private int compareFields(Object a, Object b) throws InvocationTargetException, IllegalAccessException {
        final Method[] methods = a.getClass().getMethods();

        for (Method method : methods) {
            // go through all getters
            if (isGetter(method)) {
                final int compareResult = compare(method.invoke(a, null), method.invoke(b, null));
                if (compareResult != 0) {
                    return compareResult;
                }
            }
        }

        return 0;
    }

    private boolean isGetter(Method method) {
        return method.getName().startsWith("get") && method.getParameterCount() == 0;
    }
}
