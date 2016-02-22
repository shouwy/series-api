package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Element;
import org.tekCorp.api.domain.Etat;
import org.tekCorp.api.domain.EtatPersonnel;
import org.tekCorp.api.domain.Type;
import org.tekCorp.api.repository.ElementRepository;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/elements")
public class ElementsController {

    @Autowired ElementRepository elementRepository;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody Element view(@PathVariable String id){
        Element element = elementRepository.findOne(id);
        return element;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Element add(@RequestBody Element element){
        Element oriElement = elementRepository.findByNameAndYear(element.getName(), element.getYear());

        if (oriElement != null){
            return oriElement;
        }

        element = elementRepository.save(element);
        return element;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public @ResponseBody Element edit(@PathVariable String id){
        Element element = elementRepository.findOne(id);
        return element;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Element> list(){
        List<Element> elementList = elementRepository.findAll();
        return elementList;
    }

    @RequestMapping(value = "/list/type/", method = RequestMethod.POST)
    public @ResponseBody List<Element> listByType(@RequestBody Type type){
        List<Element> elementList = elementRepository.findByType(type);
        return elementList;
    }

    @RequestMapping(value = "/list/etat/", method = RequestMethod.POST)
    public @ResponseBody List<Element> listByEtat(@RequestBody Etat etat){
        List<Element> elementList = elementRepository.findByEtat(etat);
        return elementList;
    }

    @RequestMapping(value = "/list/etatPersonnal/", method = RequestMethod.POST)
    public @ResponseBody List<Element> listByEtatPersonnal(@RequestBody EtatPersonnel etatPersonnel){
        List<Element> elementList = elementRepository.findByEtatPersonal(etatPersonnel);
        return elementList;
    }
}
