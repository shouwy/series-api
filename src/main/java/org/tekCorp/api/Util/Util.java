package org.tekCorp.api.Util;

import org.tekCorp.api.domain.Element;
import org.tekCorp.api.domain.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
public class Util {
    public static HashMap<Type, List<Element>> mapElementByIdType(List<Type> typeList, List<Element> listSeries){
        HashMap<Type, List<Element>> mapElement = new HashMap<Type, List<Element>>();
        for (Type type : typeList){
            for (Element element : listSeries) {
                if (!mapElement.containsKey(type.getId())) {
                    mapElement.put(type, new ArrayList<Element>());
                }
                mapElement.get(type).add(element);
            }
        }

        return mapElement;
    }
}
