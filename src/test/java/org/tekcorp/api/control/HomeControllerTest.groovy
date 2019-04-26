package org.tekcorp.api.control

import org.apache.commons.lang3.RandomStringUtils
import org.junit.Before
import org.junit.Test
import org.tekcorp.api.domain.dto.ElementDto
import org.tekcorp.api.domain.mapper.*
import org.tekcorp.api.domain.model.ElementModel
import org.tekcorp.api.domain.model.EtatModel
import org.tekcorp.api.domain.model.TypeModel
import org.tekcorp.api.repository.ElementRepository
import org.tekcorp.api.repository.TypeRepository
import spock.lang.Specification

class HomeControllerTest extends Specification {

    HomeController controller
    TypeRepository typeRepository
    ElementRepository elementRepository
    ElementMapper elementMapper
    TypeMapper typeMapper
    EtatMapper etatMapper

    @Before
    init() {
        typeRepository = Mock(TypeRepository.class)
        elementRepository = Mock(ElementRepository.class)
        elementMapper = new ElementMapperImpl()
        typeMapper = new TypeMapperImpl()
        etatMapper = new EtatMapperImpl()
        elementMapper.typeMapper = typeMapper
        elementMapper.etatMapper = etatMapper

        controller = new HomeController(typeRepository, elementRepository, elementMapper)

        TypeModel typeBook = new TypeModel()
        typeBook.setTypeName("Book")
        typeBook.setEtatList(Arrays.asList("OK"))

        TypeModel typeMovie = new TypeModel()
        typeMovie.setTypeName("Movie")
        typeMovie.setEtatList(Arrays.asList("OK"))

        EtatModel etatModelok = new EtatModel()
        etatModelok.setEtatName("OK")

        EtatModel etatModelko = new EtatModel()
        etatModelko.setEtatName("KO")

        typeRepository.findAll() >> {
            return Arrays.asList(typeBook, typeMovie)
        }

        elementRepository.findAll() >> {
            List<ElementModel> list = new ArrayList<>()

            for (Integer i = 0; i <= 30; i++) {
                ElementModel model = new ElementModel()
                model.setId(RandomStringUtils.randomAlphabetic(10))
                model.setTitle("Test Ok")
                model.setYear(2019)
                if (i % 2) {
                    model.setEtat(etatModelok)
                } else {
                    model.setEtat(etatModelko)
                }

                if (i % 3) {
                    model.setType(typeMovie)
                } else {
                    model.setType(typeBook)
                }

                list.add(model)
            }

            return list
        }

    }

    @Test
    def "Home"() {
        when:
        List<ElementDto> elements = controller.home()
        then:
        elements.size() == 2
    }
}
