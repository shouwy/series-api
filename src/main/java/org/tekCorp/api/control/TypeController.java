package org.tekCorp.api.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekCorp.api.domain.Type;
import org.tekCorp.api.repository.TypeRepository;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Type> list(){
        return typeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Type view(@PathVariable String id){
        Optional<Type> type = typeRepository.findById(id);

        return type.orElse(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Type add(@RequestBody Type type){
        Type oriType = typeRepository.findByTypeName(type.getTypeName());
        if (oriType != null) {
            return oriType;
        }
        type = typeRepository.save(type);

        return type;
    }
}
