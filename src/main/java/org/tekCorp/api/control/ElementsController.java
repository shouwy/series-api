package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Element;
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
        Element oriElement = elementRepository.findByNomAndYear(element.getNom(), element.getYear());

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

    @RequestMapping(value = "/list/type/{idType}", method = RequestMethod.GET)
    public @ResponseBody List<Element> listByType(@PathVariable String idType){
        List<Element> elementList = elementRepository.findByType(idType);
        return elementList;
    }

    @RequestMapping(value = "/list/etat/{idEtat}", method = RequestMethod.GET)
    public @ResponseBody List<Element> listByEtat(@PathVariable String idEtat){
        List<Element> elementList = elementRepository.findByEtat(idEtat);
        return elementList;
    }

    @RequestMapping(value = "/list/etatPersonnal/{idEtat}", method = RequestMethod.GET)
    public @ResponseBody List<Element> listByEtatPersonnal(@PathVariable String idEtat){
        List<Element> elementList = elementRepository.findByEtatPersonal(idEtat);
        return elementList;
    }
}
