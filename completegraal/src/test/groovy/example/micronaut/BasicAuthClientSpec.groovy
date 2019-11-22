package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class BasicAuthClientSpec extends Specification {

    @Shared
    @AutoCleanup // <1>
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer) // <2>

    def "Verify HTTP Basic Auth works"() {
        when:
        AppClient appClient = embeddedServer.applicationContext.getBean(AppClient) // <3>

        then:
        noExceptionThrown() // <4>

        when:
        String credsEncoded = "sherlock:password".bytes.encodeBase64().toString()
        String rsp = appClient.home("Basic ${credsEncoded}") // <5>

        then:
        rsp == 'sherlock'
    }
}
