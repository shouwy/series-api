package org.tekcorp.api.domain

import groovy.util.logging.Slf4j
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.RegexPatternTypeFilter

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.regex.Pattern

@Slf4j
class AccessorTest {

    /** Suffixe DTO. */
    private static final List<String> SUFFIXE_ACC = Arrays.asList("Model", "Dto")

    /** Préfixe get des accessors. */
    private static final String PREFIXE_GET = "get"

    /** Préfixe is des accessors. */
    private static final String PREFIXE_IS = "is"

    /** Préfixe set des accessors. */
    private static final String PREFIXE_SET = "set"

    /** BLACKLIST de DTO. */
    private static final Set<String> BLACKLIST = new HashSet<String>(Arrays.asList(""))

    /**
     * Vérification des accessors d'une classe.
     *
     * @param obj
     * @return résultat
     */
    private static boolean checkAccessor(Object obj) {
        if (obj != null) {
            Class<? extends Object> classz = obj.getClass()
            String methodeName = null
            //Reverse (equals en last)
            Method[] methodArray = classz.getMethods()
            for (Method methode : methodArray) {
                try {
                    // pour chaque méthode de classe
                    // on va vérifier les getter et setter

                    Class<?>[] paramTypes = methode.getParameterTypes()
                    methodeName = methode.getName()

                    if (methodeName.startsWith("toString") && paramTypes.length == 0) {
                        methode.invoke(obj)
                    }

                    if (methodeName.startsWith("hashCode") && paramTypes.length == 0) {
                        methode.invoke(obj)
                    }

                    if (methodeName.startsWith("equals") && paramTypes.length == 1) {
                        methode.invoke(obj, obj)

                        Object otherObj = classz.newInstance()
                        methode.invoke(obj, otherObj)
                    }

                    if ((methodeName.startsWith(PREFIXE_GET) || methodeName.startsWith(PREFIXE_IS))
                            && paramTypes.length == 0) {
                        // getter
                        methode.invoke(obj)
                    }
                    if (methodeName.startsWith(PREFIXE_SET) && paramTypes.length == 1) {
                        // verification sur le type de paramètre
                        Class<?> paramType = paramTypes[0]

                        if (boolean.class.getName() == (paramType.getName())
                                || Boolean.class.getName() == (paramType.getName())) {
                            // booleen
                            methode.invoke(obj, false)

                        } else if (long.class.getName() == (paramType.getName())
                                || Long.class.getName() == (paramType.getName())) {
                            // long
                            methode.invoke(obj, 0L)

                        } else if (int.class.getName() == (paramType.getName())
                                || Integer.class.getName() == (paramType.getName())) {
                            // integer
                            methode.invoke(obj, 0)

                        } else if (String.class.getName() == (paramType.getName())) {
                            // string
                            methode.invoke(obj, "")

                        } else if (BigDecimal.class.getName() == (paramType.getName())) {
                            // decimale
                            methode.invoke(obj, new BigDecimal("10.01"))

                        } else if (double.class.getName() == (paramType.getName())
                                || Double.class.getName() == (paramType.getName())) {
                            // double
                            methode.invoke(obj, Double.MAX_VALUE)

                        } else if (Date.class.getName() == (paramType.getName())) {
                            // date
                            methode.invoke(obj, Calendar.getInstance().getTime())

                        } else {
                            // type de paramètre trop spécifique
                            // new Object[1] = tableau contenant null
                            methode.invoke(obj, new Object[1])
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                    log.error("Erreur lors du test de la méthode {}.{}", classz.getSimpleName(), methodeName)
                    log.error(exception.getMessage(), exception)
                    return false
                }
            }
        }
        return true
    }

    void computePojo(Set<BeanDefinition> classes) {
        Object pojo
        classes.each { BeanDefinition bean ->
            if (BLACKLIST.contains(bean.getBeanClassName())) {
                log.warn("TODO nettoyage enum {}", bean.getBeanClassName())
            } else {
                SUFFIXE_ACC.each {
                    String suffix ->
                        if (bean.getBeanClassName().endsWith(suffix)) {
                            log.debug(bean.getBeanClassName())
                            pojo = Class.forName(bean.getBeanClassName()).newInstance()
                            Assert.assertTrue("checkAccessor failed for " + bean.getBeanClassName(), this.checkAccessor(pojo))
                        }
                }
            }
        }
    }

    /**
     * Test de POJO.
     *
     * @throws IOException* @throws ClassNotFoundException* @throws InstantiationException* @throws IllegalAccessException
     */
    @Test
    void testPojo() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // create scanner and disable default filters (that is the 'false' argument)
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false)
        // add include filters which matches all the classes (or use your own)
        Pattern pattern = Pattern.compile(".*")
        provider.addIncludeFilter(new RegexPatternTypeFilter(pattern))

        // get matching classes defined in the Model package
        computePojo(provider.findCandidateComponents("org..tekcorp.api.domain.model"))
        // get matching classes defined in the Dto package
        computePojo(provider.findCandidateComponents("org..tekcorp.api.domain.dto"))
    }
}
