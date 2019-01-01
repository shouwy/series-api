package org.tekCorp.api.Util;

import org.tekCorp.api.domain.model.ElementModel;
import org.tekCorp.api.domain.model.TypeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
public class Util {
    public static HashMap<TypeModel, List<ElementModel>> mapElementByIdType(List<TypeModel> typeModelList, List<ElementModel> listSeries){
        HashMap<TypeModel, List<ElementModel>> mapElement = new HashMap<TypeModel, List<ElementModel>>();
        for (TypeModel typeModel : typeModelList){
            for (ElementModel elementModel : listSeries) {
                if (!mapElement.containsKey(typeModel.getId())) {
                    mapElement.put(typeModel, new ArrayList<ElementModel>());
                }
                mapElement.get(typeModel).add(elementModel);
            }
        }

        return mapElement;
    }
}
