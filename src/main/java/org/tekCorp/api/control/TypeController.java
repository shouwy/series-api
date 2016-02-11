package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Type;
import org.tekCorp.api.repository.TypeRepository;
import org.tekCorp.api.response.JsonResponse;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired TypeRepository typeRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResponse list(){
        List<Type> typeList = typeRepository.findAll();
        return new JsonResponse(200, "Type List", typeList);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public JsonResponse view(@PathVariable String id){
        Type type = typeRepository.findOne(id);
        JsonResponse jsonResponse = new JsonResponse(200, "Find Type", type);

        if (type == null){
            jsonResponse = new JsonResponse(203, "Type Not Found");
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody JsonResponse add(@RequestBody Type type){
        Type oriType = typeRepository.findByNom(type.getNom());
        if (oriType != null) {
            return new JsonResponse(203, "Type Allready Exists");
        }
        type = typeRepository.save(type);

        return new JsonResponse(200, "Insert OK", type);
    }
}
