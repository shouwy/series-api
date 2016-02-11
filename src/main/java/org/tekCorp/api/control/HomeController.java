package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekCorp.api.Util.Util;
import org.tekCorp.api.domain.Element;
import org.tekCorp.api.domain.Type;
import org.tekCorp.api.repository.ElementRepository;
import org.tekCorp.api.repository.TypeRepository;
import org.tekCorp.api.response.JsonResponse;

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

    @RequestMapping("/")
    public @ResponseBody JsonResponse home(){
        List<Type> typeList = typeRepository.findAll();
        List<Element> elementList = elementRepository.findAll();
        List<Element> typeElementHashMap = getRandElement(typeList, elementList);
        return new JsonResponse(200, "", typeElementHashMap);
    }

    private List<Element> getRandElement(List<Type> typeList, List<Element> elementList) {
        List<Element> typeElementHashMap = new ArrayList<Element>();
        HashMap<Type, List<Element>> mapElementByIdType = Util.mapElementByIdType(typeList, elementList);

        for (Type type : typeList){
            List<Element> elements = mapElementByIdType.get(type);
            if (elements != null && !elements.isEmpty()){
                Element element = elements.get((int) Math.random() * elements.size());
                typeElementHashMap.add(element);
            }
        }

        return typeElementHashMap;
    }

}
