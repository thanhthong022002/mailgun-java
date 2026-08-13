package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Engagement data of the validated address, returned in the {@code engagement} object of the
 * single address validation response.
 * </p>
 * <p>
 * Only populated for accounts subscribed to Mailgun's engagement data; it is {@code null} otherwise.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/validate/single-valid-ir">Single Validation</a>
 */
@Value
@Jacksonized
@Builder
public class Engagement {

    /**
     * <p>
     * Whether the recipient has been identified as a bot rather than a person.
     * </p>
     */
    @JsonProperty("is_bot")
    Boolean isBot;

    /**
     * <p>
     * Whether the recipient has engaged with previously sent email.
     * </p>
     */
    @JsonProperty("engaged")
    Boolean engaged;

    /**
     * <p>
     * The type of engagement recorded for the recipient.
     * </p>
     */
    @JsonProperty("engagement")
    String engagement;

}
