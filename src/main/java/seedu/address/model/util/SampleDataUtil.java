package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.PolicyBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPolicyBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Premium;
import seedu.address.model.person.PremiumList;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyLink;
import seedu.address.model.policy.PolicyName;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.policy.ProviderCompany;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new Birthday("1990-05-21"), new PremiumList(new Premium("LifeShield", 100)),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Birthday("1988-11-02"), new PremiumList(new Premium("Health2040", 300)),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Birthday("1992-07-15"), new PremiumList(new Premium("ETFBonds", 0)),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Birthday("1985-03-10"), new PremiumList(new Premium("Eldershield", 1000)),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new Birthday("1993-09-30"), new PremiumList(new Premium("GreatInvestment", 5000)),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Birthday("1987-12-12"), new PremiumList(new Premium("LifeShield", 8000)),
                getTagSet("colleagues"))

        };
    }

    /**
     * Returns an array of sample {@code Policy} objects.
     */
    public static Policy[] getSamplePolicies() {
        return new Policy[] {
            new Policy(new PolicyName("LifeShield"), new PolicyNumber("POL123"),
                new ProviderCompany("ShieldCorp"),
                new PolicyLink("https://www.shieldcorp.com/policy123")),
            new Policy(new PolicyName("HealthPlus"), new PolicyNumber("POL456"),
                new ProviderCompany("HealthCorp"),
                new PolicyLink("https://www.healthcorp.com/policy456")),
            new Policy(new PolicyName("SecureFuture"), new PolicyNumber("POL789"),
                new ProviderCompany("SafeInsure"),
                new PolicyLink("https://www.safeinsure.com/policy789")),
            new Policy(new PolicyName("HomeSafe"), new PolicyNumber("POL101"),
                new ProviderCompany("HomeGuard"),
                new PolicyLink("https://www.homeguard.com/policy101")),
            new Policy(new PolicyName("AutoCare"), new PolicyNumber("POL202"),
                new ProviderCompany("CarProtect"),
                new PolicyLink("https://www.carprotect.com/policy202")),
            new Policy(new PolicyName("TravelAssure"), new PolicyNumber("POL303"),
                new ProviderCompany("TravelSafe"),
                new PolicyLink("https://www.travelsafe.com/policy303"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a {@code ReadOnlyPolicyBook} with all the sample policies.
     */
    public static ReadOnlyPolicyBook getSamplePolicyBook() {
        PolicyBook samplePolicyBook = new PolicyBook();
        for (Policy samplePolicy : getSamplePolicies()) {
            samplePolicyBook.addPolicy(samplePolicy);
        }
        return samplePolicyBook;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
