package net.samitkumar.person.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@Slf4j
public class PersonRouter {

    @Bean
    RouterFunction<ServerResponse> routes(PersonHandler personHandler, AddressHandler addressHandler, ContactInfoHandler contactInfoHandler, SocialMediaHandler socialMediaHandler) {
        return RouterFunctions
                .route()
                .before(request -> {
                    log.info("REQUEST {} {}", request.method(), request.path());
                    return request;
                })
                .path("/persons", personBuilder -> personBuilder
                        .GET("", personHandler::getAllPersons)
                        .POST("", accept(MediaType.APPLICATION_JSON), personHandler::createPerson)
                        .GET("/{personId}", personHandler::getPerson)
                        .PUT("/{personId}", personHandler::updatePerson)
                        .PATCH("/{personId}", personHandler::partialUpdatePerson)
                        .DELETE("/{personId}", personHandler::deletePerson)
                        .path("/{personId}/addresses", addressBuilder -> addressBuilder
                                .GET("", addressHandler::getPersonAddress)
                                .POST("", addressHandler::createAddress)
                                .GET("/{addressId}", addressHandler::getAddressById)
                                .PUT("/{addressId}", addressHandler::updateAddress)
                                .PATCH("/{addressId}", addressHandler::partialUpdateAddress)
                                .DELETE("/{addressId}", addressHandler::deleteAddress)
                        )
                        .path("/{personId}/contact-info", contactInfoBuilder -> contactInfoBuilder
                                .GET("", contactInfoHandler::getPersonContactInfo)
                                .POST("", contactInfoHandler::createContactInfo)
                                .GET("/{contactInfoId}", contactInfoHandler::getContactInfo)
                                .PUT("/{contactInfoId}", contactInfoHandler::updateContactInfo)
                                .PATCH("/{contactInfoId}", contactInfoHandler::partialUpdateContactInfo)
                                .DELETE("/{contactInfoId}", contactInfoHandler::deleteContactInfo)
                        )
                        .path("/{personId}/social-media", socialMediaBuilder -> socialMediaBuilder
                                .GET("", request -> ServerResponse.noContent().build())
                                .POST("", request -> ServerResponse.noContent().build())
                                .GET("/{socialMediaId}", request -> ServerResponse.noContent().build())
                                .PUT("/{socialMediaId}", request -> ServerResponse.noContent().build())
                                .PATCH("/{socialMediaId}", request -> ServerResponse.noContent().build())
                                .DELETE("/{socialMediaId}", request -> ServerResponse.noContent().build())
                        )
                        .build()
                )
                .after((request, response) -> {
                    log.info("RESPONSE {} {} {}", request.method(), request.path(), response.statusCode());
                    return response;
                })
                .build();
    }
}
