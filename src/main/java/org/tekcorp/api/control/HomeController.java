package org.tekcorp.api.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.Util.Util;
import org.tekcorp.api.domain.model.ElementModel;
import org.tekcorp.api.domain.model.TypeModel;
import org.tekcorp.api.repository.ElementRepository;
import org.tekcorp.api.repository.TypeRepository;

/**
 * Created by Inspiron on 15/06/2015.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final TypeRepository typeRepository;
    private final ElementRepository elementRepository;

    public HomeController(TypeRepository typeRepository, ElementRepository elementRepository) {
        this.typeRepository = typeRepository;
        this.elementRepository = elementRepository;
    }

    @RequestMapping("home")
    public @ResponseBody List<ElementModel> home(){
        List<TypeModel> typeModelList = typeRepository.findAll();
        List<ElementModel> elementModelList = elementRepository.findAll();
        return getRandElement(typeModelList, elementModelList);
    }

    private List<ElementModel> getRandElement(List<TypeModel> typeModelList, List<ElementModel> elementModelList) {
        List<ElementModel> typeElementModelHashMap = new ArrayList<ElementModel>();
        HashMap<TypeModel, List<ElementModel>> mapElementByIdType = Util.mapElementByIdType(typeModelList, elementModelList);

        for (TypeModel typeModel : typeModelList){
            List<ElementModel> elementModels = mapElementByIdType.get(typeModel);
            if (elementModels != null && !elementModels.isEmpty()){
                ElementModel elementModel = elementModels.get((int) (Math.random() * elementModels.size()));
                typeElementModelHashMap.add(elementModel);
            }
        }

        return typeElementModelHashMap;
    }

}
