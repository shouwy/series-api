package org.tekcorp.api.control;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.domain.model.ElementModel;
import org.tekcorp.api.domain.model.TypeModel;
import org.tekcorp.api.repository.ElementRepository;
import org.tekcorp.api.repository.TypeRepository;
import org.tekcorp.api.util.Util;

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

    @GetMapping("home")
    public @ResponseBody
    List<ElementModel> home() throws NoSuchAlgorithmException {
        List<TypeModel> typeModelList = typeRepository.findAll();
        List<ElementModel> elementModelList = elementRepository.findAll();
        return getRandElement(typeModelList, elementModelList);
    }

    private List<ElementModel> getRandElement(List<TypeModel> typeModelList, List<ElementModel> elementModelList) throws NoSuchAlgorithmException {
        List<ElementModel> typeElementModelHashMap = new ArrayList<>();
        Map<TypeModel, List<ElementModel>> mapElementByIdType = Util.mapElementByIdType(typeModelList, elementModelList);

        for (TypeModel typeModel : typeModelList){
            List<ElementModel> elementModels = mapElementByIdType.get(typeModel);
            if (elementModels != null && !elementModels.isEmpty()){
                Random random = SecureRandom.getInstanceStrong();
                ElementModel elementModel = elementModels.get(random.nextInt(elementModels.size()));
                typeElementModelHashMap.add(elementModel);
            }
        }

        return typeElementModelHashMap;
    }

}
