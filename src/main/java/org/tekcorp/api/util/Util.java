package org.tekcorp.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tekcorp.api.domain.model.ElementModel;
import org.tekcorp.api.domain.model.TypeModel;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
public class Util {

    /**
     * Constructor
     */
    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<TypeModel, List<ElementModel>> mapElementByIdType(List<TypeModel> typeModelList, List<ElementModel> listSeries) {
        HashMap<TypeModel, List<ElementModel>> mapElement = new HashMap<>();
        for (TypeModel typeModel : typeModelList){
            for (ElementModel elementModel : listSeries) {
                if (!mapElement.containsKey(typeModel)) {
                    mapElement.put(typeModel, new ArrayList<>());
                }
                mapElement.get(typeModel).add(elementModel);
            }
        }

        return mapElement;
    }
}
