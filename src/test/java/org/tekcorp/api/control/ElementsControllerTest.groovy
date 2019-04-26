package org.tekcorp.api.control

import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test
import org.tekcorp.api.domain.dto.ElementDto
import org.tekcorp.api.domain.dto.EtatDto
import org.tekcorp.api.domain.dto.EtatPersonnelDto
import org.tekcorp.api.domain.dto.TypeDto
import org.tekcorp.api.domain.mapper.*
import org.tekcorp.api.domain.model.ElementModel
import org.tekcorp.api.domain.model.EtatModel
import org.tekcorp.api.domain.model.TypeModel
import org.tekcorp.api.repository.ElementRepository
import org.tekcorp.api.service.ElementService
import org.tekcorp.api.service.impl.ElementServiceImpl
import spock.lang.Specification

class ElementsControllerTest extends Specification {

    ElementService elementService
    ElementsController controller
    ElementMapper elementMapper
    ElementRepository elementRepository
    TypeMapper typeMapper
    EtatMapper etatMapper

    String idOk
    String idKo

    ElementModel modelOk
    ElementModel modelKo
    ElementDto elementDto
    EtatPersonnelDto etatPers

    @Before
    init() {
        elementMapper = new ElementMapperImpl()
        typeMapper = new TypeMapperImpl()
        etatMapper = new EtatMapperImpl()
        elementMapper.typeMapper = typeMapper
        elementMapper.etatMapper = etatMapper
        elementRepository = Mock(ElementRepository.class)

        elementService = new ElementServiceImpl(elementRepository, elementMapper, typeMapper, etatMapper)
        controller = new ElementsController(elementService)

        idOk = "12345"
        idKo = "6789"
        etatPers = new EtatPersonnelDto()

        EtatModel etatModelok = new EtatModel()
        etatModelok.setEtatName("OK")

        EtatModel etatModelko = new EtatModel()
        etatModelko.setEtatName("KO")

        TypeModel typeBook = new TypeModel()
        typeBook.setTypeName("Book")
        typeBook.setEtatList(Arrays.asList("OK"))

        TypeModel typeMovie = new TypeModel()
        typeMovie.setTypeName("Movie")
        typeMovie.setEtatList(Arrays.asList("OK"))

        modelOk = new ElementModel()
        modelOk.setId(idOk)
        modelOk.setTitle("Test Ok")
        modelOk.setYear(2019)
        modelOk.setEtat(etatModelok)
        modelOk.setType(typeMovie)

        modelKo = new ElementModel()
        modelKo.setId(idKo)
        modelKo.setTitle("Test KO")
        modelKo.setYear(2018)
        modelKo.setEtat(etatModelko)
        modelKo.setType(typeBook)

        elementRepository.findById(_ as String) >> {
            String s ->
                if (StringUtils.isEmpty(s))
                    return null
                if (s == idOk)
                    return Optional.of(modelOk)
                if (s == idKo)
                    return Optional.of(modelKo)
                return null
        }

        elementRepository.save(_ as ElementModel) >> {
            ElementModel model ->
                model.setId("123456789")
                return model
        }

        elementRepository.findAll() >> {
            return Arrays.asList(modelOk, modelKo)
        }

        elementRepository.findByEtatModel(_ as EtatModel) >> {
            EtatModel model ->
                if (model.getEtatName() == "OK")
                    return Arrays.asList(modelOk)
                if (model.getEtatName() == "KO")
                    return Arrays.asList(modelKo)
                return new ArrayList<>()
        }

        elementRepository.findByTypeModel(_ as TypeModel) >> {
            TypeModel model ->
                if (model.getTypeName() == "Book")
                    return Arrays.asList(modelKo)
                if (model.getTypeName() == "Movie")
                    return Arrays.asList(modelOk)
                return new ArrayList<>()
        }

        elementRepository.findByTitleAndYear(_ as String, _ as Integer) >> {
            String s, Integer i ->
                if (StringUtils.isEmpty(s) || i == null)
                    return null
                if (s == "Test" && i == 2019)
                    return modelOk
                return null
        }

        elementDto = new ElementDto()
        elementDto.setTitle("Test")
        elementDto.setYear(2019)
    }

    @Test
    def "View"() {
        when:
        ElementDto elementDto = controller.view(idOk)
        then:
        elementDto.getId() == idOk
    }

    @Test
    def "Add - Without Exist"() {
        given:
        ElementDto model = new ElementDto()
        model.setTitle("new")
        model.setYear(2010)
        when:
        ElementDto dto = controller.add(model)
        then:
        dto.getId() == "123456789"
    }

    @Test
    def "Add - With Exist"() {
        when:
        ElementDto dto = controller.add(elementDto)
        then:
        dto.getId() == idOk
    }

    @Test
    def "List"() {
        when:
        List<ElementDto> list = controller.list()
        then:
        list.size() == 2
    }

    @Test
    def "ListByType"() {
        given:
        etatPers.setEtatPersName("OK")
        TypeDto type = new TypeDto()
        type.setTypeName("Book")
        type.setEtatList(Arrays.asList(etatPers))
        when:
        List<ElementDto> list = controller.listByType(type)
        then:
        list.size() == 1
        list.get(0).getType().getTypeName() == type.getTypeName()
    }

    @Test
    def "ListByEtat"() {
        given:
        EtatDto etat = new EtatDto()
        etat.setEtatName("OK")
        when:
        List<ElementDto> list = controller.listByEtat(etat)
        then:
        list.size() == 1
        list.get(0).getEtat().getEtatName() == etat.getEtatName()
    }
}
