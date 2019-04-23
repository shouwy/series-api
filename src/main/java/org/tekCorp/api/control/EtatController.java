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
import org.tekCorp.api.domain.dto.EtatDto;
import org.tekCorp.api.domain.model.EtatModel;
import org.tekCorp.api.repository.EtatRepository;
import org.tekCorp.api.service.EtatService;
import org.tekCorp.api.service.impl.EtatServiceImpl;

/**
 * Created by FRERES Thierry on 22/02/2016.
 */
@Controller
@RequestMapping("/etat")
public class EtatController {


    private final EtatService etatService;

    @Autowired
    public EtatController(EtatServiceImpl etatService) {
        this.etatService = etatService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
        public @ResponseBody List<EtatDto> list(){
        return etatService.findAll();
        }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    EtatDto view(@PathVariable String id){
        return etatService.find(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    EtatDto add(@RequestBody EtatDto etatDto){
        EtatDto orietat = etatService.find(etatDto);
        if (orietat != null) {
            return orietat;
        }
        etatDto = etatService.save(etatDto);

        return etatDto;
    }
}
