package com.mailgun.model.message;

import com.mailgun.form.PojoUtil;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * The xomad build used to carry a dedicated {@code xoEmailIdOnHeader} field annotated
 * {@code @FormProperty("h:X-Email-ID")}. It was redundant -- {@link Message#headers} is annotated
 * {@code @CustomProperties(prefix = "h:")} and emits any custom header -- and it is gone as of
 * 2.4.2-xomad-2.
 *
 * <p>The removal only holds if the generic map really produces the same form field, and a header
 * that quietly stops being sent breaks event-to-row matching downstream without failing anything,
 * so it is asserted here through the same {@link PojoUtil#toMap} the encoder uses.
 */
class MessageCustomHeaderTest {

    @Test
    void customHeaderIsEmittedWithTheHPrefix() {
        Message message = Message.builder()
            .from("sender@xomad.com")
            .to("recipient@example.com")
            .subject("subject")
            .text("body")
            .headers(Map.of("X-Email-ID", "1234567"))
            .build();

        var form = PojoUtil.toMap(message);

        assertEquals("1234567", form.get("h:X-Email-ID"));
        assertFalse(form.containsKey("X-Email-ID"), "the h: prefix must not be dropped");
        assertFalse(form.containsKey("headers"), "the map itself must not be sent as a field");
    }

    @Test
    void severalCustomHeadersAreEmittedIndependently() {
        Message message = Message.builder()
            .from("sender@xomad.com")
            .to("recipient@example.com")
            .subject("subject")
            .text("body")
            .headers(Map.of("X-Email-ID", "1234567", "X-Campaign", "spring"))
            .build();

        var form = PojoUtil.toMap(message);

        assertEquals("1234567", form.get("h:X-Email-ID"));
        assertEquals("spring", form.get("h:X-Campaign"));
    }

}
