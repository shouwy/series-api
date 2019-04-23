package org.tekcorp.api.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tekcorp.api.domain.dto.EtatDto;
import org.tekcorp.api.service.EtatService;
import org.tekcorp.api.service.impl.EtatServiceImpl;

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
