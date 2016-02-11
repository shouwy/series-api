package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.EtatPersonnel;
import org.tekCorp.api.repository.EtatPersonnelRepository;
import org.tekCorp.api.response.JsonResponse;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/etatPersonnal")
public class EtatPersonnalController {

    @Autowired EtatPersonnelRepository etatPersonnelRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResponse list(){
        List<EtatPersonnel> etatList = etatPersonnelRepository.findAll();
        return new JsonResponse(200, "Etat List", etatList);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public JsonResponse view(@PathVariable String id){
        EtatPersonnel etatPersonnel = etatPersonnelRepository.findOne(id);
        JsonResponse jsonResponse = new JsonResponse(200, "Find EtatPersonnal", etatPersonnel);

        if (etatPersonnel == null) {
            jsonResponse = new JsonResponse(203, "EtatPersonnal Not Found");
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody JsonResponse add(@RequestBody EtatPersonnel etatPersonnel){
        EtatPersonnel oriEtatPersonnal = etatPersonnelRepository.findByNom(etatPersonnel.getNom());
        if (oriEtatPersonnal != null) {
            return new JsonResponse(203, "EtatPersonnal Allresdy Exists");
        }

        etatPersonnel = etatPersonnelRepository.save(etatPersonnel);

        return new JsonResponse(200, "Insert OK", etatPersonnel);
    }
}
