package org.tekcorp.api.control

import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test
import org.tekcorp.api.domain.dto.EtatPersonnelDto
import org.tekcorp.api.domain.dto.TypeDto
import org.tekcorp.api.domain.mapper.EtatPersonalMapper
import org.tekcorp.api.domain.mapper.EtatPersonalMapperImpl
import org.tekcorp.api.domain.mapper.TypeMapper
import org.tekcorp.api.domain.mapper.TypeMapperImpl
import org.tekcorp.api.domain.model.TypeModel
import org.tekcorp.api.repository.TypeRepository
import org.tekcorp.api.service.TypeService
import org.tekcorp.api.service.impl.TypeServiceImpl
import spock.lang.Specification

class TypeControllerTest extends Specification {

    TypeController controller
    TypeService typeService
    TypeRepository typeRepository
    TypeMapper typeMapper
    EtatPersonalMapper etatMapper

    String idOk
    String idKo

    TypeDto typeDto

    @Before
    init() {
        etatMapper = new EtatPersonalMapperImpl()
        typeRepository = Mock(TypeRepository.class)
        typeMapper = new TypeMapperImpl()
        typeService = new TypeServiceImpl(typeRepository, typeMapper)
        controller = new TypeController(typeService)

        idOk = "12345"
        idKo = "67890"

        TypeModel typeBook = new TypeModel()
        typeBook.setId(idOk)
        typeBook.setTypeName("Book")
        typeBook.setEtatList(Arrays.asList("OK"))

        TypeModel typeMovie = new TypeModel()
        typeMovie.setId(idKo)
        typeMovie.setTypeName("Movie")
        typeMovie.setEtatList(Arrays.asList("OK"))

        typeRepository.findAll() >> {
            return Arrays.asList(typeBook, typeMovie)
        }

        typeRepository.findById(_ as String) >> {
            String s ->
                if (StringUtils.isEmpty(s))
                    return null
                if (s == idOk)
                    return Optional.of(typeBook)
                if (s == idKo)
                    return Optional.of(typeMovie)
                return null
        }

        typeRepository.findByTypeName(_ as String) >> {
            String s ->
                if (StringUtils.isEmpty(s))
                    return null
                if (s == "Book")
                    return typeBook
                if (s == "Movie")
                    return typeMovie
                return null
        }

        typeRepository.save(_ as TypeModel) >> {
            TypeModel model ->
                model.setId("123456789")
                return model
        }

        typeDto = new TypeDto()
        typeDto.setId(idOk)
        typeDto.setTypeName("Book")
        EtatPersonnelDto etatPersonnelDto = new EtatPersonnelDto()
        etatPersonnelDto.setEtatPersName("OK")
        typeDto.setEtatList(Arrays.asList(etatPersonnelDto))
    }

    @Test
    def "List"() {
        when:
        List<TypeDto> list = controller.list()
        then:
        list.size() == 2
    }

    @Test
    def "View"() {
        when:
        TypeDto dto = controller.view(idOk)
        then:
        dto.getId() == idOk
    }

    @Test
    def "Add - With Exist"() {
        when:
        TypeDto dto = controller.add(typeDto)
        then:
        dto.getTypeName() == typeDto.getTypeName()
    }

    @Test
    def "Add - Without Exist"() {
        given:
        TypeDto model = new TypeDto()
        model.setTypeName("Series")
        EtatPersonnelDto etatPersonnelDto = new EtatPersonnelDto()
        etatPersonnelDto.setEtatPersName("OK")
        model.setEtatList(Arrays.asList(etatPersonnelDto))
        when:
        TypeDto dto = controller.add(model)
        then:
        dto.getId() == "123456789"
        dto.getTypeName() == model.getTypeName()
    }
}
