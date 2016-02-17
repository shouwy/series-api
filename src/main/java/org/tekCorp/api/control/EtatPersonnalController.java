package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.EtatPersonnel;
import org.tekCorp.api.repository.EtatPersonnelRepository;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/etatPersonal")
public class EtatPersonnalController {

    @Autowired EtatPersonnelRepository etatPersonnelRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<EtatPersonnel> list(){
        List<EtatPersonnel> etatList = etatPersonnelRepository.findAll();
        return etatList;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody EtatPersonnel view(@PathVariable String id){
        EtatPersonnel etatPersonnel = etatPersonnelRepository.findOne(id);
        return etatPersonnel;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody EtatPersonnel add(@RequestBody EtatPersonnel etatPersonnel){
        EtatPersonnel oriEtatPersonnal = etatPersonnelRepository.findByNomAndIdType(etatPersonnel.getNom(), etatPersonnel.getIdType());
        if (oriEtatPersonnal != null) {
            return oriEtatPersonnal;
        }

        etatPersonnel = etatPersonnelRepository.save(etatPersonnel);

        return etatPersonnel;
    }
}
