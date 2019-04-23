package org.tekcorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.Util.Util;
import org.tekcorp.api.domain.model.ElementModel;
import org.tekcorp.api.domain.model.TypeModel;
import org.tekcorp.api.repository.ElementRepository;
import org.tekcorp.api.repository.TypeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Inspiron on 15/06/2015.
 */
@Controller
public class HomeController {

    @Autowired TypeRepository typeRepository;
    @Autowired ElementRepository elementRepository;

    @RequestMapping("/home")
    public @ResponseBody List<ElementModel> home(){
        List<TypeModel> typeModelList = typeRepository.findAll();
        List<ElementModel> elementModelList = elementRepository.findAll();
        List<ElementModel> typeElementModelHashMap = getRandElement(typeModelList, elementModelList);
        return typeElementModelHashMap;
    }

    private List<ElementModel> getRandElement(List<TypeModel> typeModelList, List<ElementModel> elementModelList) {
        List<ElementModel> typeElementModelHashMap = new ArrayList<ElementModel>();
        HashMap<TypeModel, List<ElementModel>> mapElementByIdType = Util.mapElementByIdType(typeModelList, elementModelList);

        for (TypeModel typeModel : typeModelList){
            List<ElementModel> elementModels = mapElementByIdType.get(typeModel);
            if (elementModels != null && !elementModels.isEmpty()){
                ElementModel elementModel = elementModels.get((int) Math.random() * elementModels.size());
                typeElementModelHashMap.add(elementModel);
            }
        }

        return typeElementModelHashMap;
    }

}
