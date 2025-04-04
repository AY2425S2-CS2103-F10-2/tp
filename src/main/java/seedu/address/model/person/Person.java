package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Birthday birthday;
    private final PremiumList premiumList = new PremiumList();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Birthday birthday,
                  PremiumList premiumlist, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, birthday, premiumlist, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.premiumList.addAll(premiumlist);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public PremiumList getPremiumList() {
        return premiumList;
    }

    /**
     * Returns true if both persons have the same name, phone, email, address, and birthday.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().fullName.equalsIgnoreCase(getName().fullName)
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().value.equalsIgnoreCase(getEmail().value)
                && otherPerson.getAddress().value.equalsIgnoreCase(getAddress().value)
                && otherPerson.getBirthday().equals(getBirthday());
    }

    /**
     * Checks if this object contains any premium from the provided premium list.
     *
     * @param premiumlist The premium list to check against this object's premium list
     * @return true if at least one premium from the provided list is found in this object's premium list,
     *         false otherwise
     */
    public boolean hasPremium(PremiumList premiumlist) {
        for (Premium p : premiumlist.premiumList) {
            if (this.premiumList.contains(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && birthday.equals(otherPerson.birthday)
                && premiumList.equals(otherPerson.premiumList)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, premiumList, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("birthday", birthday)
                .add("premiums", premiumList)
                .add("tags", tags)
                .toString();
    }

}
