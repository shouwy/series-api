package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Etat;
import org.tekCorp.api.repository.EtatRepository;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/etat")
public class EtatController {

    @Autowired EtatRepository etatRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Etat> list(){
        List<Etat> etatList = etatRepository.findAll();
        return etatList;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody Etat view(@PathVariable String id){
        Etat etat = etatRepository.findOne(id);
        return etat;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Etat add(@RequestBody Etat etat){
        Etat oriEtat = etatRepository.findByName(etat.getName());
        if (oriEtat != null) {
            return oriEtat;
        }
        etat = etatRepository.save(etat);

        return etat;
    }
}
