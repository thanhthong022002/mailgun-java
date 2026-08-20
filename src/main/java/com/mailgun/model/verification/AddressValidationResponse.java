package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Address Validation Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#field-explanation">field-explanation</a>
 */
@Value
@Jacksonized
@Builder
public class AddressValidationResponse {

    /**
     * <p>
     * Email address being validated
     * </p>
     */
    @JsonProperty("address")
    String address;

    /**
     * <p>
     * If the domain is in a list of disposable email addresses, this will be appropriately categorized.
     * </p>
     */
    @JsonProperty("is_disposable_address")
    Boolean isDisposableAddress;

    /**
     * <p>
     * Checks the mailbox portion of the email if it matches a specific role type (<code>admin</code>, <code>sales</code> or <code>webmaster</code>).
     * </p>
     */
    @JsonProperty("is_role_address")
    Boolean isRoleAddress;

    /**
     * <p>
     * List of potential reasons why a specific validation may be unsuccessful.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#reason-explanation">reason-explanation</a>
     */
    @JsonProperty("reason")
    List<String> reason;

    /**
     * <p>
     * Either <code>deliverable</code>, <code>undeliverable</code>, <code>do_not_send</code>, <code>catch_all</code> or <code>unknown</code>.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#result-types">result-types</a>
     */
    @JsonProperty("result")
    String result;

    /**
     * <p>
     * <code>high</code>, <code>medium</code>, <code>low</code> or <code>unknown</code>.
     * Depending on the evaluation of all aspects of the given email.
     * </p>
     */
    @JsonProperty("risk")
    String risk;

    /**
     * <p>
     * Suggested correction when the domain looks like a typo of a well-known one,
     * for example <code>user@gmial.com</code> yielding <code>user@gmail.com</code>.
     * <code>null</code> when no suggestion applies.
     * </p>
     */
    @JsonProperty("did_you_mean")
    String didYouMean;

    /**
     * <p>
     * The base address when the validated address is an alias, for example
     * <code>user@example.com</code> for <code>user+tag@example.com</code>.
     * <code>null</code> when the address is not an alias.
     * </p>
     */
    @JsonProperty("root_address")
    String rootAddress;

    /**
     * <p>
     * Engagement data of the address. Only populated for accounts subscribed to
     * Mailgun's engagement data.
     * </p>
     */
    @JsonProperty("engagement")
    Engagement engagement;

}
