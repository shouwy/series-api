package org.tekcorp.api.control

import org.junit.Before
import org.junit.Test
import org.tekcorp.api.domain.dto.EtatDto
import org.tekcorp.api.domain.mapper.EtatMapper
import org.tekcorp.api.domain.mapper.EtatMapperImpl
import org.tekcorp.api.domain.model.EtatModel
import org.tekcorp.api.repository.EtatRepository
import org.tekcorp.api.service.EtatService
import org.tekcorp.api.service.impl.EtatServiceImpl
import spock.lang.Specification

class EtatControllerTest extends Specification {

    EtatController controller
    EtatService etatService
    EtatRepository etatRepository
    EtatMapper etatMapper

    String idOk
    String idKo

    EtatDto etatDto

    @Before
    init() {
        etatRepository = Mock(EtatRepository.class)
        etatMapper = new EtatMapperImpl()
        etatService = new EtatServiceImpl(etatRepository, etatMapper)
        controller = new EtatController(etatService)

        idOk = "12345"
        idKo = "6789"

        EtatModel modelOk = new EtatModel()
        modelOk.setId(idOk)
        modelOk.setEtatName("OK")

        EtatModel modelKo = new EtatModel()
        modelKo.setId(idKo)
        modelKo.setEtatName("KO")

        etatRepository.findAll() >> {
            return Arrays.asList(modelOk, modelKo)
        }

        etatRepository.findById(_ as String) >> {
            String s ->
                if (s == null || s == "")
                    return null
                if (s == idOk)
                    return Optional.of(modelOk)
                if (s == idKo)
                    return Optional.of(modelKo)
                return null
        }

        etatRepository.findByEtatName(_ as String) >> {
            String s ->
                if (s == null || s == "")
                    return null
                if (s == "OK")
                    return modelOk
                if (s == "KO")
                    return modelKo
                return null
        }

        etatRepository.save(_ as EtatModel) >> {
            EtatModel model ->
                model.setId("123456789")
                return model
        }

        etatDto = new EtatDto()
        etatDto.setId(idOk)
        etatDto.setEtatName("OK")
    }

    @Test
    def "List"() {
        when:
        List<EtatDto> etats = controller.list()
        then:
        etats.size() == 2
    }

    @Test
    def "View"() {
        when:
        EtatDto etat = controller.view(idOk)
        then:
        etat.getId() == idOk
        etat.getEtatName() == "OK"
    }

    @Test
    def "Add - Without Exist"() {
        given:
        EtatDto dto = new EtatDto()
        dto.setEtatName("STANFBY")
        when:
        EtatDto etat = controller.add(dto)
        then:
        etat.getId() == "123456789"
        etat.getEtatName() == "STANFBY"
    }

    @Test
    def "Add - With Exist"() {
        when:
        EtatDto etat = controller.add(etatDto)
        then:
        etat.getId() == idOk
    }
}
