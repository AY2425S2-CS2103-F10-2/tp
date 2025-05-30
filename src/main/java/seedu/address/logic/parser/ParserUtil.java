package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Premium;
import seedu.address.model.person.PremiumList;
import seedu.address.model.policy.PolicyLink;
import seedu.address.model.policy.PolicyName;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.policy.ProviderCompany;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String birthday} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String string} into an {@code Integer number}.
     * Parses {@code Integer number} into an {@code Premium}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code string} is invalid.
     */
    public static PremiumList parsePremium(String string) throws ParseException {
        requireNonNull(string);
        PremiumList premiumList = new PremiumList();
        String[] split = string.split(" ");
        if (split.length < 2) {
            throw new ParseException(Premium.MESSAGE_CONSTRAINTS);
        }
        for (int i = 0, size = split.length; i < size; i = i + 2) {
            String premiumValue = split[i + 1].replace("$", "");
            try {
                if (!Premium.isValidPremium(split[i], Integer.parseInt(premiumValue))) {
                    throw new ParseException(Premium.MESSAGE_CONSTRAINTS);
                }
                Premium premium = new Premium(split[i], Integer.parseInt(premiumValue));
                premiumList.add(premium);
            } catch (NumberFormatException e) {
                throw new ParseException(Premium.MESSAGE_CONSTRAINTS);
            }

        }
        return premiumList;
    }

    /**
     * Parses an optional string into a {@code PremiumList}.
     * If the optional string is present, it is parsed into a {@code PremiumList}.
     * Otherwise, an empty {@code PremiumList} is returned.
     *
     * @param string An {@code Optional<String>} containing the premium data.
     * @return A {@code PremiumList} parsed from the string if present, otherwise an empty {@code PremiumList}.
     * @throws ParseException If the string cannot be parsed into a {@code PremiumList}.
     */
    public static PremiumList parsePremium(Optional<String> string) throws ParseException {
        if (string.isPresent() && !string.get().isBlank()) {
            return parsePremium(string.get());
        } else {
            return new PremiumList();
        }
    }

    /**
     * Parses a {@code String string} into a {@code PremiumList}.
     * The string is expected to contain premium identifiers separated by spaces.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param string Premium identifiers separated by spaces.
     * @return A PremiumList containing all valid Premium objects.
     * @throws ParseException if the given {@code string} contains invalid premium identifiers.
     */
    public static PremiumList parseDeletePremium(String string) throws ParseException {
        requireNonNull(string);
        PremiumList premiumList = new PremiumList();
        String trimmedString = string.trim();

        // Return empty list if string is empty
        if (trimmedString.isEmpty()) {
            return premiumList;
        }

        String[] split = trimmedString.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            if (!Premium.isValidPremium(split[i], 0)) {
                throw new ParseException(Premium.MESSAGE_CONSTRAINTS);
            }

            Premium premium = new Premium(split[i], 0);
            premiumList.add(premium);
        }
        return premiumList;
    }


    /**
     * Parses a {@code String policyNumber} into a {@code PolicyNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code policyNumber} is invalid.
     */
    public static PolicyNumber parsePolicyNumber(String policyNumber) throws ParseException {
        requireNonNull(policyNumber);
        String trimmedPolicyNumber = policyNumber.trim();
        if (!PolicyNumber.isValidPolicyNumber(trimmedPolicyNumber)) {
            throw new ParseException(PolicyNumber.MESSAGE_CONSTRAINTS);
        }
        return new PolicyNumber(trimmedPolicyNumber);
    }

    /**
     * Parses a {@code String policyName} into a {@code PolicyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code policyName} is invalid.
     */
    public static PolicyName parsePolicyName(String policyName) throws ParseException {
        requireNonNull(policyName);
        String trimmedPolicyName = policyName.trim();
        if (!PolicyName.isValidPolicyName(trimmedPolicyName)) {
            throw new ParseException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        return new PolicyName(trimmedPolicyName);
    }

    /**
     * Parses a {@code String providerCompany} into a {@code ProviderCompany}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code providerCompany} is invalid.
     */
    public static ProviderCompany parseProviderCompany(String providerCompany) throws ParseException {
        requireNonNull(providerCompany);
        String trimmedProviderCompany = providerCompany.trim();
        if (!ProviderCompany.isValidProviderCompany(trimmedProviderCompany)) {
            throw new ParseException(ProviderCompany.MESSAGE_CONSTRAINTS);
        }
        return new ProviderCompany(trimmedProviderCompany);
    }

    /**
     * Parses a {@code String policyLink} into a {@code PolicyLink}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code policyLink} is invalid.
     */
    public static PolicyLink parsePolicyLink(String policyLink) throws ParseException {
        requireNonNull(policyLink);
        String trimmedPolicyLink = policyLink.trim();
        if (!PolicyLink.isValidPolicyLink(trimmedPolicyLink)) {
            throw new ParseException(PolicyLink.MESSAGE_CONSTRAINTS);
        }
        return new PolicyLink(trimmedPolicyLink);
    }
}
