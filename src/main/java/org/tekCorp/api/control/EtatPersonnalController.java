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
import org.tekCorp.api.domain.dto.EtatPersonnelDto;
import org.tekCorp.api.domain.model.EtatPersonnelModel;
import org.tekCorp.api.repository.EtatPersonnelRepository;
import org.tekCorp.api.service.EtatPersonalService;
import org.tekCorp.api.service.impl.EtatPersonalServiceImpl;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/etatPersonal")
public class EtatPersonnalController {

    private final EtatPersonalService etatPersonalService;

    @Autowired
    public EtatPersonnalController(EtatPersonalServiceImpl etatPersonalService) {
        this.etatPersonalService = etatPersonalService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<EtatPersonnelDto> list(){
        return etatPersonalService.findAll();
    }

    /*
    @RequestMapping(value = "/list/type/", method = RequestMethod.GET)
    public @ResponseBody List<EtatPersonnelDto> listByType(@RequestBody TypeDto type){
        List<EtatPersonnelDto> etatList = etatPersonnelRepository.findByType(type);
        return etatList;
    }
    */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody
    EtatPersonnelDto view(@PathVariable String id){
        return etatPersonalService.find(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    EtatPersonnelDto add(@RequestBody EtatPersonnelDto etatPersonnelModel){
        EtatPersonnelDto oriEtatPersonnal = etatPersonalService.find(etatPersonnelModel);
        if (oriEtatPersonnal != null) {
            return oriEtatPersonnal;
        }

        etatPersonnelModel = etatPersonalService.save(etatPersonnelModel);

        return etatPersonnelModel;
    }
}
