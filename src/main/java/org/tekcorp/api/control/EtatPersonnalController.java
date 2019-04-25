package org.tekcorp.api.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.domain.dto.EtatPersonnelDto;
import org.tekcorp.api.service.EtatPersonalService;
import org.tekcorp.api.service.impl.EtatPersonalServiceImpl;

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

    @GetMapping(value = "/list")
    public @ResponseBody List<EtatPersonnelDto> list(){
        return etatPersonalService.findAll();
    }

    @GetMapping(value = "/view/{id}")
    public @ResponseBody
    EtatPersonnelDto view(@PathVariable String id){
        return etatPersonalService.find(id);
    }

    @PostMapping(value = "/add")
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
