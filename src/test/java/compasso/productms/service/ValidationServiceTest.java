package compasso.productms.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import compasso.productms.exception.ValidationException;
import compasso.productms.helper.ProductHelper;

public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    public void shouldThrowValidationExceptionForAllFields() {

        var request = ProductHelper.createCustomProductRequest(null, null, null);

        try {
            validationService.validate(request);
            Assertions.fail();
        } catch (ValidationException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertFalse(StringUtils.isEmpty(ex.getMessage()));
            Assertions.assertEquals(400, ex.getStatusCode());
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        }
    }

    @Test
    public void shouldThrowValidationExceptionWhenNameNotExists() {

        var request = ProductHelper.createCustomProductRequest(null, "Feijão namorado", 7.50);

        try {
            validationService.validate(request);
            Assertions.fail();
        } catch (ValidationException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals("[O campo name é obrigatório]", ex.getMessage());
            Assertions.assertEquals(400, ex.getStatusCode());
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        }
    }

    @Test
    public void shouldThrowValidationExceptionWhenDescriptionNotExists() {

        var request = ProductHelper.createCustomProductRequest("Feijão", null, 7.50);

        try {
            validationService.validate(request);
            Assertions.fail();
        } catch (ValidationException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals("[O campo description é obrigatório]", ex.getMessage());
            Assertions.assertEquals(400, ex.getStatusCode());
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        }
    }

    @Test
    public void shouldThrowValidationExceptionWhenPriceNotExists() {

        var request = ProductHelper.createCustomProductRequest("Feijão", "Feijão namorado", null);

        try {
            validationService.validate(request);
            Assertions.fail();
        } catch (ValidationException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals("[O campo price é obrigatório]", ex.getMessage());
            Assertions.assertEquals(400, ex.getStatusCode());
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        }
    }

    @Test
    public void shouldThrowValidationExceptionWhenPriceIsNegative() {

        var request = ProductHelper.createCustomProductRequest("Feijão", "Feijão namorado", -7.50);

        try {
            validationService.validate(request);
            Assertions.fail();
        } catch (ValidationException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals("[O campo price deve ser positivo]", ex.getMessage());
            Assertions.assertEquals(400, ex.getStatusCode());
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        }
    }
}
