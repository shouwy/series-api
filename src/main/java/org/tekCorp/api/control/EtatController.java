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
import org.tekCorp.api.domain.Etat;
import org.tekCorp.api.repository.EtatRepository;

/**
 * Created by FRERES Thierry on 22/02/2016.
 */
@Controller
@RequestMapping("/etat")
public class EtatController {

    private final EtatRepository etatRepository;

    @Autowired
    public EtatController(EtatRepository etatRepository) {
        this.etatRepository = etatRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
        public @ResponseBody List<Etat> list(){
        return etatRepository.findAll();
        }

        @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
        public @ResponseBody Etat view(@PathVariable String id){
            Optional<Etat> etat = etatRepository.findById(id);
            return etat.orElse(null);
        }

        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public @ResponseBody Etat add(@RequestBody Etat etat){
            Etat orietat = etatRepository.findByEtatName(etat.getEtatName());
            if (orietat != null) {
                return orietat;
            }
            etat = etatRepository.save(etat);

            return etat;
        }
}
