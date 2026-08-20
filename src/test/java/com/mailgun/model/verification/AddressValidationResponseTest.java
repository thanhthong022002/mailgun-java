package com.mailgun.model.verification;

import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddressValidationResponseTest {

    @Test
    void deserializeAddressValidationResponseTest() throws Exception {
        String json = "{\"address\":\"user+tag@gmial.com\",\"is_disposable_address\":false,"
                + "\"is_role_address\":true,\"reason\":[\"mailbox_is_role_address\"],"
                + "\"result\":\"deliverable\",\"risk\":\"medium\","
                + "\"did_you_mean\":\"user+tag@gmail.com\",\"root_address\":\"user@gmial.com\","
                + "\"engagement\":{\"is_bot\":true,\"engaged\":false,\"engagement\":\"disengaged\"}}";

        AddressValidationResponse result = ObjectMapperUtil.getObjectMapper()
                .readValue(json, AddressValidationResponse.class);

        assertNotNull(result);
        assertEquals("user+tag@gmial.com", result.getAddress());
        assertEquals("deliverable", result.getResult());
        assertEquals("medium", result.getRisk());
        assertEquals(Boolean.FALSE, result.getIsDisposableAddress());
        assertEquals(Boolean.TRUE, result.getIsRoleAddress());
        assertEquals(1, result.getReason().size());

        assertEquals("user+tag@gmail.com", result.getDidYouMean());
        assertEquals("user@gmial.com", result.getRootAddress());
        assertNotNull(result.getEngagement());
        assertEquals(Boolean.TRUE, result.getEngagement().getIsBot());
        assertEquals(Boolean.FALSE, result.getEngagement().getEngaged());
        assertEquals("disengaged", result.getEngagement().getEngagement());
    }

    @Test
    void deserializeAddressValidationResponseWithoutOptionalFieldsTest() throws Exception {
        String json = "{\"address\":\"user@example.com\",\"is_disposable_address\":false,"
                + "\"is_role_address\":false,\"reason\":[],\"result\":\"deliverable\",\"risk\":\"low\"}";

        AddressValidationResponse result = ObjectMapperUtil.getObjectMapper()
                .readValue(json, AddressValidationResponse.class);

        assertNotNull(result);
        assertEquals("user@example.com", result.getAddress());
        assertNull(result.getDidYouMean());
        assertNull(result.getRootAddress());
        assertNull(result.getEngagement());
    }

}
