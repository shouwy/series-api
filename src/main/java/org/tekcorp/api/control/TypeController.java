package org.tekcorp.api.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.domain.dto.TypeDto;
import org.tekcorp.api.service.TypeService;
import org.tekcorp.api.service.impl.TypeServiceImpl;

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

    @GetMapping(value = "/list")
    public @ResponseBody List<TypeDto> list(){
        return typeService.findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    TypeDto view(@PathVariable String id){
        return typeService.find(id);
    }

    @PostMapping(value = "/add")
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
