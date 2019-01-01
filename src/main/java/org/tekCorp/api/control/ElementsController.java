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
import org.tekCorp.api.domain.model.ElementModel;
import org.tekCorp.api.domain.model.EtatModel;
import org.tekCorp.api.domain.model.TypeModel;
import org.tekCorp.api.repository.ElementRepository;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/elements")
public class ElementsController {

    private final ElementRepository elementRepository;

    @Autowired
    public ElementsController(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ElementModel view(@PathVariable String id){
        Optional<ElementModel> element = elementRepository.findById(id);

        return element.orElse(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ElementModel add(@RequestBody ElementModel elementModel){
        ElementModel oriElementModel = elementRepository.findByTitleAndYear(elementModel.getTitle(), elementModel.getYear());

        if (oriElementModel != null){
            return oriElementModel;
        }

        elementModel = elementRepository.save(elementModel);
        return elementModel;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ElementModel edit(@PathVariable String id){
        Optional<ElementModel> element = elementRepository.findById(id);

        return element.orElse(null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<ElementModel> list(){
        return elementRepository.findAll();
    }

    @RequestMapping(value = "/list/type/", method = RequestMethod.POST)
    public @ResponseBody List<ElementModel> listByType(@RequestBody TypeModel typeModel){
        return elementRepository.findByType(typeModel);
    }

    @RequestMapping(value = "/list/etat/", method = RequestMethod.POST)
    public @ResponseBody List<ElementModel> listByEtat(@RequestBody EtatModel etatModel){
        return elementRepository.findByEtat(etatModel);
    }

    /*
    @RequestMapping(value = "/list/etatPersonnal/", method = RequestMethod.POST)
    public @ResponseBody List<ElementDto> listByEtatPersonnal(@RequestBody EtatPersonnelDto etatPersonnel){
        List<ElementDto> elementList = elementRepository.findByEtatPersonal(etatPersonnel);
        return elementList;
    }
    */
}
