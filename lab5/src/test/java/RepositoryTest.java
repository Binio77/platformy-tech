import org.example.MageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;
import org.example.Mage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RepositoryTest {
    private MageRepository mageRepository;

    @BeforeEach
    public void setUp() {
        mageRepository = new MageRepository();
    }

    @Test
    void testDeleteNotExisting()
    {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> mageRepository.delete("Radagast"));
    }

    @Test
    void testFindNotExisting()
    {
        assertThat(mageRepository.find("Radagast")).isEqualTo(Optional.empty());
    }

    @Test
    void testSaveAlreadyExisting()
    {
        Mage mage1 = new Mage("Radagast", 1);
        Mage mage2 = new Mage("Radagast", 1);
        mageRepository.save(mage1);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(()
                -> mageRepository.save(mage2)).withMessage("Mage Radagast already exists");
    }
}
