import org.example.MageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.example.Mage;
import org.example.MageRepository;
public class ControllerTest {
    private MageController mageController;

    private MageRepository mageRepository;

    @BeforeEach
    public void setUp() {
        mageRepository = mock(MageRepository.class);
        mageController = new MageController(mageRepository);
    }

    @Test
    public void testFind_found()
    {
        Mage mage = new Mage("Radagast", 1);
        when(mageRepository.find("Radagast")).thenReturn(Optional.of(mage));
        assertThat(mageController.find("Radagast")).isEqualTo(mage.toString());
    }
    @Test
    public void testFind_notFound()
    {
        when(mageRepository.find("Radagast")).thenReturn(Optional.empty());
        assertThat(mageController.find("Radagast")).isEqualTo("not found");
    }
    @Test
    public void delete_deleted()
    {
        when(mageRepository.find("Radagast")).thenReturn(Optional.of(new Mage("Radagast", 1)));
        assertThat(mageController.delete("Radagast")).isEqualTo("done");
    }
    @Test
    public void delete_notDeleted()
    {
        doThrow(new IllegalArgumentException("Mage Radagast not found")).when(mageRepository).delete("Radagast");
        assertThat(mageController.delete("Radagast")).isEqualTo("not found");
    }

    @Test
    public void save_saved()
    {
        assertThat(mageController.save("Radagast", 1)).isEqualTo("done");
    }
    @Test
    public void save_notSaved()
    {
        doThrow(new IllegalArgumentException()).when(mageRepository).save(new Mage("mag1", 10));
        assertThat(mageController.save("mag1", 10)).isEqualTo("not found");
    }


}
