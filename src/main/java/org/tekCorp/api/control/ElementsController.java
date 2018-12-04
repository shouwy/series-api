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
import org.tekCorp.api.domain.Element;
import org.tekCorp.api.domain.Etat;
import org.tekCorp.api.domain.Type;
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
    public @ResponseBody Element view(@PathVariable String id){
        Optional<Element> element = elementRepository.findById(id);

        return element.orElse(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Element add(@RequestBody Element element){
        Element oriElement = elementRepository.findByTitleAndYear(element.getTitle(), element.getYear());

        if (oriElement != null){
            return oriElement;
        }

        element = elementRepository.save(element);
        return element;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public @ResponseBody Element edit(@PathVariable String id){
        Optional<Element> element = elementRepository.findById(id);

        return element.orElse(null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Element> list(){
        return elementRepository.findAll();
    }

    @RequestMapping(value = "/list/type/", method = RequestMethod.POST)
    public @ResponseBody List<Element> listByType(@RequestBody Type type){
        return elementRepository.findByType(type);
    }

    @RequestMapping(value = "/list/etat/", method = RequestMethod.POST)
    public @ResponseBody List<Element> listByEtat(@RequestBody Etat etat){
        return elementRepository.findByEtat(etat);
    }

    /*
    @RequestMapping(value = "/list/etatPersonnal/", method = RequestMethod.POST)
    public @ResponseBody List<Element> listByEtatPersonnal(@RequestBody EtatPersonnel etatPersonnel){
        List<Element> elementList = elementRepository.findByEtatPersonal(etatPersonnel);
        return elementList;
    }
    */
}
