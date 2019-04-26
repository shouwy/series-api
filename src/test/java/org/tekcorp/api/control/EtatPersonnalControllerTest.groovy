package org.tekcorp.api.control

import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test
import org.tekcorp.api.domain.dto.EtatPersonnelDto
import org.tekcorp.api.domain.mapper.EtatPersonalMapper
import org.tekcorp.api.domain.mapper.EtatPersonalMapperImpl
import org.tekcorp.api.domain.model.EtatPersonnelModel
import org.tekcorp.api.repository.EtatPersonnelRepository
import org.tekcorp.api.service.EtatPersonalService
import org.tekcorp.api.service.impl.EtatPersonalServiceImpl
import spock.lang.Specification

class EtatPersonnalControllerTest extends Specification {

    EtatPersonnalController controller
    EtatPersonnelRepository etatPersonnelRepository
    EtatPersonalMapper etatPersonalMapper
    EtatPersonalService etatPersonalService

    String idOk
    String idKo

    EtatPersonnelDto etatPersonnelDto

    @Before
    init() {
        etatPersonnelRepository = Mock(EtatPersonnelRepository.class)
        etatPersonalMapper = new EtatPersonalMapperImpl()
        etatPersonalService = new EtatPersonalServiceImpl(etatPersonnelRepository, etatPersonalMapper)

        controller = new EtatPersonnalController(etatPersonalService)

        idOk = "12345"
        idKo = "67890"

        EtatPersonnelModel modelOk = new EtatPersonnelModel()
        modelOk.setId(idOk)
        modelOk.setEtatPersName("OK")

        EtatPersonnelModel modelKo = new EtatPersonnelModel()
        modelKo.setId(idKo)
        modelKo.setEtatPersName("KO")

        etatPersonnelRepository.findAll() >> {
            Arrays.asList(modelOk, modelKo)
        }

        etatPersonnelRepository.findById(_ as String) >> {
            String s ->
                if (StringUtils.isEmpty(s))
                    return null
                if (s == idOk)
                    return Optional.of(modelOk)
                if (s == idKo)
                    return Optional.of(modelKo)
                return null
        }

        etatPersonnelRepository.findByEtatPersName(_ as String) >> {
            String s ->
                if (StringUtils.isEmpty(s))
                    return null
                if (s == "OK")
                    return modelOk
                if (s == "KO")
                    return modelKo
                return null
        }

        etatPersonnelRepository.save(_ as EtatPersonnelModel) >> {
            EtatPersonnelModel model ->
                model.setId("123456789")
                return model
        }

        etatPersonnelDto = new EtatPersonnelDto()
        etatPersonnelDto.setId(idOk)
        etatPersonnelDto.setEtatPersName("OK")
    }

    @Test
    def "List"() {
        when:
        List<EtatPersonnelDto> list = controller.list()
        then:
        list.size() == 2
    }

    def "View"() {
        when:
        EtatPersonnelDto dto = controller.view(idOk)
        then:
        dto.getEtatPersName() == "OK"
    }

    def "Add - With Exist"() {
        when:
        EtatPersonnelDto dto = controller.add(etatPersonnelDto)
        then:
        dto.getEtatPersName() == etatPersonnelDto.getEtatPersName()
    }

    def "Add - Without Exist"() {
        given:
        EtatPersonnelDto model = new EtatPersonnelDto()
        model.setEtatPersName("STANDBY")
        when:
        EtatPersonnelDto dto = controller.add(model)
        then:
        dto.getId() == "123456789"
        dto.getEtatPersName() == "STANDBY"
    }
}
