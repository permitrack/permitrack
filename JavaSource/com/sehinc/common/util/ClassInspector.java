package com.sehinc.common.util;

import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ClassInspector
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClassInspector.class);
    public static final
    String
        MAPPED_CLASS_KEY =
        "MappedClassKey";
    public static final
    Class[]
        EMPTY_ARRAY =
        {};
    public static final
    Map
        classProperties =
        new HashMap();

    public static Map objectToMap(Object obj)
    {
        Map
            map =
            new HashMap();
        Class
            objectClass =
            obj.getClass();
        map.put(MAPPED_CLASS_KEY,
                objectClass.getName());
        Iterator
            properties =
            null;
        try
        {
            properties =
                getProperties(objectClass).iterator();
        }
        catch (IntrospectionException e)
        {
            LOG.warn("Error",
                     e);
        }
        while (properties.hasNext())
        {
            PropertyDescriptor
                property =
                (PropertyDescriptor) properties.next();
            String
                propertyName =
                property.getName();
            Method
                method =
                property.getReadMethod();
            Object
                value =
                null;
            try
            {
                value =
                    method.invoke(obj,
                                  (Object[]) EMPTY_ARRAY);
            }
            catch (IllegalAccessException e)
            {
                LOG.warn("Error",
                         e);
            }
            catch (InvocationTargetException e)
            {
                LOG.warn("Error",
                         e);
            }
            map.put(propertyName,
                    value);
        }
        return map;
    }

    public static String inspect(Object obj)
    {
        if (obj
            == null)
        {
            return " null ";
        }
        Map
            map =
            objectToMap(obj);
        StringBuffer
            buffer =
            new StringBuffer();
        Iterator
            keys =
            map.keySet()
                .iterator();
        buffer.append(" CLASS: [ "
                      + map.get(MAPPED_CLASS_KEY)
                      + "] properties: (");
        while (keys.hasNext())
        {
            String
                key =
                (String) keys.next();
            Object
                value =
                map.get(key);
            buffer.append(key
                          + "=> '"
                          + value
                          + "'");
            if (keys.hasNext())
            {
                buffer.append(", ");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

    public static Object mapToObject(Map map)
        throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IntrospectionException, ClassNotFoundException
    {
        Class
            tupacClass =
            Class.forName((String) map.get(MAPPED_CLASS_KEY));
        Object
            unmappedObject =
            tupacClass.newInstance();
        Iterator
            properties =
            getProperties(tupacClass).iterator();
        while (properties.hasNext())
        {
            PropertyDescriptor
                property =
                (PropertyDescriptor) properties.next();
            Method
                setMethod =
                property.getWriteMethod();
            Object[]
                arguments =
                {map.get(property.getName())};
            setMethod.invoke(unmappedObject,
                             arguments);
        }
        return unmappedObject;
    }

    public static List objectListToMapList(List list)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IntrospectionException
    {
        List
            newList =
            new ArrayList(list.size());
        Iterator
            it =
            list.iterator();
        while (it.hasNext())
        {
            Object
                object =
                it.next();
            newList.add(objectToMap(object));
        }
        return newList;
    }

    public static List mapListToObjectList(List list)
        throws Exception
    {
        List
            newList =
            new ArrayList(list.size());
        Iterator
            it =
            list.iterator();
        while (it.hasNext())
        {
            Map
                object =
                (Map) it.next();
            newList.add(mapToObject(object));
        }
        return newList;
    }

    private static List getProperties(Class c)
        throws IntrospectionException
    {
        List
            properties =
            (List) classProperties.get(c);
        if (properties
            == null)
        {
            Field[]
                fields =
                c.getDeclaredFields();
            properties =
                new ArrayList();
            for (
                int
                    i =
                    0; i
                       < fields.length; i++)
            {
                Field
                    field =
                    fields[i];
                int
                    fieldModifier =
                    field.getModifiers();
                if (Modifier.isStatic(fieldModifier)
                    || Modifier.isTransient(fieldModifier))
                {
                    continue;
                }
                String
                    propertyName =
                    fields[i].getName();
                properties.add(new PropertyDescriptor(propertyName,
                                                      c));
            }
            classProperties.put(c,
                                properties);
        }
        return properties;
    }
}

