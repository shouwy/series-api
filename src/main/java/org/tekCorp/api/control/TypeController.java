package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Type;
import org.tekCorp.api.repository.TypeRepository;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired TypeRepository typeRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Type> list(){
        List<Type> typeList = typeRepository.findAll();
        return typeList;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody Type view(@PathVariable String id){
        Type type = typeRepository.findOne(id);
        return type;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Type add(@RequestBody Type type){
        Type oriType = typeRepository.findByName(type.getNom());
        if (oriType != null) {
            return oriType;
        }
        type = typeRepository.save(type);

        return type;
    }
}
