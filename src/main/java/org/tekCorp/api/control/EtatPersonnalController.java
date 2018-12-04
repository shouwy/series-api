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
import org.tekCorp.api.domain.EtatPersonnel;
import org.tekCorp.api.repository.EtatPersonnelRepository;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/etatPersonal")
public class EtatPersonnalController {

    private final EtatPersonnelRepository etatPersonnelRepository;

    @Autowired
    public EtatPersonnalController(EtatPersonnelRepository etatPersonnelRepository) {
        this.etatPersonnelRepository = etatPersonnelRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<EtatPersonnel> list(){
        return etatPersonnelRepository.findAll();
    }

    /*
    @RequestMapping(value = "/list/type/", method = RequestMethod.GET)
    public @ResponseBody List<EtatPersonnel> listByType(@RequestBody Type type){
        List<EtatPersonnel> etatList = etatPersonnelRepository.findByType(type);
        return etatList;
    }
    */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody EtatPersonnel view(@PathVariable String id){
        Optional<EtatPersonnel> etatPersonnel = etatPersonnelRepository.findById(id);

        return etatPersonnel.orElse(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody EtatPersonnel add(@RequestBody EtatPersonnel etatPersonnel){
        EtatPersonnel oriEtatPersonnal = etatPersonnelRepository.findByEtatPersName(etatPersonnel.getEtatPersName());
        if (oriEtatPersonnal != null) {
            return oriEtatPersonnal;
        }

        etatPersonnel = etatPersonnelRepository.save(etatPersonnel);

        return etatPersonnel;
    }
}
