package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Element;
import org.tekCorp.api.repository.ElementRepository;
import org.tekCorp.api.response.JsonResponse;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/elements")
public class ElementsController {

    @Autowired ElementRepository elementRepository;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public JsonResponse view(@PathVariable String id){
        Element element = elementRepository.findOne(id);
        JsonResponse jsonResponse = new JsonResponse(200, "Find Element", element);

        if (element == null){
            jsonResponse = new JsonResponse(203, "Element Not Found");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody JsonResponse add(@RequestBody Element element){
        Element oriElement = elementRepository.findByNomAndYear(element.getNom(), element.getYear());

        if (oriElement != null){
            return new JsonResponse(203, "Element Allready Exists");
        }

        element = elementRepository.save(element);

        return new JsonResponse(200, "Insert Element OK", element);
    }
}
