package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.dto.TypeDto;
import org.tekCorp.api.service.TypeService;
import org.tekCorp.api.service.impl.TypeServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/type")
public class TypeController {


    private TypeService typeService;
    
    @Autowired
    public TypeController(TypeServiceImpl typeService) {
        this.typeService = typeService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<TypeDto> list(){
        return typeService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    TypeDto view(@PathVariable String id){
        return typeService.find(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    TypeDto add(@RequestBody TypeDto typeDto){
        TypeDto oriTypeDto = typeService.find(typeDto);
        if (oriTypeDto != null) {
            return oriTypeDto;
        }
        typeDto = typeService.save(typeDto);

        return typeDto;
    }
}
