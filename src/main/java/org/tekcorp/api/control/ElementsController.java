package org.tekcorp.api.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.domain.dto.ElementDto;
import org.tekcorp.api.domain.model.EtatModel;
import org.tekcorp.api.domain.model.TypeModel;
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

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ElementDto view(@PathVariable String id){
        return elementService.find(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ElementDto add(@RequestBody ElementDto elementModel){
        ElementDto oriElementDto = elementService.findByTitleAndYear(elementModel);

        if (oriElementDto != null){
            return oriElementDto;
        }

        elementModel = elementService.save(elementModel);
        return elementModel;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<ElementDto> list(){
        return elementService.findAll();
    }

    @RequestMapping(value = "/list/type/", method = RequestMethod.POST)
    public @ResponseBody List<ElementDto> listByType(@RequestBody TypeModel typeModel){
        return elementService.findByTypeModel(typeModel);
    }

    @RequestMapping(value = "/list/etat/", method = RequestMethod.POST)
    public @ResponseBody List<ElementDto> listByEtat(@RequestBody EtatModel etatModel){
        return elementService.findByEtatModel(etatModel);
    }

    /*
    @RequestMapping(value = "/list/etatPersonnal/", method = RequestMethod.POST)
    public @ResponseBody List<ElementDto> listByEtatPersonnal(@RequestBody EtatPersonnelDto etatPersonnel){
        List<ElementDto> elementList = elementService.findByEtatPersonal(etatPersonnel);
        return elementList;
    }
    */
}
