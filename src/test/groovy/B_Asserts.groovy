import spock.lang.PendingFeature
import spock.lang.Specification

class B_Asserts extends Specification {

    @PendingFeature
    void "the two lists are equal"() {
        given: "two lists that are not really equal"
        List firstList = ["a", "b", "c"]
        List secondList = ["a", "b", "d"]

        expect: "a failure, but on purpose"
        firstList == secondList
    }

    void "an exception is thrown"() {
        when:
        throwsException()

        then:
        def myException = thrown(IllegalArgumentException)

        and:
        myException.message == "The important message"
    }

    private static void throwsException() {
        throw new IllegalArgumentException("The important message")

    }

    void "checks in closures do not auto-assert"() {
        given:
        Map myMap = [a:1, b:2]

        expect:
        for (i in myMap.values()) {
            myMap.i == 1
        }

        myMap.with {
            a == 2
            b == 2
        }
    }

    @PendingFeature
    void "explicit asserts and Spock with() work"() {
        given:
        Map myMap = [a:1, b:2]

        expect:
        for (i in myMap.values()) {
            assert i == 1
        }

        with(myMap) {
            a == 2
            b == 2
        }
    }

    @PendingFeature
    void "Map operations and Spock with() are even better"() {
        given:
        Map myMap = [a:1, b:2]

        expect:
        myMap.every { k,v -> v == 1 }

        with(myMap) {
            a == 2
            b == 2
        }
    }

    @PendingFeature
    void "and now with better assertion output"() {
        given:
        Map myMap = [a:1, b:2]

        expect:
        myMap.values().every { it == 1 }

        with(myMap) {
            a == 2
            b == 2
        }
    }
}
