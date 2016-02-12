package org.tekCorp.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tekCorp.api.domain.Etat;
import org.tekCorp.api.repository.EtatRepository;
import org.tekCorp.api.response.JsonResponse;

import java.util.List;

/**
 * Created by FRERES Thierry on 10/02/2016.
 */
@Controller
@RequestMapping("/etat")
public class EtatController {

    @Autowired EtatRepository etatRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody JsonResponse list(){
        List<Etat> etatList = etatRepository.findAll();
        return new JsonResponse(200, "Etat List", etatList);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public @ResponseBody JsonResponse view(@PathVariable String id){
        Etat etat = etatRepository.findOne(id);
        JsonResponse jsonResponse = new JsonResponse(200, "Find Etat", etat);

        if (etat == null){
            jsonResponse = new JsonResponse(203, "Etat Not Found");
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody JsonResponse add(@RequestBody Etat etat){
        Etat oriEtat = etatRepository.findByNom(etat.getNom());
        if (oriEtat != null) {
            return new JsonResponse(203, "Etat Allreasy Exists");
        }
        etat = etatRepository.save(etat);

        return new JsonResponse(200, "Insert OK", etat);
    }
}
