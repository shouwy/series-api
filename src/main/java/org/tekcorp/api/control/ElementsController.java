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
import org.tekcorp.api.domain.dto.ElementDto;
import org.tekcorp.api.domain.dto.EtatDto;
import org.tekcorp.api.domain.dto.TypeDto;
import org.tekcorp.api.service.ElementService;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/element")
public class ElementsController {

    
    private ElementService elementService;
    
    @Autowired
    public ElementsController(ElementService elementService) {
        this.elementService = elementService;
    }

    @GetMapping(value = "/view/{id}")
    public @ResponseBody
    ElementDto view(@PathVariable String id){
        return elementService.find(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody
    ElementDto add(@RequestBody ElementDto elementModel){
        ElementDto oriElementDto = elementService.findByTitleAndYear(elementModel);

        if (oriElementDto != null){
            return oriElementDto;
        }

        elementModel = elementService.save(elementModel);
        return elementModel;
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<ElementDto> list(){
        return elementService.findAll();
    }

    @PostMapping(value = "/list/type/")
    public @ResponseBody
    List<ElementDto> listByType(@RequestBody TypeDto typeModel) {
        return elementService.findByTypeModel(typeModel);
    }

    @PostMapping(value = "/list/etat/")
    public @ResponseBody
    List<ElementDto> listByEtat(@RequestBody EtatDto etatModel) {
        return elementService.findByEtatModel(etatModel);
    }
}
